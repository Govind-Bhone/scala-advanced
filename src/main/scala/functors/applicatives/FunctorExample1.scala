package functors.applicatives

/**
  * Created by govind.bhone on 5/7/2017.
  */

/*

A functor is a typed data structure that encapsulates some value(s).
From a syntactic perspective a functor is a container with the following API:

import java.util.function.Function;
interface Functor<T> {
    <R> Functor<R> map(Function<T, R> f);
}

map operation on functor always returns new functor ,

In programming the functors come into play when we have types or values wrapped inside context or containers.
This wrapping up inside context, blocks application of normal functions on the values.
This happens because the result on application of function is dependent on the context.

The solution to above scenario is a function that knows how to apply functions to the
values wrapped in a context(map function). Internally speaking this function should have a potential to

1.unwrap(fetch) the value from the context
2. apply the function onto the value
3. re-wrap the resultant into context

A Functor is defined by a type Constructor F[_] together with a function

                                  map :(A=>B) => F[A]=>F[B]

where the following holds true :

1. map identity means the same thing as identity
2.(map f) compose (map g) is equivalent of map ( f compose g)

Functor(s) are not in-built in Scala i.e as abstractions they do not exist in Scala but functor like behavior is build into Scala.
The invocations of map on various types in scala is consistent with the definition of Functors. The example below
illustrates the point :

example ,
def sum(num:Int)=num +10
Some(2).map(sum)

 */
object FunctorExample1 extends App {

  def sum(num: Int) = num + 10

  println(Some(2).map(sum))


  //scalaz functor

  import scalaz.Functor

  case class Container[A](first: A, second: A)

  implicit val containerFunctor = new Functor[Container] {

    def map[A, B](fa: Container[A])(f: A => B): Container[B] = Container(f(fa.first), f(fa.second))

  }

  def sum2(a: Int) = Math.sqrt(a)

  println(containerFunctor.map(Container(10, 20))(sum2))


  implicit val OptionFunctor = new Functor[Option] {

    def fmap[A, B](f: A => B): Option[A] => Option[B] = option => option map f

    override def map[A, B](fa: Option[A])(f: (A) => B): Option[B] = ???
  }

  implicit val ListFunctor = new Functor[List] {

    def fmap[A, B](f: A => B): List[A] => List[B] = list => list map f

    override def map[A, B](fa: List[A])(f: (A) => B): List[B] = ???
  }

  println(OptionFunctor.fmap(sum)(Some(10)))

  println(ListFunctor.fmap(sum2)(List(1, 2, 3, 4, 5)))
}
