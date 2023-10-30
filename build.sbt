ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

val AkkaVersion = "2.8.5"

lazy val root = (project in file("."))
  .settings(
    name := "Reactive_Systems",
    coverageEnabled := true
  )
  .settings(
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.17",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17" % "test",
    libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.2.0",
    libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
    libraryDependencies += "com.typesafe.akka" %% "akka-stream" % AkkaVersion
  )
