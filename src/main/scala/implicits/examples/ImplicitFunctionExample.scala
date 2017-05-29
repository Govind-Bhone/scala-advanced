package implicits.examples

import java.awt.Button
import java.awt.event.{ActionEvent, ActionListener}

/**
  * Created by govind.bhone on 5/9/2017.
  */

/*
Don't use implicits with primitive data type ,if you want then wrap it within some wrapper class

so this feature is used by library developers , so in scala 2.10 we need to import explicitly using
import language.ImplicitConversions
*/
object ImplicitFunctionExample extends App {

  //===================Traditional way of writing ==============
  val button = new Button()
  button.addActionListener(new ActionListener {
    override def actionPerformed(e: ActionEvent) = println("Button is pressed")
  })

  //===================Functional way of writing ==============
  def function2ActionListener(fn: ActionEvent => Unit): ActionListener = new ActionListener {
    override def actionPerformed(e: ActionEvent) = fn(e)
  }

  button.addActionListener(function2ActionListener((_: ActionEvent) => println("button is pressed")))

  //===================Implicits way of writing ==============
  implicit def fun2ActionListener(fn: ActionEvent => Unit): ActionListener = new ActionListener {
    override def actionPerformed(e: ActionEvent) = fn(e)
  }

  button.addActionListener((_: ActionEvent) => println("Button is pressed"))


  //Rules for Implicits
  //1.Marking Rule only methods who mark as implicits are available

  def dbtoString(d:Double):String=d.toString
  implicit  def intToString(i:Int):String=i.toString


  //2. Scope Rule --it will get applied only when it get invited into current scope
  case class Dolr()
  case class Pd()
  object Dollar{
    // define in companion object is available to related class but other object implicits
    //we need to import explicitly , we can even put it in trait and then mixin that trait
     implicit  def dollarToPound(x:Dolr):Pd= ???
  }

  class Dollar(){

  }

  // 3. one at a time Rule : one chain up to three implicit conversion one after the another can't possible
  //compiler  will not pipeline it

  //dollartoPound(dollaToRupee(x))

  //4. Explicit First rule --
}
