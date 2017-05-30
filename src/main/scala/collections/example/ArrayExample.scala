package collections.example

/**
  * Created by govind.bhone on 5/29/2017.
  */
/*
scala collections provide lot of transformation methods like map ,flatmap , groupBy also
they are immutable so provide good performance but sometimes

Nevertheless there are some situations when we have to pay a substantial penalty for using these nice high-level
and thread-safe collections. Luckily Scala is a multi-paradigm language geared to real-world applications and hence lets
us pick the right tool among several for the job at hand: In these situations, when collections and functional
programming don’t give us the performance we need, we can use arrays and imperative programming.


Scala allows us to add extension methods to any existing type without subclassing, simply by defining an
implicit class that wraps a value of the type to be extended. By using a value class via extending AnyVal the
Scala compiler is able to avoid creating instances of the wrapper and instead inline everything, so there’s
negligible runtime overhead.

As we want to be able to call toHex and/or toHexString on any byte array, our implementation looks like this:
 */
object ArrayExample extends App {

  //Array[Byte](0, 1, 15, 16)
  val byteArray = Array[Byte](0, 1, 15, 16)
  println(byteArray.toList)

  // transform above array to array hex
  // Array[Char](0, 0, 0, 1, 0, F, 1, 0)

  implicit class ByteArrayOps(val bytes: Array[Byte]) extends AnyVal {
    def toHexString: String = new String(toHex)

    private def hexDigit(nibble: Int)={
      val hexDigit = Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
      hexDigit(nibble)
    }

    def toHex = bytes.flatMap { byte =>
      val high = hexDigit((byte & 0xF0) >>> 4)
      val low = hexDigit(byte & 0x0F)
      Array(high, low)
    }

    def toHexImperative: Array[Char] = {
      val hex = Array.ofDim[Char](bytes.length * 2) // 2 hex chars for each byte
      var n = 0
      while (n < bytes.length) {
        hex(n * 2) = hexDigit((bytes(n) & 0xF0) >>> 4)
        hex(n * 2 + 1) = hexDigit(bytes(n) & 0x0F)
        n += 1
      }
      hex
    }
  }

  println(byteArray.toHex.toList)
  println(byteArray.toHexString)

}
