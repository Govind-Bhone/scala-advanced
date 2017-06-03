package oops.examples

/**
  * Created by govind.bhone on 6/3/2017.
  */
package object typeclasses extends RedApples with GreenApples {

  val redApples = List(red1, red2)
  val greenApples = List(green1, green2)
}

trait RedApples {
  val red1, red2 = "red"
}

trait GreenApples {
  val green1, green2 = "green"
}


