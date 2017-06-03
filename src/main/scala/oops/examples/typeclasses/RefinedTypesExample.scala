package oops.examples.typeclasses

/**
  * Created by govind.bhone on 6/3/2017.
  */
object RefinedTypesExample extends App{

  //here in case class if we have not provided any parameters to constructor then
  // we need to instantiate object of it using new
  case class Entity(name:String){
    def persistForReal(): Unit ={
      System.out.println("Persisting the entity"+name)
    }
  }

  trait Persister{
    def doPersist(e:Entity)
  }

  // our refined instance (and type):
  val refinedMockPersister = new Persister {
    override def doPersist(e: Entity) = {
      e.persistForReal()
    }
  }

  refinedMockPersister.doPersist(Entity("govind"))
}
