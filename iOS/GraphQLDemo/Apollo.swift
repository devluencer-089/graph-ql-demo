import Apollo

/// The shared apollo client instance.
let apollo: ApolloClient = {
    let client = ApolloClient(url: URL(string: "https://graphicademy.herokuapp.com/graphql")!)
    client.cacheKeyForObject = { $0["id"] }
    
    return client
}()
