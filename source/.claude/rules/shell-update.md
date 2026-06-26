# Shell — Atualização Obrigatória

Sempre que um módulo ganhar um view controller (`@Controller` com rotas `/modulo/ui/...`), atualizar `src/main/resources/templates/shell.html` antes de considerar a tarefa concluída.

## O que atualizar

1. **`ICONS`** — adicionar SVG Heroicons outline (`w-4 h-4`, `stroke-width="2"`, `viewBox="0 0 24 24"`) para o novo módulo.

2. **`nav`** dentro de `shell()` — adicionar chave com array de itens:
```js
{modulo}: [
    { href: '/{modulo}/ui', label: 'Rótulo', icon: ICONS.nomeDoIcone },
],
```

3. **Sidebar HTML** — inserir bloco de grupo antes de `<!-- Placeholders futuros -->`:
```html
<div>
    <p class="px-2 mb-1 text-[10px] font-semibold text-gray-500 uppercase tracking-widest">
        {Rótulo do grupo}
    </p>
    <template x-for="item in nav.{modulo}" :key="item.href">
        <button @click="navigate(item.href)"
                :class="isActive(item.href)
                    ? 'bg-brand-600 text-white'
                    : 'text-gray-400 hover:bg-white/10 hover:text-white'"
                class="w-full flex items-center gap-2.5 px-2 py-1.5 rounded-lg
                       transition-colors text-left">
            <span x-html="item.icon" class="shrink-0 opacity-90"></span>
            <span x-text="item.label"></span>
        </button>
    </template>
</div>
```

4. **`nav.futuro`** — remover o placeholder do módulo se ele estava listado como "Em breve".

## Referência

Use a skill `/shell-nav` para o passo a passo completo.
