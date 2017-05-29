package app.design

/**
  * Created by govind.bhone on 5/23/2017.
  */

case class BankAccountDbHandler(balance: Money) {

  def debit(amount: Money) = {
    // <2>
    assert(balance >= amount,
      s"Overdrafts are not permitted, balance = $balance, debit = $amount")
    new BankAccountDbHandler(balance - amount)
  }

  def credit(amount: Money) = {
    // <3>
    new BankAccountDbHandler(balance + amount)
  }
}

object BankAccountDbHandler {
  def main(args: Array[String]) = {
    import scala.util.Try

    Seq(-10, 0, 10) foreach (i => println(f"$i%3d: ${Try(Money(i))}"))

    val ba1 = BankAccountDbHandler(Money(10.0))
    val ba2 = ba1.credit(Money(5.0))
    val ba3 = ba2.debit(Money(8.5))
    val ba4 = Try(ba3.debit(Money(10.0)))

    println(
      s"""
         |Initial state: $ba1
         |After credit of $$5.0: $ba2
         |After debit of $$8.5: $ba3
         |After debit of $$10.0: $ba4""".stripMargin)
  }
}

