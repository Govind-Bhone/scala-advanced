package oops.examples.typeclasses

/**
  * Created by govind.bhone on 6/3/2017.
  */
object TypeAliasExample4 extends App {

  sealed trait Animal

  trait Mamal extends Animal

  trait Birds extends Animal

  case class Human() extends Mamal

  case class Elephant() extends Mamal

  case class Parrot() extends Birds

  case class Sparrow() extends Birds


  trait AnimalContainer {
    type A

    def setName(name: A):Unit
  }

  trait MamalContainer extends AnimalContainer {
    type A >: Mamal
  }

  trait BirdsContainer extends AnimalContainer {
    type A >: Birds
  }


  val mamals = new AnimalContainer with MamalContainer {
    override def setName(name: A): Unit =  System.out.println(s"$name is set")
  }

  val birds = new AnimalContainer with BirdsContainer {
    override def setName(name: A): Unit =  System.out.println(s"$name is set")
  }

  //Gives Error
  //  val birds1 = new AnimalContainer with BirdsContainer{
  //    override def getName = Human
  //  }

  mamals.setName(new Human)
  //birds.setName(new Human)
  birds.setName(new Parrot)
}
