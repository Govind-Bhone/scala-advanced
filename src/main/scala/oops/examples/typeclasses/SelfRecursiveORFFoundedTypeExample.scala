package oops.examples.typeclasses

/**
  * Created by govind.bhone on 6/3/2017.
  */
/*
suppose we wanted to restrict function to pass particular type subtypes then we use self recursive
 or f bounded types

// naive impl, Fruit is NOT self-recursively parameterised

trait Fruit {
  final def compareTo(other: Fruit): Boolean = true // impl doesn't matter in our example, we care about compile-time
}

class Apple  extends Fruit
class Orange extends Fruit

val apple = new Apple()
val orange = new Orange()

apple compareTo orange // compiles, but we want to make this NOT compile!


In above example we are able to compare apple with orange but we can restrict this using f bounded declaration .
 */


object SelfRecursiveORFBoundedTypeExample extends App{

  trait Fruit[T <: Fruit[T]] {
    final def compareTo(other: Fruit[T]): Boolean = true // impl doesn't matter in our example
  }

  class Apple  extends Fruit[Apple]
  class Orange extends Fruit[Orange]

  val apple = new Apple
  val orange = new Orange

  apple compareTo apple

  //Error
  //apple compareTo orange
}
