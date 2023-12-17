package util

import model.Flight
import scala.util.parsing.combinator._

case class Ticket(vorname: String, nachname: String, von: String, nach: String, uhrzeit: String,
                  seat: String, gate: String, boardingzeit: String, flightnumber: Long, airline: String)

object FlightDSL extends RegexParsers {
  def flight: Parser[Ticket] =
    Vorname ~ ";" ~ Nachname ~ ";" ~ Von ~ ";" ~ Nach ~ ";" ~ Uhrzeit  ~ ";" ~ Seat ~ ";" ~ Gate ~ ";" ~ Boardingzeit
      ~ ";" ~ Flugnummer ~ ";" ~ Airline ^^ {
      case vorname ~ ";" ~ nachname ~ ";" ~ von ~ ";" ~ nach ~ ";" ~ uhrzeit ~ ";" ~  seat ~ ";" ~ gate ~ ";" ~ boarding
      ~ ";" ~ flightnumber ~ ";" ~ airline =>
        Ticket(vorname, nachname, von, nach, uhrzeit, seat, gate,  boarding, flightnumber, airline)
    }

  def Vorname: Parser[String] = """[a-zA-Z]+""".r
  def Nachname: Parser[String] = """[a-zA-Z]+""".r
  def Von: Parser[String] = """[a-zA-Z]+""".r
  def Nach: Parser[String] = """[a-zA-Z]+""".r
  def Seat: Parser[String] = """[0-9A-Z]+""".r
  def Gate: Parser[String] = """[0-9A-Z]+""".r
  def Uhrzeit: Parser[String] = """\d{2}:\d{2}""".r
  def Boardingzeit: Parser[String] = """\d{2}:\d{2}""".r
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
  val sth = FlightDSL
  val input = "Liam;Johnson;Berlin;Paris;14:45;1A;2D;14:25;1234;Air Berlin"
  val sth_else = sth.parseFlight(input)
  println(sth_else)
}
