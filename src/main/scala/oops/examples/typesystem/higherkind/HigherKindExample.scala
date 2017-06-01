package oops.examples.typesystem.higherkind

/**
  * Created by govind.bhone on 5/31/2017.
  */
object HigherKindExample extends App {

  trait Add[T] {
    // <1>
    def add(t1: T, T2: T): T
  }

  object Add {
    // <2>
    implicit val addInt = new Add[Int] {
      def add(i1: Int, i2: Int): Int = i1 + i2
    }

    implicit val addIntIntPair = new Add[(Int, Int)] {
      def add(p1: (Int, Int), p2: (Int, Int)): (Int, Int) =
        (p1._1 + p2._1, p1._2 + p2._2)
    }
  }

  def sumSeq[T: Add](seq: Seq[T]): T = // <2>
    seq reduce (implicitly[Add[T]].add(_, _))

  sumSeq(Vector((1, 10), 2 -> 20, 3 -> 30)) // Result: (6,60)
  sumSeq(1 to 10)

  // Result: 55
  //sumSeq(Option(2)) //Error


 import scala.language.higherKinds

  trait Reduce[T, -M[T]] {
    def reduce(m: M[T])(f: (T, T) => T): T
  }

  object Reduce {
    implicit def seqReduce[T] = new Reduce[T, Seq] {
      def reduce(seq: Seq[T])(f: (T, T) => T): T = seq reduce f
    }

    implicit def optionReduce[T] = new Reduce[T, Option] {
      def reduce(opt: Option[T])(f: (T, T) => T): T = opt reduce f
    }
  }


  def sum[T : Add, M[T]](container: M[T])(implicit red: Reduce[T,M]): T =
    red.reduce(container)(implicitly[Add[T]].add(_,_))

  println(sum(Vector(1 -> 10, 2 -> 20, 3 -> 30)) )                    // Result: (6,60)
  println(sum(1 to 10))           // Result: 55
  println(sum(Option(2)))                                             // Result: 2
  //Error//println(sum[Int,Option](None))

}
