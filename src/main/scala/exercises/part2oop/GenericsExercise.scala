package exercises.part2oop

object GenericsExercise extends App {
  val listOfIntegers: MeList[Int] = new FilledList[Int](1, new FilledList[Int](2, new FilledList[Int](3, EmptyMeList)))
  val listOfStrings: MeList[String] = new FilledList[String]("Hello", new FilledList[String]("World", EmptyMeList))

  println(listOfIntegers)
  println(listOfStrings)
}

abstract class MeList[+A] {
  def head: A
  def tail: MeList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MeList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"
}

object EmptyMeList extends MeList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MeList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MeList[B] = new FilledList(element, EmptyMeList)
  def printElements: String = ""
}

class FilledList[+A](h: A, t: MeList[A]) extends MeList[A] {
  def head: A = h
  def tail: MeList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MeList[B] = new FilledList(element, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"
}