# API de Gestão de Tarefas

## 📌 Sobre a API
Esta API RESTful permite o gerenciamento de tarefas, possibilitando a criação, listagem, atualização e remoção de tarefas.
A API foi desenvolvida utilizando **Spring Boot** e segue boas práticas como o uso de **DTOs** e **HATEOAS** para facilitar a navegação entre recursos.

## 🛠️ Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 3.4.4**
  - Spring Web
  - Spring Data JPA
  - Spring HATEOAS
- **PostgreSQL** (rodando via Docker)
- **Content Negotiation** para suportar JSON/XML
- **Flyway** (para migrations)
- **Lombok** (para reduzir boilerplate de código)
- **Swagger (OpenAPI)** (para documentação da API)
- **Docker** (para containerização do banco de dados e execução da API)
- **Maven** (para gestão de dependências)

## 🚀 Como Rodar o Projeto
### **1️⃣ Rodando o Banco de Dados (PostgreSQL) no Docker**
Antes de iniciar a API, suba o container do PostgreSQL:
```sh
docker run --name postgres-tarefas -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=tarefas_db -p 5432:5432 -d postgres
```

### **2️⃣ Configurando o `application.properties`**
No arquivo `src/main/resources/application.properties`, configure a conexão com o banco de dados:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tarefas_db
spring.datasource.username=admin
spring.datasource.password=admin
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.hateoas.use-hal-as-default=true
```

### **3️⃣ Rodando a API no Docker**
Se quiser rodar a API diretamente via Docker, siga os passos:
```sh
docker build -t api-tarefas .
docker run -p 8080:8080 --name api-tarefas --link postgres-tarefas api-tarefas
```

Caso prefira rodar localmente sem Docker:
```sh
mvn spring-boot:run
```

## 📑 Endpoints da API
### 📌 **Criar uma Tarefa**
**POST** `/tarefas`
```json
{
  "titulo": "Estudar Spring Boot",
  "descricao": "Aprender sobre Spring Boot e APIs RESTful",
  "completa": false
}
```
**Resposta:**
```json
{
  "id": 1,
  "titulo": "Estudar Spring Boot",
  "descricao": "Aprender sobre Spring Boot e APIs RESTful",
  "completa": false,
  "_links": {
    "self": { "href": "http://localhost:8080/tarefas/1" },
    "all-tasks": { "href": "http://localhost:8080/tarefas" }
  }
}
```

### 📌 **Listar todas as Tarefas**
**GET** `/tarefas`

### 📌 **Buscar uma Tarefa por ID**
**GET** `/tarefas/{id}`

### 📌 **Atualizar uma Tarefa**
**PUT** `/tarefas/{id}`
```json
{
  "titulo": "Estudar Docker",
  "descricao": "Aprender sobre conteinerização e Docker Compose",
  "completa": true
}
```

### 📌 **Deletar uma Tarefa**
**DELETE** `/tarefas/{id}`

## 📄 Documentação com Swagger
A API conta com documentação interativa gerada pelo Swagger. Acesse no navegador:
```
http://localhost:8080/swagger-ui/index.html
```

## 🔗 HATEOAS e Navegação entre Recursos
A API implementa **HATEOAS** para facilitar a navegação entre os recursos. Exemplo de resposta:
```json
{
  "id": 1,
  "titulo": "Estudar Spring Boot",
  "descricao": "Aprender sobre Spring Boot e APIs RESTful",
  "completa": false,
  "links": [
            {
                "rel": "listar",
                "href": "http://localhost:8080/tarefas",
                "type": "GET"
            },
            {
                "rel": "listarPorId",
                "href": "http://localhost:8080/tarefas/1",
                "type": "GET"
            },
            {
                "rel": "salvar",
                "href": "http://localhost:8080/tarefas",
                "type": "POST"
            },
            {
                "rel": "atualizar",
                "href": "http://localhost:8080/tarefas/1",
                "type": "PUT"
            },
            {
                "rel": "deletar",
                "href": "http://localhost:8080/tarefas/1",
                "type": "DELETE"
            }
        ]
}
```

## 🏗️ Migrations com Flyway
As migrations são gerenciadas pelo **Flyway**, garantindo que o banco esteja sempre atualizado.
Arquivos SQL de migração ficam em `src/main/resources/db/migration`.

## 🤝 Contribuição
Sinta-se à vontade para sugerir melhorias ou abrir PRs!
