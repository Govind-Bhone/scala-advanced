package io.examples

import java.io.{BufferedWriter, File, FileWriter}

import scala.io.Source

/**
  * Created by govind.bhone on 5/30/2017.
  */
object ScalaFileGrepperReportGenerator extends App {

  val HOME_DIR_TO_SEARCH = "D:\\kohls\\ES_BigData"
  val SCALA_EXTENSION = ".scala"
  val JAVA_EXTENSION = ".java"
  val IMPORT_CLAUSE = "import"
  val IMPORT_JAVA_LIB = "import java"

  val file = new File("D:\\kohls\\ES_BigData\\dependency_report.txt")
  val bw = new BufferedWriter(new FileWriter(file))

  case class DependencyObject(fileName: String, importStatements: List[String])

  def getFileTree(f: File): Stream[File] =
    f #:: (if (f.isDirectory) f.listFiles().toStream.flatMap(getFileTree)
    else Stream.empty)

  val medadata = getFileTree(new File(HOME_DIR_TO_SEARCH))
    .filter(fileName => fileName.getName.endsWith(SCALA_EXTENSION) || fileName.getName.endsWith(JAVA_EXTENSION))
    .map(fileName => {
      val thirdPartyImportsStatement = Source.fromFile(fileName).getLines().toList
        .filter(_.contains(IMPORT_CLAUSE))
        .filterNot(_.contains(IMPORT_JAVA_LIB))
      DependencyObject(fileName.getAbsolutePath, thirdPartyImportsStatement)
    }).foreach(dependencyObject => {
    dependencyObject.importStatements.foreach(statement => {
      bw.write(dependencyObject.fileName + "-->" + statement + "\n")
      bw.flush()
    })

  })
  bw.close()
}
