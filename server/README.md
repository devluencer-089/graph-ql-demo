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

## TODOs
- custom Scalar Types: Date, Email
- mutations
- subscriptions
- conversion into different units
- https://graphql.org/learn/schema/#interfaces
- https://graphql.org/learn/schema/#union-types

