package oops.examples.visibility

/**
  * Created by govind.bhone on 5/26/2017.
  */
package scopeA {

  class Class1 {
    private[scopeA] val scopeA_privateField = 1
    protected[scopeA] val scopeA_protectedField = 2
    private[Class1] val class1_privateField = 3
    protected[Class1] val class1_protectedField = 4
    private[this] val this_privateField = 5
    protected[this] val this_protectedField = 6
  }

  class Class2 extends Class1 {
    val field1 = scopeA_privateField
    val field2 = scopeA_protectedField
    // val field3 = class1_privateField     // ERROR
    val field4 = class1_protectedField
    // val field5 = this_privateField       // ERROR
    val field6 = this_protectedField
  }

}

package scopeB {

  class Class2B extends scopeA.Class1 {
    // ERROR
    //val field1 = scopeA_privateField

    val field2 = scopeA_protectedField

    // ERROR
    //val field3 = class1_privateField

    val field4 = class1_protectedField
    // ERROR
    //val field5 = this_privateField

    val field6 = this_protectedField
  }

}
