# Pos Electricity App

### How to run
1. Having a JDK with a minimal version 17
2. Having MySQL with version 8
3. Import/execute sql dump script from package `dbdump/db-dumb.sql` to the database
4. Adjust database config on file `src/main/resources/application.properties`. Set properties `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password`
5. Run app by command (bash) `./mvnw spring-boot:run`
6. Open the Swagger UI by accessing this url: `http://localhost:8080/swagger-ui/index.html#/`
