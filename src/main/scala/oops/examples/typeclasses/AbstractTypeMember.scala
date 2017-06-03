package oops.examples.typeclasses

/**
  * Created by govind.bhone on 6/3/2017.
  */
object AbstractTypeMember extends App {

  trait SimpleContainer {
    type A //Abstract type Member

    def value: A
  }

  object IntContainer extends SimpleContainer{
    type A =Int

    override def value: Int = 100
  }

  //new SimpleContainer  //cannot be instantiate
  println(IntContainer.value)
}
