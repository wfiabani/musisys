# ADR-004 – Adoção de Monólito Modular com Spring Modulith

## Contexto  
A aplicação está sendo desenvolvida em Java (LTS) com Spring Boot e possui múltiplos domínios de negócio bem definidos, modelados como Bounded Contexts.

Existe a necessidade de:
- Manter o sistema inicialmente como um monólito
- Garantir isolamento entre contextos de negócio
- Facilitar uma possível migração futura para uma arquitetura de microservices
- Evitar acoplamento indevido entre módulos ao longo da evolução do sistema

---

## Decisão  
Foi adotada a abordagem de **monólito modular utilizando Spring Modulith**.

Cada Bounded Context é tratado como um **módulo explícito**, com dependências claramente declaradas e controladas, permitindo:
- Isolamento estrutural entre módulos
- Governança de dependências em tempo de build
- Comunicação entre módulos por meio de contratos explícitos e eventos de domínio

O Spring Modulith será utilizado como mecanismo de suporte para validação arquitetural, documentação e organização modular da aplicação.

---

## Consequências  

### Positivas
- Forte alinhamento com conceitos de DDD
- Redução de acoplamento entre Bounded Contexts
- Facilidade na evolução do monólito
- Base sólida para futura extração de microservices

### Negativas
- Curva de aprendizado inicial
- Complexidade maior quando comparado a um monólito tradicional

---
