package parser.combinators

import scala.util.parsing.combinator.RegexParsers

/**
  * Created by govind.bhone on 5/23/2017.
  */
/*

grammer for arithmetic evaluation

digit ::= "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"
number ::= digit | digit number
operator ::= "+" | "-" | "*" | "/"
expr ::= number (operator expr)?

*/


class ExprParser extends RegexParsers {
  val number: scala.util.matching.Regex = """[0-9]+""".r

  def expr1: Parser[Any] = number ~ opt(operator ~ expr1)

  def operator: Parser[Any] = "+" | "-" | "*" | "/"

  def expr: Parser[Int] = (number ^^ {
    _.toInt
  }) ~ opt(operator ~ expr) ^^ {
    case a ~ None => a
    case a ~ Some("*" ~ b) => a * b
    case a ~ Some("/" ~ b) => a / b
    case a ~ Some("+" ~ b) => a + b
    case a ~ Some("-" ~ b) => a - b
  }
}

object ParserCombinatorsForArithemeticExpression {
  def main(args: Array[String]) {
    val parser = new ExprParser
    val result = parser.parseAll(parser.expr, "9*8+21/7")
    println(result.get)

    val result1 = parser.parseAll(parser.expr1, "9*8+21/7")
    println(result1.get)
  }
}
