# Use Cases — Regra de Ouro

Use cases são classes Java comuns. Nunca recebem anotações Spring.

**Proibido:**
```java
@Service                  // ❌
@Component                // ❌
@Transactional            // ❌
public class CreateMusicUseCase { ... }
```

**Correto:**
```java
public class CreateMusicUseCase {   // ✅ sem anotações

    private final MusicRepository repository;

    public CreateMusicUseCase(MusicRepository repository) {
        this.repository = repository;
    }

    public UUID execute(String title, String author, String key, String description) {
        ...
    }
}
```

Use cases são instanciados exclusivamente como `@Bean` na classe `*Config` do módulo:

```java
@Bean
public CreateMusicUseCase createMusicUseCase(MusicRepository musicRepository) {
    return new CreateMusicUseCase(musicRepository);
}
```

O método público obrigatório é `execute(...)` com parâmetros primitivos ou tipados — nunca objetos de request HTTP.

UUID de novas entidades é gerado dentro do use case via `UUID.randomUUID()`, não no controller nem no domínio.
