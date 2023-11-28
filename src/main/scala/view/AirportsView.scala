package view

import model.{Airports}

object AirportsView {
  def printAirports(airports: Airports): String = {
    "Airports\n" + "-" * 30 + "\n" + airports.names.sorted.grouped(3).map { (row) =>
      row.map((airport) => {
        f"%%-20s".format(airport)
      }).mkString
    }.toList.mkString("\n")
  }
}
