package util

import scala.io.Source
import util.{FlightDSL, Ticket}

object ReadTxtFile extends App{

  // Specify the path to your text file
  val filePath = "C:\\Users\\KhadidjaKebaili\\Documents\\GitHub\\Reactive_Systems\\src\\test\\scala\\util\\FlightData.txt"

  // Using Source.fromFile to open the file
  val source = Source.fromFile(filePath)

  try {
    // Reading lines from the file
    val lines = source.getLines()

    // Process each line as needed
    lines.foreach { line =>
      val flightDSL = FlightDSL
      val ticket = flightDSL.parseFlight(line)
      println(ticket)
    }
  } finally {
    // It's important to close the file after reading
    source.close()
  }
}

