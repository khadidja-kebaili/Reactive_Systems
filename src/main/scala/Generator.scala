import scala.util.Random
import java.time.LocalTime
import scala.util.Random
import model.Airplane
import model.Airport
import java.time.temporal.ChronoUnit


object airlineGenerator {
  val airlines = List("Lufthansa", "Air Berlin", "Ryanair", "Emirates", "United Airlines")

  // Funktion zum Generieren eines zufälligen Flugzeugs
  def airplaneGenerator: Airplane = {
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
  def airportGenerator(name: String, numberOfFlights: Int): Airport = {
    val departures = List.fill(numberOfFlights)(airplaneGenerator)
    val arrivals = List.fill(numberOfFlights)(airplaneGenerator)
    Airport(name, arrivals, departures )
  }
}

@main
def generator(airport: String): Unit = {
  // Beispiel: Generiere 50 zufällige Abflüge / Ankünfte für zufällig selektierte Airlines

  val randomAirport = airlineGenerator.airportGenerator(airport, 50)

  // Ausgabe der generierten Daten
  println(s"Flughafen: ${randomAirport.name}")
  println("Abflüge:")
  randomAirport.arrivals.foreach { flug =>
    println(s"Fluglinie: ${flug.airlineName} Flugnummer: ${flug.flightNumber} geplante Ankunft: ${flug.estimatedArrivalTime} " +
      s"tatsächliche Ankunft: ${flug.arrivalTime}")
  }
}
