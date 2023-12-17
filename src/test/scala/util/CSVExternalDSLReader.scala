package util

import com.opencsv.CSVReader

import scala.io.Source
import util.{FlightDSL, Ticket}

import java.io.{FileInputStream, InputStreamReader}

val flightDSL = FlightDSL

/*object CSVExternalDSLReader extends App {

  // Specify the path to your CSV file
  val filePath = "C:\\Users\\KhadidjaKebaili\\Documents\\GitHub\\Reactive_Systems\\src\\test\\scala\\util\\Testdaten.CSV"
  // Using Source.fromFile to open the file
  val source = Source.fromFile(filePath)


  try {
    // Reading lines from the file
    val lines = source.getLines()

    // Process each line as needed
    lines.foreach { line =>
      val ticket = flightDSL.parseFlight(line)
      println(ticket)
    }
  } finally {
    // It's important to close the file after reading
    source.close()
  }
}*/

import com.opencsv.CSVReader
import java.io.{BufferedReader, FileReader}
import collection.JavaConverters.*

object CSVExternalDSLReader extends App {
  // Specify the path to your CSV file
  val filePath = "C:\\Users\\KhadidjaKebaili\\Documents\\GitHub\\Reactive_Systems\\src\\test\\scala\\util\\Testdaten.CSV"

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

