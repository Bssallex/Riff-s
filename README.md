# 🎸 Aluguel de Instrumentos Musicais

API REST desenvolvida para gerenciar o aluguel de instrumentos musicais.  
Permite que usuários realizem cadastro, consultem o catálogo de instrumentos disponíveis e efetuem aluguéis.  
Ao confirmar o aluguel, o sistema envia ao usuário todas as informações essenciais — como data de devolução, instrumento escolhido e demais detalhes da locação.

---

## 📊 Tecnologias e Ferramentas

- **Java 17**
- **Spring Boot**
- **Arquitetura de Microserviços**
- **Lombok**
- **DTOs**
- **Maven**
- **Docker-PostgreSQL**
- **Flyway**
- **RabbitMQ** (mensageria entre serviços)
- **Exceções personalizadas**
- **SMTP** (envio de emails)
- **Validation**
- **Jackson-Datatype**

---

## 🚀 Funcionalidades

### 👤 Usuários (User Service)
- 🔍 Listar todos os usuários  
- 🔍 Buscar usuário por ID  
- 📝 Criar novo usuário  
- ✏️ Atualizar dados do usuário  
- 🗑️ Deletar usuário  

---

### 🎸 Instrumentos (Instrument Service)
- 🔍 Listar todos os instrumentos  
- 🔍 Buscar instrumento por ID  
- 📝 Cadastrar novo instrumento  
- ✏️ Atualizar dados do instrumento  
- 🗑️ Deletar instrumento  

---

### 📦 Aluguéis (Rentals Service)
- 🔍 Listar todos os aluguéis  
- 🔍 Buscar aluguel pelo nome do usuário  
- 📝 Criar um novo aluguel  
  - Ao criar um aluguel, o serviço envia uma mensagem para o serviço de email via RabbitMQ

---

### ✉️ Serviço de Email (Email Service)
- 📩 Recebe os dados do aluguel através do RabbitMQ  
- 📤 Envia um email de confirmação ao usuário contendo:
  - Instrumento alugado  
  - Data do aluguel  
  - Data de devolução  
  - Outras informações relevantes 


