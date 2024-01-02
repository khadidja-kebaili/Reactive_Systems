package controller

import akka.actor.AbstractActor.Receive
import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import com.opencsv.CSVReader

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.*
import scala.concurrent.ExecutionContext.Implicits.global
import util.{FlightDSL, Ticket}

import java.io.{BufferedReader, FileReader}
import util.CSVExternalDSLReader.filePath

import scala.collection.JavaConverters.collectionAsScalaIterableConverter
import java.io.{BufferedReader, FileReader}
import java.lang
import scala.::
import scala.language.postfixOps

object DivideAndConquerSearch {

  def divideAndConquerSearch(tickets: Option[List[Ticket]], criteria: Ticket => Boolean)(implicit ec: ExecutionContext): Boolean = {
    val threshold = 1000

    if (tickets.getOrElse(List()).size <= threshold) {
      tickets.exists(_.exists(criteria))
    } else {
      val (left, right) = tickets.getOrElse(List()).splitAt(tickets.getOrElse(List()).length / 2)

      val leftSearch = Future(divideAndConquerSearch(Some(left), criteria))
      val rightSearch = Future(divideAndConquerSearch(Some(right), criteria))

      val combinedSearch = for {
        leftResult <- leftSearch
        rightResult <- rightSearch
      } yield leftResult || rightResult

      Await.result(combinedSearch, 5.seconds)
    }
  }


  def main(args: Array[String]): Unit = {
    val flightDSL = FlightDSL
    val filePath = "C:\\Users\\KhadidjaKebaili\\Documents\\GitHub\\Reactive_Systems\\src\\main\\scala\\util\\Testdaten.CSV"
    val reader = new CSVReader(new BufferedReader(new FileReader(filePath)))
    val rows = reader.readAll().asScala.filter(_.nonEmpty)

    val ticketList: Option[List[Ticket]] = Some {
      rows.flatMap { row =>
        val entry = row.toList
        entry.map { elem =>
          flightDSL.parseFlight(elem)
        }
      }.toList.flatten
    }

    val numberOfTickets: Int = ticketList.getOrElse(List()).size
    print(numberOfTickets)

    ticketList.foreach(elem => print(elem))

      // Example criteria: searching by last name "Doe"
      val lastNameCriteria: Ticket => Boolean = ticket => ticket.nachname == "Chloe"

      // Example criteria: searching by first name "John" and flight number 12345
      val firstNameAndFlightCriteria: Ticket => Boolean = ticket =>
        ticket.vorname == "Chloe"

      // Example criteria: searching by boarding date "11:30"
      val boardingTimeCriteria: Ticket => Boolean = ticket => ticket.boardingzeit == "11:30"

      // Example usage with lastNameCriteria
      val resultLastName = divideAndConquerSearch(ticketList, lastNameCriteria)

      // Example usage with firstNameAndFlightCriteria
      val resultFirstNameAndFlight = divideAndConquerSearch(ticketList, firstNameAndFlightCriteria)

      // Example usage with boardingTimeCriteria
      val resultBoardingTime = divideAndConquerSearch(ticketList, boardingTimeCriteria)

      // Handle results as needed
      print(resultLastName, resultBoardingTime, resultFirstNameAndFlight)

      // Make sure to manage the ExecutionContext and shut it down properly if needed
      // ...

  }
}
