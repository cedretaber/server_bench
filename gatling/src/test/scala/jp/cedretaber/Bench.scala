package jp.cedretaber

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class Bench extends Simulation {
  val httpConf = http.baseURL("http://localhost:8080")

  val sce1 = scenario("PingPong")
      .repeat(5, "pongpong10") {
        exec(http("ping").get("/ping").check(substring("pong")))
      }

  val sce2 = scenario("factorial")
    .repeat(5, "factorial5") {
      exec(http("factorial").get("/fact?n=10").check(bodyString.saveAs("3628800")))
    }

  val sce3 = scenario("get user")
    .repeat(5, "getUser5") {
      exec(http("getUser").get("/users/1"))
    }

  setUp(
    sce1.inject(atOnceUsers(10)),
    sce2.inject(atOnceUsers(10)),
    sce3.inject(atOnceUsers(10))
  ).protocols(httpConf)
}
