package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s = union(s1,s2)
    val t = union(s2,s3)
    val st = union(s,t)
    val x = intersect(s,t)
    val d = diff(t,x)
    val f = filter(union(s,t),(x:Int)=> (x%3)==0)
    val m = map(st, (x:Int)=> 2*x)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(contains(s2,2), "Singleton 2")
      assert(contains(s3,3), "Singleton 3")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      assert(contains(s, 1), "Union 1- Success is True")
      assert(contains(s, 2), "Union 2- Sucess is True")
      assert(!contains(s, 3), "Union 3- Fail is True")
    }
  }
  test("intersect contains only elements shared"){
    new TestSets{
      assert(contains(x,2), "Intersect 2- Success is True")
      assert(!contains(x,1), "Intersect 1 - Fail is True")
    }
  }
  test("difference contains elements not shared"){
    new TestSets{
      assert(contains(d,3), "Difference 3 - success is True")
      assert(!contains(d,2), "Difference 2 - Fail is True")
    }
  }
  test("filter returns the subset for which p holds"){
    new TestSets{
      assert(contains(f,3), "filtered by modulus of three - success is True")
      assert(!contains(f,2), "filtered by modulus of three - Fail is False")
    }
  }
  test("forall test returns true if all bounded integers within s satisfy p"){
    new TestSets{
      assert(forall(st,(x:Int) => x%1 == 0),"forall test - success is True")
      assert(!forall(st,(x:Int) => x%3 == 0), "forall test - Fail is true")
    }
  }
  test("exists test returns true if there exists one integer within s to satify p"){
    new TestSets{
      assert(exists(st,(x:Int)=> x%3 ==0), "exists test - success is True")
      assert(exists(st,(x:Int)=> x%1 ==0), "exists test - success is True")
      assert(!exists(st,(x:Int)=> x%4 == 0), "exists test - fail is true")
    }
  }
  test("map test returns a set that has been transformed  by applying f to each element in s"){
    new TestSets{
      assert(contains(m,6), "map test- success is true")
      assert(!contains(m,1), "map test - fail is true")
    }
  }
}
