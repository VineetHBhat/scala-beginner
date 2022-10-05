package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x: Int = 42
  println(x)

  // VALS ARE IMMUTABLE, hence can't be re-assigned
  // COMPILER can infer types
  // SEMICOLON works but is not needed unless multiple lines of code are written in a single line

  val aString: String = "Hello World !"
  val sBoolean: Boolean = true || false
  val aChar: Char = 'a' // Characters in between single quotes
  val anInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 9283749360734L // 'L' at end consistent with JAVA syntax
  val aFloat: Float = 2.5f // 'f' at end consistent with JAVA syntax
  val aDouble: Double = 3.14


  // Variables in Scala
  // variables can be re-assigned
  var aVariable: Int = 4
  aVariable = 5 // Variables are used for side-effects ( in functional programming // )

  // Functional programming involves working less with variables
  // So prefer val over var


}
