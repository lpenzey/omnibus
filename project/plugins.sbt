addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1")
addSbtPlugin("com.github.tototoshi" % "sbt-slick-codegen" % "1.4.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.12")

libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.17"
resolvers += "Flyway" at "https://flywaydb.org/repo"