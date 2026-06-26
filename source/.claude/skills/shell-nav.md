# Skill: shell-nav

Atualiza o shell front-end (`templates/shell.html`) para incluir páginas de novos módulos na navegação lateral.

## Quando usar

Invoque com `/shell-nav` ao finalizar um novo módulo que tem view controller (`/{modulo}/ui/...`) e precisa aparecer na sidebar do MusiSYS.

---

## Arquitetura do shell

O shell é um único arquivo HTML: `src/main/resources/templates/shell.html`.

Servido por `ShellController` na rota `/`, renderiza:
- **Sidebar** estática com grupos de navegação
- **`<iframe id="frame">`** que carrega as páginas dos módulos

As páginas dos módulos são projetadas como "miolo" (sem layout próprio) e carregadas dentro desse iframe. Toda navegação acontece trocando o `src` do iframe — nunca há redirecionamento na janela principal.

O componente Alpine.js `shell()` gerencia:
- `currentUrl` — URL atual exibida no iframe
- `nav` — objeto com as listas de itens por grupo
- `navigate(url)` — troca o iframe + atualiza `history` + persiste no `sessionStorage`
- `isActive(href)` — retorna `true` se `currentUrl === href` ou começa com `href + '/'`
- `onFrameLoad()` — sincroniza a URL do browser com a URL real dentro do iframe
- `init()` — restaura a última página visitada (via URL path ou `sessionStorage`)

---

## Partes a modificar

Todas as mudanças ficam em `src/main/resources/templates/shell.html`.

### Parte 1 — Adicionar o ícone SVG ao dicionário `ICONS`

`ICONS` é um objeto literal no `<script>` ao fim do arquivo. Adicionar uma entrada com SVG `w-4 h-4 stroke` (Heroicons outline, `stroke-width="2"`):

```js
const ICONS = {
    // ... ícones existentes ...

    nomeDoIcone: `<svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="/* path do heroicon */"/>
                  </svg>`,
}
```

Ícones em uso: `music`, `list`, `cal`, `dollar`, `agenda`.
Usar Heroicons v1 outline (compatível com o viewport `0 0 24 24`).

---

### Parte 2 — Registrar os itens de navegação no objeto `nav`

No `shell()`, dentro do objeto `nav`, adicionar uma nova chave com o array de itens do módulo:

```js
nav: {
    repertorio: [ /* ... */ ],
    eventos:    [ /* ... */ ],
    financeiro: [ /* ... */ ],

    // novo módulo:
    {modulo}: [
        { href: '/{modulo}/ui',         label: 'Página Principal', icon: ICONS.nomeDoIcone },
        { href: '/{modulo}/ui/sub',     label: 'Sub-página',       icon: ICONS.outroIcone  },
    ],

    futuro: [ /* ... */ ],
},
```

Cada item:
- `href` — rota exata do `@Controller` (ex: `/repertorio/ui/musics`)
- `label` — texto exibido na sidebar
- `icon` — referência à entrada em `ICONS`

---

### Parte 3 — Renderizar o grupo na sidebar HTML

Localizar o bloco `<!-- Placeholders futuros -->` na sidebar e inserir o novo grupo **antes** dele:

```html
<!-- {NomeDoMódulo} -->
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

O `x-for` itera sobre `nav.{modulo}` — o mesmo nome da chave adicionada no passo 2.

---

### Parte 4 — Remover ou promover itens "Em breve"

O bloco `<!-- Placeholders futuros -->` renderiza itens desabilitados do array `nav.futuro`. Quando um módulo fica pronto:

1. Remover o item correspondente de `nav.futuro`
2. Criar o grupo ativo conforme os passos 2 e 3

Item "Em breve" (referência — não tem `@click` nem `href`):
```html
<div class="flex items-center gap-2.5 px-2 py-1.5 rounded-lg
            text-gray-600 cursor-not-allowed select-none">
    <span x-html="item.icon" class="shrink-0 opacity-50"></span>
    <span x-text="item.label" class="opacity-50"></span>
</div>
```

Para adicionar um novo placeholder "Em breve" (módulo planejado, não implementado):
```js
futuro: [
    { label: 'Agenda',    icon: ICONS.agenda },
    { label: '{Módulo}',  icon: ICONS.novoIcone },  // ← adicionar aqui
],
```

---

## Comportamento de `isActive`

```js
isActive(href) {
    return this.currentUrl === href || this.currentUrl.startsWith(href + '/')
}
```

Consequência: se um grupo tiver apenas uma rota raiz (`/financeiro/ui`), qualquer sub-rota (`/financeiro/ui/detail/123`) também ativa o item. Para grupos com múltiplos itens (como Repertório), cada item é ativado individualmente pela rota exata ou pelo seu prefixo.

---

## Persistência de navegação

O shell persiste a última URL visitada em `sessionStorage` com a chave `musisys_last_url`. Ao reabrir `/`, restaura essa URL no iframe automaticamente. Nenhuma mudança necessária ao adicionar rotas novas — o mecanismo é genérico.

---

## Checklist

- [ ] Ícone adicionado ao objeto `ICONS`
- [ ] Chave adicionada ao objeto `nav` dentro de `shell()`
- [ ] Bloco HTML do grupo inserido na sidebar (antes de `<!-- Placeholders futuros -->`)
- [ ] Item removido de `nav.futuro` se o módulo estava lá como placeholder
- [ ] `isActive` funciona corretamente para rotas com sub-páginas (prefixo bate)
- [ ] Testar navegação: clicar no item carrega o iframe, URL do browser atualiza, item fica destacado
