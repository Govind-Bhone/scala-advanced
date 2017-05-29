package parser.combinators.model

/**
  * Created by govind.bhone on 5/23/2017.
  */
import scala.collection.mutable.HashMap

class Scope(val name: String, val parentScope: Scope) {
  var variables = new HashMap[String, Expr]
}
