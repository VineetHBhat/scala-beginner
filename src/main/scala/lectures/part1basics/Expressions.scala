package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2 // RHS here is called EXPRESSION
  println(x)

  println(2 + 3 * 4)
  // + - * / % & | ^ << >> >>> (Right Shift with zero extension. Only specific to Scala)

  println(1 == x)
  // == != > >= < <=

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= ..... these are side-effects. Changing a variable is called side-effect.
  println(aVariable)


  // Instructions/Statement (DO) vs Expressions (VALUE)

  // If Expression (Not an If statement or instruction)(works in a very very different way in Scala)
  // This outputs a value
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3
  println(aConditionedValue)


  // There are LOOPS in Scala but their usage should be discouraged
  // This is because they execute side-effects
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }
  // NEVER WRITE THIS AGAIN

  // EVERYTHING in SCALA is an EXPRESSION
  // Definition of a class, package, val is not an EXPRESSION, but everything else is.

  val aWeirdValue = (aVariable = 3) // The type of aWeirdValue is UNIT
  // UNIT is equivalent to void in other languages
  println(aWeirdValue) // The output of this is: ()
  // () is the only value that the type UNIT can hold

  // Side-effects in Scala are expressions returning UNIT

  // while also returns a UNIT. Hence they cause side-effects
  // Hover over val below and see its type
  val aWhile = while (i < 10) {
    println(i)
    i += 1
  }

  // Examples of side-effects
  // println(), while, reassignment of vars


  // CODE BLOCKS ( These are also Expressions )
  // The value of a code block is the value of the last expression
  // Definitions within the code block curly braces are only accessible inside

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "Hello !" else "Good Bye !"
  }
}
