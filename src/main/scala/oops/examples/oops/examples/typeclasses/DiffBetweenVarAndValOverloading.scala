package oops.examples.oops.examples.typeclasses

/**
  * Created by govind.bhone on 5/8/2017.
  */
/*
We can't override mutable variable but we can assign value to it .
*/
class Vehicle {
  var speed: Int = 60

}

class Bike extends Vehicle {
  //override var speed: Int = 100
  speed = 100

  def show() {
    println(speed)
  }
}

object MainObject {
  def main(args: Array[String]) {
    var b = new Bike()
    b.show()
  }
}
