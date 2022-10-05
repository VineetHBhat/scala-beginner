package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // here it looks as if we are instantiating an abstract class
  // but instead we are using anonymous classes
  // here the compiler actually took the part after "=" below and created a class named
  // "AnonymousClasses$$anon$1" & then instantiated it
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("aahhahahaahaah")
  }

  println(funnyAnimal.getClass)
  // above line prints the below on the console
  // class lectures.part2oop.AnonymousClasses$$anon$1

  /*
   * Below code is the equivalent of line 13 above. The compiler actually does this.
        class AnonymousClasses$$anon$1 extends Animal {
          override def eat: Unit = println("aahhahahaahaah")
        }
        val funnyAnimal: Animal = new AnonymousClasses$$anon$1
   */

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi my name is $name, how can I help ?")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi my name is Jim, how can I be of service ?")
  }

  // Anonymous classes work for both abstract and non-abstract data types (traits & classes)
  // Rules:
  // 1. Pass in required constructor arguments if needed
  // 2. Implement all abstract fields and methods
}
