# SQLite Slick

[SQLite](http://www.sqlite.org/) is a self-contained and serverless SQL database engine. I've put together an example of how to use it in scala using [Slick](http://slick.typesafe.com/).

## Technology
+ SBT 0.13
+ Scala 2.10.3
+ Java 1.7.0_25

## Run
With SBT on the path:

```
sbt run
```

## More

When writing code I like to make the program as self contained as possible, which is especially useful when writing test cases. However, the goal can be difficult to reach when a database it required.

Normally when using a JDBC driver you connect to a database that has already been created (at least all examples I've seen do this). Using slick you can connect to the database engine and issue SQL to create the database. In my example below I have MySQL already setup on my Mac with a user *root*.

```scala
import scala.slick.session.Database
import scala.slick.jdbc.StaticQuery
import Database.threadLocalSession

object CreateDatabase {
  def main(args: Array[String]) {
    Database.forURL("jdbc:mysql://localhost:3306/", driver = "com.mysql.jdbc.Driver", user = "root") withSession {
      StaticQuery.updateNA("CREATE DATABASE `databaseName`").execute
    }
  }
}
```

And in my `build.sbt`:

```scala
libraryDependencies ++= List(
  "com.typesafe.slick" %% "slick" % "1.0.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "mysql" % "mysql-connector-java" % "5.1.23"
)
```

Code possible thanks to [Răzvan Panda](http://stackoverflow.com/questions/20247742/slick-create-database).
