package lectures.part3fp

object WhatsAFunction extends App {
  // DREAM: Use functions as first class elements. This means that:
  //          - We want to pass functions as parameters
  //          - We want to use functions as values
  // PROBLEM: We come from an object oriented world which means that everything is an object
  // So Scala had to resort to some clever tricks

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2)) // We know that directly calling an instance calls the "apply" method
  // So "doubler" instance can be called like a function

  // Scala supports few function types out-of-the-box up to 22 parameters
  // Function types: Function1[A, B]
  // Function1, Function2, ... , Function22
  // Function1[A, B]: Function with 1 parameter and 1 result
  // All these 22 functions are traits
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("5") + 2)

  // Syntactic sugar for Function2[Int, Int, Int] is ((Int, Int) => Int)
  val adder: ((Int, Int) => Int) = new ((Int, Int) => Int) {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // We will use the below syntactic sugar a lot more
  // Function2[A, B, R] <==> ((A, B) => R)

  // ALL SCALA FUNCTIONS ARE OBJECTS
}

trait MyFunction[A, B] {
  def apply(element: A): B
}