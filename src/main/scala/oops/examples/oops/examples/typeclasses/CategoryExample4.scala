package oops.examples.oops.examples.typeclasses

/**
  * Created by govind.bhone on 5/30/2017.
  */

/*

Let’s look at what happens if we call fmap to partially apply an arity-2 function to its first
argument within the computational context of an Option:

scala> val f = (x: Int) => (y: Int) => x + y + 10
f: (Int) => (Int) => Int = <function1>

scala> fmap(Option(1))(f)
res0: Option[(Int) => Int] = Some()
What we get back is an Option[Int => Int], i.e. the “rest” of the partially applied
function wrapped in an Option. Now we have a problem, because we cannot give this lifted function to
another call of fmap.

scala> fmap(Option(2))(fmap(Option(1))(f))
:13: error: type mismatch;
 found   : Option[(Int) => Int]
 required: (Int) => ?
       fmap(Option(2))(fmap(Option(1))(f))
Of course we cannot, because fmap expects a pure function, not a lifted one. And that’s the moment
when applicatives enter the stage. The idea is simple and follows intutively from what we have just seen:
Instead of fmap taking a pure function, an Applicative defines the method apply taking a lifted function.
And it defines the method pure to lift pure functions. Using these it is perfectly possible to partially apply
an arity-n function to all of its arguments within a computational context. Before we look at our example from
this new perspective, let’s code up our new abstraction:


 As you can see, there is a strong relation between functors and applicatives: Each applicative
    is a functor and by one of the laws for applicatives the following has to hold true: fmap = apply ο pure.
    Well, this law is pretty intuitive, because it makes sure we can use an applicative as a functor,
  i.e. for a pure arity-1 function, and it will behave as expected.

*/

object CategoryExample4 extends App {

  trait GenericFunctor[->>[_, _], ->>>[_, _], F[_]] {
    def fmap[A, B](f: A ->> B): F[A] ->>> F[B]
  }

  trait Functor[F[_]] extends GenericFunctor[Function, Function, F] {
    final def fmap[A, B](as: F[A])(f: A => B): F[B] =
      fmap(f)(as)
  }

  trait Applicative[F[_]] extends Functor[F] {

    def pure[A](a: A): F[A]

    def apply[A, B](f: F[A => B]): F[A] => F[B]

    final def apply[A, B](fa: F[A])(f: F[A => B]): F[B] = apply(fa)(f)

    override def fmap[A, B](f: A => B): F[A] => F[B] =
      apply(pure(f))
  }

  object Applicative {

    def pure[A, F[_]](a: A)(implicit applicative: Applicative[F]): F[A] =
      applicative pure a

    def apply[A, B, F[_]](fa: F[A])(f: F[A => B])(implicit applicative: Applicative[F]): F[B] =
      applicative.apply(fa)(f)

    implicit object OptionApplicative extends Applicative[Option] {

      override def pure[A](a: A): Option[A] =
        Option(a)

      override def apply[A, B](f: Option[A => B]): Option[A] => Option[B] =
        o => for {a <- o; p <- f} yield p(a)
    }

  }

  import Applicative._

  //  apply(Option(1))(apply(Option(2))(pure(f))

}
