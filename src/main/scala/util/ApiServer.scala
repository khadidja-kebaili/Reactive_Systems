package util

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.server.Route
import model.*
import spray.json.*
import spray.json.DefaultJsonProtocol.listFormat

import java.time.LocalTime
import java.time.temporal.ChronoUnit
import scala.concurrent.ExecutionContext
import scala.io.StdIn.*
import scala.util.Random

// This is needed to convert between models and json
class ModelJsonConverters extends DefaultJsonProtocol {
  implicit val airplaneFormat: RootJsonFormat[Flight] = jsonFormat4(Flight.apply)
  implicit val airportFormat: RootJsonFormat[Airport] = jsonFormat3(Airport.apply)
  implicit val airportsFormat: RootJsonFormat[List[Airport]] = listFormat[Airport]
}

object ApiServer extends ModelJsonConverters with SprayJsonSupport {
  val airlines = List("Lufthansa", "Air Berlin", "Ryanair", "Emirates", "United Airlines")

  // Funktion zum Generieren eines zufälligen Flugzeugs
  def airplaneGenerator: Flight = {
    val airline = airlines(Random.nextInt(airlines.length))

    // Deklaration der Variablen mit initialen Werten
    val flightNumber: Long = Random.nextInt()
    val estimatedArrivalTime: LocalTime = LocalTime.parse(LocalTime.of(Random.nextInt(24), Random.nextInt(60)).toString)

    // Generiere zufällige Anzahl von Minuten oder Stunden
    val randomMinutes: Int = Random.nextInt(120) // Beispiel: Maximal 2 Stunden
    val randomHours: Int = Random.nextInt(4) // Beispiel: Maximal 4 Stunden

    // Berechne die Arrival Time durch Hinzufügen der zufälligen Minuten und Stunden
    val arrivalTime: LocalTime = estimatedArrivalTime
      .plus(randomMinutes, ChronoUnit.MINUTES)
      .plus(randomHours, ChronoUnit.HOURS)

    Flight(flightNumber, airline, estimatedArrivalTime.toString, arrivalTime.toString)
  }

  // Funktion zum Generieren eines zufälligen Flughafens mit einer bestimmten Anzahl von Abflügen bzw. Ankünften
  def airportGenerator(name: String): Airport = {
    val departures = List.fill(Random.nextInt(5))(airplaneGenerator)
    val arrivals = List.fill(Random.nextInt(5))(airplaneGenerator)
    Airport(name, arrivals, departures)
  }

  // Funktion zum Generieren eines zufälligen Flughafens mit einer bestimmten Anzahl von Abflügen bzw. Ankünften
  def airportsGenerator(): List[Airport] = {
    val airportList: List[Airport] = airlines.map(airportGenerator)
    airportList
  }

  // define api routes
  val routes: Route = pathPrefix("airport") {
    (get & path(Segment)) { name =>
      complete(airportGenerator(name))
    }
  } ~
    pathPrefix("airports") {
      (get) {
        complete(airportsGenerator())
      }
    }
}
