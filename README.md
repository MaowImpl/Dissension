![Logo](https://i.imgur.com/HUchPtQ.png)
---
![Issues](https://img.shields.io/github/issues/Maowcraft/Dissension)
![Pull Requests](https://img.shields.io/github/issues-pr/Maowcraft/Dissension)
# Overview
Dissension is a Java library designed for use alongside [Discord4J](https://github.com/Discord4J/Discord4J). It offers common utilities that reduce boilerplate and increase overall code quality while still remaining simple for bot creators.
## Features
* Easily create commands.
* Clean tree-based arguments system.
* Simple reaction emoji listeners.

(More coming soon, this library is still in development ATM)
## Examples
**Setup**
```java
Bot bot = new Bot();
bot.start();
```
**Commands**
```java
new Command.Builder()
    .setPrefix("ex!")
    .setName("example")
    .setArgs(null)
    .setExecutor(((event, args) -> {
        System.out.println(Arrays.toString(args));
    }))
    .build();
```
**Arguments**
```java
Args args = Args.start(
    Args.optional(),
    Args.branch(
        Args.arg("list")
    ),
    Args.branch(
        Args.arg("add"),
        Args.anon()
    ),
    Args.branch(
        Args.arg("remove"),
        Args.anon()
    )
);
```
**Reaction Listeners**
```java
new ReactionListener(true, message,
    new ReactionHandler(
        ReactionEmoji.unicode("\uD83D\uDC4D"),
        event -> System.out.println(":thumbsup: added.")
    ),
    new ReactionHandler(...)
);
```
