# graph-ql-demo

## Repository structure

```md
/server -> GraphQL backend
/iOS  -> iOS client
/web   -> JS client
```

## Recommended GraphQL-related reading

- [GraphQL.org](http://graphql.org/)
  - Facebook's official GraphQL is obviously the primary resource for familiarizing yourself with the concepts behind GraphQL.
- [GraphQL vs Rest: Overview](https://philsturgeon.uk/api/2017/01/24/graphql-vs-rest-overview/)
  - Excellent comparison by Phil Sturgeon between REST and GraphQL which points out that many of GraphQL's stated features can also be achieved through proper usage of REST, but admits that GraphQL does excel for certain applications.
- [How to GraphQL](https://www.howtographql.com/)
  - A community-driven fullstack tutorial for GraphQL.

### Other GraphQL-related resources

- [Spring Boot and GraphQL](http://www.baeldung.com/spring-graphql)
  - An excellent primer from Bealung for getting started with GraphQL and Spring Boot.
- [GraphQL Faker](https://github.com/APIs-guru/graphql-faker) is a tool which lets you mock or extend GraphQL-based APIs. Very useful for playing around in the frontend.

## Research goals

- multiple clients, client-specific queries
- server-side aggregation vs. client-side aggregation
- schema migration/evolution (e.g. optionality)
- mutations
- writing tests against GraphQL APIs

### Stretch goals

- error handling: timeouts, request validation
- file upload and non JSON-MediaTypes
- pagination

## Starting the server

`mvn spring-boot:run`