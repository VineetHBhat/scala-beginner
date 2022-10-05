package exercises.part2oop


abstract class MyList {
  /*
   * Singly linked list which holds integers
   * Method head: return first element of the list
   * Method tail: returns remainder of the list
   * Method isEmpty: return a boolean to tell if the list is empty
   * Method add(int): returns new list with the element added
   * override method toString: returns a string representation of the list
   */
  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(element: Int): MyList
  def printElements: String
  // polymorphic call to printElements
  override def toString: String = "[" + printElements + "]"
  // toString, equals & hashcode are methods present in the AnyRef class
  // that's why we need to override them
}

object Empty extends MyList { // Objects can extend classes
  // Here we decided to create an Object for an empty list. Not sure why ...
  /*
   * ANSWER FROM CHAT SECTION
   * Hi,
   * There are multiple reasons:
   * 1) Empty can be assigned to any kind of empty list (of Strings, Persons, whatever), so it makes sense to extend MyList[Nothing] so that it's applicable to any type
   * 2) it doesn't make sense to instantiate a new Empty every time for every empty list
   * 3) it's easy to do equality comparisons between empty lists because the reference is identical
   * Cheers,
   * Daniel
   */
  def head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(element: Int): MyList = new Cons(element, Empty)
  def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
  def head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def add(element: Int): MyList = new Cons(element, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.head)
  println(list.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)
  println(list)
  println(list.toString)
}