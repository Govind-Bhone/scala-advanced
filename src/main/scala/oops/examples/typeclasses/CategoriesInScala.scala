package oops.examples.typeclasses

/**
  * Created by govind.bhone on 5/30/2017.
  */
/*
A category is made up from objects and maps (aka morphisms or arrows) between these objects.
Maps can be composed in an associative
fashion and for each object there is an identity map which is neutral with regard to composition.

 */
object CategoriesInScala extends App {

  object Category {
    def id[A]: A => A = a => a

    def compose[A, B, C](g: B => C, f: A => B): A => C =
      g compose f // This is Function.compose, not a recursive call!

    def andThen[A,B,C](g:B=>C,f:A=>B):A=>C= f andThen g
  }

  import Category._

  val f = (i: Int) => i.toString
  val g = (s: String) => s.length
  val h = (i: Int) => i * i

  assert(compose(h, compose(g, f))(10) == compose(compose(h, g), f)(10))
  assert(compose(f, id[Int])(10) == compose(id[String], f)(10))
}
