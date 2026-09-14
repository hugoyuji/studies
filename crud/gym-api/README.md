## 🎯 Aprendizado

* **Arquitetura de camadas:** Organização clara das responsabilidades entre `Controller`, `Service`, `Repository` e `Model`.
* **Persistência no banco de dados:** Spring Data JPA e PostgreSQL, garantindo que o banco de dados tivesse as informações salvas persistentemente.
* **Segurança de credenciais e variáveis de ambiente:** Configuração do `application` utilizando variáveis de ambiente (`${DB_USERNAME}`, `${DB_PASSWORD}`) para impedir o vazamento de dados sensíveis no versionamento do Git.
* **Transferência de dados segura e otimizada:** Através do padrão DTO (Data Transfer Object) com Java Records.
* **Validação de dados:** Uso de anotações como `@Valid`, `@NotBlank` e `@NotNull` no payload de entrada.
* **Tratamento centralizado de exceções:** Implementação do `@RestControllerAdvice` para padronizar respostas de erro (400, 404).
* **Testes manuais com Postman:** Construção de requisições cobrindo cenários de sucesso (`201 Created`, `200 OK`, `204 No Content`), erros de validação (`400 Bad Request`) e recursos ausentes (`404 Not Found`).
* **Testes unitários:** Cobertura de regras de negócio com JUnit 5 e Mockito para simulação de dependências.
* **Documentação interativa:** Mapeamento das rotas utilizando Swagger / OpenAPI.

## 💡 Retrospectiva

> Esse projeto foi mais um dos trabalhos em que eu pude praticar e buscar consolidar o meu conhecimento na construção de uma API Rest, especialmente no que se diz reforçar, de forma prática, o desenvolvimento de DTOs, validação de dados, tratamento de exceções e testes unitários.


## 🚀 Tecnologias Utilizadas

<div align="left">

![Java](https://img.shields.io/badge/Java-26-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-ORM-blue?style=for-the-badge)
![Hibernate](https://img.shields.io/badge/Hibernate-Validation-red?style=for-the-badge)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15+-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![JUnit 5](https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5)
![Mockito](https://img.shields.io/badge/Mockito-Framework-green?style=for-the-badge)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203.0-158300?style=for-the-badge&logo=swagger)

</div>
