# GraphQL Demo Server

## Quickstart
Server is running on port 8100.

GraphiQL In-Browser IDE for exploring the API is located at

<http://localhost:8100/graphiql>


Voyager, a visualization tool for GraphQL APIs is located at

<http://localhost:8100/voyager>


Open the the GraphiQL IDE and execute the following query
```
employees {
    id,
    firstName,
    lastName,
    age,
    gender
  }
```


Run app locally:
```
mvn spring-boot:run 

```
or
```
mvn spring-boot:start 

```


Deploy to Heroku:
```
mvn heroku:deploy

```
Open [https://graphicademy.herokuapp.com/graphiql]

## Technologies Inside
* Spring Boot 2.x
* GraphQL-Spring-Boot-Starter
  * Schema-files: resources/*.graphqls
  * every *.graphqls file will be evaluated on the classpath

