package demo

import org.scalatest.FunSuite

/**
  * Created by Chas on 5/24/16.
  */
class HelloTest extends FunSuite {

  test("testSayHello") {
    val hello = new Hello
    assert(hello.sayHello("Scala") == "Hello, Scala!")
  }

}
