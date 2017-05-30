package oops.examples.oops.examples.typeclasses

/**
  * Created by govind.bhone on 5/30/2017.
  */

/*Consider two categories C1 and C2; then a functor F is a structure-preserving mapping between these categories
but what does that mean? A mapping between categories simply means that

every object A ∈ C1 is mapped to to an object F(A) ∈ C2 and
every map A → B between two objects A, B ∈ C1 is mapped to a map F(A) → F(B) between two objects F(A), F(B) ∈ C2.


Again, ->> and ->>> as well as F are type constructors.

*/


object CategoryExample3 extends App {

  trait GenericFunctor[->>[_, _], ->>>[_, _], F[_]] {
    def fmap[A, B](f: A ->> B): F[A] ->>> F[B]
  }

  trait Functor[F[_]] extends GenericFunctor[Function, Function, F] {
    final def fmap[A, B](as: F[A])(f: A => B): F[B] =
      fmap(f)(as)
  }

  object ListFunctor extends Functor[List] {
    def fmap[A, B](f: A => B): List[A] => List[B] = as => as map f
  }
}
