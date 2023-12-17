package util

import com.opencsv.CSVReader
import util.{FlightDSL, Ticket}

import java.io.{BufferedReader, FileReader}
import scala.collection.JavaConverters.collectionAsScalaIterableConverter

object CSVExternalDSLReader extends App {
  val flightDSL = FlightDSL
  // Specify the path to your CSV file
  val filePath = "C:\\Users\\KhadidjaKebaili\\Documents\\GitHub\\Reactive_Systems\\src\\main\\scala\\util\\Testdaten.CSV"

  // Create a CSVReader object
  val reader = new CSVReader(new BufferedReader(new FileReader(filePath)))

  try {
    // Read all rows from the CSV file
    val rows = reader.readAll().asScala.filter(_.nonEmpty)

    // Process each row
    rows.foreach { row =>
      // Assuming each entry is a list of strings
      val entry = row.toList

      // Your processing logic here
      entry.foreach(elem => {
        val ticket = flightDSL.parseFlight(elem)
        println(ticket)
      })

    }
  } finally {
    // It's important to close the CSVReader after reading
    reader.close()
  }
}

