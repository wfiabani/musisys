# ADR-002 – Consumo da Agenda via API (contrato) pelos módulos de Eventos e Marketing

## Contexto
O sistema será implementado inicialmente como um **monólito modularizado**, organizado em módulos que representam Bounded Contexts (DDD).

O módulo **Agenda** é responsável por consolidar compromissos e datas relevantes.
Os módulos **Eventos** e **Marketing** precisam consultar essas informações.

Foi avaliado o consumo via eventos de domínio.

## Decisão
Eventos e Marketing irão consumir a Agenda **por meio de um contrato de API (Application Layer)** exposto pelo módulo Agenda.

A comunicação será **in-process**, sem uso de HTTP real, respeitando os limites do módulo.

Não haverá:
- Consumo de eventos
- Acesso direto a entidades ou repositórios da Agenda

## Justificativa
- Preserva os **limites do Bounded Context**
- Evita acoplamento a modelos internos
- Mantém **consistência forte** no momento da consulta
- Facilita futura extração para microserviço
- Evita complexidade desnecessária de eventos ou HTTP

## Consequências
### Positivas
- Arquitetura clara e evolutiva
- Baixo acoplamento entre módulos
- Facilidade de refatoração futura

### Negativas
- Introduz dependência direta entre módulos
- Requer disciplina na manutenção dos contratos

## Observações
Caso o módulo Agenda seja extraído futuramente como microserviço, o contrato existente poderá ser exposto via HTTP sem impacto significativo nos consumidores.
