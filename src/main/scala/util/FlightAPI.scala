package util

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.server.Route
//import model.{Airport, Airports, Flight}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport.*
import akka.http.scaladsl.model.StatusCodes
import util.SprayJsonExample.system
import akka.Done
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}


object FlightAPI extends App {
  implicit val system: ActorSystem[_] = ActorSystem(Behaviors.empty, "FlightAPI")
  implicit val executionContext: ExecutionContext = system.executionContext

  final case class Flight(flightNumber: Long, airlineName: String, dep: String, arr: String)

  var flight_I: Flight = Flight(1, "Lufthansa", "08:10", "09:20")
  var flight_II: Flight = Flight(2, "Air Algerie", "19:00", "19:00")
  var flight_III: Flight = Flight(3, "Air France", "11:40", "11:43")
  var flights: List[Flight] = List(flight_I, flight_II, flight_III)

  implicit val flightFormat: RootJsonFormat[Flight] = jsonFormat4(Flight.apply)
  implicit val flightsFormat: RootJsonFormat[List[Flight]] = listFormat[Flight]
  def getAllFlights: Future[List[Flight]] = Future.successful(flights)
  def getOneFlight(flightNumber: Long): Future[Option[Flight]] = Future {
    flights.find(f => f.flightNumber == flightNumber)
  }

  val route: Route =
    concat(
          get {
            pathPrefix("flight" / LongNumber ) { flightNumber =>
              val futureFlights: Future[Option[Flight]] = getOneFlight(flightNumber)
              onComplete(futureFlights) { flight =>
                complete(flight)
              }
            }
          },
          get {
            path("flights") {
            val futureFlights: Future[List[Flight]] = getAllFlights
            onComplete(futureFlights) { flights =>
              complete(flights)
            }
          }
        }
          )

  val server = Http().newServerAt("localhost", 4040).bind(route)
  server.map { _ =>
    println("Successfully started on localhost:4040 ")
  } recover { case ex =>
    println("Failed to start the server due to: " + ex.getMessage)
  }
}