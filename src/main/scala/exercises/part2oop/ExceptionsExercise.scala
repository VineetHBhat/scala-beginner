package exercises.part2oop

object ExceptionsExercise extends App {
  // 1. Crash your program wih OutOfMemoryError (OOM)
//  val arr = Array.ofDim[Int](Int.MaxValue)

  // 2. Crash with StackOverflowError
//  def infinite: Int = 1 + infinite
//  val noLimit = infinite

  // 3. Pocket Calculator
  //      - add(x,y)
  //      - subtract(x,y)
  //      - multiply(x,y)
  //      - divide(x,y)
  //    Throw
  //      - OverflowException if add(x,y) exceeds Int.MaxValue
  //      - UnderflowException if subtract(x,y) Int.MinValue
  //      - MathCalculationException for division by 0

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by zero")

  object PocketCalculator {

    def add(x: Int, y: Int) = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

//  println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divide(2, 0))
}
