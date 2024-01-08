package util

import model._

import java.time.LocalTime
import java.time.temporal.ChronoUnit
import scala.util.Random



object ApiServer{
  val airports = List("Berlin-Tegel", "München", "Dortmund", "Erfurt-Weimar", "Stuttgart")
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
  def airportGenerator(name: String): Airport = {
    val departures = List.fill(Random.nextInt(5))(airplaneGenerator)
    val arrivals = List.fill(Random.nextInt(5))(airplaneGenerator)
    Airport(name, arrivals, departures)
  }

  // Funktion welche für eine Liste an Flughäfen inkl. Flüge usw. zurückliefert
  def airportsGenerator(): AirportsWithData = {
    AirportsWithData(airports.map(airportGenerator))
  }
}
