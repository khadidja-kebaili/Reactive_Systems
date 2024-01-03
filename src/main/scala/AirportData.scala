import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import controller.AirportController
import model.*
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer
import spray.json.*
import util.ApiServer
import view.AirportsView.printAirports

import java.util.Properties
import scala.concurrent.ExecutionContext


@main
def mainAirportData(args: String*): Unit = {
  println("Starting 'Airport Data' application...")
  print(printAirports(Airports(AirportController.getAirports())))
}

object mainGenerator extends ModelJsonConverters with SprayJsonSupport with App{
  implicit val system: ActorSystem = ActorSystem("ApiServer")
  implicit val ec: ExecutionContext = system.dispatcher

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.serializer", classOf[StringSerializer].getName)
  props.put("value.serializer", classOf[StringSerializer].getName)

  val producer = new KafkaProducer[String, String](props)

  val TOPIC = "original-data"
  val KEY = "message"

  // Start a separate thread for the loop
  println("Generator online!")
  while (true) {
    val value = ApiServer.airportsGenerator()
    val valueJson = value.toJson.toString()
    producer.send(new ProducerRecord(TOPIC, KEY, valueJson))
    // Sleep for 1 second
    Thread.sleep(1000)
  }

  producer.close()
  system.terminate()
  println("Generator stopped. Exiting program...")

}

