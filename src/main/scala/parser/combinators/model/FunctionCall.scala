package parser.combinators.model

/**
  * Created by govind.bhone on 5/23/2017.
  */
import scala.collection.mutable.HashMap
import scala.util.parsing.input.Positional

case class FunctionCall(name: String, values: Map[String, Expr]) extends Expr with Statement
