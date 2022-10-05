package exercises.part1basics

import scala.annotation.tailrec

object RecursionExercise extends App {

  def concatNTimes(aString: String, n: Int): String = {
    @tailrec
    def concatHelper(aStr: String, i: Int, accumulator: String): String = {
      if (i < 1) accumulator
      else concatHelper(aStr, i-1, aStr + accumulator)
    }
    concatHelper(aString, n, "")
  }
  println(concatNTimes("Hello World ! ", 5))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int, isStillPrime: Boolean): Boolean = {
      if (! isStillPrime) false
      else if (t <= 1) true
      else isPrimeUntil(t-1, n % t != 0 && isStillPrime)
    }
    isPrimeUntil(n / 2, true) // starts with positive assumption
  }
  println(isPrime(2003))

  def fib(n: Int): Int = {
    @tailrec
    def fibAccumulator(x: Int, last: Int, nextToLast: Int): Int = {
      if (x >= n) last
      else fibAccumulator(x+1, last + nextToLast, last)
    }
    if (n <= 2) 1
    else fibAccumulator(2, 1, 1)
  }
  println(fib(8))
}
