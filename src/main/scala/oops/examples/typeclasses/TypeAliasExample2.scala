package oops.examples.typeclasses

/**
  * Created by govind.bhone on 6/3/2017.
  */

/*
The more interesting uses of Abstract Type Members start when we apply constraints to them.
For example imagine you want to have a container that can only store anything that is of a Number instance.
Such constraint can be annotated on a type member right where we defined it first:

 */
trait SimpleContainer {
  type A

  def value: A
}

trait OnlyOptions extends SimpleContainer{
  type T
  type A >: Option[T]
}

trait OnlyNumbers extends SimpleContainer{
  type A >: Number
}


object TypeAliasExample2 extends App {

  val optionInts = new SimpleContainer with OnlyOptions {
    type T = Int
    def value = Some(12)
  }

  // bellow won't compile
  val optionStrings = new SimpleContainer with OnlyOptions {
    type T = String
    def value = Some("String") // error: type mismatch; found: String(""); required: this.A
  }

  println(optionInts.value)
  println(optionStrings.value)

  val ints = new SimpleContainer with OnlyNumbers {
    def value = 12
  }

/*  // bellow won't compile
  val strings = new SimpleContainer with OnlyNumbers {
    def value ="String" // error: type mismatch; found: String(""); required: this.A
  }*/

  println(ints.value+12)
}
