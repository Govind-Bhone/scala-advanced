package functors.applicatives

/**
  * Created by govind.bhone on 5/29/2017.
  */

/*
Monad feeds returned value of fun as wrapped value to wrapped value
Monad is type class . A monad is data type that implements flatMap

so lets consider we have constructor C[_] and two types A and B we want to apply function of
C[A]=>C[B]

so we need adequate transformation
    (A=>C[B]) => C[A]=>C[B]

And we need to define flatMap

def flatMap[A,B](a:A=>C[B]):C[A]=>C[B]


Difference between Monad and Monoids
Ans : Given type T , a binary operation op:(T,T)=>T and instance zero :T
then the triple ( T , op,Zero ) is called Monoids (reduce). it has following properties
1. Neutral element
2. Associativity

Monoids instances already knows how to combine instances of type T in given context

whereas Monoid are simply wraps value of type T and then provides transformation methods

Monads are more powerful than applicatives as with the help of monad we can make result of second computation depends on
result of first computation in sequencial  computation .

 */
object MonadExample1 extends App{

  case class Box[A](value:A)

  def func(b:String):Box[Int]=Box(b.length)

  def flatMap[A,B](func:A=>Box[B]):Box[A]=>Box[B]=(a:Box[A])=>func(a.value)

  println(flatMap(func)(Box("govind")))

}
