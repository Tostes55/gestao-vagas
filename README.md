# 💼 Gestão de Vagas

Sistema backend desenvolvido em **Java** para gerenciamento de vagas de emprego, permitindo o cadastro de empresas, candidatos e controle de oportunidades disponíveis.

---

## 📌 Sobre o projeto

O **Gestão de Vagas** é uma aplicação backend que simula um sistema de recrutamento.

A ideia é permitir que:

- Empresas cadastrem vagas
- Candidatos visualizem oportunidades
- (Futuramente) candidatos se candidatem às vagas

O projeto segue boas práticas de organização, utilizando Maven e uma estrutura padrão de aplicações Java.

> 🚧 **Status:** Em desenvolvimento

---

## 🚀 Tecnologias utilizadas

- ☕ Java
- 🧩 Maven
- 🌱 Spring Boot (estrutura baseada)
- 🐙 Git e GitHub

---

## 🧠 Objetivo do projeto

Este projeto foi desenvolvido com o objetivo de:

- Praticar desenvolvimento backend com Java
- Implementar APIs REST
- Aplicar conceitos de arquitetura de software
- Simular um sistema real de recrutamento
- Evoluir como projeto de portfólio

---

## 📂 Estrutura do projeto

```bash
gestao-vagas/
│
├── src/                # Código fonte da aplicação
│
├── .mvn/              # Configurações do Maven Wrapper
│
├── pom.xml            # Gerenciador de dependências
│
├── mvnw               # Script Maven (Linux/Mac)
├── mvnw.cmd           # Script Maven (Windows)
│
├── .gitignore
├── .gitattributes
│
└── README.md          # Documentação do projeto
```

## ⚙️ Como executar o projeto
###  ✅ Pré-requisitos

Java 17 ou superior
Maven (ou usar o wrapper incluído)

## 📌 Funcionalidades
### ✅ Já implementado

Estrutura inicial do projeto

Configuração com Maven

Base para API REST

### 🚧 Em desenvolvimento

Cadastro de empresas

Cadastro de candidatos

Criação de vagas

Listagem de vagas


### 🔮 Futuro

- Aplicação em vagas
- Autenticação (JWT)
- Integração com banco de dados
- Paginação e filtros
- Upload de currículo

---

## 🔗 Endpoints (em breve)

```http
GET     /vagas
POST    /vagas
GET     /empresas
POST    /candidatos