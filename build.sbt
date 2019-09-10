name := "omnibus"
version := "0.1"
scalaVersion := "2.13.0"

lazy val akkaHttpVersion = "10.1.9"
lazy val akkaVersion     = "2.5.25"

enablePlugins(CodegenPlugin)

lazy val root = (project in file(".")).
  settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml"        % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"          % akkaVersion,
      
      "com.typesafe.slick" %% "slick"               % "3.3.2",
      "com.typesafe.slick" %% "slick-codegen"       % "3.3.2",
      "org.slf4j" % "slf4j-nop" % "1.7.10",
      

      "net.codingwell" %% "scala-guice"             % "4.2.6",
      "com.h2database" % "h2"                       % "1.4.187",
      "com.typesafe.slick" %% "slick-hikaricp"      % "3.3.2",
      "mysql" % "mysql-connector-java"              % "8.0.17",
      "com.github.t3hnar" %% "scala-bcrypt" % "4.1",
      
      "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test,
      "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion     % Test,
      "org.scalatest"     %% "scalatest"            % "3.0.8"         % Test
    )
  )

