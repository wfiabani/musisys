# Skill: new-entity

Adiciona uma nova entidade de domínio a um módulo existente, com toda a stack: modelo, repositório, JPA, use cases básicos e endpoint REST.

## Quando usar

Invoque com `/new-entity` quando um módulo existente precisa gerenciar um novo conceito de domínio que ainda não existe no projeto.

Exemplos: adicionar `Member` ao módulo `membros`, adicionar `Contract` ao módulo `comercial`.

---

## Referência canônica

Consulte `Music` + `MusicEntity` + `JpaMusicRepository` no módulo `repertorio` antes de criar qualquer arquivo.

---

## Sequência de implementação

### 1. Entidade de domínio

```
src/main/java/br/com/band/band/{modulo}/domain/model/{Entidade}.java
```

```java
package br.com.band.band.{modulo}.domain.model;

import java.util.Objects;
import java.util.UUID;

public class {Entidade} {

    private UUID id;
    private String name;           // adapte os campos ao domínio real
    // outros campos...

    public {Entidade}(UUID id, String name /* , outros */) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        // ...
    }

    // Métodos de negócio expressivos (ex: rename, activate, assign...)
    public void rename(String newName) {
        this.name = Objects.requireNonNull(newName);
    }

    public UUID getId()   { return id; }
    public String getName() { return name; }
    // ...getters
}
```

**Enums** do mesmo agregado ficam em `domain/model/`:
```java
public enum {EntidadeStatus} { ACTIVE, INACTIVE, ARCHIVED }
```

---

### 2. DTO

```
src/main/java/br/com/band/band/{modulo}/application/dto/{Entidade}DTO.java
```

```java
package br.com.band.band.{modulo}.application.dto;

import java.util.UUID;

public record {Entidade}DTO(UUID id, String name /* , outros */) {}
```

---

### 3. Exceção not-found

```
src/main/java/br/com/band/band/{modulo}/application/exception/{Entidade}NotFoundException.java
```

```java
package br.com.band.band.{modulo}.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class {Entidade}NotFoundException extends RuntimeException {
    public {Entidade}NotFoundException(UUID id) {
        super("{Entidade} not found: " + id);
    }
}
```

---

### 4. Interface de repositório

```
src/main/java/br/com/band/band/{modulo}/application/port/repository/{Entidade}Repository.java
```

```java
package br.com.band.band.{modulo}.application.port.repository;

import br.com.band.band.{modulo}.domain.model.{Entidade};
import java.util.*;

public interface {Entidade}Repository {
    List<{Entidade}> findAll();
    Optional<{Entidade}> findById(UUID id);
    void save({Entidade} entity);
    void deleteById(UUID id);
}
```

---

### 5. JPA Entity

```
src/main/java/br/com/band/band/{modulo}/infrastructure/persistence/{Entidade}Entity.java
```

```java
package br.com.band.band.{modulo}.infrastructure.persistence;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "{tabela_plural}")
public class {Entidade}Entity {

    @Id
    private UUID id;
    private String name;
    // outros campos...
    // Enums: @Enumerated(EnumType.STRING)

    protected {Entidade}Entity() {}

    public {Entidade}Entity(UUID id, String name /* , outros */) {
        this.id = id;
        this.name = name;
        // ...
    }

    public UUID getId()     { return id; }
    public String getName() { return name; }
    // getters...
}
```

---

### 6. Spring Data Repository

```
src/main/java/br/com/band/band/{modulo}/infrastructure/persistence/SpringData{Entidade}Repository.java
```

```java
package br.com.band.band.{modulo}.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpringData{Entidade}Repository extends JpaRepository<{Entidade}Entity, UUID> {
    // Adicionar métodos customizados apenas se necessário
    // boolean existsByName(String name);
}
```

---

### 7. Adaptador JPA

```
src/main/java/br/com/band/band/{modulo}/infrastructure/persistence/Jpa{Entidade}Repository.java
```

```java
package br.com.band.band.{modulo}.infrastructure.persistence;

import br.com.band.band.{modulo}.application.port.repository.{Entidade}Repository;
import br.com.band.band.{modulo}.domain.model.{Entidade};
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class Jpa{Entidade}Repository implements {Entidade}Repository {

    private final SpringData{Entidade}Repository repository;

    public Jpa{Entidade}Repository(SpringData{Entidade}Repository repository) {
        this.repository = repository;
    }

    @Override
    public List<{Entidade}> findAll() {
        return repository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<{Entidade}> findById(UUID id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public void save({Entidade} entity) {
        repository.save(toEntity(entity));
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    private {Entidade} toDomain({Entidade}Entity e) {
        return new {Entidade}(e.getId(), e.getName() /* , outros */);
    }

    private {Entidade}Entity toEntity({Entidade} d) {
        return new {Entidade}Entity(d.getId(), d.getName() /* , outros */);
    }
}
```

