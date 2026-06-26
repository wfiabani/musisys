# Skill: new-module

Cria um novo módulo completo seguindo a arquitetura Clean Architecture + Spring Modulith deste projeto.

## Quando usar

Invoque com `/new-module <nome-do-modulo>` para gerar toda a estrutura de um novo módulo a partir do zero.

O nome deve estar em minúsculas (ex: `comercial`, `marketing`, `membros`).

## Referência canônica

Os módulos `repertorio` e `eventos` são a referência. Consulte-os antes de criar qualquer arquivo.
Pacote raiz do projeto: `br.com.band.band`.

---

## Sequência obrigatória de implementação

Siga esta ordem exata. Nunca pule etapas.

### 1. package-info.java

```
src/main/java/br/com/band/band/{modulo}/package-info.java
```

```java
@org.springframework.modulith.ApplicationModule
package br.com.band.band.{modulo};
```

---

### 2. Entidade de domínio

```
src/main/java/br/com/band/band/{modulo}/domain/model/{Entidade}.java
```

Regras obrigatórias:
- POJO puro: zero anotações Spring ou JPA
- UUID como chave primária
- Construtor all-args com parâmetros tipados
- Apenas getters (sem setters)
- Métodos de negócio expressivos (ex: `changeStatus(...)`, `assignMember(...)`) com `Objects.requireNonNull` em campos obrigatórios
- Enums de tipo/status ficam em `domain/model/` também

```java
package br.com.band.band.{modulo}.domain.model;

import java.util.UUID;

public class {Entidade} {

    private UUID id;
    // ... campos

    public {Entidade}(UUID id, /* campos */) {
        this.id = id;
        // ...
    }

    // métodos de negócio aqui

    public UUID getId() { return id; }
    // ... getters
}
```

---

### 3. Interface de repositório (porta de saída)

```
src/main/java/br/com/band/band/{modulo}/application/port/repository/{Entidade}Repository.java
```

- Interface Java pura, sem Spring
- Métodos no vocabulário do domínio (não JPA)
- Retorna tipos de domínio, nunca entidades JPA

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

### 4. Exceções de aplicação

```
src/main/java/br/com/band/band/{modulo}/application/exception/{Entidade}NotFoundException.java
```

- Sempre anotadas com `@ResponseStatus`
- Extends `RuntimeException`
- Mensagem clara com o ID

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

### 5. DTOs de aplicação

```
src/main/java/br/com/band/band/{modulo}/application/dto/{Entidade}DTO.java
```

- Records Java (imutáveis por padrão)
- Apenas dados necessários para a camada web
- Criados dentro dos use cases ou no service

```java
package br.com.band.band.{modulo}.application.dto;

import java.util.UUID;

public record {Entidade}DTO(UUID id, /* campos */) {}
```

---

### 6. Casos de uso

```
src/main/java/br/com/band/band/{modulo}/application/usecase/Create{Entidade}UseCase.java
src/main/java/br/com/band/band/{modulo}/application/usecase/List{Entidade}sUseCase.java
src/main/java/br/com/band/band/{modulo}/application/usecase/Update{Entidade}UseCase.java
src/main/java/br/com/band/band/{modulo}/application/usecase/Delete{Entidade}UseCase.java
```

Regras obrigatórias:
- Classe Java comum — SEM `@Service`, `@Component` ou qualquer anotação Spring
- Um use case por classe
- Método público `execute(...)` com parâmetros primitivos/tipados
- UUID gerado aqui via `UUID.randomUUID()` (não no domínio nem no controller)
- Dependências injetadas via construtor

```java
package br.com.band.band.{modulo}.application.usecase;

import br.com.band.band.{modulo}.application.port.repository.{Entidade}Repository;
import br.com.band.band.{modulo}.domain.model.{Entidade};
import java.util.UUID;

public class Create{Entidade}UseCase {

    private final {Entidade}Repository repository;

    public Create{Entidade}UseCase({Entidade}Repository repository) {
        this.repository = repository;
    }

    public UUID execute(/* parâmetros */) {
        {Entidade} entity = new {Entidade}(UUID.randomUUID(), /* args */);
        repository.save(entity);
        return entity.getId();
    }
}
```

