package parser.combinators

/**
  * Created by govind.bhone on 5/23/2017.
  */
import parser.combinators.model.Program

import scala.io.Source

object SmallLanguage {
  def main(args: Array[String]) {
    val inputFile = Source.fromFile("./src/main/resources/language.txt")
    val inputSource = inputFile.mkString

    val parser = new SmallLanguageParser
    parser.parseAll(parser.program, inputSource) match {
      case parser.Success(r, n) => {
        val interpreter = new Interpreter(r.asInstanceOf[Program])

        try {
          interpreter.run
        } catch {
          case e: RuntimeException => println(e.getMessage)
        }
      }
      case parser.Error(msg, n) => println("Error: " + msg)
      case parser.Failure(msg, n) => println("Error: " + msg)
      case _ =>
    }
  }
}
