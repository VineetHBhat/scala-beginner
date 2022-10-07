package lectures.part2oop

import playground.{Cinderella, PrinceCharming}

// import playground._ // this will import everything from playground. Not best practice, but can be useful sometimes.

// Name aliasing during imports
// import playground.{Cinderella => Princess, PrinceCharming}
import java.util.Date
import java.sql.{Date => SqlDate}


object PackagingAndImports extends App {

  // package members are accessible by their simple name

  // non-package members
  // - Either need to be imported
  // - Or we need to use the fully qualified class name, shown below;
  val writer = new exercises.part2oop.Writer("Daniel", "RockTheJVM", 2018)

  // Packages
  // Ordered hierarchically
  // Hierarchy is given by the . (dot) notation. This maps the folder structure in the filesystem
  // (mirrors the folder structure - BEST PRACTICE)

  // Scala specific code organizing structure - PACKAGE OBJECT
  // Package objects originated from the problem that sometimes we may want to write methods or constants
  // outside of everything else. We may sometimes need Universal constants or methods that reside outside
  // of classes
  // Right click package > New > Package Object
  // Package objects can only be one-per-package

  sayHello
  println(SPEED_OF_LIGHT)

  // Package objects are rarely used in practice


  // IMPORTS
  val prince = new PrinceCharming
  val princess = new Cinderella

  // Name aliasing during imports can be useful when we want to import a class with the same name from
  // multiple packages

  // Options when using classes with same name from different packages
  // Option 1. Use fully qualified class name
  val date = new Date // compiler assumes this to be the first import
  val sqlDate = new java.sql.Date(2018, 5, 4)

  // Option 2. Use aliasing
//  val sqlDate = new SqlDate(2018, 5, 4) // deprecated constructor for SqlDate

  // DEFAULT IMPORTS
  // Packages that are automatically imported without any intentional import from our side
  // Examples:
  // 1. java.lang - String, Object, Exception ...
  // 2. scala - Int, Nothing, Function ...
  // 3. scala.Predef - println, ??? ...

}
