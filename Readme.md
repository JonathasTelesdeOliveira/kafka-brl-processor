# 🚀 uploadBRL - Gateway de Transações Cripto - 

Microsserviço desenvolvido em **Spring Boot 3** para processamento, consulta e mensageria de transações financeiras e criptoativos (Binance), atuando na integração com tópicos **Apache Kafka** e banco de dados relacional.
Recebo dados do kafaka da api que tem a responsabilidade de ler arquivos em EXEL, XLSX ou csv 
---

## 📋 Sumário
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura e Fluxo de Dados](#-arquitetura-e-fluxo-de-dados)
- [Configuração do Ambiente (`application.properties`)](#-configuração-do-ambiente-applicationproperties)
- [Endpoints da API](#-endpoints-da-api)
- [Estrutura do Evento Kafka (`TransacaoKafkaDTO`)](#-estrutura-do-evento-kafka-transacaokafkadto)
- [Como Executar o Projeto](#-como-executar-o-projeto)

---

## 🛠️ Tecnologias Utilizadas

- **Java:** `21`
- **Spring Boot / Spring Cloud:** `3.x` / `2025.1.3`
- **Spring Data JPA & Hibernate:** Persistência de dados
- **Spring Kafka:** Processamento e mensageria distribuída
- **Spring Cloud OpenFeign:** Comunicação declarativa entre microsserviços
- **Banco de Dados:** MySQL (`mysql-connector-j`) / SQL Server (`mssql-jdbc`)
- **Documentação:** OpenAPI 3 / Swagger UI (`springdoc-openapi`)
- **Utilitários:** Lombok, Jackson (com módulo JSR310 para suporte a `LocalDateTime`), Hibernate Validator

---

## 📌 Arquitetura e Fluxo de Dados

1. **Entrada / Consumo:** A aplicação lê e recupera registros de transações de criptomoedas via Kafka / Banco de dados.
2. **Exposição REST:** Através do `GtwayController`, disponibiliza endpoints para consulta do extrato completo de transações.
3. **Disparo de Mensagens:** Por meio do `DisparadortemplateAgrupamento`, os lotes de transações processadas são enviados para o tópico Kafka **`transacoes-processadas`**.

---

## ⚙️ Configuração do Ambiente (`application.properties`)

Certifique-se de configurar o arquivo `src/main/resources/application.properties` antes de rodar a aplicação:

```properties
spring.application.name=uploadBRL

# =========================
# Banco de dados (MySQL)
# =========================
spring.datasource.url=jdbc:mysql://localhost:3306/db_transacao?createDatabaseIfNotExist=true
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.datasource.hikari.initializationFailTimeout=0
spring.datasource.hikari.connectionTimeout=30000

# =========================
# Servidor
# =========================
server.port=8082

# =========================
# Swagger / OpenAPI
# =========================
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/api-docs

# =========================
# JPA / Hibernate
# =========================
spring.jpa.open-in-view=false
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.globally_quoted_identifiers=true

# =========================
# Docker
# =========================
spring.docker.compose.enabled=false

# =========================
# Apache Kafka
# =========================
spring.kafka.bootstrap-servers=localhost:9092
logging.level.org.springframework.kafka=DEBUG

# Consumer Config
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
```

---

## 📡 Endpoints da API

A documentação interativa fica disponível via **Swagger UI** após iniciar a aplicação:
👉 **URL:** `http://localhost:8082/swagger-ui.html`

### Binance / Carteira Cripto

| Método | Endpoint | Descrição | Respostas Esperadas |
| :--- | :--- | :--- | :--- |
| `GET` | `/extrato-transacoes` | Retorna a lista completa de transações cadastradas no banco de dados. | `200 OK`<br>`400 Bad Request`<br>`409 Conflict`<br>`500 Internal Server Error` |

---

## 📦 Estrutura do Evento Kafka (`TransacaoKafkaDTO`)

Abaixo está o contrato de dados transmitido no tópico Kafka `transacoes-processadas`:

```json
{
  "id": 1,
  "dataSped": "2026-03-30T10:15:30",
  "tradeId": 100234,
  "symbol": "BTCUSDT",
  "lado": "COMPRA",
  "quantycoinExe": 0.00500000,
  "quantityDol": 320.50,
  "symbol_quantity_Dol": "USDT",
  "price": 64100.00,
  "taxa": 0.000005,
  "symbol_taxa": "BTC",
  "valorBrl": 1826.85,
  "dataSpedUSDTBRL": "2026-03-30T10:15:30"
}
```

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- **Java 21** ou superior
- **Maven** instalado
- Instância do **MySQL** ativa na porta `3306`
- **Apache Kafka** ativo na porta `9092`

### Passos
1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/uploadBRL.git
   ```
2. Acesse a pasta do projeto:
   ```bash
   cd uploadBRL
   ```
3. Realize o build e baixe as dependências:
   ```bash
   mvn clean install
   ```
4. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```