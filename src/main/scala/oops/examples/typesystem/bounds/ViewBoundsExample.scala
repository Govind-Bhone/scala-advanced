package oops.examples.typesystem.bounds

/**
  * Created by govind.bhone on 5/26/2017.
  */
object Serialization {

  case class Writable(value: Any) {
    def serialized: String = s"-- $value --" // <1>
  }

  implicit def fromInt(i: Int) = Writable(i)

  // <2>
  implicit def fromFloat(f: Float) = Writable(f)

  implicit def fromString(s: String) = Writable(s)
}

import Serialization._

object RemoteConnection {
  // <3>
  def write[T <% Writable](t: T): Unit = // <4>
    println(t.serialized) // Use stdout as the "remote connection"
}


object ViewBoundsExample extends App {
  RemoteConnection.write(100) // Prints -- 100 --
  RemoteConnection.write(3.14f) // Prints -- 3.14 --
  RemoteConnection.write("hello!") // Prints -- hello! --
  // RemoteConnection.write((1, 2))
}