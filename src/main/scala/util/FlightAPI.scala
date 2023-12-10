package util

import util.FlightServices

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.server.Route
import model.{Airport, Flight}
//import model.{Airport, Airports, Flight}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport.*
import akka.http.scaladsl.model.StatusCodes
import akka.Done
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}


object FlightAPI extends App {
  implicit val system: ActorSystem[_] = ActorSystem(Behaviors.empty, "FlightAPI")
  implicit val executionContext: ExecutionContext = system.executionContext

  var flightService = new FlightServices
  var flights = flightService.airplanesGenerator
  var airport = flightService.airportGenerator
  var airports = flightService.airportsGenerator()

  implicit val flightFormat: RootJsonFormat[Flight] = jsonFormat4(Flight.apply)
  implicit val flightsFormat: RootJsonFormat[List[Flight]] = listFormat[Flight]
  implicit val airportFormat: RootJsonFormat[Airport] = jsonFormat3(Airport.apply)
  implicit val airportsFormat: RootJsonFormat[List[Airport]] = listFormat[Airport]
  def getAllFlights: Future[List[Flight]] = Future.successful(flights)
  def getOneFlight(flightNumber: Long): Future[Option[Flight]] = Future {
    flights.find(f => f.flightNumber == flightNumber)
  }
  
  def getAllAirports: Future[List[Airport]] = Future.successful(airports)

  def getOneAirport(name: String): Future[Option[Airport]] = Future {
    airports.find( a=> a.name == name)
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
        },
      get {
        path("airports"){
          val futureAirports: Future[List[Airport]] = getAllAirports
          onComplete(futureAirports) { airports =>
            complete(airports)
          }
        }
      },
      get {
        pathPrefix("airport" / Segment){ name =>
            val futureAirport: Future[Option[Airport]] = getOneAirport(name)
            onComplete(futureAirport) { airport =>
              complete(airport)
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