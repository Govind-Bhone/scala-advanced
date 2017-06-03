package oops.examples.typeclasses

/**
  * Created by govind.bhone on 6/2/2017.
  */
object TypeLinearization extends App {

  trait A {
    def common = "A"
  }

  trait B extends A {
    override def common = "B"
  }

  trait C extends A {
    override def common = "C"
  }


  /*  // start with D1:
      B with C with <D1>

      // expand all the types until you rach Any for all of them:
      (Any with AnyRef with A with B) with (Any with AnyRef with A with C) with <D1>

      // remove duplicates by removing "already seen" types, when moving left-to-right:
      (Any with AnyRef with A with B) with (                            C) with <D1>

      // write the resulting type nicely:
      Any with AnyRef with A with B with C with <D1>*/

  class D1 extends B with C

  /*
  Start with D2
  C with B with <D2>

  Expand All types
  (Any with AnyRef with A with C ) with ( Any with AnyRef with A with B ) with <D2>

  remove duplicates by already seen types
  (Any with AnyRef with A with C with B with <D2>

  */

  class D2 extends C with B

  assert((new D1).common == "C")
  assert((new D2).common == "B")
}
