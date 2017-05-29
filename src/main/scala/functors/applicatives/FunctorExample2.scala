package functors.applicatives

import functors.applicatives.ApplicativesExample2.Box

import scalaz.Functor

/**
  * Created by govind.bhone on 5/29/2017.
  */

/*
Problem :
we have simple value and we applies normal function to it . (2 .+(3) [ possible ]
but if value is in some wrappper context ( option , List , Set etc) then we need map for applying function .

Functors :
1. Functor is type class . Functor is any datatype that defines how map applies to it .
so lets consider we have constructor C[_] and two types A and B we want to apply function of
C[A]=>C[B]

so we need adequate transformation
    (A=>B) => C[A]=>C[B]

and we need to define map

def map[A,B](A=>B):(F[A]=>F[B])

map operation on functor always returns new functor

 */
object FunctorExample2 extends App {

  //Examples options streams

  object OptionFunctor extends Functor[Option] {
    override def map[A, B](fa: Option[A])(f: (A) => B): Option[B] = fa map f

    //def map1[A, B](f: (A) => B): Option[A] => Option[B] = option => option map f
  }

  import OptionFunctor._

  println(map(Option(3))(f => f * 2))


  //==============Example 2========================

  case class Box[A](value:A)

  def func(b:String):Int=b.length

  def map1[A,B](func:A=>B):Box[A]=>Box[B]=(a:Box[A])=>Box(func(a.value))

  println(map1(func)(Box("govind")))

}
