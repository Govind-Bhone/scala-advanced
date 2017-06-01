package reflection.example

/**
  * Created by govind.bhone on 5/31/2017.
  */
object AccessFieldsDynamicallyAccessingExample extends App {

  case class Employee(eid: Int, ename: String, eSal: Int)

  val employees = List(Employee(101, "govind", 100), Employee(102, "ashish", 110), Employee(103, "sunil", 120))

  def getMaxForProperty(propertyName: String): Int = {
    val salaryList = employees.map(employee => {
      val field = employee.getClass.getDeclaredField(propertyName)
      field.setAccessible(true)
      val value = field.get(employee)
      value.asInstanceOf[Int]
    })
    salaryList.reduceLeft(_ max _)
  }

  println(getMaxForProperty("eSal"))

}
