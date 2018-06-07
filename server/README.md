# GraphQLDemo (server)

## Setup / Requirements

Configure your IDE to use a Java 8 JDK and Maven Plugin. Import the Server as a Maven project.
Verify your setup by running `mvn test`. (Maven Wrapper is configured for project if you prfer `mvni`.)

The app generates AssertJ-assertions during build execution. Assertions can also be generated manually:
```
mvn assertj:generate-assertions
```

## Quickstart

Start the server by running:
```
mvn spring-boot:run 
```
or
```
mvn spring-boot:start 

```
Server is running on port 8100.


GraphiQL, an in-browser IDE for exploring the API is located at
<http://localhost:8100/graphiql>


Voyager, a visualization tool for GraphQL APIs is located at
<http://localhost:8100/voyager>


Open the the GraphiQL IDE and execute the following query
```
{
  employees {
    id,
    firstName,
    lastName,
    age,
    gender
  }
}
```

# Heroku
The app is deployed on Heroku:
Open [https://graphicademy.herokuapp.com/graphiql]


deploying to Heroku (requires explicit access):
```
mvn heroku:deploy
```


## TODOs
- custom Scalar Types: Date, Email
- mutations
- subscriptions
- conversion into different units
- https://graphql.org/learn/schema/#interfaces
- https://graphql.org/learn/schema/#union-types

