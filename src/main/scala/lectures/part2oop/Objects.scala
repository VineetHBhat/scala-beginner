package lectures.part2oop

object Objects extends App {

  // SCALA doesn't have Class-level functionality (i.e. 'static' values/methods)
  // Class-level functionality is something that is accessed using the class name directly
  // instead of using an instance of the class. (example is static variables in JAVA)
  // It instead uses objects for providing class-level functionality
  // Objects don't receive parameters
  object Person { // type + its only instance
    // objects are in their own type and the only instance
    // "static"/class-level functionality
    // Major advantage of Scala Objects: Can implement singleton pattern in one line of code
    val N_EYES = 2
    def canFly: Boolean = false
    // This is a factory method

    // It's sole purpose is to build Persons given some parameters
    def from(mother: Person, father: Person): Person = new Person("Bobbie")
    // Often these factory methods are called apply
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }
  class Person(val name: String) { // we define class and object with the same name
    // instance-level functionality
  }
  // This pattern of writing classes and objects with the same name & in the same scope is called
  // COMPANIONS DESIGN PATTERN
  // companions can access each others private members
  // Scala is more OO than JAVA

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = SINGLETON INSTANCE
  val person1 = Person
  val person2 = Person
  println(person1 == person2)

  // But this doesn't mean that all instances of type Person (class) are also same
  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john)

  val bobbie1 = Person.from(mary, john)
  val bobbie2 = Person(mary, john)

  // SCALA APPLICATIONS
  // This is a Scala object with below method
  // def main(args: Array[String]): Unit
  // The method needs to have this exact definition because Scala applications are turned in to JVM applications
  // whose entry point has to be "public static void main(String[] args)"
  // So "void" is translated to "Unit" & "static" is translated to a single plain method in Scala object
}
