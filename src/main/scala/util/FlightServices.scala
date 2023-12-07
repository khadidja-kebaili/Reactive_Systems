package util

import model.Flight

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import akka.http.scaladsl.server.Directives
import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol.*

class FlightServices extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val flightFormat: RootJsonFormat[Flight] = jsonFormat4(Flight.apply)

  var flights: List[Flight] = List(Flight("F001", "Air Algerie", "19:00", "19:10"))
  def getAllFlights: Future[List[Flight]] = Future.successful(flights)


}
