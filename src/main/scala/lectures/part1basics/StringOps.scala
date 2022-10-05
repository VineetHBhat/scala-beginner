package lectures.part1basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala"

  // Borrowed from JAVA
  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.length)

  // Utilities built within SCALA
  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println(aNumber)
  println('a' +: aNumberString :+ 'z') // Prepending and appending (SCALA specific)
  println(str.reverse)
  println(str.take(2))

  // SCALA specific: String Interpolator

  // S-interpolator
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old"
  println(anotherGreeting)

  // F-interpolator
  val speed = 1.2345f
  val myth = f"$name can eat $speed%2.2f burgers per minute" // formatted strings similar to printf pattern
  val myth_2 = f"$name%s can eat $speed%2.2f burgers per minute" // formatted strings similar to printf pattern
  // %s    -> string format
  // %2.2f -> float number format. 2 characters total, minimum 2 decimal precision
  println(myth)
  println(myth_2)
  // F-interpolates strings can check for TYPE CORRECTNESS
  val x = 1.1f // value is float
//  val str2 = f"$x%3d" // this will not work since f string is expecting int value
  val str2 = f"$x%.3f"

  // Raw-interpolator
  println(raw"This is a \n newline") // special characters are also printed literally
  val escaped = "This is a \n newline"
  println(raw"$escaped") // here special characters will not be escaped
}
