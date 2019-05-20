# Quarkus Simple Rest Crud
A simple project to test quarkus rest features

## Create command

```
mvn io.quarkus:quarkus-maven-plugin:0.15.0:create \
    -DprojectGroupId=org.acme.quarkus.sample \
    -DprojectArtifactId=crud-sample \
    -DclassName="org.acme.quarkus.sample.PessoasResource" \
    -Dpath="/pessoa" \
    -Dextensions="resteasy-jsonb,quarkus-smallrye-openapi,quarkus-swagger-ui"
```
## Running the application

```
mvn compile quarkus:dev
```

## Accessing

* Application will be accessible on http://localhost:8080
* Swagger UI will be accessible on http://localhost:8080/swagger-ui
