package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  // @tailrec - Using this here causes compilation error since recursive call is not at tail position here
  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n - 1))
      val result = n * factorial(n - 1) // This is not TAIL RECURSION as it is not the last expression in this path
      println("Computed factorial of " + n)
      result
    }
  }
    println(factorial(10))
    // factorial(5000) will give StackOverflowError. We need to modify factorial function definition
    // to avoid this error

    def anotherFactorial(n: Int): BigInt = {
      @tailrec // Helps us to create tail recursive functions
      def factHelper(x: Int, accumulator: BigInt): BigInt = {
        if (x <= 1) accumulator
        else factHelper(x - 1, x * accumulator) // TAIL RECURSION
        // For TAIL RECURSION, use recursive call as the LAST expression on each path that it occurs on
      }
      factHelper(n, 1)
    }
    /*
      anotherFactorial(10) = factHelper(10, 1)
      = factHelper(9, 10 * 1)
      = factHelper(8, 9 * 10 * 1)
      = factHelper(7, 8 * 9 * 10 * 1)
      = ...
      = factHelper(2, 3 * 4 * ... * 10 * 1)
      = factHelper(1, 2 * 3 * 4 * ... * 10 * 1)
      = 1 * 2 * 3 * 4 * ... * 10
    */

    // WHEN YOU NEED LOOPS, USE _TAIL_ RECURSION
}
