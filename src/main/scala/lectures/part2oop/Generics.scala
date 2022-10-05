package lectures.part2oop


// GENERICS: Use the same code on many (potentially unrelated) types
object Generics extends App {

  class DemoList[A] { // Parameterized with a type A
    // Type A can be used inside the class definition
  }
  // The above class is called a Generic Class and A is a type parameter. (A can be given any name)

  // Using this DemoList type
  val listOfIntegers = new DemoList[Int]
  val listOfStrings = new DemoList[String]

  // We can also have multiple type parameters
  class MyMap[Key, Value]

  // This also works for traits
  trait MyCollection[Elem]

  // Objects can't be type parameterized

  // Generic Methods
  object DemoList { // This is a companion for the class DemoList defined above
    def empty[A]: DemoList[A] = ???
  }

  val emptyListOfIntegers = DemoList.empty[Int]

  // VARIANCE PROBLEM
  // If B extends A, should List[B] extend List[A] ?
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // QUESTION:
  // If a Cat extends from Animal, does a list of Cat also extend a list of Animal ?
  // 3 possible answers to this question

  // Answer 1: Yes List[Cat] extends List[Animal] --> This behavior is called COVARIANCE
  class CovariantList[+A] // Declaring a covariant list. +A means this is a covariant list
  val animal: Animal = new Cat // Like we can do this (polymorphic type substitution)
  // Similarly we can do the following
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // EVEN HARDER QUESTION:
  // Once the animalList is defined, can we add any Animal to it ? Like, can we do the following
  // animalList.add(new Dog) ??? Doing this will pollute the animalList as it is a list of cats.
  // But the answer to this is that we return a list of animals

  // Answer 2: No. List[Cat] and List[Animal] are two separate things. --> INVARIANCE
  // Declaring an Invariant list
  class InvariantList[A]
  // Invariant classes are each in its own world and we can't substitute one for another
  // Following is not allowed
  // val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat]

  // Answer 3: Hell, No! --> CONTRAVARIANCE
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]
  // Not very intuitive for lists as we are faced with the question: How can we replace a list of cats with a list of animals.
  // Let's try the same thing when the classes are not lists
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]
  // In this case, it makes sense that a trainer of animal can also train a cat

  // BOUNDED TYPES
  // Bounded types allow us to use generic classes only for certain types that are:
  // Either - a sub-class of a different type
  // OR     - a super-class of a different type.
  // <: - Denotes sub-types. (upper-bounded type)
  // >: - Denotes super-types (lower-bounded type)
  class Cage[A <: Animal](animal: A)
  // above statement means that class Cage only accepts type parameters A which are sub-types of Animal
  val cage = new Cage(new Dog)

  class Car
  // Below is not allowed
  // val newCage = new Cage(new Car)

  // Bounded types solve a variance problem which is very-very annoying when we want to write covariant collections
  class MyList[+A] {
    // Below is not allowed
    // def add(element: A): MyList[A] = ???
    def add[B >: A](element: B): MyList[B] = ??? // This is the technical implementation of HARDER question at line 43
    // Say;
    // A = Cat
    // B = Dog; which is an Animal
    // So MyList turns into a list of animals
  }
}