---

### 7. Service de aplicação (facade)

```
src/main/java/br/com/band/band/{modulo}/application/{Modulo}Service.java
```

- Classe Java comum — SEM anotação Spring
- Recebe todos os use cases no construtor
- Métodos delegam para o use case correspondente (sem lógica própria)

```java
package br.com.band.band.{modulo}.application;

public class {Modulo}Service {

    private final Create{Entidade}UseCase create{Entidade};
    // ... outros use cases

    public {Modulo}Service(Create{Entidade}UseCase create{Entidade} /* , ... */) {
        this.create{Entidade} = create{Entidade};
    }

    public UUID create{Entidade}(/* params */) {
        return create{Entidade}.execute(/* params */);
    }
    // ...
}
```

---

### 8. JPA Entity

```
src/main/java/br/com/band/band/{modulo}/infrastructure/persistence/{Entidade}Entity.java
```

- `@Entity`, `@Table(name = "{tabela}")`
- `@Id` UUID, sem `@GeneratedValue` (UUID gerado na aplicação)
- Construtor `protected` sem args (para JPA)
- Construtor all-args público
- Apenas getters

```java
package br.com.band.band.{modulo}.infrastructure.persistence;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "{tabela}")
public class {Entidade}Entity {

    @Id
    private UUID id;
    // campos...

    protected {Entidade}Entity() {}

    public {Entidade}Entity(UUID id, /* campos */) {
        this.id = id;
        // ...
    }

    public UUID getId() { return id; }
    // getters...
}
```

---

### 9. Spring Data Repository

```
src/main/java/br/com/band/band/{modulo}/infrastructure/persistence/SpringData{Entidade}Repository.java
```

```java
package br.com.band.band.{modulo}.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpringData{Entidade}Repository extends JpaRepository<{Entidade}Entity, UUID> {
    // queries customizadas com @Query se necessário
}
```

---

### 10. Mapper (se a entidade for complexa)

```
src/main/java/br/com/band/band/{modulo}/infrastructure/persistence/mapper/{Entidade}Mapper.java
```

Quando usar: entidade com campos derivados, collections, ou lógica de reconstituição não trivial.
Quando **não** usar: mapeamento simples que cabe em linha no adapter (como JpaMusicRepository).

```java
package br.com.band.band.{modulo}.infrastructure.persistence.mapper;

import br.com.band.band.{modulo}.domain.model.{Entidade};
import br.com.band.band.{modulo}.infrastructure.persistence.{Entidade}Entity;

public class {Entidade}Mapper {

    private {Entidade}Mapper() {}

    public static {Entidade} toDomain({Entidade}Entity e) {
        return new {Entidade}(e.getId(), /* campos */);
    }

    public static {Entidade}Entity toEntity({Entidade} d) {
        return new {Entidade}Entity(d.getId(), /* campos */);
    }
}
```

---

### 11. Adaptador JPA (implementação do repository port)

```
src/main/java/br/com/band/band/{modulo}/infrastructure/persistence/Jpa{Entidade}Repository.java
```

- `@Repository` (único componente gerenciado Spring na camada de persistência)
- Implementa a interface de domínio
- Converte entre domínio e entidade JPA

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
        return new {Entidade}(e.getId(), /* campos */);
    }

    private {Entidade}Entity toEntity({Entidade} d) {
        return new {Entidade}Entity(d.getId(), /* campos */);
    }
}
```

---

### 12. REST Controller

```
src/main/java/br/com/band/band/{modulo}/infrastructure/web/{Modulo}Controller.java
```

- `@RestController`, `@RequestMapping("/{modulo}")`
- Injetar `{Modulo}Service` via construtor
- Records para request bodies (inner classes no fim do arquivo)
- `@ResponseStatus` em cada método que não retorna 200

```java
package br.com.band.band.{modulo}.infrastructure.web;

