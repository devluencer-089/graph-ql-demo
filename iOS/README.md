# GraphQLDemo (iOS app)

## Installation

The app _should_ run out of the box (🤞). If it doesn't, ask @msewell for help.

The app uses CocoaPods for dependency management, so you should open the .xcworkspace file instead of the .xcproject.
The only dependency is [Apollo iOS](https://github.com/apollographql/apollo-ios) for the GraphQL stuff.

Run the app on the simulator to avoid code signing issues.

If you're using Xcode to edit .graphql files, you should probably install the [GraphQL syntax highlighting plugin](https://github.com/apollographql/xcode-graphql).

## Exercises

The app has some built-in flaws which you can try to rectify, if you want.
- The most obvious issue is that in the "Projects" view, the same project is listed multiple times. Think about what it takes to resolve this issue properly.
- There is some overfetching going on. See if you can spot and correct it.
- Try using [fragments](https://www.apollographql.com/docs/ios/fragments.html) as configuration objects for the used `UITableViewCell`s.
