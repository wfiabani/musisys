# Skill: new-usecase

Adiciona um novo caso de uso a um módulo existente, seguindo os padrões de Clean Architecture do projeto.

## Quando usar

Invoque com `/new-usecase` para adicionar uma operação de negócio a um módulo já existente.

Exemplos de operações: `MarkAsPaid`, `TransferMember`, `PublishEvent`, `ArchiveSetlist`.

---

## Contexto do projeto

- Use cases são classes Java comuns — **sem** `@Service`, `@Component` ou qualquer anotação Spring
- São instanciados explicitamente como `@Bean` na classe `*Config` do módulo
- O método público obrigatório é `execute(...)` com parâmetros explícitos
- UUID é sempre gerado aqui via `UUID.randomUUID()` para operações de criação
- A referência canônica é o módulo `repertorio` em `br.com.band.band.repertorio`

---

## Sequência de implementação

### 1. Identificar o módulo e pacote

Localizar:
```
src/main/java/br/com/band/band/{modulo}/application/usecase/
src/main/java/br/com/band/band/{modulo}/infrastructure/config/{Modulo}Config.java
src/main/java/br/com/band/band/{modulo}/application/{Modulo}Service.java
```

### 2. Verificar se o repositório precisa de novo método

Se a operação exige uma query inexistente, adicionar primeiro:

**Interface (porta):**
```
application/port/repository/{Entidade}Repository.java
```
```java
// Adicionar o novo método à interface
boolean existsByStatus(Status status);
// ou
List<{Entidade}> findByStatus(Status status, int limit);
```

**Adaptador JPA:**
```
infrastructure/persistence/Jpa{Entidade}Repository.java
```
```java
@Override
public boolean existsByStatus(Status status) {
    return repository.countByStatus(status.name()) > 0;
}
```

**Spring Data Repository:**
```
infrastructure/persistence/SpringData{Entidade}Repository.java
```
```java
long countByStatus(String status);
// ou com @Query:
@Query("SELECT e FROM {Entidade}Entity e WHERE e.status = :status")
List<{Entidade}Entity> findByStatus(@Param("status") String status);
```

### 3. Criar a classe do caso de uso

```
src/main/java/br/com/band/band/{modulo}/application/usecase/{Acao}{Entidade}UseCase.java
```

**Padrão de mutação (update, delete, state change):**
```java
package br.com.band.band.{modulo}.application.usecase;

import br.com.band.band.{modulo}.application.exception.{Entidade}NotFoundException;
import br.com.band.band.{modulo}.application.port.repository.{Entidade}Repository;
import java.util.UUID;

public class {Acao}{Entidade}UseCase {

    private final {Entidade}Repository repository;

    public {Acao}{Entidade}UseCase({Entidade}Repository repository) {
        this.repository = repository;
    }

    public void execute(UUID id /* , outros parâmetros */) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new {Entidade}NotFoundException(id));

        entity.{acaoDominio}(/* params */);

        repository.save(entity);
    }
}
```

**Padrão de criação (gera novo ID):**
```java
public UUID execute(/* parâmetros */) {
    var entity = new {Entidade}(UUID.randomUUID(), /* campos */);
    repository.save(entity);
    return entity.getId();
}
```

**Padrão de consulta (retorna DTO):**
```java
import br.com.band.band.{modulo}.application.dto.{Entidade}DTO;
import java.util.List;

public class List{Entidade}sByStatusUseCase {

    private final {Entidade}Repository repository;

    public List{Entidade}sByStatusUseCase({Entidade}Repository repository) {
        this.repository = repository;
    }

    public List<{Entidade}DTO> execute(Status status) {
        return repository.findByStatus(status)
                .stream()
                .map(e -> new {Entidade}DTO(e.getId(), /* campos */))
                .toList();
    }
}
```

**Padrão com múltiplos repositórios (regra cross-entidade):**
```java
public class {Acao}UseCase {

    private final {Entidade}ARepository aRepository;
    private final {Entidade}BRepository bRepository;

    public {Acao}UseCase({Entidade}ARepository a, {Entidade}BRepository b) {
        this.aRepository = a;
        this.bRepository = b;
    }

    public void execute(UUID aId, UUID bId) {
        var a = aRepository.findById(aId)
                .orElseThrow(() -> new {Entidade}ANotFoundException(aId));
        // lógica de negócio ...
        aRepository.save(a);
    }
}
```

### 4. Registrar no Config

Abrir `{Modulo}Config.java` e adicionar:

```java
@Bean
public {Acao}{Entidade}UseCase {acao}{Entidade}UseCase({Entidade}Repository repo) {
    return new {Acao}{Entidade}UseCase(repo);
}
```

Se o use case tiver múltiplas dependências:
```java
@Bean
public {Acao}UseCase {acao}UseCase(
        {Entidade}ARepository aRepo,
        {Entidade}BRepository bRepo
) {
    return new {Acao}UseCase(aRepo, bRepo);
}
```

Adicionar também ao `@Bean` do Service:
```java
@Bean
public {Modulo}Service {modulo}Service(
        /* use cases existentes */,
        {Acao}{Entidade}UseCase {acao}{Entidade}  // ← novo
) {
    return new {Modulo}Service(
            /* existentes */,
            {acao}{Entidade}                       // ← novo
    );
}
```

### 5. Expor no Service

Abrir `{Modulo}Service.java` e adicionar campo + delegação:

```java
// campo
private final {Acao}{Entidade}UseCase {acao}{Entidade};

// construtor — adicionar parâmetro
public {Modulo}Service(/* existentes */, {Acao}{Entidade}UseCase {acao}{Entidade}) {
    // ...
    this.{acao}{Entidade} = {acao}{Entidade};
}

// método público
public void {acaoModulo}(UUID id /* , params */) {
    {acao}{Entidade}.execute(id /* , params */);
}
```

### 6. Expor no Controller (se necessário)

Adicionar endpoint no `{Modulo}Controller.java`:

```java
// Mutation via PATCH/PUT
@PatchMapping("/{id}/{acao}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void {acaoModulo}(@PathVariable UUID id) {
    {modulo}Service.{acaoModulo}(id);
}

// Com body
@PatchMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void update(@PathVariable UUID id, @RequestBody {Acao}Request request) {
    {modulo}Service.{acaoModulo}(id, /* request fields */);
}

// Record de request (no fim do arquivo, junto dos outros)
record {Acao}Request(/* campos */) {}
```

---

## Checklist

- [ ] Use case sem anotações Spring
- [ ] `execute(...)` com parâmetros explícitos (não objetos de request)
- [ ] Busca a entidade via repositório antes de mutar
- [ ] Salva após mutação (`repository.save(entity)`)
- [ ] `@Bean` adicionado no `*Config`
- [ ] Service atualizado com novo campo + construtor + método delegador
- [ ] Config atualizado para passar o novo use case ao Service
- [ ] `./mvnw test` passa
