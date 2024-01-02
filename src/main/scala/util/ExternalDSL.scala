package util

import model.Flight
import scala.util.parsing.combinator._
import scala.concurrent.duration.{FiniteDuration, MILLISECONDS, MINUTES, SECONDS}
import java.time.{LocalTime, Duration}
import java.time.temporal.ChronoUnit

case class Ticket(vorname: String, nachname: String, von: String, nach: String, uhrzeit: String,
                  seat: String, gate: String, boardingzeit: String, boardingdauer: Long,
                  flightnumber: Long, airline: String)

object TimeDSL {
  implicit class RichLocalTime(time: LocalTime) {
    def -(other: LocalTime): Duration = java.time.Duration.between(other, time)
  }
}

object FlightDSL extends RegexParsers {
  def flight: Parser[Ticket] =
    Vorname ~ ";" ~ Nachname ~ ";" ~ Von ~ ";" ~ Nach ~ ";" ~ Uhrzeit  ~ ";" ~ Seat ~ ";" ~ Gate ~ ";" ~ Boardingzeit
       ~ ";" ~ Flugnummer ~ ";" ~ Airline ^^ {
         case vorname ~ ";" ~ nachname ~ ";" ~ von ~ ";" ~ nach ~ ";" ~ uhrzeit ~ ";" ~  seat ~ ";" ~ gate ~ ";" ~ boarding
         ~ ";" ~ flightnumber ~ ";" ~ airline =>
        val boardingdauer = Boardingdauer(uhrzeit, boarding)
        Ticket(vorname, nachname, von, nach, uhrzeit, seat, gate,  boarding, boardingdauer, flightnumber, airline)
    }

  def Vorname: Parser[String] = """[a-zA-Z]+""".r
  def Nachname: Parser[String] = """[a-zA-Z]+""".r
  def Von: Parser[String] = """[a-zA-Z]+""".r
  def Nach: Parser[String] = """[a-zA-Z]+""".r
  def Seat: Parser[String] = """[0-9A-Z]+""".r
  def Gate: Parser[String] = """[0-9A-Z]+""".r
  def Uhrzeit: Parser[String] = """\d{2}:\d{2}""".r
  def Boardingzeit: Parser[String] = """\d{2}:\d{2}""".r
  def Boardingdauer(uhrzeit: String, boardingzeit: String): Long = {
    import TimeDSL._

    val time1 = LocalTime.parse(uhrzeit)
    val time2 = LocalTime.parse(boardingzeit)
    val timeDifferenceInSeconds = (time1 - time2).toMinutes

    timeDifferenceInSeconds
  }
  def Flugnummer: Parser[Long] = """\d+""".r ^^ (_.toLong)
  def Airline: Parser[String] = """[a-zA-Z ]+""".r
  def parseFlight(input: String): Option[Ticket] = {
    parseAll(flight, input) match {
      case Success(result, _) => Some(result)
      case _ => None
    }
  }
}

object ExternalDSL extends App {
  val flightDSL = FlightDSL
  val input = "Ava;Taylor;Berlin;Athen;17:00;18B;3B;16:40;7752;Lufthansa"
  val output = flightDSL.parseFlight(input)
  println(output)
}