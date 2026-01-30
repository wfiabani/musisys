# ADR-002 – Comunicação entre Bounded Contexts via API REST

## Contexto
O sistema é um monólito modularizado, estruturado em Bounded Contexts (DDD).
Alguns módulos precisam consumir dados de outros Bounded Contexts.

## Decisão
A comunicação entre Bounded Contexts será feita **exclusivamente via API REST**, mesmo no contexto do monólito.

O acesso direto a código, entidades ou repositórios de outro BC não será permitido.

## Justificativa
- Protege as **fronteiras entre Bounded Contexts**
- Reduz acoplamento estrutural e conceitual
- Torna as dependências explícitas por meio de contratos
- Prepara o sistema para futura migração para **microservices**

Embora a comunicação via HTTP seja mais custosa que chamadas diretas de código, o ganho arquitetural na separação dos BCs e na proteção das fronteiras justifica a decisão.

## Consequências
- Overhead de comunicação entre módulos
- Arquitetura mais clara, resiliente e evolutiva