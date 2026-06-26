# Fronteiras de Módulo — Spring Modulith

Nenhum módulo pode importar classes internas de outro módulo.

**Proibido:**
```java
// módulo `eventos` acessando classe interna de `repertorio` ❌
import br.com.band.band.repertorio.domain.model.Setlist;
import br.com.band.band.repertorio.infrastructure.persistence.setlist.SetlistEntity;
import br.com.band.band.repertorio.application.usecase.GetSetlistWithMusicsUseCase;
```

**Único caminho legítimo entre módulos:**

1. **Tipos em `shared/api/events/`** — `@NamedInterface` explicitamente exposto; qualquer módulo pode importar.
2. **Eventos de domínio** — publicar via `DomainEventPublisher` (porta de saída); consumir via `@EventListener`.
3. **HTTP via RestClient/RestTemplate** — consultas entre módulos usam cliente HTTP com URL configurável via `@Value`.

Violações quebram `ModulithStructureTest`. Sempre rodar antes de concluir qualquer tarefa:

```bash
./mvnw test -Dtest=ModulithStructureTest
```

Se uma colaboração entre módulos parece exigir acesso a internos, o dado deve ser exposto via API REST do módulo de origem ou promovido para `shared/`.
