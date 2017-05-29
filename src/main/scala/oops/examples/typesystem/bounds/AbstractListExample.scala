package oops.examples.typesystem.bounds

/**
  * Created by govind.bhone on 5/26/2017.
  */
sealed abstract class AbbrevList[+A] {
  def isEmpty: Boolean

  def head: A

  def tail: AbbrevList[A]

  def ::[B >: A](x: B): AbbrevList[B] =
    new cons(x, this)

  final def foreach(f: A => Unit) = {
    var these = this
    while (!these.isEmpty) {
      f(these.head)
      these = these.tail
    }
  }
}

// The empty AbbrevList.

case object AbbrevNil extends AbbrevList[Nothing] {
  override def isEmpty = true

  def head: Nothing =
    throw new NoSuchElementException("head of empty AbbrevList")

  def tail: AbbrevList[Nothing] =
    throw new NoSuchElementException("tail of empty AbbrevList")
}

// A non-empty AbbrevList characterized by a head and a tail.

final case class cons[B](private var hd: B,
                         private var tl: AbbrevList[B]) extends AbbrevList[B] {

  override def isEmpty: Boolean = false

  def head: B = hd

  def tail: AbbrevList[B] = tl
}

object AbbrevList {
  def apply(args: String*)={
    var list = AbbrevNil.asInstanceOf[AbbrevList[String]]
    for (arg <- args) {
      list = list.::(arg)

    }
    list
  }
}

object AbbrevListExample extends App {

  val languages = AbbrevList("Scala", "Java", "Ruby", "C#", "C++", "Python")
  val list = 3.14 :: languages
  println(list)
}