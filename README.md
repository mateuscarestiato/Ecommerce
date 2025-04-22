# IBMEC-MALL: E-commerce com Autorização de Cartão e Gestão de Produtos

Projeto de e-commerce desenvolvido para a disciplina de Big Data e Cloud Computing, com funcionalidades que incluem cadastro de usuários, produtos, endereços e gerenciamento de cartões de crédito com autorização de compras.

## Membros do Grupo
- **Mateus Padilha**  
- **Ian Esteves**  
- **Brenda Mendes**

## Objetivos do Projeto

O objetivo principal do IBMEC-MALL é desenvolver uma aplicação de e-commerce moderna, escalável e baseada em nuvem que permita:

- A compra de produtos por usuários com cartão de crédito validado e saldo controlado.
- A administração de produtos e usuários por meio de uma API RESTful.
- O armazenamento eficiente de dados utilizando bancos relacionais e não relacionais.
- A automação de deploy e testes para garantir confiabilidade e agilidade no desenvolvimento.
- A geração de relatórios de vendas com integração a um pipeline simplificado de Big Data.
- A criação de um ambiente completo que simula um sistema de vendas real, com funcionalidades completas de cadastro, compra e análise de dados.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3
- Banco NoSQL (CosmosDB)
- Deploy em Azure App Service
- Swagger UI para testes


## Principais Endpoints

### Usuários
`POST /users` – Cadastro de novo usuário  
```json
{
  "nome": "Mateus Padilha",
  "email": "mateuspcdaniel@gmail.com",
  "dtNascimento": "2025-04-11T02:37:46.136Z",
  "cpf": "12345678900",
  "telefone": "21997537491"
}
```

---

### Produtos
`POST /products` – Cadastro de produto  
```json
{
  "productCategory": "Videogames",
  "productName": "PlayStation 5",
  "price": 3000,
  "imageUrl": [
    "https://http2.mlstatic.com/D_NQ_NP_2X_970771-MLA73347645092_122023-F.webp"
  ],
  "productDescription": "Console Game"
}
```

---

### Cartão de Crédito
`POST /credit_card/{id_user}` – Cadastro de cartão  
```json
{
  "numero": "4000123456789010",
  "dtExpiracao": "2027-04-11T03:02:45.999Z",
  "cvv": "123",
  "saldo": 5000
}
```

`POST /credit_card/{id_user}/authorize` – Autorização de valor  
```json
{
  "numero": "4000123456789010",
  "dtExpiracao": "2027-04-11T03:09:43.421Z",
  "cvv": "123",
  "valor": 2000
}
```

---

### Endereço
`POST /address/{id_user}` – Cadastro de endereço  
```json
{
  "logradouro": "Rua Monteiro Lobato 21",
  "complemento": "701",
  "bairro": "Laranjeiras",
  "cidade": "Rio de Janeiro",
  "estado": "Rio de Janeiro",
  "cep": "22030-100"
}
```

---

## Execução do Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/mateuscarestiato/Ecommerce.git
   ```

2. Configure o arquivo `application.properties` com os dados de conexão do banco.

3. Execute o projeto com Maven:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse a API através do Swagger UI:
   ```
   http://localhost:8081/swagger-ui/index.html
   ```

