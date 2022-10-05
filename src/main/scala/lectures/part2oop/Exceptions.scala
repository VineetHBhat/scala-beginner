package lectures.part2oop


// Exceptions are JVM specific thing and not a Scala specific thing. They come from JAVA.
object Exceptions extends App {
  val x: String = null
//  println(x.length) // This will throw NullPointerException

  // Throwing and catching exceptions
  // 1. How to throw exceptions
//  val aWeirdValue: String = throw new NullPointerException() // Commenting this so that we can run this file

  // Throwable classes extend the Throwable class.
  // Exception and Error are the major Throwable sub-types
  // Exceptions: Denote something that went wrong with the program. For example: NullPointerException
  // Error: Denotes something that went wrong with the system (JVM). For example: StackOverflowError

  // 2. How to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No Int for you !")
    else 42

  val potentialFail = try {
    // code that might fail or throw an exception
    getInt(true)
  } catch {
        // try to match against all exception that we might have
    case e: RuntimeException => println("Caught a Runtime exception") // If we don't catch this, the program will crash
    case f: NullPointerException => println("Caught a NullPointerException exception")
  } finally {
    // code that will get executed NO MATTER WHAT
    // this block is optional
    // this block doesn't influence the return type of this expression
    // So, use finally only for side-effects. For example: logging something to a file
    println("Finally")
  }

  // Like everything else, try-catch-finally is an expression in Scala.
  // So what is the value of this expression
  println(potentialFail.getClass)

  // 3. How to define our own exceptions
  class MyException extends Exception
  val exception = new MyException
//  throw exception // this will crash the program
}