import br.com.band.band.{modulo}.application.{Modulo}Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/{modulo}")
public class {Modulo}Controller {

    private final {Modulo}Service {modulo}Service;

    public {Modulo}Controller({Modulo}Service {modulo}Service) {
        this.{modulo}Service = {modulo}Service;
    }

    @GetMapping
    public List<{Entidade}DTO> findAll() {
        return {modulo}Service.listAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID create(@RequestBody Create{Entidade}Request request) {
        return {modulo}Service.create(/* request fields */);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id, @RequestBody Update{Entidade}Request request) {
        {modulo}Service.update(id, /* request fields */);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        {modulo}Service.delete(id);
    }

    record Create{Entidade}Request(/* campos */) {}
    record Update{Entidade}Request(/* campos */) {}
}
```

---

### 13. View Controller (Thymeleaf)

```
src/main/java/br/com/band/band/{modulo}/infrastructure/web/{Modulo}ViewController.java
```

- `@Controller` (não `@RestController`)
- Rota base: `/{modulo}/ui`
- Injeta dados via `Model` para Thymeleaf renderizar no carregamento inicial
- Alpine.js assume mutações posteriores via fetch REST

```java
package br.com.band.band.{modulo}.infrastructure.web;

import br.com.band.band.{modulo}.application.{Modulo}Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{modulo}/ui")
public class {Modulo}ViewController {

    private final {Modulo}Service {modulo}Service;

    public {Modulo}ViewController({Modulo}Service {modulo}Service) {
        this.{modulo}Service = {modulo}Service;
    }

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("pageTitle", "/* título */");
        model.addAttribute("items", {modulo}Service.listAll());
        return "{modulo}/index";
    }
}
```

---

### 14. Classe de configuração

```
src/main/java/br/com/band/band/{modulo}/infrastructure/config/{Modulo}Config.java
```

- `@Configuration`
- Instancia TODOS os use cases como `@Bean`
- Instancia o Service como `@Bean` recebendo os use cases
- Este é o único lugar que usa `new` para use cases

```java
package br.com.band.band.{modulo}.infrastructure.config;

import br.com.band.band.{modulo}.application.{Modulo}Service;
import br.com.band.band.{modulo}.application.port.repository.{Entidade}Repository;
import br.com.band.band.{modulo}.application.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class {Modulo}Config {

    @Bean
    public Create{Entidade}UseCase create{Entidade}UseCase({Entidade}Repository repo) {
        return new Create{Entidade}UseCase(repo);
    }

    // ... demais use cases

    @Bean
    public {Modulo}Service {modulo}Service(Create{Entidade}UseCase create /* , ... */) {
        return new {Modulo}Service(create /* , ... */);
    }
}
```

---

### 15. Templates Thymeleaf

```
src/main/resources/templates/{modulo}/_layout.html
src/main/resources/templates/{modulo}/index.html
```

`_layout.html` deve conter três fragmentos:
- `:: head` — `<head>` completo com Tailwind CDN + Alpine CDN
- `:: toastWidget` — notificação flutuante Alpine
- `:: scripts` — `Alpine.store('toast', {...})` + `window.readError`

Copiar o padrão de `templates/repertorio/_layout.html` integralmente.

Nas páginas, injetar dados iniciais via:
```html
<script th:inline="javascript">
    window.__items = /*[[${items}]]*/ [];
</script>
```

Alpine.js usa esses dados como estado inicial e faz fetch para mutações.

---

## Checklist final

Antes de considerar o módulo completo, verifique:

- [ ] `package-info.java` com `@ApplicationModule`
- [ ] Entidade de domínio sem anotações Spring/JPA
- [ ] Interface de repositório retornando tipos de domínio
- [ ] Exceções com `@ResponseStatus`
- [ ] Use cases sem `@Service`/`@Component`
- [ ] `*Config` instanciando todos os use cases como `@Bean`
- [ ] `@Repository` apenas no adapter JPA
- [ ] Controller com records para requests
- [ ] `./mvnw test -Dtest=ModulithStructureTest` passando
