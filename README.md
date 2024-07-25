# Start

Este projeto utiliza uma arquitetura distribuída composta por dois microsserviços: **Principal** e **Transaction**. A seguir, detalhes sobre as responsabilidades e tecnologias usadas em cada microsserviço.

### Microsserviço Principal

#### Responsabilidades:
- Manipulação dos dados do cliente.
- Autenticação e autorização.

### Microsserviço Transaction

#### Responsabilidades:
- Registro dos empréstimos dos clientes.

## Tecnologias:
- **Spring Boot 3.3.1**
- **Java 17**
- **Redis**
- **PostgreSQL**
- **MongoDb**
- **Flyway**
- **Token JWT**
- **Docker**
- **Kafka**
- **Swagger**
- **Maven**
- **Mapper**
- **JUnit**
- **Mockito**

### Camadas:
- **Controller**: Controladores REST para manipulação das requisições HTTP.
- **Service**: Lógica de negócios.
- **Config**: Configurações do projeto.
- **Repository**: Acesso aos dados e operações de banco de dados.
- **Model**: Modelos de dados.
- **Mapper**: Mapeamento de entidades.
- **Validation**: Validação de dados.

## Comunicação entre Microsserviços

Os microsserviços **Principal** e **Transaction** se comunicam de maneira assíncrona utilizando o Kafka, que garante a integração e a troca de mensagens entre eles.

## Ferramentas e Tecnologias

### Banco de Dados:
- **PostgreSQL**: Utilizado pelo Microsserviço Principal para armazenar dados do cliente.
- **MongoDB**: Utilizado pelo Microsserviço Transaction para armazenar dados dos empréstimos.

### Autenticação e Autorização:
- **Token JWT**: Usado para autenticação e autorização dos usuários no Microsserviço Principal.

### Controle de Versionamento do Banco de Dados:
- **Flyway**: Ferramenta para versionamento e migração de banco de dados no Microsserviço Principal.

### Monitoramento e Documentação:
- **Swagger**: Utilizado para documentação e testes das APIs.

### Cache e Performance:
- **Redis**: Utilizado para cache de dados e melhorar a performance das operações frequentes, reduzindo a carga no banco de dados e acelerando o acesso às informações.

### Ferramentas de Build e Dependências:
- **Maven**: Ferramenta de build e gerenciamento de dependências.

### Testes:
- **JUnit**: Framework de testes unitários.
- **Mockito**: Framework para criação de mocks nos testes.

## Como Executar o Projeto

### Pré-requisitos:
- Docker e Docker Compose instalados.
- Maven instalado.
- JDK 17 instalado.

### Passos para Execução:

1. **Clonar o repositório**:
   ```bash
   git clone https://github.com/MathBergamo/start.git
   cd start
   ```
2. **Compilar o projeto com Maven:**:
   ```bash
   mvn clean install
   ```
3. **Subir os containers Docker:**:
   ```bash
   docker-compose up
   ```
4. **Acessar a documentação no Swagger:**:
   ```bash
   http://localhost:8080/swagger-ui.html
   ```
