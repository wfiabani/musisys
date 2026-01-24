# ADR-001 – Adoção de Monólito Modularizado

## Contexto
O sistema é voltado a grupos musicais que buscam organização interna, onde o volume de dados e a complexidade operacional tendem a ser baixos.

Foi considerada a adoção imediata de microserviços.

## Decisão
O sistema será implementado como um **monólito modularizado**, onde cada módulo representa um **Bounded Context**, com:

- Isolamento de domínio
- Contratos explícitos entre módulos
- Comunicação in-process

A arquitetura será preparada para **extração futura de módulos como microserviços**, caso surja uma necessidade real.

## Justificativa
- Reduz a complexidade operacional e cognitiva inicial
- Evita custos prematuros de infraestrutura

## Consequências
### Positivas
- Menor custo inicial
- Time-to-market mais rápido
- Arquitetura clara e sustentável

### Negativas
- Escalabilidade independente limitada
- Requer disciplina para manter o isolamento entre módulos

## Observações
A adoção de microserviços será considerada apenas caso surjam necessidades claras, como:
- Escalabilidade independente
- Autonomia de times
- Requisitos operacionais específicos