package oops.examples.typeclasses

import scala.collection.mutable.ArrayBuffer

/**
  * Created by govind.bhone on 6/1/2017.
  */
object UnifiedTypeSystemExample extends App {

  class Person

  val allThings = ArrayBuffer[Any]()

  val myInt = 42 // Int, kept as low-level `int` during runtime

  allThings += myInt // Int (extends AnyVal)
  // has to be boxed (!) -> becomes java.lang.Integer in the collection (!)

  allThings += new Person() // Person (extends AnyRef), no magic here


  var s = null
  var s1: Null = null

//  var s2: Any = None
//  var s3: Nothing = throw new Exception() // Nothing means method or expression never return anything , it get used for handling
//  //exception
//
//  val s4: Unit = () // unit is special return type like void in other languages
//
//  val s5: Unit = s3;


  //  Null might be used as a bottom type for any value that is nullable. An example is this:

  trait Zero[B]

  implicit def zeroNull[B >: Null] =
    new Zero[B] {
      def apply = null
    }

  // Nothing is used in the definition of None

  //object None extends Option[Nothing]
  //This allows you to assign a None to any type of Option because Nothing 'extends' everything.

  val x: Option[String] = None

  //val a:AnyVal = new Person() //Error
  val a: AnyRef = new Person()

  //Error
/*
    def function:Boolean = null

    val sFun = function
    println(sFun)
*/

  /*  val a10: Nothing = throw new NoSuchElementException

    val a11: Boolean = a10

    println(a11)*/


//  val a11: Nothing = throw new NoSuchElementException
//  val p1: Person = a11
//  println(p1)

  val p12: Person = null

  // val none :Null = p12 //Error


  def check(in: AnyVal) = ()

  check(42) // Int -> AnyVal
  check(13.37) // Double -> AnyVal

  //check(new Object) // -> AnyRef = fails to compile


  //Below statements compile fine because Nothing is also subtype of Int

  val thing: Int =
    if (true)
      42 // : Int
    else
      throw new Exception("Whoops!") // : Nothing


  val thing2: String =
    if (true)
      "101"  // : String
    else
      null    // : Null

  println(thing2)




  val thing3 =
    if (true)
      101  // : String
    else
      null    // : Null

  println(thing2)
}
