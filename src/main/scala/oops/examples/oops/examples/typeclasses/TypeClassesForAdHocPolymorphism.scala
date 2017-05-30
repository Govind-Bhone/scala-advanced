package oops.examples.oops.examples.typeclasses

/**
  * Created by govind.bhone on 5/30/2017.
  */
object TypeClassesForAdHocPolymorphism extends App{
  trait Adder[A] {
    def add(x: A, y: A): A
  }

  /*
  With this we can implement our function by requiring an implementation
  of the Adder through a second parameter list:
 */
  def combine[A](x: A, y: A)(implicit adder: Adder[A]): A =
    adder.add(x, y)

  /*
  This is just simple dependency injection (parameter injection, to be precise).
  The reason for using a second parameter list will become clear shortly.
  */

  implicit object IntAdder extends Adder[Int] {
    override def add(x: Int, y: Int) = x + y
  }

  implicit object StringAdder extends Adder[String] {
    override def add(x: String, y: String)= x + y
  }

  println(combine(1, 2))
  println(combine("abc", "xyz"))
  println(combine("abc",combine("abc","xyz")))

}
