# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Build
./mvnw clean package

# Run (porta 8081)
./mvnw spring-boot:run

# Testes
./mvnw test

# Testar apenas estrutura modular
./mvnw test -Dtest=ModulithStructureTest

# H2 Console (app em execução)
# URL: http://localhost:8081/h2-console
# JDBC: jdbc:h2:mem:band-db  |  user: sa  |  senha: (vazio)
```

## Diretrizes para IA

### Objetivo

Evolua o projeto preservando sua arquitetura e convenções. Priorize consistência com o código existente em vez de introduzir novos padrões.
Em caso de dúvida, priorize consistência com o código existente em vez de aplicar a solução considerada mais moderna.

### Antes de implementar

Sempre:

- Leia o código existente relacionado à funcionalidade.
- Reutilize padrões já adotados pelo projeto.
- Preserve o estilo de código já existente.

Nunca assuma que uma abstração não existe antes de pesquisar no repositório.

### Preservação da arquitetura

A arquitetura atual é intencional.

Não:

- altere fronteiras entre módulos;
- mova regras de negócio para controllers;
- acesse classes internas de outro módulo;
- introduza dependências circulares;
- substitua Clean Architecture por outro estilo.

Caso uma mudança arquitetural pareça melhor, explique a proposta e aguarde aprovação.

### Casos de Uso

Casos de uso são classes Java comuns.

Nunca utilize:

- `@Service`
- `@Component`
- `@Repository`

Eles devem continuar sendo instanciados pelas classes `*Config`.

### Implementação de funcionalidades

Sempre que possível siga esta sequência:

1. Domínio
2. Caso de Uso
3. Porta
4. Adaptador
5. Controller
6. Testes

### Refatorações

Refatore apenas quando necessário para:

- corrigir bugs;
- remover duplicação;
- melhorar legibilidade.

Evite refatorações não relacionadas à tarefa.
Evite renomear classes, métodos ou pacotes sem necessidade funcional.

### Dependências

Não adicione novas bibliotecas sem solicitação explícita.

### Quando pedir confirmação

Solicite aprovação antes de:

- alterar arquitetura;
- remover código existente;
- modificar APIs públicas;
- mover classes entre módulos;
- alterar regras de negócio;
- adicionar novas dependências de infraestrutura;

### Resultado esperado

O código gerado deve parecer ter sido escrito pelo mesmo autor do restante do projeto.


## Visão geral

**MusiSYS** é um sistema de gestão para grupos musicais construído como um monólito modular com Spring Modulith. O projeto aplica **Arquitetura Limpa (Clean Architecture)** dentro de cada módulo, com fronteiras de módulo verificadas em tempo de teste por `ModulithStructureTest`.

Stack: Java 21 · Spring Boot 3.3.4 · Spring Modulith 1.1.3 · H2 in-memory · Thymeleaf · Tailwind CSS (CDN) · Alpine.js (CDN)

---

## Arquitetura Modular

| Módulo | Pacote raiz | Responsabilidade | Estado |
|---|---|---|---|
| `repertorio` | `br.com.band.band.repertorio` | CRUD de Músicas e Setlists | Completo (API + UI) |
| `eventos` | `br.com.band.band.eventos` | Eventos do grupo (shows, ensaios, reuniões) | API parcial, listener incompleto |
| `agenda` | `br.com.band.band.agenda` | Visão consolidada de eventos + financeiro | Mock/esboço |
| `financeiro` | `br.com.band.band.financeiro` | Dados financeiros | Placeholder vazio |
| `shared` | `br.com.band.band.shared` | Kernel compartilhado — tipos públicos entre módulos | Apenas `SetlistRemovedEvent` |

### Regra de ouro dos módulos

Nenhum módulo pode importar classes internas de outro módulo. A única exceção são os tipos em `shared/api/events/`, que é um `@NamedInterface` explicitamente exposto. Qualquer violação quebra `ModulithStructureTest`.

### Comunicação entre módulos

Dois padrões coexistem no projeto:

**1. Eventos de domínio (assíncrono in-process)** — padrão preferido para reações a mudanças de estado:
- `repertorio` publica `SetlistRemovedEvent` via `DomainEventPublisher` (porta de saída) → adaptado por `SpringDomainEventPublisher` → Spring `ApplicationEventPublisher`
- `eventos` consome via `@EventListener` em `SetlistRemovedEventListener`

**2. HTTP via RestClient** — usado por `agenda` para consumir `eventos`:
- `EventosClient` chama `GET http://localhost:8081/eventos` (URL hardcoded)
- Este padrão foi adotado antes da refatoração para eventos de domínio e representa uma inconsistência a ser resolvida

