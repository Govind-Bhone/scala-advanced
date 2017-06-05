package oops.examples.typeclasses

import oops.examples.typeclasses.SelfRecursiveORFBoundedTypeExample2.Account.MalignantAccount

/**
  * Created by govind.bhone on 6/3/2017.
  */
object SelfRecursiveORFBoundedTypeExample2 extends App {

  trait Account[T <: Account[T]] {
    def addFunds(amount: BigDecimal): T
  }

  class BrokerageAccount(total: BigDecimal) extends Account[BrokerageAccount] {
    override def addFunds(amount: BigDecimal) = new BrokerageAccount(total + amount)
  }

  class SavingsAccount(total: BigDecimal) extends Account[SavingsAccount] {
    override def addFunds(amount: BigDecimal) = new SavingsAccount(total + amount)
  }


  object Account {

    val feePercentage = BigDecimal("0.02")
    val feeThreshold = BigDecimal("10000.00")


    def deposit[T <: Account[T]](amount: BigDecimal, account: T): T = {
      if (amount < feeThreshold) account.addFunds(amount - (amount * feePercentage))
      else account.addFunds(amount)
    }

    def debitAll[T <: Account[T]](amount: BigDecimal, accounts: List[T] forSome {type T <: Account[T]})
    : List[T forSome {type T <: Account[T]}] = {
      accounts.asInstanceOf[List[T]] map {
        _.addFunds(-amount)
      }


    }

    class MalignantAccount extends Account[SavingsAccount] {
      def addFunds(amount: BigDecimal) = new SavingsAccount(-amount)

    }


  }

    Account.deposit(BigDecimal("10.00"), new MalignantAccount)

}
