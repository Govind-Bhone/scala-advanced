package oops.examples.typesystem.inference

/**
  * Created by govind.bhone on 5/30/2017.
  */
object TypeInferenceExample extends App{

  trait Thing

  def getThing = new Thing { }

  // without Type Ascription, the type is infered to be `Thing`
  val infered = getThing

  // with Type Ascription
  val thing: Thing = getThing

}
