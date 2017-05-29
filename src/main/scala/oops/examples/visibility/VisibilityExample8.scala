package oops.examples.visibility

/**
  * Created by govind.bhone on 5/26/2017.
  */
package scopeA {
  class PublicClass1 {
    val publicField = 1

    class Nested {
      val nestedField = 1
    }

    val nested = new Nested
  }

  class PublicClass2 extends PublicClass1 {
    val field2  = publicField + 1
    val nField2 = new Nested().nestedField
  }
}

package scopeB {
  class PublicClass1B extends scopeA.PublicClass1

  class UsingClass(val publicClass: scopeA.PublicClass1) {
    def method = "UsingClass:" +
      " field: " + publicClass.publicField +
      " nested field: " + publicClass.nested.nestedField
  }
}
