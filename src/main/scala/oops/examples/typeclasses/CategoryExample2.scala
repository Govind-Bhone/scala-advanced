package oops.examples.typeclasses

/**
  * Created by govind.bhone on 5/30/2017.
  */

/*

Please note, that A ->> B is just another way to write ->>[A, B], which nicely reflects the fact
that we are talking about maps here.

 */
object CategoryExample2 extends App{
  trait GenericCategory[->>[_, _]] {
    def id[A]: A ->> A
    def compose[A, B, C](g: B ->> C, f: A ->> B): A ->> C
  }

  object Category extends GenericCategory[Function] {
    def id[A]: A => A = a => a
    def compose[A, B, C](g: B => C, f: A => B): A => C =
      g compose f // This is Function.compose, not a recursive call!
  }

  import Category._

  val f = (i: Int) => i.toString
  val g = (s: String) => s.length
  val h = (i: Int) => i * i

  assert(compose(h, compose(g, f))(10) == compose(compose(h, g), f)(10))
  assert(compose(f, id[Int])(10) == compose(id[String], f)(10))


}
