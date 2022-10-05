package lectures.part2oop

object AbstractDataTypes extends App {

  // Abstract classes
  // Classes which contain unimplemented (or abstract) fields or methods are called Abstract classes
  // An abstract class can't be instantiated
  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    override def eat: Unit = println("crunch crunch")
    // override keyword is not mandatory for abstract members. Compiler can figure that out.
  }

  // Traits: These are the ultimate abstract data types in Scala
  // Like abstract classes, these have abstract fields and methods
  // Unlike abstract classes, traits can be inherited along classes
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "Croc"
    override def eat: Unit = println("nomnom")

    override def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile

  croc.eat(dog)

  // traits vs abstract classes
  // Abstract classes: 1. Can have abstract and non-abstract types
  //                   2. We can extend only one class
  //                   3. Abstract classes describe "things"
  // Traits : 1. These can also have abstract and non-abstract types
  //          2. Traits don't have constructor parameters. But in Scala 3, this is now possible
  //          3. We can mix in (or inherit from) multiple traits
  //          4. Traits describe "behavior"


  // SCALA TYPE HIERARCHY
  // - scala.any
  //     |- scala.AnyVal (contains primitives like Int, Unit, Boolean, Float & some other classes that extend AnyVal)(We rarely need to extend AnyVal. Maybe needed for memory optimizations.)
  //         |- scala.Nothing (inherits from all of these)(can replace everything)
  //     |- scala.AnyRef (mapped to java.lang.Object) (String, List, Set, User derived classes will extend AnyRef)
  //         |- scala.Null (Null extends everything. So we can replace any instance with Null)
  //             |- scala.Nothing (inherits from all of these)(can replace everything)

}
