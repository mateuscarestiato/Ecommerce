# Ecommerce API Documentation

Este documento descreve os endpoints disponíveis na API do projeto Ecommerce, incluindo exemplos de requisições para serem testados no Postman.

---

## **Cartão de Crédito (CartaoController)**

### **Criar Cartão de Crédito**
- **Endpoint:** `POST /credit_card/{id_user}`
- **Descrição:** Cria um cartão de crédito para o usuário especificado.
- **Body (JSON):**
```json
{
  "numero": "1234567812345678",
  "dtExpiracao": "2025-12-31T23:59:59",
  "cvv": "123",
  "saldo": 1000.00
}

### **Criar Endereço**
- **Endpoint:** `POST /address/{id_user}`
- **Descrição:** Cria um endereço para o usuário especificado.
- **Body (JSON):**
```json
{
  "logradouro": "Rua Exemplo",
  "complemento": "Apto 101",
  "bairro": "Centro",
  "cidade": "São Paulo",
  "estado": "SP",
  "cep": "01000-000"
}

### **Criar Produto**
- **Endpoint:** `POST /products`
- **Descrição:** Cria um novo produto.
- **Body (JSON):**
```json
{
  "productCategory": "Eletrônicos",
  "productName": "Smartphone",
  "price": 1999.99,
  "imageUrl": ["https://example.com/image1.jpg", "https://example.com/image2.jpg"],
  "productDescription": "Um smartphone de última geração."
}

### **Listar Todos os Usuários**
- **Endpoint:** `GET /users`
- **Descrição:** Retorna todos os usuários cadastrados.
- **Resposta de Sucesso (HTTP 200):**
```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "email": "joao.silva@example.com",
    "dtNascimento": "1990-01-01T00:00:00",
    "cpf": "12345678900",
    "telefone": "11999999999"
  },
  {
    "id": 2,
    "nome": "Maria Oliveira",
    "email": "maria.oliveira@example.com",
    "dtNascimento": "1985-05-15T00:00:00",
    "cpf": "98765432100",
    "telefone": "21988888888"
  }
] 
