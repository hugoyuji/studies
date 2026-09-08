## 🎯 Aprendizado

* **Arquitetura de camadas:** Organização clara das responsabilidades entre `Controller`, `Service`, `Repository` e `Model`.
* **Persistência no banco de dados:** Spring Data JPA e PostgreSQL, garantindo que o banco de dados tivesse as informações salvas persistentemente.
* **Segurança de credenciais e variáveis de ambiente:** Configuração do `application` utilizando variáveis de ambiente (`${DB_USERNAME}`, `${DB_PASSWORD}`) para impedir o vazamento de dados sensíveis no versionamento do Git.
* **Transferência de dados segura e otimizada:** Através do padrão DTO (Data Transfer Object) com Java Records.
* **Tipagem forte e domínio fechado com Enums:** Padronização de atributos finitos (`Status` e `Tipo`) para impedir entradas inconsistentes no payload e garantir a integridade dos dados no banco.
* **Validação de dados:** Uso de anotações como `@NotBlank`, `@NotNull` e `@Future` no payload de entrada.
* **Testes de Controllers/API (WebMvcTest):** Validação dos endpoints REST (`POST`, `GET`, `DELETE`), serialização JSON, conversão de DTOs e status HTTP (`200 OK`, `204 No Content`) utilizando `MockMvc` e `@MockitoBean`.

## 💡 Retrospectiva

> Durante esse estudo, tive um contato mais próximo com a estrutura exigida em desafios de recrutamento e testes técnicos. O exercício foi disponibilizado no canal do professor Matheus Leandro Ferreira, onde tive meu primeiro contato prático com testes unitários e de integração com Spring Boot, exercitando o ciclo completo de rotas REST (`POST`, `GET` e `DELETE`).

## 🚀 Tecnologias Utilizadas

<div align="left">

![Java](https://img.shields.io/badge/Java-26-orange?style=for-the-badge&logo=java)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-ORM-blue?style=for-the-badge)
![Hibernate Validation](https://img.shields.io/badge/Hibernate-Validation-red?style=for-the-badge)
![Lombok](https://img.shields.io/badge/Lombok-Builder-red?style=for-the-badge&logo=lombok)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15+-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![JUnit 5](https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5)
![Mockito](https://img.shields.io/badge/Mockito-Framework-green?style=for-the-badge)

</div>
