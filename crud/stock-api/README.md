## 🎯 Aprendizado

* **Arquitetura de camadas estruturada:** Organização clara e separação de responsabilidades no backend utilizando as camadas `Model`,`Repository`,`Service` e `Controller`.
* **Persistência de dados:** Spring Data JPA e PostgreSQL, garantindo que o banco de dados tivesse as informações salvas persistentemente.
* **Segurança de credenciais e variáveis de ambiente:** Configuração do application utilizando variáveis de ambiente (${DB_USERNAME}, ${DB_PASSWORD}) para impedir o vazamento de dados sensíveis no versionamento do Git.
* **Transferência de dados segura e otimizada:** Através do padrão DTO (Data Transfer Object) com Java Records.
* **Tipagem forte e domínio fechado com Enums:** Padronização de atributos finitos no tipo de movimentação para impedir anomalias nos registros financeiros e de estoque.
* **Integridade de Transações:** Utilização da anotação `@Transactional` para garantir operações seguras de banco de dados, permitindo rollback automático em caso de falhas nas transferências ou atualizações de estoque.
* **Validação de dados:** Proteção dos payloads de entrada (Bean Validation) com anotações como `@Valid`, `@NotBlank`, `@NotNull` e `@Min` para barrar requisições inconsistentes antes mesmo de chegarem às regras de negócio.
* **Tratamento global de exceções:** Implementação da camada `@RestControllerAdvice` para capturar exceções customizadas e devolver respostas de erro limpas e padronizadas com os devidos status HTTP (400, 404).
* **Documentação interativa:** Mapeamento das rotas utilizando Swagger / OpenAPI.

## 💡 Retrospectiva

> Durante o estudo dessa API de estoque, alguns aprendizados mereceram maior destaque, especialmente o uso do `transactional` para a realização de operações mais seguras e uma maior consistência nos dados persistidos no banco. Além de ter, também, possibilitado praticar um projeto com múltiplas entidades que interagem entre si.

## 🚀 Tecnologias Utilizadas

<div align="left">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-ORM-blue?style=for-the-badge)
![Hibernate](https://img.shields.io/badge/Hibernate-Validation-red?style=for-the-badge)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203.0-158300?style=for-the-badge&logo=swagger)

</div>
