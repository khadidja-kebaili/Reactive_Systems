import akka.http.scaladsl.model.*
import akka.http.scaladsl.server.*
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.testkit.ScalatestRouteTest
import model.*
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import spray.json.RootJsonFormat


class GeneratorTest extends AnyWordSpec with Matchers with ScalatestRouteTest {
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
    "return some airports for GET requests to the '/airport' path" in {
      Get("/airport") ~> Generator.routes ~> check {
        status should ===(StatusCodes.OK)
        contentType should ===(ContentTypes.`application/json`)
        entityAs[String] should include regex "\"airports\":\\["
      }
    }

    "return the right airport for GET requests to the '/airport/name' path" in {
      Get("/airport/Frankfurt") ~> Generator.routes ~> check {
        status should ===(StatusCodes.OK)
        contentType should ===(ContentTypes.`application/json`)
        entityAs[String] should include regex "\"arrivals\":\\["
        entityAs[String] should include regex "\"departures\":\\["
        entityAs[String] should include regex "\"name\""
      }
    }

  }
}
