ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

val AkkaVersion = "2.8.5"
val AkkaHttpVersion = "10.5.0"

lazy val root = (project in file("."))
  .settings(
    name := "Reactive_Systems",
    coverageEnabled := true,
    coverageExcludedPackages := "<empty>;AirportData.*;frontend\\.*"
  )
  .settings(
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.17",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17" % "test",
    libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.2.0",
    libraryDependencies += "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
    libraryDependencies += "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion,
    libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
    libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
    libraryDependencies += "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
    libraryDependencies += "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion,
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.4.11" % Runtime
  )

