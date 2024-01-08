package controller

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, HttpResponse, StatusCodes}
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success, Try}


object AirportController {
  implicit val system: ActorSystem = ActorSystem("Client")
  implicit val ec: ExecutionContext = system.dispatcher
  val BASE_URL = "https://localhost:8081"
  val API_KEY = "YOUR-API-KEY"

  def getAirports(): List[String] = {
    val future = Http(system).singleRequest(
      HttpRequest(
        method = HttpMethods.GET,
        uri = s"$BASE_URL/airports"
      )
    )
    // TODO: do something with the future
    return List("Stuttgart", "Berlin", "München", "Frankfurt", "Bremen", "Hanover", "Hamburg")
  }
}
