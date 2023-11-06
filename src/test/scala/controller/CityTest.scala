package controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import model.City
import controller.CityController

class CityTest extends AnyWordSpec with Matchers {

  "CityController" when {
    "given a valid german city name" should {
      val cityController = CityController
      "return true" in {
        val cityName = City("Berlin")
        cityController.isCityValid(cityName) shouldBe true
      }
      "return false" in {
        val cityName = City("Paris")
        cityController.isCityValid(cityName) shouldBe false
      }
    }
  }

}
