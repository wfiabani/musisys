# ADR-000 – Identificação de Subdomínios e Definição de Bounded Contexts

## Contexto  
O sistema tem como objetivo apoiar a gestão de bandas e grupos musicais, abrangendo atividades operacionais, financeiras e comerciais recorrentes.

Durante a fase inicial de análise do domínio, foram identificados diferentes **subdomínios de negócio**, cada um com responsabilidades, regras e linguagem próprias. Esse entendimento evoluiu conforme o domínio foi sendo explorado e refinado.

Observou-se também que alguns subdomínios possuem **subdivisões internas**, representando variações de comportamento dentro de um mesmo contexto, mas que não justificaram, naquele momento, a criação de Bounded Contexts independentes.

---

## Decisão  
Foram identificados e definidos os seguintes **subdomínios de negócio**, posteriormente materializados como **Bounded Contexts (BCs)**:

- **Financeiro**
- **Eventos**
- **Repertório**
- **Comercial**
- **Marketing**

Cada Bounded Context possui:
- Modelo de domínio próprio
- Regras de negócio bem delimitadas
- Linguagem ubíqua específica

Dentro de alguns Bounded Contexts, foram identificados **subdomínios internos**, que permanecem no mesmo BC por compartilharem o mesmo modelo conceitual e não apresentarem complexidade ou autonomia suficientes para justificar separação adicional:

### Eventos
- Shows  
- Ensaios  
- Reuniões  

### Financeiro
- Pagamentos  
- Cobranças  

Além disso, foi identificada a necessidade de um **Infra-Context denominado Agenda**, responsável por fornecer uma visão transversal de compromissos oriundos de múltiplos Bounded Contexts, sem centralizar ou assumir regras de negócio pertencentes a esses contextos.

---

## Consequências  

### Positivas
- Clareza na organização
- Evolução independente dos Bounded Contexts
- Modelo alinhado à complexidade real do negócio

### Negativas
- Complexidade arquitetural maior quando comparada a um modelo monolítico simples
