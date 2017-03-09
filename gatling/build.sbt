name := "gatling"

version := "1.0"

scalaVersion := "2.11.8"

enablePlugins(GatlingPlugin)

libraryDependencies ++= Seq(
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.3" % "test,it",
  "io.gatling"            % "gatling-test-framework"    % "2.2.3" % "test,it"
)
