package exercises.part2oop

object AnonymousClassesExercise extends App {
  val listOfIntegers: AList[Int] = new FilledAList[Int](1, new FilledAList[Int](2, new FilledAList[Int](3, EmptyAList)))
  val anotherListOfIntegers: AList[Int] = new FilledAList[Int](9, new FilledAList[Int](2, EmptyAList))
  val listOfStrings: AList[String] = new FilledAList[String]("Hello", new FilledAList[String]("All", EmptyAList))

  println(listOfIntegers)
  println(listOfStrings)

  // Example: [1,2,3].map(n * 2) = [2,4,6]
  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)

  // Example: [1,2,3,4].filter(n % 2) = [2,4]
  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  // Example: [1,2,3].flatMap(n => [n, n+1]) = [1,2,2,3,3,4]
  println(listOfIntegers.flatMap(new MyTransformer[Int, AList[Int]] {
    override def transform(elem: Int): AList[Int] = new FilledAList[Int](elem, new FilledAList[Int](elem + 1, EmptyAList))
  }).toString)
}

abstract class AList[+A] {
  def head: A

  def tail: AList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): AList[B]

  def map[B](transformer: MyTransformer[A, B]): AList[B]

  def filter(predicate: MyPredicate[A]): AList[A]

  def ++[B >: A](list: AList[B]): AList[B] // method for concatenation

  def flatMap[B](transformer: MyTransformer[A, AList[B]]): AList[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"
}

object EmptyAList extends AList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: AList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): AList[B] = new FilledAList(element, EmptyAList)

  def map[B](transformer: MyTransformer[Nothing, B]): AList[B] = EmptyAList

  def filter(predicate: MyPredicate[Nothing]): AList[Nothing] = EmptyAList

  def ++[B >: Nothing](list: AList[B]): AList[B] = list

  def flatMap[B](transformer: MyTransformer[Nothing, AList[B]]): AList[B] = EmptyAList

  def printElements: String = ""
}

class FilledAList[+A](h: A, t: AList[A]) extends AList[A] {
  def head: A = h

  def tail: AList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): AList[B] = new FilledAList(element, this)

  def map[B](transformer: MyTransformer[A, B]): AList[B] = {
    new FilledAList(transformer.transform(h), t.map(transformer))
  }

  def filter(predicate: MyPredicate[A]): AList[A] = {
    if (predicate.test(h)) new FilledAList(h, t.filter(predicate))
    else t.filter(predicate)
  }

  def ++[B >: A](list: AList[B]): AList[B] = new FilledAList[B](h, t ++ list)

  def flatMap[B](transformer: MyTransformer[A, AList[B]]): AList[B] = {
    transformer.transform(h) ++ t.flatMap(transformer)
  }

  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"
}

trait MyPredicate[-T] { // Contravariant in the type T
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B] { // Contravariant in the type A
  def transform(elem: A): B
}
