# GraphQL Demo Server

## Quickstart
Server is running on port 8100.

GraphiQL In-Browser IDE for exploring test queries is located at
```
http://localhost:8100/graphiql
```

A test-query should be present, if not you can try this one:
```
{
  version
  movie {
    id
    title
    description
  }
}
```

## Technologies Inside
* Spring Boot 2.0.0
* H2 In-Memory Database
* Flyway for testdata
  * scripts located at resources/db/migration
* GraphQL-Spring-Boot-Starter
  * Schema-files: resources/*.graphqls
  * every *.graphqls file will be evaluated on the classpath

