package dal

import javax.inject.{Inject, Singleton}

import models.User
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  private class Users(tag: Tag) extends Table[User](tag, "users") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def dept = column[String]("dept")

    def * = (id, name, dept) <> ((User.apply _).tupled, User.unapply)
  }

  private val user = TableQuery[Users]

  def findBy(id: Long): Future[Option[User]] = db.run {
    user.filter { u => u.id === id }.take(1).result
  }.map(_.headOption)
}
