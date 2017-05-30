package oops.examples.oops.examples.typeclasses

/**
  * Created by govind.bhone on 5/30/2017.
  */
/*
In our previous Adder example
if it is fine with below law (associativity )

If our Adder implementation obeys the associativity law, i.e.

add(x, add(y, z)) == add(add(x, y), z) for all x, y, z

then it can be called a Semigroup.

A Semigroup is just a collection of objects – for example integers or strings – with a defined binary operation on
them producing another object of the same type. For example, two integers can be added producing another integer, and two
strings can be concatenated producing another string. If there are more than two objects to be combined
using this operation, the order of the applications of individual operators must not matter. This property is called associativity.


 */
object SemiGroupExample extends App {

  trait Semigroup[A] {
    def add(x: A, y: A): A
  }

  object Semigroup {
    def apply[A: Semigroup]: Semigroup[A] = implicitly
  }

  trait Monoid[A] extends Semigroup[A] {
    def zero: A
  }

  object Monoid {
    def apply[A: Monoid]: Monoid[A] = implicitly
  }

  implicit object Integers extends Monoid[Int] {
    override def add(x: Int, y: Int) = x + y

    override def zero: Int = 0
  }

  implicit object Strings extends Monoid[String] {
    override def add(x: String, y: String) = x + y

    override def zero: String = ""
  }

  def combine[A: Semigroup](x: A, y: A): A =
    Semigroup[A].add(x, y)

  def aggregate[A: Monoid](xs: Iterable[A]): A = xs.fold(Monoid[A].zero)(Monoid[A].add)

  println(aggregate(Seq(1, 2, 3)))
  println(aggregate(Seq("abc", "xyz", "XYX", "ABC")))
}
