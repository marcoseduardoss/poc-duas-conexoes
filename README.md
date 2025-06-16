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

### Banco Primário

- `GET /api/primario`  
  Persiste um produto aleatório no banco primário (entidade `Produto`).  
  > **Observação:** o ideal é mudar para `POST` no futuro.

- `GET /api/produtos`  
  Lista os produtos do banco primário.

### Banco Secundário

- `GET /api/secundario`  
  Persiste um log aleatório no banco secundário (entidade `Log`).  
  > **Observação:** o ideal é mudar para `POST` no futuro.

- `GET /api/logs`  
  Lista os logs do banco secundário.

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
```

## Como executar
```
Execute a aplicação com mvn spring-boot:run ou pela sua IDE.
Teste os endpoints em http://localhost:8080/api
Acesse o console do H2 em:
http://localhost:8080/h2-console
```

## JDBC URLs no console:
```
Banco primário: jdbc:h2:mem:primario-db
Banco secundário: jdbc:h2:mem:secundario-db
Username: sa
Sem senha
```
