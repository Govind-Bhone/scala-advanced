package functors.applicatives

/**
  * Created by govind.bhone on 5/7/2017.
  */

/*
Applicatives are also often referred as generalized Functors.

As per the definition, Functors are used for lifting computations to a category and they work perfectly for functions with
single variable like:

 val incr =(num:Int)=>num+1

But what if we have a function with two or more parameters like the one defined below:

 val sum =(num1:Int)=>(num2:Int)=>num1+num2

Functors are restricted to lift only functions of arity -1 into computational context. So then we have the problem of how
to lift a function of arity -2 into computational context.

Let us see this by above example: Let us invoke fmap to partially apply an arity-2 function(say sum) to its first argument within the computational context of an Option.

 fmap(Option(20))(sum)

The resultant is of type  Option[(Int) => Int], the remaining partially applied function has been wrapped inside Option. Now if we wish to give this to fmap
which expects a pure function (not a lifted one) we get STUCK !

  def pure[A](a: A): F[A]
  def apply[A, B](f: F[A => B]): F[A] => F[B]
  final def apply[A, B](fa: F[A])(f: F[A => B]): F[B] = apply(f)(fa)
  override def fmap[A, B](f: A => B): F[A] => F[B] = apply(pure(f))


 */
object ApplicativeExample1 extends App{

  //functor --(A=>B) => (C[A]=>C[B])

  case class MyBox[T](val value:T)

  def map[A,B]( rawfunc:A=>B ) : MyBox[A]=>MyBox[B] = (a:MyBox[A]) => MyBox( rawfunc(a.value) )

  val boxedstring:MyBox[String] = MyBox("Hello") // a boxed value

  def rawLengthOf(a:String) : Int = a.length // the raw function we want to use

  def transformedLenghtOf = map(rawLengthOf _) // applying the transformation, so we get our new function

  val result:MyBox[Int] = transformedLenghtOf( boxedstring ) // applying the new function

  println("Functor result is "+result)

  //monad--(A=>C[B]) => (C[A]=>C[B])

  def lengthOf(a:String) = new MyBox( a.length ) // a function which takes a raw type but boxes the result itself
  def flatMap[A,B]( func:A=>MyBox[B] ): MyBox[A]=>MyBox[B] = (a:MyBox[A]) => func( a.value )
  val transformedLenghtOf1 = flatMap(lengthOf) // applying the transformation, so we get our new function
  val result1:MyBox[Int] = transformedLenghtOf1( boxedstring ) // applying the new function
  println("Monad result is "+result1)

 //Applicative --( C[A=>B] ) => ( C[A]=>C[B] )

  val boxedLengthOf:MyBox[String=>Int] = MyBox( rawLengthOf _)
  def apply[A,B](b:MyBox[A=>B]): MyBox[A]=>MyBox[B] = (a:MyBox[A]) => MyBox(b.value(a.value))
  val transformedLenghtOf2 = apply(boxedLengthOf) // applying (*haha*) the transformation, to get our new function
  val result2:MyBox[Int] = transformedLenghtOf2( boxedstring ) // applying the new function
  println("Applicative result is "+result2)


  def map[A,B](boxedFunc:MyBox[A=>B]):MyBox[A]=>MyBox[B]=apply(boxedFunc)
  println("Applicative result is "+map(boxedLengthOf)(boxedstring))

}
