package oops.examples.typeclasses

import scala.collection.mutable

/**
  * Created by govind.bhone on 6/3/2017.
  */
object TypeAliasExample extends App {

  type User = String
  type Age = Int

  var data: mutable.HashMap[User, Age] = new scala.collection.mutable.HashMap[User, Age]()

  data.put("govind", 26)
  data.+=("ashish" -> 27)
  //data=data.+(("sachin",29)) // it will not work with type alias

  println(data)

  //case classes type aliasing
  // in below example we can access Employee using Emp type alias but
  // we can't do that when we use case class instead of normal class because
  // we are not type aliasing companion objects

  class Employee(val ename: String, val eage: Int, val eSal: Int) {}

  type Emp = Employee

  val employee = new Emp("govind", 26, 28000)

  println(employee.eage)


  case class Employee1(val ename: String, val eage: Int, val eSal: Int)

  type Emp1 = Employee1

  //Error
  //val employee1 = Emp1("govind", 25, 27000)

  //works
  val employee1 = new Emp1("govind", 25, 27000)

}
