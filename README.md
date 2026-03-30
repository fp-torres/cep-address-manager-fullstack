<div align="center">

```
 ██████╗███████╗██████╗      █████╗ ██████╗ ██████╗ ██████╗ ███████╗███████╗███████╗
██╔════╝██╔════╝██╔══██╗    ██╔══██╗██╔══██╗██╔══██╗██╔══██╗██╔════╝██╔════╝██╔════╝
██║     █████╗  ██████╔╝    ███████║██║  ██║██║  ██║██████╔╝█████╗  ███████╗███████╗
██║     ██╔══╝  ██╔═══╝     ██╔══██║██║  ██║██║  ██║██╔══██╗██╔══╝  ╚════██║╚════██║
╚██████╗███████╗██║         ██║  ██║██████╔╝██████╔╝██║  ██║███████╗███████║███████║
 ╚═════╝╚══════╝╚═╝         ╚═╝  ╚═╝╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝

                        M A N A G E R  —  F U L L S T A C K
```

### Gerenciamento de Usuários com Busca Automática de CEP

<p>
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Boot-4-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" />
  <img src="https://img.shields.io/badge/PostgreSQL-15-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" />
  <img src="https://img.shields.io/badge/React-18-61DAFB?style=for-the-badge&logo=react&logoColor=black" />
  <img src="https://img.shields.io/badge/Axios-HTTP-5A29E4?style=for-the-badge&logo=axios&logoColor=white" />
</p>

<p>
  <img src="https://img.shields.io/badge/ViaCEP-integrado-success?style=flat-square" />
  <img src="https://img.shields.io/badge/RESTful_API-documentada-blue?style=flat-square" />
  <img src="https://img.shields.io/badge/Bean_Validation-Jakarta-orange?style=flat-square" />
  <img src="https://img.shields.io/badge/CORS-configurado-informational?style=flat-square" />
  <img src="https://img.shields.io/badge/licença-técnico-lightgrey?style=flat-square" />
</p>

<br/>


> Aplicação fullstack desenvolvida como **desafio técnico para vaga Full-Stack**. Demonstra arquitetura limpa em camadas, validação robusta, integração com API externa (ViaCEP), tratamento global de erros e frontend moderno com React 18.

<br/>

