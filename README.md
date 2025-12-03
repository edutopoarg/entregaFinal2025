# 🌱 Proyecto Huerta - Spring Boot

API REST para gestión de plantas, clientes y pedidos.

## Tecnologías
- Spring Boot 3.5.8
- Java 17
- JPA / Hibernate
- Base de datos H2
- Maven

## Endpoints

### Plantas
- `GET /plantas` - Listar todas
- `GET /plantas/{id}` - Obtener por ID
- `POST /plantas` - Crear una
- `POST /plantas/batch` - Crear múltiples
- `PUT /plantas/{id}` - Actualizar
- `DELETE /plantas/{id}` - Eliminar

### Clientes
- `GET /clientes` - Listar todos
- `POST /clientes/batch` - Crear múltiples
- ... (similar a plantas)

### Pedidos
- `GET /pedidos` - Listar todos
- `GET /pedidos/cliente/{id}` - Por cliente
- `GET /pedidos/planta/{id}` - Por planta
- `POST /pedidos/batch` - Crear múltiples

## Ejecutar
```bash
mvn spring-boot:run
```

Aplicación disponible en: `http://localhost:8080`

Consola H2: `http://localhost:8080/h2-console`
