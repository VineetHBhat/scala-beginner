package exercises.part3fp

object WhatsAFunctionExercise extends App {
  val concatenator = new ((String, String) => String) {
    override def apply(a: String, b: String): String = a + b
  }

  println(concatenator("Hello", "World"))


  // This is also higher order function (HOF)
  val superAdder = new ((Int) => ((Int) => Int)) {
    override def apply(x: Int): ((Int) => Int) = new ((Int) => Int) {
        override def apply(y: Int): Int = x + y
      }
  }
  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // This is called a curried function
  // Curried functions have the property that they can be called with multiple parameter lists
}
