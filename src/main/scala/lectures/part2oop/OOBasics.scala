package lectures.part2oop

import lectures.part2oop.Person

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  println(person.x)
  person.greet("Daniel")
  person.greet()
}

class Person(name: String, val age: Int) { // constructor
  // class parameters from the constructor are NOT FIELDS
  // to change a class parameter to a field, add val before it
  // class body
  val x = 2 // This is a FIELD
  println(1 + 3)

  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")
  // We need to access class parameter with ${this.name} here since the method parameter also has the same name

  // overloading
  def greet(): Unit = println(s"Hi, I am $name") // here $name automatically resolves to class parameter name
  // so no need to explicitly mention ${this.name} here

  // multiple constructors or constructor overloading
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
  // auxiliary construction can only call another constructor
  // better alternative is to use default parameters in main constructor definition
}