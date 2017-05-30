package oops.examples.typesystem.phantomTypes

/**
  * Created by govind.bhone on 5/29/2017.
  */

/*

Phantom types are called this way, because they never get instantiated. Really? So what are they good for? Simply to
encode type constraints, i.e. prevent some code from being compiled in certain situations.

Let’s look at an example: Software developers are known for their vast need of coffee. So let’s define the
class Hacker that defines the two methods hackOn and drinkCoffee.


class Hacker {

  def hackOn: Hacker = {
    println("Hacking, hacking, hacking!")
    new Hacker
  }

  def drinkCoffee: Hacker = {
    println("Slurp ...")
    new Hacker
  }
}


Of course the above implementation doesn’t encode the following constraint which is imposed by the very nature of
human beings in general and software developer in particular: After hacking a Hacker can’t continue doing so, but needs
to drink some coffee.
Put another way, we should not be able to call hackOn again on the Hacker returned by a previous call of hackOn:

scala> val hacker = new Hacker
hacker: Hacker = Hacker@3fb4f649

scala> hacker.hackOn.hackOn // This should not compile, but it does!
Hacking, hacking, hacking!
Hacking, hacking, hacking!
res0: Hacker = Hacker@2ff5659e

We could solve this problem by introducing two subclasses of Hacker – e.g. ExhaustedHacker and CaffeinatedHacker –
which only define the respective methods returning the other subclass. But sometimes this is not a viable option, e.g.
if we need subclassing to implement some other concern. Just think of
a class hierarchy of animals or such: In this case the former approach would lead to an explosion of classes.

Therefore let’s implement the two states a hacker can be in using type parameters.
First we define the possible states in the companion object:

Notice that we don’t define any concrete subclasses, hence nobody will
ever be able to instantiate State, State.Caffeinated or State.Decaffeinated.

Next we add a type parameter to Hacker and constrain it to a subtype of Hacker.State using an upper bound:


 */
object PhantomTypeExample1 extends App {

  /*  object Hacker {

      sealed trait State

      object State {

        sealed trait Caffeinated extends State

        sealed trait Decaffeinated extends State

      }

    }

    class Hacker[S <: Hacker.State] {

      import Hacker._

      def hackOn: Hacker[State.Decaffeinated] = {
        println("Hacking, hacking, hacking!")
        new Hacker
      }

      def drinkCoffee: Hacker[State.Caffeinated] = {
        println("Slurp ...")
        new Hacker
      }
    }*/

  /*
  Unfortunately we’re not yet there:

scala> val hacker = new Hacker[Hacker.State.Caffeinated]
hacker: Hacker[Hacker.State.Caffeinated] = Hacker@33e5ccce

scala> hacker.hackOn
Hacking, hacking, hacking!
res0: Hacker[Hacker.State.Decaffeinated] = Hacker@34340fab

scala> hacker.hackOn.hackOn  // This should not compile, but it still does!
Hacking, hacking, hacking!
Hacking, hacking, hacking!
res1: Hacker[Hacker.State.Decaffeinated] = Hacker@3b6eb2ec

While calling hackOn returns a decaffeinated hacker, we’re still able to call hackOn a second time.
This is not a surprise, because we haven’t constrained calling any of the two methods yet.



implicit defines more better than bound
object =:= {
   implicit def tpEquals[A]: A =:= A = singleton_=:=.asInstanceOf[A =:= A]
}
   */


  object Hacker {

    sealed trait State

    object State {

      sealed trait Caffeinated extends State

      sealed trait Decaffeinated extends State

    }

    def caffeinated: Hacker[State.Caffeinated] = new Hacker

    def decaffeinated: Hacker[State.Decaffeinated] = new Hacker
  }

  class Hacker[ S <: Hacker.State] private {

    import Hacker._

    // using type bound

/*    def hackOn[ T >: S <: State.Caffeinated]: Hacker[State.Decaffeinated] = {
      println("Hacking, hacking, hacking!")
      new Hacker
    }

    def drinkCoffee[ T >: S <: State.Decaffeinated]: Hacker[State.Caffeinated] = {
      println("Slurp ...")
      new Hacker
    }*/

    //using implicit evidence
    def hackOn(implicit ev: S =:= State.Caffeinated): Hacker[State.Decaffeinated] = {
      println("Hacking, hacking, hacking!")
      new Hacker
    }

    def drinkCoffee(implicit ev: S =:= State.Decaffeinated): Hacker[State.Caffeinated] = {
      println("Slurp ...")
      new Hacker
    }
  }

  Hacker.caffeinated.hackOn.drinkCoffee.hackOn.drinkCoffee
  //Hacker.caffeinated.hackOn.hackOn

}
