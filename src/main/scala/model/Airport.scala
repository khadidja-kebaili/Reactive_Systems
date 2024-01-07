package model

case class Airport(name: String, arrivals: List[Airplane], departures:List[Airplane]) {
  override def toString: String = name
}