> **Atenção:** O `SetlistClient` (porta em `eventos/application/port/`) é um vestígio da abordagem HTTP inicial e não está mais conectado a nada. Pode ser removido.

---

## Estrutura interna de cada módulo (Clean Architecture)

O projeto adota **Arquitetura Limpa**, não Hexagonal. A diferença prática: sem "portas primárias/secundárias", sem adaptadores de entrada — apenas as quatro camadas canônicas do Clean Architecture:

| Camada Clean Architecture | Onde no projeto |
|---|---|
| **Entidades** (regras de negócio) | `domain/model/` — POJOs puros, sem Spring ou JPA |
| **Casos de Uso** (regras de aplicação) | `application/usecase/` — um use case por classe |
| **Adaptadores de Interface** | `application/port/repository/` (gateways) · `infrastructure/persistence/` · DTOs |
| **Frameworks & Drivers** | `infrastructure/web/` · JPA · Spring · Config |

O módulo `repertorio` é o mais completo e serve de referência canônica:

```
repertorio/
  domain/model/               ← Entidades: Music, Setlist, SetlistItem
                                 Sem anotações Spring ou JPA. Toda lógica de negócio aqui.
  application/
    usecase/                  ← Casos de uso: um por classe; orquestra domínio + gateways
    port/repository/          ← Gateways (interfaces de repositório — visão do domínio)
    port/                     ← Outros gateways (DomainEventPublisher)
    dto/                      ← SetlistDTO, SetlistSummaryDTO, SetlistItemDTO
    exception/                ← MusicNotFoundException (404), MusicInUseException (409),
                                 SetlistNotFoundException (404) — anotadas com @ResponseStatus
  infrastructure/
    persistence/music/        ← MusicEntity, SpringDataMusicRepository, JpaMusicRepository
    persistence/setlist/      ← SetlistEntity, SetlistItemEntity, JpaSetlistRepository
    web/                      ← RepertorioController (@RestController, API REST)
                                 RepertorioViewController (@Controller, views Thymeleaf)
    config/                   ← RepertorioConfig — instancia use cases como @Bean
    event/                    ← SpringDomainEventPublisher
```

### Convenção crítica: use cases NÃO são Spring beans

Use cases são classes Java comuns. Eles são instanciados explicitamente no `*Config` do módulo:

```java
// ✅ Correto — use case instanciado via @Bean
@Bean
public DeleteMusicUseCase deleteMusicUseCase(MusicRepository musicRepository) {
    return new DeleteMusicUseCase(musicRepository);
}

// ❌ Errado — não usar @Service/@Component nos use cases
```

Somente repositórios adaptadores (`Jpa*Repository`), controllers e o `SpringDomainEventPublisher` são componentes Spring gerenciados diretamente.

---

## Camada de Frontend (módulo `repertorio`)

Thymeleaf + Tailwind CSS (CDN) + Alpine.js (CDN) + `@alpinejs/sort` (drag-and-drop).

| Rota | Template | Descrição |
|---|---|---|
| `GET /repertorio/ui/musics` | `musics.html` | CRUD de músicas com modal e busca |
| `GET /repertorio/ui/setlists` | `setlists.html` | Lista de setlists com criação e exclusão |
| `GET /repertorio/ui/setlists/{id}` | `setlist-editor.html` | Editor two-panel com reordenação |

