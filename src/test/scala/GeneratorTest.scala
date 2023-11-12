import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.*
import akka.http.scaladsl.server.*
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.testkit.ScalatestRouteTest
import model.*
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import spray.json.RootJsonFormat

import scala.concurrent.duration.*
import scala.concurrent.{ExecutionContext, Future}


class GeneratorTest extends AnyWordSpec with Matchers with ScalatestRouteTest with ScalaFutures {
  "ModelJsonConverters" should {
    "provide implicit RootJsonFormat for Airplane" in {
      val converters = new ModelJsonConverters
      val airplaneFormat: RootJsonFormat[Airplane] = converters.airplaneFormat
      assert(airplaneFormat != null)
    }

    "provide implicit RootJsonFormat for Airport" in {
      val converters = new ModelJsonConverters
      val airportFormat: RootJsonFormat[Airport] = converters.airportFormat
      assert(airportFormat != null)
    }

    "provide implicit RootJsonFormat for Airports" in {
      val converters = new ModelJsonConverters
      val airportsFormat: RootJsonFormat[Airports] = converters.airportsFormat
      assert(airportsFormat != null)
    }
  }

  "Generator" should {
    implicit val system: ActorSystem = ActorSystem("Generator")
    implicit val ec: ExecutionContext = system.dispatcher
    val routes = Generator.routes

    "return some airports for GET requests to the '/airport' path" in {
      Get("/airport") ~> routes ~> check {
        status should ===(StatusCodes.OK)
        contentType should ===(ContentTypes.`application/json`)
        entityAs[String] should include regex "\"airports\":\\["
      }
    }

    "return the right airport for GET requests to the '/airport/name' path" in {
      Get("/airport/Frankfurt") ~> routes ~> check {
        status should ===(StatusCodes.OK)
        contentType should ===(ContentTypes.`application/json`)
        entityAs[String] should include regex "\"arrivals\":\\["
        entityAs[String] should include regex "\"departures\":\\["
        entityAs[String] should include regex "\"name\""
      }
    }
  }
}
