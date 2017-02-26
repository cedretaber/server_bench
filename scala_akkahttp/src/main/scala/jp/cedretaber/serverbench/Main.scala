package jp.cedretaber.serverbench

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.stream.ActorMaterializer
import spray.json._
import scalikejdbc._

import scala.io.StdIn

case class User(id: Long, name: String, dept: String)
object Users extends SQLSyntaxSupport[User] {
  val u = syntax("u")

  override val tableName = "users"
  override val columnNames = "id" :: "name" :: "dept" :: Nil
  def apply(rs: WrappedResultSet) = User(
    rs.long(u.resultName.id), rs.string(u.resultName.name), rs.string(u.resultName.dept)
  )
}


object Main extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val userFormat = jsonFormat3(User.apply)

  val routes =
    get {
      path("ping") {
        complete {
          HttpEntity("pong")
        }
      } ~
        path("fact") {
          parameter('n.as[Int]) { n =>
            complete {
              HttpEntity((1 to n).product.toString)
            }
          }
        } ~
        path("users" / LongNumber) { userId =>
          DB.readOnly { implicit session =>
            withSQL.apply[User] {
              import Users.u
              selectFrom(Users as u)
                .where.eq(u.id, userId)
            }.map(Users.apply).single.apply()
          }.fold {
            complete(HttpResponse(StatusCodes.NotFound))
          } { user =>
            complete(user)
          }
        }
    }

  def main(args: Array[String]): Unit = {
    ConnectionPool.singleton("jdbc:mysql://localhost:3306/bench?useSSL=false", "root", "root")

    implicit val system = ActorSystem("server-bench")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val bindingFuture = Http().bindAndHandle(routes, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
