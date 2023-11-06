import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import model.Airline
import controller.AirlineController

class AirlineTest extends AnyWordSpec with Matchers {

  "AirlineController" when {
    "given a valid airline name" should {
      val airlineController = AirlineController
      "return true" in {
        val airlineName = Airline("Air Berlin")
        airlineController.isAirlineValid(airlineName) shouldBe true
      }
      "return false" in {
        val airlineName = Airline("Japan Airlines")
        airlineController.isAirlineValid(airlineName) shouldBe false
      }
    }
  }


}