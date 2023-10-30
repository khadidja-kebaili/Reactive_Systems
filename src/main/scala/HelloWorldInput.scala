object HelloWorldInput extends App {

  var name: String = ""

  def setName(newName: String): Unit = {
    name = newName
  }

  def getName(): String = {
    name
  }

  def greetings(): Unit = {
    val greeting = if (name.isEmpty) "Hello World!" else s"Hello $name!"
    println(greeting)
  }
}
