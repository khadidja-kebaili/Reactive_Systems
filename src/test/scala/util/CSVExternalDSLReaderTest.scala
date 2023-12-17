package util

import com.opencsv.CSVReader
import util.Ticket
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import util.CSVExternalDSLReader.filePath

import scala.collection.JavaConverters.collectionAsScalaIterableConverter
import java.io.{BufferedReader, FileReader}
import java.lang
import scala.language.postfixOps

class CSVExternalDSLReaderTest extends AnyWordSpec with Matchers {
  "FlightDSL" should {

    val flightDSL = FlightDSL

    "read a CSV-file and convert it" in {
      val filePath = "C:\\Users\\KhadidjaKebaili\\Documents\\GitHub\\Reactive_Systems\\src\\main\\scala\\util\\Testdaten.CSV"
      val reader = new CSVReader(new BufferedReader(new FileReader(filePath)))
      val rows = reader.readAll().asScala.filter(_.nonEmpty)

      rows.foreach { row =>
        val entry = row.toList
        entry.foreach(elem => {

          elem shouldBe a [String]
          elem.contains(";") shouldBe true
          elem.count(_ == ';') shouldBe 9

          elem match {
            case s"$vorname;$nachname;$von;$nach;$uhrzeit;$sitz;$gate;$boarding;$flugnummer;$airline" =>
              vorname shouldBe a [String]
              nachname shouldBe a [String]
              von shouldBe a [String]
              nach shouldBe a [String]
              uhrzeit shouldBe a [String]
              sitz shouldBe a [String]
              gate shouldBe a [String]
              boarding shouldBe a [String]
              flugnummer shouldBe a [String]
              airline shouldBe a [String]
            case _ => fail("Unexpected structure: " + elem)
          }

        }
          )
      }
    }

    "parse valid input correctly" in {
      val input = "Aria;Robinson;Berlin;Athen;17:00;21C;3B;16:40;7752;Lufthansa"
      val expectedFlight = Ticket("Aria", "Robinson", "Berlin", "Athen", "17:00", "21C", "3B", "16:40", 7752, "Lufthansa")
      flightDSL.parseFlight(input) shouldBe Some(expectedFlight)
    }

    "return None for invalid input" in {
      val invalidInput = "Aria;Robinson;Berlin;Athen; 21C;3B;16:40;7752;Lufthansa"
      flightDSL.parseFlight(invalidInput) shouldBe None
    }
  }

}
