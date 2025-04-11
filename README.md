# IBMEC-MALL: E-commerce com Autorização de Cartão e Gestão de Produtos

Projeto de e-commerce desenvolvido para a disciplina de Computação em Nuvem, com funcionalidades que incluem cadastro de usuários, produtos, endereços e gerenciamento de cartões de crédito com autorização de compras.

## Membros do Grupo
- **Mateus Padilha**  
- **Ian Esteves**  
- **Brenda Mendes**

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

Produtos

POST /products – Cadastro de produto

{
  "productCategory": "Videogames",
  "productName": "PlayStation 5",
  "price": 3000,
  "imageUrl": [
    "https://http2.mlstatic.com/D_NQ_NP_2X_970771-MLA73347645092_122023-F.webp"
  ],
  "productDescription": "Console Game"
}

Cartão de Crédito

POST /credit_card/{id_user} – Cadastro de cartão

{
  "numero": "4000123456789010",
  "dtExpiracao": "2027-04-11T03:02:45.999Z",
  "cvv": "123",
  "saldo": 5000
}

POST /credit_card/{id_user}/authorize – Autorização de valor

{
  "numero": "4000123456789010",
  "dtExpiracao": "2027-04-11T03:09:43.421Z",
  "cvv": "123",
  "valor": 2000
}

Endereço

POST /address/{id_user} – Cadastro de endereço

{
  "logradouro": "Rua Monteiro Lobato 21",
  "complemento": "701",
  "bairro": "Laranjeiras",
  "cidade": "Rio de Janeiro",
  "estado": "Rio de Janeiro",
  "cep": "22030-100"
}

Execução do Projeto

1. Clone o repositório


2. Configure o application.properties com os dados do banco


3. Execute com Maven:

mvn spring-boot:run


4. Acesse a API:

http://localhost:8080/swagger-ui/index.html


