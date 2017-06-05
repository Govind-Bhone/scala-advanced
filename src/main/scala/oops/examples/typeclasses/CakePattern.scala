package oops.examples.typeclasses

/**
  * Created by govind.bhone on 6/3/2017.
  */
object CakePattern extends App{

  trait Foo {
    type SpecialFoo <: Foo
    def foo: SpecialFoo
  }

  class Bar extends Foo {
    type SpecialFoo = Bar
    def foo = this
  }

  class Baz extends Foo {
    type SpecialFoo = Baz
    def foo = this
  }

  object Test extends App {
    def withFoos(foos: List[Foo]) = foos
    val foos = List(new Bar, new Baz)
    println(withFoos(foos).head.foo)
    println(withFoos(foos).last.foo)
  }

}
