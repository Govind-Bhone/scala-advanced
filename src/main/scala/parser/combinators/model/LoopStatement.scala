package parser.combinators.model

/**
  * Created by govind.bhone on 5/23/2017.
  */
case class LoopStatement(times: Int, statements: List[Statement]) extends Statement