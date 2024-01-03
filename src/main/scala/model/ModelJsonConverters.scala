package model

import spray.json._

class ModelJsonConverters extends DefaultJsonProtocol {
  implicit val airplaneFormat: RootJsonFormat[Airplane] = jsonFormat4(Airplane.apply)
  implicit val airportFormat: RootJsonFormat[Airport] = jsonFormat3(Airport.apply)
  implicit val airportsFormat: RootJsonFormat[Airports] = jsonFormat1(Airports.apply)
  implicit val airportsWithDataFormat: RootJsonFormat[AirportsWithData] = jsonFormat1(AirportsWithData.apply)
}
