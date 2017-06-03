package oops.examples.typeclasses

/**
  * Created by govind.bhone on 6/2/2017.
  */

/*
Scala `object`s are implemented via classes (obviously - as it’s the basic building block on the JVM),
but you’ll notice that we cannot get its type the same way as we would with an simple class…

I would surprisingly often get the question on how to pass an object into a method. Just saying obj: ExampleObj
won’t work because that’s already referring to the instance, so there’s a member called type which should be used in
such cases.

How it might look like in your code is explained by the below example:

 */
object TypeMemberForObjectTypeIn extends App {

  object ExampleObj

  val obj = ExampleObj

  def takeAnObject(obj: ExampleObj.type) = {

  }

  takeAnObject(ExampleObj)

  class ExampleClass

  val classEx = new ExampleClass

  def takeAnClass(obj: ExampleClass) = {

  }

  takeAnClass(classEx)

}
