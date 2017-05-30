package oops.examples.oops.examples.typeclasses

/**
  * Created by govind.bhone on 5/30/2017.
  */
/*
Let’s start with a simple function for combining two integers by adding them:

def combine(x: Int, y: Int): Int = x + y

Now suppose we want to use this function not only for integers, but for strings, too. We want it to be polymorphic,
 i.e. applicable to different types,
and implemented accordingly: addition for integers, concatenation for strings.


Scala provides multiple means to implement polymorphism. The simplest one is overloading:

def combine(x: String, y: String): String = x + y

We can overload our function similarly for more types. However, looking at the implementation, we cannot
overlook the fact that they all look the same: basically they just invoke
the “+” operator on the parameters. Since duplication is bad, we ask ourselves: can we get rid of it?

What we really need is a generic function parameterized for different types. Hence we introduce a type parameter:

def combine[A](x: A, y: A): A = ???
But how do we implement it? Since we want to combine two values of some type A, it better be a type which
supports this kind of combination. Therefore we define such a type, name it Addable, and
restrict the type parameter A in our signature to accept only subtypes of it using an upper bound clause:

trait Addable[A] {
  def add(other: A): A
}

def combine[A <: Addable[A]](x: A, y: A): A = x.add(y)

Unfortunately, since we do not have control over Integer and String because
they are defined in the standard library, we cannot make them subtypes of Addable. defined as above

But not all is lost. In fact, we do not need Integer and String to be actual subtypes of Addable; it would be sufficient if they were merely convertible to it:

def combine[A <% Addable[A]](x: A, y: A): A = x.add(y)
Here we only specify using a view bound that the values of type A must be convertible to an Addable.


Although this approach works, it has some serious drawbacks. First, we must use implicit conversion,
even if we just want to perform a single operation. Second, it is not natural to define a binary operation on a
single value. It would be
much better to define it on a separate class. For these reasons, view bounds are deprecated in Scala since version 2.11.

 */
object AdhocPolyMorphismInScala extends App{

  trait Addable[A] {
    def add(other: A): A
  }

  implicit def intToAddable(x: Int): Addable[Int] = new Addable[Int] {
    override def add(other: Int) = x + other
  }

  implicit def stringToAddable(x: String): Addable[String] = new Addable[String] {
    override def add(other: String) = x + other
  }

  def combine[A <% Addable[A]](x: A, y: A): A = x.add(y)

  println(combine(10,20))
  println(combine("govind","bhone"))

}
