package parser.combinators.model

/**
  * Created by govind.bhone on 5/23/2017.
  */
case class IfStatement(condition: Condition, trueBranch: List[Statement], falseBranch: List[Statement]) extends Statement
