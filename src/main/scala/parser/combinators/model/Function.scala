package parser.combinators.model

/**
  * Created by govind.bhone on 5/23/2017.
  */
import scala.collection.mutable.HashMap

case class Function(name: String, arguments: Map[String, Int], statements: List[Statement], val returnValue: Expr)
