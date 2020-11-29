![Logo](https://i.imgur.com/HUchPtQ.png)
---
![Issues](https://img.shields.io/github/issues/Maowcraft/Dissension)
![Pull Requests](https://img.shields.io/github/issues-pr/Maowcraft/Dissension)
# Overview
Dissension is a Java library designed for use alongside [Discord4J](https://github.com/Discord4J/Discord4J). It offers common utilities that reduce boilerplate and increase overall code quality while still remaining simple for bot creators.
## Features
* Easily create commands.
* Clean tree-based arguments system.

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
    .setName("example")
    .setArgs(null)
    .setExecutor(((message, args) -> {
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
