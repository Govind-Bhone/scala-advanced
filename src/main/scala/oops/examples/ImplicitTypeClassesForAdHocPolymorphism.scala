package oops.examples

/**
  * Created by govind.bhone on 5/30/2017.
  */

/*
Much better! Looking at the implicit parameter we see that its name is of no significance.
What we actually need is just an instance of the type Adder to which we can delegate the task
of performing the addition. Since it is marked implicit, we can just “summon” it from the context and use it. There is a function in the standard library which does exactly that:

def implicitly[T](implicit e: T) = e

def combine[A](x: A, y: A)(implicit adder: Adder[A]): A =
  implicitly[Adder[A]].add(x, y)

Since this is a commonly used idiom, Scala provides syntactic sugar for declaring
implicit parameters like this, which is called a context bound:

def combine[A: Adder](x: A, y: A): A =
  implicitly[Adder[A]].add(x, y)


 */
object ImplicitTypeClassesForAdHocPolymorphism extends App {

  object Adder {
    def apply[A: Adder]: Adder[A] = implicitly
  }

  trait Adder[A] {
    def add(x: A, y: A): A
  }

  implicit object IntAdder extends Adder[Int] {
    override def add(x: Int, y: Int) = x + y
  }

  implicit object StringAdder extends Adder[String] {
    override def add(x: String, y: String) = x + y
  }

  def combine1[A](x: A, y: A)(implicit adder: Adder[A]): A =
    implicitly[Adder[A]].add(x, y)

  def combine2[A: Adder](x: A, y: A): A =
    implicitly[Adder[A]].add(x, y)

  def combine3[A: Adder](x: A, y: A): A =
    Adder[A].add(x, y)

  println(combine1("govind","ashish"))
  println(combine2("govind","ashish"))
  println(combine3("govind","ashish"))


}
