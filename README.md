# Projeto Spring Boot com Duas Conexões (Primário e Secundário)

Este projeto demonstra como configurar duas conexões de banco de dados usando Spring Boot com JPA e H2 em memória. Cada conexão possui sua própria entidade e persistence unit.

## Tecnologias

- Java 11
- Spring Boot 2.7.18
- Spring Data JPA
- HikariCP
- Banco H2 (em memória)
- Spring Web (REST)

## Endpoints

- `GET /api/testar`  
  Persiste um produto no banco primário e um log no banco secundário.

- `GET /api/produtos`  
  Lista os produtos persistidos no banco primário.

- `GET /api/logs`  
  Lista os logs persistidos no banco secundário.

## Configurações de banco (application.properties)

```properties
# Primário
spring.datasource.jdbc-url=jdbc:h2:mem:primario-db;DB_CLOSE_DELAY=-1
spring.datasource.username=sa
spring.datasource.driver-class-name=org.h2.Driver

# Secundário
secundario.datasource.jdbc-url=jdbc:h2:mem:secundario-db;DB_CLOSE_DELAY=-1
secundario.datasource.username=sa
secundario.datasource.driver-class-name=org.h2.Driver

# Console H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
