# graph-ql-demo

## Repository structure

```md
/server -> GraphQL backend
/mobile  -> iOS client
/web   -> JS client
```

## Recommended GraphQL-related reading

- [GraphQL.org](http://graphql.org/)
  - Facebook's official GraphQL is obviously the primary resource for familiarizing yourself with the concepts behind GraphQL.
- [GraphQL vs Rest: Overview](https://philsturgeon.uk/api/2017/01/24/graphql-vs-rest-overview/)
  - Excellent comparison by Phil Sturgeon between REST and GraphQL which points out that many of GraphQL's stated features can also be achieved through proper usage of REST, but admits that GraphQL does excel for certain applications.
- [How to GraphQL](https://www.howtographql.com/)
  - A community-driven fullstack tutorial for GraphQL.

## Other GraphQL-related resources

- [graphql/graphql-js Star History](http://www.timqian.com/star-history/#graphql/graphql-js)
  - The [`graphql-js` repository](https://github.com/graphql/graphql-js) is the most popular public repo of [Facebook's `graphql` Github organization](https://github.com/graphql) in terms of stars. Its star history shows that GraphQL adoption (as measured in stars, an admittedly flawed metric) has been increasing at a surprisingly steady pace since the repo went live.
- [Spring Boot and GraphQL](http://www.baeldung.com/spring-graphql)
  - An excellent primer from Bealung for getting started with GraphQL and Spring Boot.


### research goals
- mutliple clients, client specific queries
- server side aggregation vs client side aggregation
- schema migration/evolution (e.g. Optionalität)
- mutations
- writing tests against GraphQL APIs

### stretch goals
- error handling: timeouts, request validation
- file upload and non JSON-MediaTypes
- pagination


## proposed schema
```
type Employee {
    id: ID!
    level: Level!
    project: Project!
    coworkers: [Employee]!
    phoneNumber: PhoneNumber
}
 
type Project {
    id: ID!
    cstLead: Employe!
    staff: [Employee]!
    profit: Int!
    rating: Int
    //more properties for comparison
}
 
type Query {
   getEmployee(id: ID): Employee
   getProject(id: ID): Project

   //read multiple projects
}
 

type Mutation {
   
   reassignEmployee(id: ID, newProject: ID): Employe!
}
```
