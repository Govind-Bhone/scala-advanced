package oops.examples.typeclasses

/**
  * Created by govind.bhone on 6/3/2017.
  */

sealed trait Animal

trait Mamal extends Animal

trait Birds extends Animal

case object Human extends Mamal

case object Elephant extends Mamal

case object Parrot extends Birds

case object Sparrow extends Birds


trait AnimalContainer {
  type A

  def getName: A
}

trait MamalContainer extends AnimalContainer {
  type A >: Mamal
}

trait BirdsContainer extends AnimalContainer {
  type A >: Birds
}

object TypeAliasExample3 extends App {

  val mamals = new AnimalContainer with MamalContainer {
    override def getName = Human
  }

  val birds = new AnimalContainer with BirdsContainer {
    override def getName = Sparrow
  }

  //Gives Error
  //  val birds1 = new AnimalContainer with BirdsContainer{
  //    override def getName = Human
  //  }

  println(mamals.getName)
  println(birds.getName)
}
