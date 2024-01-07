package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import spray.json.RootJsonFormat

class ModelJsonConvertersTest extends AnyWordSpec with Matchers {
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
}
