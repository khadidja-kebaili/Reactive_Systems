import akka.actor.{Actor, ActorSystem, Props}
import com.opencsv.CSVReader

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.*
import scala.concurrent.ExecutionContext.Implicits.global
import util.{FlightDSL, Ticket}

import scala.collection.JavaConverters.collectionAsScalaIterableConverter
import java.io.{BufferedReader, FileReader}
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout


case class SearchTask(tickets: Option[List[Ticket]], criteria: Ticket => Boolean)
case class SearchResult(found: Boolean)

class SearchActor extends Actor {
  def receive: Receive = {
    case SearchTask(tickets, criteria) =>
      val result = DivideAndConquerSearch.divideAndConquerSearch(tickets, criteria)
      sender() ! SearchResult(result)
  }
}

object DivideAndConquerSearch {

  def divideAndConquerSearch(tickets: Option[List[Ticket]], criteria: Ticket => Boolean)(implicit ec: ExecutionContext): Boolean = {
    val threshold = 100

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
    val searchActorProps = Props(new SearchActor)
    val system = ActorSystem("SearchSystem")
    val searchActor1 = system.actorOf(searchActorProps, "searchActor1")
    val searchActor2 = system.actorOf(searchActorProps, "searchActor2")

    // Divide the task into two parts and distribute them to the actors
    val (leftTickets, rightTickets) = ticketList.getOrElse(List()).splitAt(ticketList.getOrElse(List()).length / 2)

    val task1 = SearchTask(Some(leftTickets), ticket => ticket.vorname == "Chloe")
    val task2 = SearchTask(Some(rightTickets), ticket => ticket.vorname == "Chloe")

    // Send tasks to actors
    val timeoutDuration = 5.seconds // Beispielzeit, passen Sie dies nach Bedarf an
    implicit val timeout: Timeout = Timeout(timeoutDuration)

    val futureResult1 = (searchActor1 ? task1).mapTo[SearchResult]
    val futureResult2 = (searchActor2 ? task2).mapTo[SearchResult]

    // Combine the results from both actors
    val combinedResult = for {
      result1 <- futureResult1
      result2 <- futureResult2
    } yield result1.found || result2.found

    // Get the final result
    val finalResult = Await.result(combinedResult, 5.seconds)
    println(finalResult)

    // Terminate the actor system
    system.terminate()
  }
}
