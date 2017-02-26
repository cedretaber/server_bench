name := "scala_akkahttp"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.17",
  "com.typesafe.akka" %% "akka-http" % "10.0.4",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.4.11.1",
  "org.scalikejdbc" %% "scalikejdbc" % "2.5.0",
  "mysql" % "mysql-connector-java" % "6.0.5"
)

assemblyMergeStrategy in assembly := {
  case PathList("akka", "http", xs @ _*) => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
