package model

case class Airports(airports: List[String]) {
  override def toString = {
    "Airports\n" + "-" * 30 + "\n" + airports.sorted.grouped(3).map { (row) =>
      row.map((airport) => {
        f"%%-20s".format(airport)
      }).mkString
    }.toList.mkString("\n")
  }
}
