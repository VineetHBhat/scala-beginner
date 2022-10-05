package exercises.part2oop

import scala.language.postfixOps

object MethodNotationsExercise extends App {

  class Person(val name: String, favouriteMovie: String, age: Int = 0) {
    def likes(movie: String): Boolean = movie == favouriteMovie
    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favouriteMovie)
    def unary_! : String = s"$name, what ???"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favouriteMovie. I am $age years old."
    def apply(n: Int): String = s"${this.name} watched $favouriteMovie $n times"
    def unary_+ : Person = new Person(this.name, favouriteMovie, age + 1)
    def learns(skill: String): String = s"$name learns $skill"
    def learnsScala: String = this learns "Scala"
  }

  val megan = new Person("Megan", "Spotlight")
  println((megan + "the rockstar")())

  println((+megan)())

  println(megan.learns("MySQL"))
  println(megan learnsScala)

  println(megan(2))
}