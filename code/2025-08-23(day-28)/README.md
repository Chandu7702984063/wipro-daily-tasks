# BookMyFood - Full Green Stack (Eureka, Gateway, User, Food, Angular)

This bundle includes:
- Eureka Server (8761)
- API Gateway (8080)
- User Service (MySQL + JPA + JWT) (8082)
- Food Service (MySQL + JPA) (8081)
- Angular frontend (http://localhost:4200)

## Prerequisites
- Java 17, Maven 3.9+, Node 18+, Angular CLI (for frontend)
- Docker (optional) to run MySQL via docker-compose (recommended)

## Start MySQL with Docker (recommended)
From project root:
```
docker-compose up -d
```
This starts MySQL with root password `pass` on port 3306.

## Run services (order)
1. Start Eureka:
```
cd eureka-server
mvn -DskipTests clean spring-boot:run
# open http://localhost:8761
```

2. Start User Service (creates / connects to database `bookmyfood_users`):
```
cd ../user-service
mvn -DskipTests clean spring-boot:run
```

3. Start Food Service (creates / connects to database `bookmyfood_foods`):
```
cd ../food-service
mvn -DskipTests clean spring-boot:run
```

4. Start API Gateway:
```
cd ../api-gateway
mvn -DskipTests clean spring-boot:run
```

5. Start frontend:
```
cd ../frontend
npm install
npm start
```

## Test
- Register a user:
```
curl -X POST http://localhost:8080/auth/register -H "Content-Type: application/json" -d '{"username":"user","password":"password"}'
```
- Login and get token:
```
curl -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d '{"username":"user","password":"password"}'
```
- Use token to call protected endpoints (gateway will forward):
```
TOKEN=<paste token>
curl http://localhost:8080/users -H "Authorization: Bearer $TOKEN"
```
- List foods (no auth required):
```
curl http://localhost:8080/food
```

## Notes
- JWT secret is hardcoded in `user-service` JwtUtil; replace with secure secret in production.
- Databases auto-create when using MySQL docker-compose. If you use local MySQL, create DBs: `bookmyfood_users`, `bookmyfood_foods`.
- Gateway routes:
  - `/auth/**`, `/users/**` -> user-service
  - `/food/**` -> food-service
