# Web application

`node` and `yarn` must be installed.

To run the application:

```shell
npm install
yarn start
```

The web app will be available at <http://localhost:3200>.

## GraphQL using Apollo Client

Uses the Apollo GraphQL Framework according to <https://www.apollographql.com/docs/react/essentials/get-started.html>.

Apollo Boost is like Spring Boot. It allows us to use a preconfigured Apollo client.

## GraphQL Testing Issues

https://github.com/graphql/graphql-js/issues/1248
https://github.com/apollographql/graphql-tag/issues/155

That's why we have to pin graphql-js to 0.13.0 right now...
