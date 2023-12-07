package model
import model.Flight

case class Airport(name: String, arrivals: List[Flight], departures:List[Flight]) {
  override def toString: String = name
}
