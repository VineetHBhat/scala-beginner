package lectures.part1basics

object CBNvsCBV extends App {
  def calledByValue(x: Long): Unit = {
    println("By value: " + x)
    println("By value: " + x)
  }

  def calledByName(x: => Long): Unit = {
    println("By name: " + x)
    println("By name: " + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime()) // Expression is evaluated at every use within

  def infinite():Int =  1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

//  printFirst(infinite(), 34) // Causes StackOverflowError since infinite() is evaluated before passing to the function
  printFirst(34, infinite()) // Works fine since infinite() will only be evaluated when y in used in the function
}
