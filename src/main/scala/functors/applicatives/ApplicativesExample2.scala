package functors.applicatives

/**
  * Created by govind.bhone on 5/29/2017.
  */

/*
Like value wrapped in context we have functions wrapped in context too .

when value and functions wrapped in context then we can't directly used to apply a simple function . we
need an apply method . it knows how to apply a function wrapped in context

Applicative is type class . applicative is any datatype that defines how apply applies to it .

apply takes a functor that has function define in it and another functor
and then extract that function and maps it over second functor

so lets consider we have constructor C[_] and two types A and B we want to apply function of
C[A]=>C[B]

so we need adequate transformation
    (C[A=>B]) => C[A]=>C[B]

And we need to define apply

def apply[A,B](func:C[A=>B]):C[A]=>C[B]

*/


object ApplicativesExample2 extends App {

  case class Box[T](val value: T)

  def fun(input: String): Int = input.length

  val boundedFun: Box[String => Int] = Box(fun)

  def apply[A, B](boundedFunc: Box[A => B]): Box[A] => Box[B] = (a: Box[A]) => Box(boundedFunc.value(a.value))

  def map[A, B](boundedFun: Box[A => B]): Box[A] => Box[B] = apply(boundedFun)

  println(map(boundedFun)(Box("govind")))
}
