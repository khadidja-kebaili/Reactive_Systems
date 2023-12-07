package util

import model.{Airport, Airports, Flight}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import akka.http.scaladsl.server.Directives
import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol.*

trait ModelJsonConverter extends DefaultJsonProtocol {
  implicit val airplaneFormat: RootJsonFormat[Flight] = jsonFormat4(Flight.apply)
  implicit val airportFormat: RootJsonFormat[Airport] = jsonFormat3(Airport.apply)
  implicit val airportsFormat: RootJsonFormat[Airports] = jsonFormat1(Airports.apply)

}
