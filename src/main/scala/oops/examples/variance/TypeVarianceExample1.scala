package oops.examples.variance

/**
  * Created by govind.bhone on 6/2/2017.
  */
/*
Variance, in general, can be explained as "type compatible-ness" between types, forming an extends relation. The most popular cases where you’ll have to deal with this is
when working with containers or functions (so… surprisingly often!).

A major difference from Java in Scala is, that container types are not-variant by default!
This means that if you have a container defined as Box[A], and then use it with a Fruit in place of the type parameter A, you will not be able to insert an Apple (which IS-A Fruit) into it.

Variance in Scala is defined by using + and - signs in front of type parameters.

It’s worth mentioning that while having immutable collections co-variant is safe, the same cannot be said about mutable collections. The classic example here is Array[T] which is invariant. Let’s look at what invariance means for us here, and how it saves us from mistakes:

// won't compile
val a: Array[Any] = Array[Int](1, 2, 3)
Such an assigment won’t compile, because of Array’s invariance. If this assignment would be
valid, we’d run into the problem of being able to write such code: a(0) = "" // ArrayStoreException!
 would fail with the dreaded ArrayStoreException.

We said that "most" immutable collections are covariant in scala. In case you’re curious, one example of
an immutable collection which stands out from that, and is invariant, it’s Set[A].


Name	Description	Scala Syntax
Invariant  C[T'] and C[T] are not related    C[T]
Covariant  C[T'] is a subclass of C[T]   C[+T]
Contravariant C[T] is a subclass of C[T'] C[-T]


Most immutable collections are covariant, and most mutable collections are invariant.
 */

object TypeVarianceExample1 extends App{


  //Covariant List

  class Fruit
  case class Apple() extends Fruit
  case class Orange() extends Fruit

  val l1: List[Apple] = Apple() :: Nil
  val l2: List[Fruit] = Orange() :: l1

  // and also, it's safe to prepend with "anything",
  // as we're building a new list - not modifying the previous instance

  val l3: List[AnyRef] = "" :: l2

  //Invariant

  // won't compile
  //val a: Array[Any] = Array[Int](1, 2, 3)
}
