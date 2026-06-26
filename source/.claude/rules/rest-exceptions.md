# REST — Exceções, Status e Request Bodies

## Exceções de domínio

Toda exceção de domínio deve carregar seu próprio status HTTP via `@ResponseStatus`. Não existe `@RestControllerAdvice` global neste projeto — não criar um.

```java
@ResponseStatus(HttpStatus.NOT_FOUND)           // ✅
public class MusicNotFoundException extends RuntimeException {
    public MusicNotFoundException(UUID id) {
        super("Music not found: " + id);
    }
}

@ResponseStatus(HttpStatus.CONFLICT)            // ✅
public class MusicInUseException extends RuntimeException { ... }
```

## Status HTTP nos controllers

Anotar explicitamente métodos que não retornam 200:

```java
@PostMapping
@ResponseStatus(HttpStatus.CREATED)     // 201
public UUID create(...) { ... }

@PutMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)  // 204
public void update(...) { ... }

@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)  // 204
public void delete(...) { ... }
```

## Request bodies

Sempre `record` declarado como inner class no fim do arquivo do controller:

```java
// ✅ — records no fim do controller
record CreateMusicRequest(String title, String author, String key, String description) {}
record UpdateMusicRequest(String title, String author, String key, String description) {}
```

Nunca usar classes separadas, DTOs de aplicação ou objetos do domínio como `@RequestBody`.
