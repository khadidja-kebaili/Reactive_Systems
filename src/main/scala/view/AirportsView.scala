package view

import model.Airport

object AirportsView {
  def printAirports(airports: List[Airport]): String = {
    "Airports\n" + "-" * 30 + "\n" + airports.foreach(airport => airport.name.sorted.grouped(3).map { (row) =>
      row.map((airport) => {
        f"%%-20s".format(airport)
      }).mkString
    }.toList.mkString("\n")) 
  }
}
