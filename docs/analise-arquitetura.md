# Análise de Arquitetura — MusiSYS
> Gerado por Claude em 2026-06-11

---

## Visão Geral

**Monólito Modularizado** com Spring Boot 3.3.4 + Spring Modulith 1.1.3 + Java 21 + H2 in-memory.
Decisão documentada nos ADRs. Abordagem DDD com intenção de extrair microserviços futuramente.

---

## Stack

- Java 21
- Spring Boot 3.3.4
- Spring Modulith 1.1.3
- Spring Data JPA
- H2 (in-memory, `create-drop`)
- Maven
- Porta: 8081

---

## Bounded Contexts (Módulos)

```
br.com.band.band/
├── repertorio/       @ApplicationModule
├── eventos/          @ApplicationModule
├── financeiro/       (sem @ApplicationModule — stub)
├── agenda/           (sem @ApplicationModule — Infra-Context intencional)
└── shared/
    └── api/events/   @NamedInterface("events")
```

### Repertorio

- Domínio: `Music`, `Setlist`, `SetlistItem`
- Use Cases: `ListAllMusics`, `GetSetlistWithMusics`, `CreateSetlist`, `AddMusicToSetlist`, `RemoveSetlist`, `ReorderSetlist`
- Facade: `RepertorioService` (expõe apenas 3 dos 6 use cases — ver seção de pendências)
- Porta de eventos: `DomainEventPublisher` → `SpringDomainEventPublisher` (via `ApplicationEventPublisher` do Spring)
- API: `GET /repertorio/musics`, `GET /repertorio/setlist/{id}`, `DELETE /repertorio/setlists/{id}`

### Eventos

- Domínio: `Event`, `EventType`
- `Event` guarda `setlistId` como `UUID` — sem acoplamento ao modelo do Repertório
- Use Cases: `ListAllEvents`, `GetEventWithSetlist`, `UpdateEventsAfterSetlistRemoval`
- Port anti-corrupção: `SetlistClient` → `RestSetlistClient` (HTTP para Repertório)
- Listener: `SetlistRemovedEventListener` (Spring `@EventListener`)
- API: `GET /eventos`, `GET /eventos/{id}`

### Financeiro

- Stub: sem domínio, sem JPA, dados hardcoded no controller
- API: `GET /financeiro/accounts`
- Enums locais no controller: `AccountType`, `AccountCategory`, `AccountStatus`

### Agenda (Infra-Context)

- Não é BC de negócio: agrega dados de Eventos e Financeiro para visão calendário
- Consome via `RestClient` HTTP apontando para `localhost:8081`
- API: `GET /agenda?date={yyyy-MM-dd}`

### Shared

- `SetlistRemovedEvent` — record com `UUID setlistId`
- Pacote `@NamedInterface("events")` — contrato público entre módulos

---

## Padrões de Comunicação

```
Repertorio ──(Spring Event: SetlistRemovedEvent)──▶ Eventos
Eventos    ──(REST HTTP: GET /repertorio/setlist/{id})──▶ Repertorio
Agenda     ──(REST HTTP: GET /eventos)──▶ Eventos
Agenda     ──(REST HTTP: GET /financeiro/accounts)──▶ Financeiro
```

Comunicação via REST dentro do mesmo processo é intencional (ADR-002),
preparando o sistema para extração futura como microserviços.

---

## Arquitetura Interna de Cada Módulo (Hexagonal/Clean)

```
[infrastructure/web]    Controller
                           ↓
[application]          Service (facade) → UseCase → Port (interface)
                           ↓
[domain/model]         POJO puro (sem dependências Spring/JPA)
                           ↓
[infrastructure]       JpaRepository / RestClient / EventPublisher
```

- Use cases são classes simples com método `execute()` — sem anotações Spring
- Composição feita via `@Configuration` (ex: `RepertorioConfig`, `EventosConfig`)
- Domain model completamente livre de frameworks

---

## Pendências e Inconsistências Identificadas

### 1. `SetlistRemovedEventListener` incompleto
- **Arquivo:** `eventos/infrastructure/listener/SetlistRemovedEventListener.java`
- O listener recebe `SetlistRemovedEvent` mas só imprime `System.out.println`
- `UpdateEventsAfterSetlistRemovalUseCase` existe e está implementado, mas **não está conectado** ao listener
- O fluxo de desassociar setlists de eventos após remoção **não funciona end-to-end**

### 2. `RepertorioService` expõe apenas 3 de 6 use cases
- `CreateSetlist`, `AddMusicToSetlist` e `ReorderSetlist` têm classes, mas:
  - Não são injetados no `RepertorioService`
  - Não têm endpoints no `RepertorioController`
- Funcionalidade de criação e reordenação de setlists está inacessível via API

### 3. `JpaSetlistRepository.findAll()` ignora os itens
- **Arquivo:** `repertorio/infrastructure/persistence/setlist/JpaSetlistRepository.java`
- O método `findAll()` cria `Setlist` sem popular os `SetlistItem`s
- O `findById()` popula corretamente (comportamento inconsistente)

### 4. `agenda` e `financeiro` sem `@ApplicationModule`
- Apenas `repertorio` e `eventos` têm `package-info.java` com `@ApplicationModule`
- O `ModulithStructureTest` pode não verificar esses módulos corretamente

### 5. URL `localhost:8081` hardcoded
- Aparece em: `EventosConfig.java`, `EventosClient.java`, `FinanceiroClient.java`
- Não externalizado via `application.properties`

### 6. `RestTemplate` vs `RestClient` inconsistente
- `eventos/infrastructure/client/RestSetlistClient.java` usa `RestTemplate` (legado)
- `agenda/infrastructure/client/EventosClient.java` e `FinanceiroClient.java` usam `RestClient` (moderno)
- Sem justificativa documentada para a inconsistência

---

## Endpoints Disponíveis

| Método | Path                            | Módulo      | Descrição                        |
|--------|---------------------------------|-------------|----------------------------------|
| GET    | /repertorio/musics              | Repertorio  | Lista todas as músicas           |
| GET    | /repertorio/setlist/{id}        | Repertorio  | Busca setlist com músicas        |
| DELETE | /repertorio/setlists/{id}       | Repertorio  | Remove setlist (publica evento)  |
| GET    | /eventos                        | Eventos     | Lista todos os eventos           |
| GET    | /eventos/{id}                   | Eventos     | Busca evento com setlist         |
| GET    | /financeiro/accounts            | Financeiro  | Lista contas (mock hardcoded)    |
| GET    | /agenda?date=yyyy-MM-dd         | Agenda      | Lista itens da agenda por data   |

---

## Arquivos-chave

| Responsabilidade                  | Arquivo                                                                 |
|-----------------------------------|-------------------------------------------------------------------------|
| Configuração Repertório           | `repertorio/infrastructure/config/RepertorioConfig.java`               |
| Configuração Eventos              | `eventos/infrastructure/config/EventosConfig.java`                     |
| Publicação de eventos de domínio  | `repertorio/infrastructure/event/SpringDomainEventPublisher.java`      |
| Contrato do evento compartilhado  | `shared/api/events/SetlistRemovedEvent.java`                           |
| Listener (incompleto)             | `eventos/infrastructure/listener/SetlistRemovedEventListener.java`     |
| Use case não conectado            | `eventos/application/usecase/UpdateEventsAfterSetlistRemovalUseCase.java` |
| Teste de estrutura modular        | `test/.../ModulithStructureTest.java`                                  |
