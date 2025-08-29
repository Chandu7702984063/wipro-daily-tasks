# Mini E-Commerce Backend (Spring Boot Microservices)
Services:
- eureka-server (8761)
- api-gateway (8080)
- user-service (8082)
- product-service (8083)
- order-service (8084)

Update MySQL passwords in each `application.properties` before running.

Run order:
1) eureka-server
2) user-service, product-service, order-service
3) api-gateway

Swagger:
- http://localhost:8082/swagger-ui/index.html
- http://localhost:8083/swagger-ui/index.html
- http://localhost:8084/swagger-ui/index.html
