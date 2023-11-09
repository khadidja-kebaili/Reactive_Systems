import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, HttpMethods}
import akka.stream.ActorMaterializer

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}


object AkkaHttp {
  val BASE_URL = "https://airlabs.co/api/v9"
  val API_KEY = "YOUR-API-KEY"

  def getAirportArrival(airportCode: String, actorSystem: ActorSystem): Future[HttpResponse] = {
    Http(actorSystem).singleRequest(
      HttpRequest(
        method = HttpMethods.GET,
        uri = s"$BASE_URL/schedules?arr_iata=$airportCode&api_key=$API_KEY"
      ))
  }


  implicit val system: ActorSystem = ActorSystem("akka-http-client")
  implicit val ec: ExecutionContext = system.dispatcher

  val responseFuture: Future[HttpResponse] = getAirportArrival("MUC", system)

  //Handle the response
  responseFuture.onComplete {
    case Success(res) => {
      // do something
      println("received response!")
      system.terminate()
    }
    case Failure(ex) => {
      println(s"Failed to make the request: ${ex.getMessage}")
      system.terminate()
    }
  }
}
