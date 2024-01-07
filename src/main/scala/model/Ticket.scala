package model
case class Ticket(vorname: String, nachname: String, von: String, nach: String, uhrzeit: String,
                  seat: String, gate: String, boardingzeit: String, boardingdauer: Long,
                  flightnumber: Long, airline: String)