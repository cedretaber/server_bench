name := "scala_play"

version := "1.0"

scalaVersion := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "2.1.0-M1",
  "mysql" % "mysql-connector-java" % "6.0.5"
)
