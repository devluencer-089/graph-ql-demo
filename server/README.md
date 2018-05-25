# GraphQL Demo Server

## Quickstart
Server is running on port 8100.

GraphiQL In-Browser IDE for exploring the API is located at

<http://localhost:8100/graphiql>


Voyager, a visualization tool for GraphQL APIs is located at

<http://localhost:8100/voyager>


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
* Spring Boot 2.x
* H2 In-Memory Database
* Flyway for testdata
  * scripts located at resources/db/migration
* GraphQL-Spring-Boot-Starter
  * Schema-files: resources/*.graphqls
  * every *.graphqls file will be evaluated on the classpath

