name := "scala-advanced-tutorial-concept"

version := "0.1"


scalaVersion := "2.11.2"

scalacOptions ++= Seq("-deprecation")

// https://mvnrepository.com/artifact/org.scalaz/scalaz-core_2.11
libraryDependencies += "org.scalaz" % "scalaz-core_2.11" % "7.3.0-M11"
libraryDependencies += "org.scala-lang" % "scala-parser-combinators" % "2.11.0-M4"

resolvers ++= Seq(
  "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots",
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Scala-Tools Snapshots" at "http://scala-tools.org/repo-snapshots",
  "Scala Tools Releases" at "http://scala-tools.org/repo-releases"
)
