package util

import akka.actor.ActorSystem
import akka.actor.Status.*
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.*
import model.Flight
import util.FlightServices
import util.*

object FlightAPI extends App {
  implicit val system: ActorSystem = ActorSystem("FlightAPI")
  val flightService = new FlightServices()

  val route = path("flights") {
    get {
      onComplete(flightService.getAllFlights) {
        case Success(res) => complete(res)
        case Failure(ex) =>
          println(s"Request failed with: ${ex.getMessage}")
          complete(StatusCodes.InternalServerError)
      }
    }
  }

  val server = Http().newServerAt("localhost", 4040).bind(route)
  server.map { _ =>
    println("Successfully started on localhost:4040 ")
  } recover { case ex =>
    println("Failed to start the server due to: " + ex.getMessage)
  }
}