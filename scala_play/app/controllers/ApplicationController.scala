package controllers

import javax.inject.{Inject, Singleton}

import dal.UserRepository
import models.User
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, Controller}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ApplicationController @Inject()(repo: UserRepository)(implicit ec: ExecutionContext)
  extends Controller{

  def ping: Action[AnyContent] = Action.async { implicit req =>
    Future { Ok("pong") }
  }

  def fact(n: Int): Action[AnyContent] = Action.async { implicit req =>
    Future {
      Ok((1 to n).product.toString)
    }
  }

  private implicit val userFormat = Json.format[User]

  def user(id: Long): Action[AnyContent] = Action.async { implicit req =>
    repo.findBy(id).map {
      case Some(user) => Ok(Json.toJson(user))
      case None => NotFound
    }
  }
}