> **Quando usar Mapper separado:** quando a entidade tiver collections, lógica de reconstituição complexa ou mais de 6-7 campos. Caso contrário, manter os métodos privados no adapter como acima.

---

### 8. Use cases CRUD básicos

Criar os quatro use cases fundamentais (um arquivo por use case):

**List:**
```java
public class List{Entidade}sUseCase {
    private final {Entidade}Repository repository;
    public List{Entidade}sUseCase({Entidade}Repository repository) { this.repository = repository; }
    public List<{Entidade}DTO> execute() {
        return repository.findAll().stream()
                .map(e -> new {Entidade}DTO(e.getId(), e.getName()))
                .toList();
    }
}
```

**Create:**
```java
public class Create{Entidade}UseCase {
    private final {Entidade}Repository repository;
    public Create{Entidade}UseCase({Entidade}Repository repository) { this.repository = repository; }
    public UUID execute(String name /* , outros */) {
        var entity = new {Entidade}(UUID.randomUUID(), name /* , outros */);
        repository.save(entity);
        return entity.getId();
    }
}
```

**Update:**
```java
public class Update{Entidade}UseCase {
    private final {Entidade}Repository repository;
    public Update{Entidade}UseCase({Entidade}Repository repository) { this.repository = repository; }
    public void execute(UUID id, String name /* , outros */) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new {Entidade}NotFoundException(id));
        entity.rename(name); // ou o método de negócio adequado
        repository.save(entity);
    }
}
```

**Delete:**
```java
public class Delete{Entidade}UseCase {
    private final {Entidade}Repository repository;
    public Delete{Entidade}UseCase({Entidade}Repository repository) { this.repository = repository; }
    public void execute(UUID id) {
        repository.findById(id).orElseThrow(() -> new {Entidade}NotFoundException(id));
        repository.deleteById(id);
    }
}
```

---

### 9. Registrar no Config e Service

**Config** — adicionar os quatro @Bean novos:
```java
@Bean
public List{Entidade}sUseCase list{Entidade}s({Entidade}Repository repo) {
    return new List{Entidade}sUseCase(repo);
}
@Bean
public Create{Entidade}UseCase create{Entidade}({Entidade}Repository repo) {
    return new Create{Entidade}UseCase(repo);
}
@Bean
public Update{Entidade}UseCase update{Entidade}({Entidade}Repository repo) {
    return new Update{Entidade}UseCase(repo);
}
@Bean
public Delete{Entidade}UseCase delete{Entidade}({Entidade}Repository repo) {
    return new Delete{Entidade}UseCase(repo);
}
```

Passar os novos use cases ao `@Bean` do Service existente.

**Service** — adicionar campos, parâmetros no construtor e métodos delegadores.

---

### 10. Endpoints REST

Adicionar ao controller existente ou criar `{Entidade}Controller.java` separado se o módulo já tiver um controller grande:

```java
// GET /modulo/{entidade}s
@GetMapping("/{entidade}s")
public List<{Entidade}DTO> list{Entidade}s() {
    return {modulo}Service.list{Entidade}s();
}

// POST /modulo/{entidade}s → 201
@PostMapping("/{entidade}s")
@ResponseStatus(HttpStatus.CREATED)
public UUID create{Entidade}(@RequestBody Create{Entidade}Request req) {
    return {modulo}Service.create{Entidade}(req.name() /* , outros */);
}

// PUT /modulo/{entidade}s/{id} → 204
@PutMapping("/{entidade}s/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void update{Entidade}(@PathVariable UUID id, @RequestBody Update{Entidade}Request req) {
    {modulo}Service.update{Entidade}(id, req.name() /* , outros */);
}

// DELETE /modulo/{entidade}s/{id} → 204
@DeleteMapping("/{entidade}s/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void delete{Entidade}(@PathVariable UUID id) {
    {modulo}Service.delete{Entidade}(id);
}

record Create{Entidade}Request(String name /* , outros */) {}
record Update{Entidade}Request(String name /* , outros */) {}
```

---

## Checklist

- [ ] Entidade domínio sem Spring/JPA
- [ ] DTO como record
- [ ] Exceção com `@ResponseStatus`
- [ ] Interface de repositório retorna tipos domínio
- [ ] `@Entity` com construtor `protected` sem args
- [ ] `@Repository` apenas no adapter JPA
- [ ] Use cases sem anotações Spring, método `execute(...)`
- [ ] Todos os @Bean registrados no `*Config`
- [ ] Service atualizado (campos + construtor + métodos)
- [ ] `./mvnw test -Dtest=ModulithStructureTest` passando
