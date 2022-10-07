package exercises.part2oop

object CaseClassesExercise extends App {
  val listOfIntegers: TheList[Int] = new FilledTheList[Int](1, new FilledTheList[Int](2, new FilledTheList[Int](3, EmptyTheList)))
  val cloneListOfIntegers: TheList[Int] = new FilledTheList[Int](1, new FilledTheList[Int](2, new FilledTheList[Int](3, EmptyTheList)))
  val anotherListOfIntegers: TheList[Int] = new FilledTheList[Int](9, new FilledTheList[Int](2, EmptyTheList))
  val listOfStrings: TheList[String] = new FilledTheList[String]("Hello", new FilledTheList[String]("All", EmptyTheList))

  println(listOfIntegers)
  println(listOfStrings)

  println(listOfIntegers.map(new Function1[Int, Int] {
    override def apply(elem: Int): Int = elem * 2
  }).toString)

  println(listOfIntegers.filter(new Function1[Int, Boolean] {
    override def apply(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(new Function1[Int, TheList[Int]] {
    override def apply(elem: Int): TheList[Int] = new FilledTheList[Int](elem, new FilledTheList[Int](elem + 1, EmptyTheList))
  }).toString)

  println(cloneListOfIntegers == listOfIntegers)
}

abstract class TheList[+A] {
  def head: A
  def tail: TheList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): TheList[B]

  // map, flatmap and filter are called higher-order functions (HOFs)
  // HOFs either receive functions as parameters or return other functions as result
  def map[B](transformer: A => B): TheList[B]
  def filter(predicate: A => Boolean): TheList[A]
  def ++[B >: A](list: TheList[B]): TheList[B] // method for concatenation
  def flatMap[B](transformer: A => TheList[B]): TheList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"
}

case object EmptyTheList extends TheList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: TheList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): TheList[B] = new FilledTheList(element, EmptyTheList)
  def map[B](transformer: Nothing => B): TheList[B] = EmptyTheList
  def filter(predicate: Nothing => Boolean): TheList[Nothing] = EmptyTheList
  def ++[B >: Nothing](list: TheList[B]): TheList[B] = list
  def flatMap[B](transformer: Nothing => TheList[B]): TheList[B] = EmptyTheList
  def printElements: String = ""
}

case class FilledTheList[+A](h: A, t: TheList[A]) extends TheList[A] {
  def head: A = h
  def tail: TheList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): TheList[B] = new FilledTheList(element, this)
  def map[B](transformer: A => B): TheList[B] = {
//    new FilledTheList(transformer.transform(h), t.map(transformer))
    new FilledTheList(transformer(h), t.map(transformer))
  }
  def filter(predicate: A => Boolean): TheList[A] = {
//    if (predicate.test(h)) new FilledTheList(h, t.filter(predicate))
    if (predicate(h)) new FilledTheList(h, t.filter(predicate))
    else t.filter(predicate)
  }
  def ++[B >: A](list: TheList[B]): TheList[B] = new FilledTheList[B](h, t ++ list)
  def flatMap[B](transformer: A => TheList[B]): TheList[B] = {
//    transformer.transform(h) ++ t.flatMap(transformer)
    transformer(h) ++ t.flatMap(transformer)
  }
  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"
}

// This is basically a Function Type from T => Boolean. Hence commenting them here
//trait ThePredicate[-T] { // Contravariant in the type T
//  def test(elem: T): Boolean
//}

// This is a function type from A => B. Hence commenting them here
//trait TheTransformer[-A, B] { // Contravariant in the type A
//  def transform(elem: A): B
//}