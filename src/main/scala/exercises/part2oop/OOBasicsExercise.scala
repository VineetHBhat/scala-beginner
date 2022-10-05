package exercises.part2oop

object OOBasicsExercise extends App {

  val author_John = new Writer("John", "Doe", 1991)
  val author_Daniel = new Writer("Daniel", "Radcliff", 1979)
  val author_Sean = new Writer("Sean", "Paul", 1999)

  println(author_John.fullname())
  println(author_Daniel.fullname())

  val novel_one = new Novel("Delta", 2008, author_Daniel)
  val novel_two = new Novel("Prank", 2019, author_John)

  println(novel_one.authorAge())
  println(novel_two.authorAge())

  println(novel_two.isWrittenBy(author_Sean))
  println(novel_two.authorAge())

  novel_one.copy(2021)
  println(novel_one.authorAge())

  val ctr = new Counter(5)
  ctr.print
  ctr.increment.print
  ctr.decrement.decrement.print
  ctr.increment(3).print
  ctr.decrement(4).print

}

class Writer(firstname: String, surname: String, val yearOfBirth: Int) {
  def fullname(): String = s"$firstname $surname"
}


class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge(): Int = yearOfRelease - author.yearOfBirth
  def isWrittenBy(author: Writer): Boolean = author == this.author
  def copy(newYearOfRelease: Int): Novel = new Novel(name, newYearOfRelease, author)
}

class Counter(val counter: Int) {

  def increment: Counter = {
    println("Incrementing ...")
    new Counter(this.counter + 1) // immutability with classes
  }

  def decrement: Counter = {
    println("Decrementing ...")
    new Counter(this.counter - 1)
  }

  def increment(n: Int): Counter = {
    if (n <= 0) this
    else increment.increment(n-1)
  }

  def decrement(n: Int): Counter = {
    if (n <= 0) this
    else decrement.decrement(n-1)
  }

  def print = println(counter)
}