[📖 Como Rodar](#️-como-executar-o-projeto) · [🔌 API Docs](#-endpoints-da-api) · [🏗️ Arquitetura](#️-arquitetura-do-sistema) · [🐛 Reportar Bug](https://github.com/fp-torres/cep-address-manager-fullstack/issues)

-----

</div>

<br/>

## 📸 Preview

<div align="center">

|🖥️ Listagem de Usuários                         |📝 Formulário de Cadastro                    |
|:---------------------------------------------:|:------------------------------------------:|
|*Cards com dados completos de endereço e ações*|*Inputs com máscara automática de CPF e CEP*|


> 📌 Adicione capturas de tela da aplicação rodando para maximizar o impacto visual no repositório!

</div>

<br/>

-----

## 🌟 O Que Este Projeto Demonstra

```
✦ Arquitetura em camadas (Controller → Service → Repository → Entity)
✦ Integração com API externa (ViaCEP) de forma transparente ao usuário
✦ Validação robusta com Bean Validation (Jakarta) no backend
✦ Tratamento global de erros com @RestControllerAdvice
✦ DTOs desacoplando a camada de API da camada de persistência
✦ Validação de unicidade de CPF com resposta HTTP 409 Conflict
✦ Mascaramento manual de CPF e CEP compatível com React 18
✦ Frontend responsivo com feedback visual de sucesso e erro
```

<br/>

-----

## 🛠️ Stack Tecnológico

### 🔹 Backend

<div align="center">

|Tecnologia             |Versão     |Papel                                             |
|:----------------------|:---------:|:-------------------------------------------------|
|Java                   |`17`       |Linguagem principal                               |
|Spring Boot            |`4`        |Framework web e IoC                               |
|Spring Data JPA        |`latest`   |Abstração de persistência                         |
|Jakarta Validation     |`latest`   |Bean Validation (`@NotBlank`, `@Pattern`, `@Size`)|
|PostgreSQL             |`15+`      |Banco de dados relacional                         |
|ViaCEP                 |API pública|Busca automática de endereço por CEP              |
|`@RestControllerAdvice`|—          |Tratamento global de exceções                     |

</div>

### 🔹 Frontend

<div align="center">

|Tecnologia          |Versão  |Papel                                    |
|:-------------------|:------:|:----------------------------------------|
|React               |`18`    |Biblioteca de interface                  |
|Axios               |`latest`|Cliente HTTP para consumo da API         |
|CSS puro            |—       |Estilização responsiva e moderna         |
|Input Masking manual|—       |Formatação de CPF e CEP sem libs externas|

</div>

<br/>

-----

## 🏗️ Arquitetura do Sistema

```
┌──────────────────────────────────────────────────────────────────┐
│                        FRONTEND (React 18)                       │
│   Formulário ──► Máscara CPF/CEP ──► Axios ──► Visual Feedback  │
└─────────────────────────────┬────────────────────────────────────┘
                              │ HTTP (REST)
                              ▼
┌──────────────────────────────────────────────────────────────────┐
│                   BACKEND (Spring Boot 4)                        │
│                                                                  │
│  Controller ──► Service ──► Repository ──► PostgreSQL            │
│      │              │                                            │
│      │         ViaCEP API (busca endereço por CEP)              │
│      │                                                           │
│  @RestControllerAdvice (tratamento global de erros)             │
│  Bean Validation (CPF, CEP, campos obrigatórios)                │
└──────────────────────────────────────────────────────────────────┘
```

### Fluxo de Criação de Usuário

```
1. Frontend envia nome + CPF + CEP
       ↓
2. Backend valida os campos (Bean Validation)
       ↓
3. Backend consulta ViaCEP → obtém logradouro, bairro, cidade, estado
       ↓
4. Backend persiste usuário completo no PostgreSQL
       ↓
5. Frontend exibe feedback visual de sucesso e atualiza a lista
```

<br/>

-----

## 📂 Estrutura do Projeto

```
cep-address-manager-fullstack/
│
├── 📁 backend/
│   ├── 📁 controller/          # Endpoints REST — recebe e responde requisições
│   ├── 📁 service/             # Regras de negócio + integração ViaCEP
│   ├── 📁 repository/          # Interfaces JPA — acesso ao banco de dados
│   ├── 📁 entity/              # Entidades JPA mapeadas para o PostgreSQL
│   ├── 📁 dto/                 # Data Transfer Objects — desacoplamento de camadas
│   ├── 📁 exception/           # GlobalExceptionHandler + exceções customizadas
│   ├── 📁 config/              # Configuração de CORS
│   └── application.properties # Configurações de datasource e JPA
│
├── 📁 frontend/
│   ├── 📁 src/
│   │   ├── App.js              # Componente raiz + estado global + chamadas Axios
│   │   ├── App.css             # Estilização completa — layout, cards, formulário
│   │   └── index.js            # Entry point React
│   └── package.json
│
└── README.md
```

<br/>

-----

## ⚙️ Como Executar o Projeto

### Pré-requisitos

- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/) (ou use o wrapper `./mvnw` incluso)
- [Node.js](https://nodejs.org/) `>= 18` + npm
- [PostgreSQL](https://www.postgresql.org/) rodando localmente

-----

### Passo 1 — Clone o Repositório

```bash
git clone https://github.com/fp-torres/cep-address-manager-fullstack.git
cd cep-address-manager-fullstack
```

-----

### Passo 2 — Configure o Banco de Dados

Conecte ao PostgreSQL e crie o banco:

```sql
CREATE DATABASE cep_address_manager_fullstack;
```

Edite `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cep_address_manager_fullstack
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

-----

### Passo 3 — Inicie o Backend

```bash
cd backend
./mvnw spring-boot:run
```

> 🟢 API disponível em **`http://localhost:8080`**

-----

### Passo 4 — Inicie o Frontend

```bash
cd frontend
npm install
npm start
```

> 🌐 Aplicação disponível em **`http://localhost:3000`**

<br/>

-----

## 🔌 Endpoints da API

### Usuários

|Método  |Endpoint        |Descrição                                    |
|:------:|:---------------|:--------------------------------------------|
|`POST`  |`/usuarios`     |Cria novo usuário (busca CEP automaticamente)|
|`GET`   |`/usuarios`     |Lista todos os usuários cadastrados          |
|`PUT`   |`/usuarios/{id}`|Atualiza dados de um usuário existente       |
|`DELETE`|`/usuarios/{id}`|Remove um usuário pelo ID                    |

-----

### 📥 Exemplo — Criar Usuário

**Request:**

```json
POST /usuarios

{
  "nome": "Felipe Torres",
  "cpf": "12345678901",
  "cep": "20040002"
}
```

**Response `201 Created`:**

```json
{
  "id": 1,
  "nome": "Felipe Torres",
  "cpf": "12345678901",
  "cep": "20040002",
  "logradouro": "Rua da Assembleia",
  "bairro": "Centro",
  "cidade": "Rio de Janeiro",
  "estado": "RJ"
}
```

> O endereço completo é retornado automaticamente após consulta à API ViaCEP — **zero esforço do usuário**.

-----

### ❗ Códigos de Erro

|Cenário                  |Status HTTP                |
|:------------------------|:-------------------------:|
|Campo inválido ou ausente|`400 Bad Request`          |
|CPF duplicado no sistema |`409 Conflict`             |
|Usuário não encontrado   |`404 Not Found`            |
|Erro interno do servidor |`500 Internal Server Error`|

<br/>

-----

## ✅ Regras de Validação

|Campo   |Regra                                                                |
|:-------|:--------------------------------------------------------------------|
|**Nome**|Obrigatório · Entre 3 e 100 caracteres                               |
|**CPF** |Obrigatório · Exatamente 11 dígitos numéricos · Único no sistema     |
|**CEP** |Obrigatório · Exatamente 8 dígitos numéricos · Deve existir no ViaCEP|

<br/>

-----

## 🎨 Funcionalidades da Interface

- **🎨 Layout com gradiente moderno** e design baseado em cards
- **🔢 Máscara automática** de CPF (`000.000.000-00`) e CEP (`00000-000`) durante a digitação
- **✅ Feedback visual** de sucesso após operações (criar, editar, deletar)
- **❌ Exibição de erros** de validação com mensagens claras
- **🗑️ Confirmação de exclusão** via diálogo antes de deletar
- **📱 Layout responsivo** — funciona em qualquer tamanho de tela

<br/>

-----

## 🗺️ Melhorias Futuras

- [ ] 🔜 Edição de usuário completa no frontend
- [ ] 🔜 Busca automática de CEP enquanto o usuário digita (debounce)
- [ ] 🔜 Notificações toast no lugar de alertas inline
- [ ] 🔜 Loading skeletons e animações de transição
- [ ] 🔜 Camada de autenticação (Spring Security + JWT)
- [ ] 🔜 Paginação e busca/filtro na listagem
- [ ] 🔜 Deploy automatizado (Render + Vercel)
- [ ] 🔜 Testes unitários e de integração (JUnit 5 + Mockito)
- [ ] 🔜 Migração do frontend para TypeScript + Vite

<br/>

-----

## 👨‍💻 Autor

<div align="center">

**Felipe Torres** — Full-Stack Developer

[![GitHub](https://img.shields.io/badge/GitHub-fp--torres-181717?style=for-the-badge&logo=github)](https://github.com/fp-torres)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Felipe_Torres-0A66C2?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/seu-perfil)

</div>

<br/>

-----

## 📄 Licença

Projeto desenvolvido para **fins de avaliação técnica**. Consulte o arquivo [`LICENSE`](LICENSE) para mais detalhes.

<br/>

-----

<div align="center">

**Java · Spring Boot · PostgreSQL · React · ViaCEP**

<br/>

⭐ **Se este projeto te impressionou, deixa uma estrela!** ⭐

</div>