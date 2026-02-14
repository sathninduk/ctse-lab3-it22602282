# Product Service (Spring Boot)

This project is a simple Product microservice built with Spring Boot that exposes CRUD REST APIs for managing products.

High-level plan
- Implement a `Product` entity (id, name, price).
- Persist using Spring Data JPA and H2 (in-memory) during runtime.
- Expose REST endpoints through a controller separated into packages (controller, service, repository).
- Document APIs with OpenAPI/Swagger UI.
- Configure the application context path to `/api`.

Project structure (key packages)
- `com.sliit.product_service.entity` - JPA entity classes (Product).
- `com.sliit.product_service.repository` - Spring Data JPA repositories (ProductRepository).
- `com.sliit.product_service.service` - Service interfaces and implementations.
- `com.sliit.product_service.controller` - REST controllers (ProductController).

Endpoints
- POST   /api/products        - Create a new product (JSON body: {"name":"...","price":123.45})
- GET    /api/products        - Get all products
- GET    /api/products/{id}   - Get a product by id
- DELETE /api/products/{id}   - Delete a product by id

H2 (in-memory) database
- H2 is used for runtime persistence so data lives only while the app is running.
- Typical `application.properties` settings used by the project:

  server.servlet.context-path=/api
  spring.datasource.url=jdbc:h2:mem:testdb
  spring.datasource.driverClassName=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=
  spring.jpa.hibernate.ddl-auto=update
  spring.h2.console.enabled=true

- H2 Console (after starting the app): http://localhost:8080/api/h2-console
  - JDBC URL: jdbc:h2:mem:testdb
  - User: sa
  - Password: (leave blank)

Swagger / OpenAPI
- The project uses springdoc-openapi to expose API docs.
- Swagger UI (after starting the app): http://localhost:8080/api/swagger-ui.html

Dependencies you should have in `pom.xml`
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- H2 Database
- Lombok (annotation-driven getters/setters/constructors)
- slf4j (logging) - usually provided by Spring Boot starter
- springdoc-openapi-ui (for Swagger)

Build & Run
- Requirements: Java 11+ (or the version configured for the project), Maven or the included wrapper.

From the project root:

- To build:

  ./mvnw clean package

- To run with the Maven Spring Boot plugin:

  ./mvnw spring-boot:run

- Or run the packaged jar:

  java -jar target/*.jar

Quick curl examples
- Create product:

  curl -X POST http://localhost:8080/api/products \
    -H "Content-Type: application/json" \
    -d '{"name":"Widget","price":9.99}'

- Get all products:

  curl http://localhost:8080/api/products

- Get by id:

  curl http://localhost:8080/api/products/1

- Delete:

  curl -X DELETE http://localhost:8080/api/products/1

Troubleshooting
- Common javac / Lombok issue:

  If you see an error like:

    java.lang.NoSuchFieldError: Class com.sun.tools.javac.tree.JCTree$JCImport does not have member field 'com.sun.tools.javac.tree.JCTree qualid'

  This typically means Lombok is incompatible with the JDK / compiler version being used (annotation-processing mismatch). Try the following:
  - Ensure your JAVA_HOME points to a supported JDK (11 or 17 recommended).
  - Update Lombok to the latest stable version in `pom.xml`.
  - Ensure annotation processing is enabled in your IDE and that the Lombok plugin (for IntelliJ) is installed and up-to-date.
  - Run a clean build: `./mvnw -U clean package` to force dependency refresh.

- H2 console not reachable:
  - Confirm `spring.h2.console.enabled=true` and note the context path: if `server.servlet.context-path=/api` is set, the H2 console will be under `/api/h2-console`.

- Swagger not found:
  - Confirm `springdoc-openapi-ui` is on the classpath and the app context path is considered (Swagger UI will be under `/api/swagger-ui.html`).

Notes / Next steps
- The repository is organized to keep controller, service, and repository responsibilities separated.
- Consider adding integration tests that spin up H2 and verify CRUD flows.

Requirements coverage
- Product entity (id, name, price): Done (project contains `entity/Product`).
- ProductRepository extending JpaRepository: Done (project contains `repository/ProductRepository`).
- ProductController with POST/GET/GET by ID/DELETE: Done (project contains `controller/ProductController`).
- Packages segregated: controller/service/repository present in the project.
- H2 configured and console enabled: See `application.properties` in `src/main/resources` (ensure it contains the H2 settings above).
- Swagger enabled with springdoc-openapi: add dependency if missing.

If you'd like, I can:
- Add sample integration tests that use H2.
- Verify and (if missing) add the required dependencies to `pom.xml` (Spring Boot starters, Lombok, H2, springdoc).
- Help fix the Lombok/javac NoSuchFieldError by updating `pom.xml` and project settings.


