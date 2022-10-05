package exercises.part2oop

object CaseClassesExercise extends App {
  val listOfIntegers: TheList[Int] = new FilledTheList[Int](1, new FilledTheList[Int](2, new FilledTheList[Int](3, EmptyTheList)))
  val cloneListOfIntegers: TheList[Int] = new FilledTheList[Int](1, new FilledTheList[Int](2, new FilledTheList[Int](3, EmptyTheList)))
  val anotherListOfIntegers: TheList[Int] = new FilledTheList[Int](9, new FilledTheList[Int](2, EmptyTheList))
  val listOfStrings: TheList[String] = new FilledTheList[String]("Hello", new FilledTheList[String]("All", EmptyTheList))

  println(listOfIntegers)
  println(listOfStrings)

  println(listOfIntegers.map(new TheTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)

  println(listOfIntegers.filter(new ThePredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(new TheTransformer[Int, TheList[Int]] {
    override def transform(elem: Int): TheList[Int] = new FilledTheList[Int](elem, new FilledTheList[Int](elem + 1, EmptyTheList))
  }).toString)

  println(cloneListOfIntegers == listOfIntegers)
}

abstract class TheList[+A] {
  def head: A
  def tail: TheList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): TheList[B]
  def map[B](transformer: TheTransformer[A, B]): TheList[B]
  def filter(predicate: ThePredicate[A]): TheList[A]
  def ++[B >: A](list: TheList[B]): TheList[B] // method for concatenation
  def flatMap[B](transformer: TheTransformer[A, TheList[B]]): TheList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"
}

case object EmptyTheList extends TheList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: TheList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): TheList[B] = new FilledTheList(element, EmptyTheList)
  def map[B](transformer: TheTransformer[Nothing, B]): TheList[B] = EmptyTheList
  def filter(predicate: ThePredicate[Nothing]): TheList[Nothing] = EmptyTheList
  def ++[B >: Nothing](list: TheList[B]): TheList[B] = list
  def flatMap[B](transformer: TheTransformer[Nothing, TheList[B]]): TheList[B] = EmptyTheList
  def printElements: String = ""
}

case class FilledTheList[+A](h: A, t: TheList[A]) extends TheList[A] {
  def head: A = h
  def tail: TheList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): TheList[B] = new FilledTheList(element, this)
  def map[B](transformer: TheTransformer[A, B]): TheList[B] = {
    new FilledTheList(transformer.transform(h), t.map(transformer))
  }
  def filter(predicate: ThePredicate[A]): TheList[A] = {
    if (predicate.test(h)) new FilledTheList(h, t.filter(predicate))
    else t.filter(predicate)
  }
  def ++[B >: A](list: TheList[B]): TheList[B] = new FilledTheList[B](h, t ++ list)
  def flatMap[B](transformer: TheTransformer[A, TheList[B]]): TheList[B] = {
    transformer.transform(h) ++ t.flatMap(transformer)
  }
  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"
}

trait ThePredicate[-T] { // Contravariant in the type T
  def test(elem: T): Boolean
}

trait TheTransformer[-A, B] { // Contravariant in the type A
  def transform(elem: A): B
}