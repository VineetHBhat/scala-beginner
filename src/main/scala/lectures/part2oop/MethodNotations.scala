package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favouriteMovie: String) {
    def likes(movie: String): Boolean = movie == favouriteMovie
    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, what ???"
    // It's important to put a space after ! above.
    // Otherwise compiler will thinkthat : is also part of the method name
    def isAlive: Boolean = true // Remember: This method doesn't receive any parameters. No parenthesis
    def apply(): String = s"Hi, my name is $name and I like $favouriteMovie" // Parenthesis are important here
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent to above statement
  // This is called infix-notation OR operator-notation (syntactic sugar)
  // This only works with methods that have only one parameter
  // This takes the form -> object method parameter

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  // here "hangOutWith" acts like an operator between two things to produce a third thing
  // So we can use + or & in method names
  println(mary + tom) // this is equivalent to below
  println(mary.+(tom))

  // Mathematical operators also behave in the same way as methods
  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS IN SCALA
  // Akka actors have ! ?

  // prefix notation (all about unary operators) (another example of syntactic sugar)
  val x = -1
  // unary operators are also methods
  val y = 1.unary_- // same as above value of x
  // unary_ prefix only works with - + ~ !

  // below two are equivalent
  println(!mary)
  println(mary.unary_!)

  // postfix notation
  // only available for methods with no parameters
  // rarely used in practice
  println(mary.isAlive)
  println(mary isAlive)

  // Special method: apply
  println(mary.apply())
  println(mary()) // has the same effect as above line

}
