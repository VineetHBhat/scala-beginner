package lectures.part2oop

object CaseClasses extends App {
  // For light-weight data structures like class Person, we need to re-implement all sorts of boilerplate
  // like companion objects, or standard methods for serializing and for printing
  // Example: equals, hashCode, toString, and so on and so forth.

  // Case classes are an ideal solution to this problem. ( Case classes is a Scala specific functionality )
  // Case classes are extraordinarily useful short-hand for defining the class, the companion object
  // and a lot of sensible defaults in one go

  case class Person(name: String, age: Int)

  // 1. Class parameters are promoted to fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. Sensible toString
  // println(instance) <==> println(instance.toString) // another form of syntactic sugar
  println(jim.toString)
  println(jim)

  // 3. equals and hashCode are implemented OOTB (out of the box)
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. CCs (case classes) have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. CCs have companion objects. Companion objects also have companion factory methods
  val thePerson = Person
  val mary = Person("Mary", 23) // <==> val mary = Person.apply("Mary", 23)
  println(mary)
  // Companion objects "apply" method does the same thing as the constructor. so in practice, we don't use the
  // keyword "new" when instantiating the case class. We only use the form shown on line 33.

  // 6. CCs are serializable (this makes case classes specially useful when dealing with distributed systems. We
  // can send instances of case classes through the network and in between JVMs. This is specially important when
  // dealing, for example, in "Akka" framework)
  // Akka deals with sending serializable messages through the network and the messages are, in general, case classes

  // 7. CCs have extractor patterns
  // This means that CCs can be used in PATTERN MATCHING (one of the most powerful Scala features)

  // Case object
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  // case objects have the same properties as the case classes except they don't get companion objects

}