**Padrão de inicialização:** o `RepertorioViewController` (`@Controller`) renderiza os dados via Thymeleaf no carregamento inicial. O Alpine.js assume o controle para mutações posteriores, chamando a API REST diretamente (`fetch`). Dados iniciais são injetados via `th:inline="javascript"`:

```html
<script th:inline="javascript">
    window.__musics = /*[[${musics}]]*/ [];
</script>
```

**Fragmentos compartilhados** em `templates/repertorio/_layout.html`:
- `:: head` — `<head>` com CDNs (Tailwind, Alpine.js, Sort plugin) e estilos
- `:: toastWidget` — notificação flutuante Alpine (`$store.toast.success/error`)
- `:: scripts` — Alpine store global `$store.toast` e utilitário `window.readError`

As pages são projetadas como "miolo" para uso dentro de `<iframe>` — sem navegação global, layout auto-contido.

---

## Banco de dados

H2 in-memory, recriado a cada reinicialização (`ddl-auto=create-drop`). Dados de seed em `src/main/resources/data.sql`:
- 40 músicas pré-carregadas
- 12 setlists (Rock 90s, Love Songs, Mixed)
- 10 eventos

Tabelas: `musics`, `setlists`, `setlist_items`, `events`.

**N+1 resolvido:** `JpaSetlistRepository.findAll()` usa `findAllWithItems()` com `LEFT JOIN FETCH` para carregar itens do setlist em uma única query.

---

## Regras de negócio

| Regra | Onde está implementada |
|---|---|
| Música não pode ser excluída se pertence a algum setlist | `DeleteMusicUseCase` verifica `MusicRepository.existsInAnySetlist()` → lança `MusicInUseException` (409) |
| Remoção de setlist publica evento de domínio | `RemoveSetlistUseCase` → `DomainEventPublisher.publish(SetlistRemovedEvent)` |
| Posições do setlist são sempre normalizadas (1-indexed) | `Setlist.normalizePositions()` chamado após qualquer mutação |
| Eventos que referenciam um setlist removido devem ter a referência limpa | `SetlistRemovedEventListener` — **atualmente apenas imprime no stdout; implementação real pendente** |

---

## Estado atual e itens pendentes

| Área | Status |
|---|---|
| `repertorio` — API REST | ✅ Completo (10 endpoints) |
| `repertorio` — UI Thymeleaf | ✅ Completo (3 páginas) |
| `eventos` — API REST | ✅ Parcialmente implementado |
| `eventos` — UI Thymeleaf | ❌ `events.html` e `event-detail.html` não criados |
| `eventos` — `SetlistRemovedEventListener` | ⚠️ Placeholder (só `System.out.println`) |
| `SetlistClient` em `eventos` | ⚠️ Vestígio sem uso — candidato a remoção |
| `agenda` — `EventosClient` URL hardcoded | ⚠️ `http://localhost:8081` hardcoded; deveria ser configurável |
| `financeiro` | ❌ Apenas controller vazio |
| Persistência real (banco externo) | ❌ Apenas H2 in-memory |

---

## Padrões e convenções

**Tratamento de erros REST:** exceções do domínio anotadas com `@ResponseStatus` propagam automaticamente o status HTTP correto. Não há `@RestControllerAdvice` global — cada exceção carrega seu próprio status.

**Chaves musicais válidas:** `C, C#, D, D#, E, F, F#, G, G#, A, A#, B` (campo `musical_key` no banco, `key` no domínio).

**UUIDs:** todas as entidades usam `UUID` como chave primária, gerado na camada de aplicação (`UUID.randomUUID()` nos use cases de criação).

**Testes de módulo:** `ModulithStructureTest` valida que nenhum módulo viola as fronteiras definidas pelos `@ApplicationModule`. Deve passar sempre antes de qualquer PR.
