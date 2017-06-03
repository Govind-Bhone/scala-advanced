package oops.examples.typeclasses

/**
  * Created by govind.bhone on 6/2/2017.
  */
object TraitDefinitionExample extends App {

  class Base {
    def b = "b"
  }

  trait Cool {
    def c = "c"
  }

  trait Awesome {
    def a = "a"
  }

  class BA extends Base with Awesome

  class BC extends Base with Cool

  // as you might expect, you can upcast these instances into any of the traits they've mixed-in
  val ba: BA = new BA
  val bc: Base with Cool = new BC

  val b1: Base = ba
  val b2: Base = bc
  val b3: Cool = bc

  println(ba.a)
  println(bc.c)
  println(b1.b)
  println(b2.b)


}
