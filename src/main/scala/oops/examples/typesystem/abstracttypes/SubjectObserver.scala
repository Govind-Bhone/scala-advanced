package oops.examples.typesystem.abstracttypes

/**
  * Created by govind.bhone on 5/26/2017.
  */
abstract class SubjectObserver {
  // <1>
  type S <: Subject
  // <2>
  type O <: Observer

  trait Subject {
    // <3>
    private var observers = List[O]()

    def addObserver(observer: O) = observers ::= observer

    def notifyObservers() = observers.foreach(_.receiveUpdate(this.asInstanceOf[S])) // <4>
  }

  trait Observer {
    // <5>
    def receiveUpdate(subject: S)
  }

}
