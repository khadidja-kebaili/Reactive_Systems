import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import controller.AirportController
import model.Airport
import util.ApiServer.routes
import view.AirportsView.printAirports

import scala.concurrent.ExecutionContext
import scala.io.StdIn.*

@main
def mainAirportData(args: String*): Unit = {
  println("Starting 'Airport Data' application...")
}

@main
def mainGenerator(args: String*): Unit = {
  implicit val system: ActorSystem = ActorSystem("ApiServer")
  implicit val ec: ExecutionContext = system.dispatcher
  val serverFuture = Http().newServerAt("localhost", 8081).bind(routes)
  println("Generator online at http://localhost:8081/\nPress ENTER to stop...")
  readLine()
  serverFuture.flatMap(_.unbind()).onComplete(_ => {
    system.terminate()
    println("Generator shutting down...")
  })
}

