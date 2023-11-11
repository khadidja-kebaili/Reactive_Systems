import scala.util.Random
import java.time.LocalTime
import scala.util.Random
import model._
import java.time.temporal.ChronoUnit
import akka.actor.ActorSystem
import akka.actor.typed.javadsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.*
import akka.stream._
import spray.json.*
import scala.concurrent.ExecutionContext
import scala.io.StdIn._

// This is needed to convert between models and json
trait AirportJsonProtocol extends DefaultJsonProtocol {
  implicit val airplaneFormat: RootJsonFormat[Airplane] = jsonFormat4(Airplane.apply)
  implicit val airportFormat: RootJsonFormat[Airport] = jsonFormat3(Airport.apply)
  implicit val airportsFormat: RootJsonFormat[Airports] = jsonFormat1(Airports.apply)
}

object Generator extends AirportJsonProtocol with SprayJsonSupport {
  private val airlines = List("Lufthansa", "Air Berlin", "Ryanair", "Emirates", "United Airlines")

  // Funktion zum Generieren eines zufälligen Flugzeugs
  private def airplaneGenerator: Airplane = {
    val airline = airlines(Random.nextInt(airlines.length))

    // Deklaration der Variablen mit initialen Werten
    val flightNumber: String = "XYZ" + Random.nextInt().toString
    val estimatedArrivalTime: LocalTime = LocalTime.parse(LocalTime.of(Random.nextInt(24), Random.nextInt(60)).toString)

    // Generiere zufällige Anzahl von Minuten oder Stunden
    val randomMinutes: Int = Random.nextInt(120) // Beispiel: Maximal 2 Stunden
    val randomHours: Int = Random.nextInt(4) // Beispiel: Maximal 4 Stunden

    // Berechne die Arrival Time durch Hinzufügen der zufälligen Minuten und Stunden
    val arrivalTime: LocalTime = estimatedArrivalTime
      .plus(randomMinutes, ChronoUnit.MINUTES)
      .plus(randomHours, ChronoUnit.HOURS)

    Airplane(airline, flightNumber, estimatedArrivalTime.toString, arrivalTime.toString)
  }

  // Funktion zum Generieren eines zufälligen Flughafens mit einer bestimmten Anzahl von Abflügen bzw. Ankünften
  private def airportGenerator(name: String): Airport = {
    val departures = List.fill(Random.nextInt(5))(airplaneGenerator)
    val arrivals = List.fill(Random.nextInt(5))(airplaneGenerator)
    Airport(name, arrivals, departures)
  }

  // define api routes
  private val routes: Route = pathPrefix("airport") {
    (get) {
      complete(Airports(airlines.map((name) => airportGenerator(name))))
    } ~
      (get & path(Segment)) { name =>
        complete(airportGenerator(name))
      }
  }

  @main
  def mainGenerator(args: String*): Unit = {
    implicit val system: ActorSystem = ActorSystem("Generator")
    implicit val ec: ExecutionContext = system.dispatcher
    val serverFuture = Http().newServerAt("localhost", 8081).bind(routes)
    println("Generator online at http://localhost:8081/\nPress ENTER to stop...")
    readLine()
    serverFuture
      .flatMap(_.unbind())
      .onComplete(_ => {
        system.terminate()
        println("Generator shutting down...")
      })
  }
}
