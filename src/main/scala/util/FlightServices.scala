/*
package util

import model.{Airport, Airports, Flight}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import akka.http.scaladsl.server.Directives
import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol.*

import util.ModelJsonConverter
class FlightServices extends App with SprayJsonSupport with ModelJsonConverter  {
  //implicit val flightFormat: RootJsonFormat[Flight] = jsonFormat4(Flight.apply)

  var flight_I: Flight = Flight("F001", "Air Algerie", "19:00", "19:10")
  var flight_II: Flight = Flight("F002", "Air Algerie", "19:00", "19:10")
  var flight_III: Flight = Flight("F003", "Air Algerie", "19:00", "19:10")


  val flights: List[Flight] = List(flight_I, flight_II, flight_III)
  
  def getAllFlights: Future[List[Flight]] = Future.successful(flights)

  def getOneFlight: Flight = {
    flight_I
  }


}
*/
