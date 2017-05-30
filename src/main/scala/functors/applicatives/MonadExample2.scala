package functors.applicatives

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by govind.bhone on 5/29/2017.
  */
/*
Monads are for sequencing dependent contextual computations.

def f[A](a: A): M[A]

So obviously we’re talking about parameterized types like Option[A] or Future[A]. These types provide some sort of context
for the type they are parameterized with. An Option[A] expresses the fact that there might or might not be a value of type A.
And the context a Future[A] defines is an asynchronous – i.e. delayed and potentially erroneous – computation of type A.

def f(n: Int): Future[Int] = Future(n + 1) // Not really rocket science ...
def g(n: Int): Future[Int] = Future(n - 1) // Can you guess what I have in mind?

Imagine we want to first call f with some value and then g with the value contained in f‘s result.
Put another way: we want to sequence the dependent computations g and f by somehow pulling the value out of the context of the
result of the first computation and passing it to the second.

Now we are facing the problem that a future might hold a value or not. This is, because its value is determined asynchronously
and the future might not yet be completed. And if something goes wrong, a future will hold a failure instead of a value.
Therefore simply grabbing into the future to pull out the value won’t work.


This is where monads shine, because when we say that some parameterized type M[A]
is a monad that means that there is a function called flatMap which takes a value of M[A] and a function
A => M[B] and returns a value of M[B]:

def flatMap[A, B](ma: M[A])(f: A => M[B]): M[B]

Getting back to our example, as a Future is a monad (well, monadic enough), it has a flatMap method.
Therefore we can sequence f and g the following way:


 */

object MonadExample2 extends App {

  def f(n: Int): Future[Int] = Future(n + 1) // Not really rocket science ...
  def g(n: Int): Future[Int] = Future(n - 1) // Can you guess what I have in mind?

  val n = f(42).flatMap(g) // Same as f(42).flatMap(n => g(n))
  assert(Await.result(n, 1.second) == 42)

  def h(n: Int): Future[Int] = Future(n * 2)

  val m = for {
    m1 <- f(42)
    m2 <- g(m1)
    m3 <- h(m2)
  } yield m3
  assert(Await.result(m, 1.second) == 84)
}
