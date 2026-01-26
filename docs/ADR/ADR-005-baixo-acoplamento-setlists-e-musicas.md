# ADR-005 – Baixo Acoplamento Inicial entre os Agregados Música e Setlist

## Contexto  
No Bounded Context **Repertório**, os agregados **Música** e **Setlist** representam conceitos distintos do domínio.  
Músicas possuem ciclo de vida próprio e **não são removidas**, apenas deixam de ser utilizadas.  
Setlists representam seleções contextuais de músicas, criadas e gerenciadas por usuários com conhecimento do repertório disponível.

De acordo com regras de negócio, entende-se que os atores envolvidos em **Músicas** são mais técnicos, enquanto **Setlists** são mais estratégicos, podendo futuramente ser administrados por diferentes equipes, podendo também se tornar módulos/micro-serviços individuais com o crescimento do sistema. Porém entende-se também que não faz sentido estabelecer uma divisão mais forte nesse momento, como segregação em BCs, por exemplo.

---

## Decisão  
Foi decidido manter **baixo acoplamento entre os agregados Música e Setlist**.

O agregado **Setlist** referencia músicas de forma fraca, utilizando apenas **identificadores e dados mínimos de exibição**, sem dependência direta do agregado Música e sem validações de integridade rígidas.

O agregado **Música** não possui qualquer conhecimento sobre Setlists.

---

## Consequências  

### Positivas
- Agregados independentes e bem delimitados
- Redução de acoplamento e dependências transitivas
- Evolução isolada de cada agregado
- Alinhamento com DDD e monólito modular

### Negativas
- Consistência fraca entre Setlists e Músicas
- Possibilidade de Setlists referenciar músicas não mais utilizadas

Essas limitações são aceitáveis no estágio atual do sistema e podem ser endereçadas futuramente caso o domínio evolua.
