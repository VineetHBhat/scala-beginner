package lectures.part2oop

object Inheritance extends App {

  // Scala allows single-class inheritance
  // which mean that we can extend only one class at a time
  class Animal {
    val creatureType = "wild"
    protected def eat = println("nomnom")
    def sound = println("gwr gwr")
  }

  // sub-class only inherits non-private members of the super class
  // protected members are usable only within their class or sub-class
  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
//  cat.eat // not allowed since eat is protected
  cat.crunch

  // Constructors
  // JVM internals: Constructor of Person is called first, only after that the constructor of Adult is called
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0) // auxiliary constructors
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)
  class Student(name: String, age: Int, school: String) extends Person(name)

  // Overriding
  class Dog extends Animal {
    override val creatureType: String = "domestic"
    override def eat = println("bark bark")
  }

  val dog = new Dog
  dog.eat
  println(dog.creatureType)

  // fields can be directly overridden in the constructor
  class Rabbit(override val creatureType: String) extends Animal {
    override def eat: Unit = println("qwr qwr")
    override def sound: Unit = println("rbt rbt rbt")
  }
  val rabbit = new Rabbit("K9")
  println(rabbit.creatureType)
  rabbit.eat

  // Type substitution (broad sense: polymorphism)
  val unknownAnimal: Animal = new Rabbit("Hobbit")
  unknownAnimal.sound // sound should be public in parent class Animal
  // A method call will always go to the most overridden version whenever possible

  // OverRIDING  = supplying a different implementation in derived class
  // OverLOADING = supplying multiple methods with different signatures, but with the same name & in the same class

  // super
  class Horse(override val creatureType: String) extends Animal {
    override def sound: Unit = {
      super.eat
      println("neigh neigh")
    }
  }
  val horse: Animal = new Horse("Stallion")
  horse.sound

  // Preventing overrides
  // Way 1: Use keyword 'final' on member
  // Way 2: Use 'final' on class. This prevents the entire class from being extended.
  //        The numerical classes in Scala are final. The String type is also final
  // Way 3: Seal the class. This is a softer restriction. The sealed class can be extended in THIS FILE.
  //        But prevents extension in other files.
  sealed class Parent
  class child extends Parent
  // sealed keyword is often used when we want to be exhaustive in type hierarchies.
  // Example: If cats and dogs are the only types of animals that ever existed. Then we would seal the
  //          Animal class and extend cat and dog in this file. This would prevent Animal from being extended
  //          in other files because cats and dogs are the only types that we could have.
}
