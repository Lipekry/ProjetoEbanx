# ProjectEbanx
API com operações simples de saque, depósito, transferência e consulta de saldo de contas bancárias.

EndPoints:

- GET /balance?account_id=1234
Objetivo: Realizar a consulta de saldo em uma conta específica.
Body: {} 

- POST /event
Objetivo: EndPoint para realizar as operações de saque, depósito e transferência de acordo com o atributo type.
Body: 
    Examples: [
        {"type":"deposit", "destination":"100", "amount":20}
        {"type":"withdraw", "origin":"100", "amount":5}
        {"type":"deposit", "destination":"300", "amount":0}
        {"type":"transfer", "origin":"100", "amount":15, "destination":"300"}
    ]   

- POST /reset
Objetivo: Limpar todos os dados cadastrados.
Body: {}