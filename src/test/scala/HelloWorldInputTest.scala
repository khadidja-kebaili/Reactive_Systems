import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class HelloWorldInputSpec extends AnyWordSpec with Matchers {
  "HelloWorldInput" should {
    "initialize name as an empty string" in {
      HelloWorldInput.name shouldBe ""
    }

    "setName should set the name correctly" in {
      HelloWorldInput.setName("Alice")
      HelloWorldInput.name shouldBe "Alice"
    }

    "getName should return the correct name" in {
      HelloWorldInput.setName("Bob")
      HelloWorldInput.getName() shouldBe "Bob"
    }
    "greetings should print the correct message" in {
      HelloWorldInput.setName("Charlie")
      val outputStream = new java.io.ByteArrayOutputStream()
      Console.withOut(outputStream) {
        HelloWorldInput.greetings()
      }
      val output = outputStream.toString
      output.trim shouldBe "Hello Charlie!"
    }
  }
}
