package scala.dynamics

/**
  * Created by govind.bhone on 5/9/2017.
  */
/*
Scalas type Dynamic allows you to call methods on objects that don't
exist or in other words it is a replica of "method missing" in dynamic languages.

In fact, one can implement four different methods:

selectDynamic - allows to write field accessors: foo.bar
updateDynamic - allows to write field updates: foo.bar = 0
applyDynamic - allows to call methods with arguments: foo.bar(0)
applyDynamicNamed - allows to call methods with named arguments: foo.bar(f = 0)

 */

import scala.language.dynamics

object DynamicExample extends App {

  //===========================selectDynamic =====================

  class Calculator extends Dynamic {
    def selectDynamic(name: String) = name
  }

  val calc = new Calculator()
  calc.selectDynamic("bonus")
  calc.salary
  calc.addition


  //============================updateDynamic===========================

  class DynImpl extends Dynamic {
    var map = Map.empty[String, Int]

    def selectDynamic(name: String) = {
      map get name getOrElse sys.error("Method not initialized yet")
    }

    def updateDynamic(name: String)(amount: Int) {
      map += name -> amount
    }
  }

  val impl = new DynImpl()


}
