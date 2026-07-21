# Gerenciador de Tarefas

API REST desenvolvida em Java utilizando Spring Boot para gerenciamento de projetos e tarefas.

O projeto foi desenvolvido seguindo princípios de Domain-Driven Design (DDD), separando responsabilidades entre as camadas de domínio, 
aplicação, infraestrutura e API.

---

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT (JSON Web Token)
- Maven
- H2 Database
- Swagger / OpenAPI

---

## Arquitetura

O projeto foi organizado seguindo uma arquitetura em camadas inspirada em DDD.

```
src/main/java
api
  controller
  dto
  exception
  mapper

application
  auth
  projeto
  tarefa
  usuario

domain
  exception
  projeto
  tarefa
  usuario
  
infrastructure
  configuration
  exception
  persistence
  security
```

Cada camada possui uma responsabilidade específica:

- **Domain**: regras de negócio e entidades.
- **Application**: orquestração dos casos de uso.
- **Infrastructure**: persistência, segurança e integrações.
- **API**: controllers, DTOs e documentação.

---

# Funcionalidades

## Usuários

- Criar usuário
- Buscar usuário
- Listar usuários
- Remover usuário

---

## Autenticação

- Login utilizando email e senha
- Geração de Token JWT
- Proteção dos endpoints utilizando Spring Security

---

## Projetos

- Criar projeto
- Buscar projeto
- Adicionar membro
- Remover membro
- Gerar relatório

---

## Tarefas

- Criar tarefa
- Alterar prioridade
- Atribuir responsável
- Iniciar tarefa
- Finalizar tarefa

---

# Regras de negócio implementadas

- Não é permitido adicionar o mesmo usuário duas vezes ao projeto.
- Apenas membros do projeto podem ser responsáveis por tarefas.
- Apenas o responsável pode iniciar uma tarefa.
- Apenas o responsável pode finalizar uma tarefa.
- Uma tarefa precisa possuir responsável antes de ser iniciada.
- A mudança de status segue o fluxo:

```
TODO
 ↓
IN_PROGRESS
 ↓
DONE
```

- O relatório apresenta a quantidade de tarefas por status e prioridade.

---

# Segurança

A API utiliza autenticação baseada em JWT.

Fluxo:

```
Criar usuário
        ↓
Realizar login
        ↓
Receber Token JWT
        ↓
Enviar Authorization: Bearer <token>
```

Endpoints públicos:

```
POST /usuarios

POST /auth/login

/swagger-ui/**

/v3/api-docs/**

/h2-console/**
```

Todos os demais endpoints exigem autenticação.

---

# Como executar

## Clonar o projeto

```bash
git clone https://github.com/andersonstella/gerenciador-tarefas.git
```

---

## Executar

```bash
mvn spring-boot:run
```

ou

```bash
./mvnw spring-boot:run
```

---

## Swagger

Após iniciar a aplicação:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Banco H2

```
http://localhost:8080/h2-console
```

Exemplo:

```
JDBC URL: jdbc:h2:file:./data/gerenciadorTarefas
User: sa
Password:
```

---

# Autenticação

Exemplo de login

```
POST /auth/login
```

```json
{
    "email": "joao@email.com",
    "senha": "123456"
}
```

Resposta

```json
{
    "token":"eyJhbGc..."
}
```

Utilizar o token:

```
Authorization: Bearer eyJhbGc...
```

---

# Decisões de Arquitetura

Durante o desenvolvimento foram adotadas algumas práticas para manter o domínio desacoplado da infraestrutura:

- Separação entre Domain, Application, Infrastructure e API.
- Entidades de domínio independentes do JPA.
- Uso de Mappers para conversão entre domínio e persistência.
- Repositórios definidos na camada de aplicação e implementados na infraestrutura.
- Regras de negócio concentradas no domínio.
- Autenticação baseada em JWT utilizando Spring Security.

# Evolução
- Refresh token
- Teste de integração
- Listagem com filtro e ordenação
- Busca textual
---

# Testes

A API está documentada utilizando **Swagger/OpenAPI**, permitindo que todos os endpoints sejam testados diretamente pelo navegador.

Após iniciar a aplicação, acesse: http://localhost:8080/swagger-ui/index.html


Para testar os endpoints protegidos:

1. Crie um usuário.
2. Realize o login em **`POST /auth/login`**.
3. Copie o token JWT retornado.
4. Clique no botão **Authorize** no Swagger.
5. Informe o token no formato:

Após a autenticação, todos os endpoints protegidos poderão ser executados diretamente pela interface do Swagger.

# Fluxo de Teste

A sequência abaixo representa o fluxo recomendado para validação completa da API.
1. Criar Usuário
2. Realizar Login
3. Autorizar no Swagger
4. Criar Projeto
5. Adicionar Membro
6. Criar Tarefa
7. Atribuir Responsável
8. Iniciar Tarefa
9. Alterar Prioridade
10. Finalizar Tarefa
11. Consultar Projeto
12. Gerar Relatório

## Observações

- Crie pelo menos um usuário antes de realizar o login.
- Todos os endpoints, exceto os públicos, exigem um token JWT válido.
- O usuário informado como responsável pela tarefa deve ser membro do projeto.
- Apenas o responsável pela tarefa pode iniciá-la e finalizá-la.
- O relatório do projeto apresenta a quantidade de tarefas por status e prioridade.
---
