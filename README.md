# Aplicacion "ms-store" **(Microservicios Store)** : Hecha con Spring Boot & Spring Cloud

Aplicacion hecha en arquitectura de microservicios, realizado en un curso de Digital Academy Lab.

Tecnologias utilizadas:
- Java
- Spring Boot
- Gradle
- H2
- Hibernate
- JPA
- Api Gateway
- Circuit Breaker
- Hystrix
- Sleuth
- Eureka
- Feign
- Admin-Actuator
- Config-Server
- POSTMAN
- Testing Unitario (JUnit y Mockito)

## Servicios

**Config-Service**

'http://localhost:8081/service-shopping/default'

**Discovery-Service (Eureka)**

http://localhost:8099

**Gateway-Service**

Customer
http://localhost:8080/customers

Product
http://localhost:8080/products

Invoices
http://localhost:8080/invoices/1

## Microservicios

**Customer**

GET

http://localhost:8092/customers

POST

'--data-raw '{
    "numberID": "32404590",
    "firstName": "Axel",
    "lastName": "Cespedes",
    "email": "adcespedes@argentina.com",
    "photoUrl": "",
    "region": {
        "id": 2,
        "name": "Centroamerica"
    }
}''

**Product**

GET

http://localhost:8091/products

POST

http://localhost:8091/products

--data-raw '{
    "name": "Libro del Rey Tigre",
    "description": "Demasiado bueno el Libro",
    "stock": 70,
    "price": 1000,
    "category": {
        "id": 2,
        "name": "books"
    }
}'

**Shopping**

GET

http://localhost:8093/invoices

POST

http://localhost:8093/invoices

--data-raw '{
    "numberInvoice": "002",
        "description": "Invoice store",
        "customerId": 2,
        "items": [
            {
                "quantity": 1,
                "price": 178.89,
                "productId": 1
            },
            {
                "quantity": 2,
                "price": 40.06,
                "productId": 3
            }
        ]
}'

