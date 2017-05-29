package oops.examples.typesystem.bounds

/**
  * Created by govind.bhone on 5/26/2017.
  */

case class ListNode[+T](h: T, t: ListNode[T]) {
  def head: T = h

  def tail: ListNode[T] = t

  def prepend[U >: T](elem: U): ListNode[U] =
    ListNode(elem, this)
}


object LowerTypeBoundExampleTest extends App {

  val list: ListNode[Null] = ListNode(null, null)
  val strList: ListNode[String] = list.prepend("Hi")
  val anyList=strList.prepend(1000)
  println(anyList)

}
