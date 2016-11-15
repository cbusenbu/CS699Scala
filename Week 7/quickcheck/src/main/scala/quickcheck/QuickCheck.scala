package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
      typeValue <- arbitrary[A]
      heapGenerate <- oneOf(const(empty), genHeap)
  }yield insert(typeValue,heapGenerate)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("meldTwoEmptyandMakeSureFindIs Accurate") = forAll{(x: H, y: H) =>
    val emptyHeap = meld(empty,empty)
    val emptySingleton = empty

    def heapEqual(heap1: H, heap2: H):Boolean = {
      if(isEmpty(heap1)&&isEmpty(heap2))true
      else
        if (findMin(heap1) == findMin(heap2))heapEqual(deleteMin(heap1),deleteMin(heap2))
        else false
    }
    heapEqual(meld(x,y),meld(deleteMin(x), insert(findMin(x),y)))

  }
  property("meldTwoEmptyandMakeSureStillEmpty") = forAll{(x:A) =>
    val emptyHeap = meld(empty,empty)
    val emptySingleton = empty

    deleteMin(insert(x,emptyHeap)) == deleteMin(insert(x,emptySingleton))

  }

  property("mergeTwoAndMakeSureMinIsCorrect") = forAll{(a:H,b:H) =>
    val minA = findMin(a)
    val minB = findMin(b)

    val heapMeld = meld(a,b)
    if( minA <= minB)(findMin(heapMeld)==minA)else(findMin(heapMeld)==minB)

  }

  property("allRemovedShouldBeSortedByMin") = forAll{ (x: H) =>

    def isSorted(heapToBeSorted: H, initialValue: A): Boolean =
      if (isEmpty(heapToBeSorted)) true
      else
        if (initialValue <= findMin(heapToBeSorted)) isSorted(deleteMin(heapToBeSorted),findMin(heapToBeSorted))
      else false

    isSorted(deleteMin(x),findMin(x))

  }

  property("insertEmptyRemoveMinGetEmpty") = forAll { (a: A) =>
    val singletonHeap = insert(a,empty)
    val deletedHeap = deleteMin(singletonHeap)
    isEmpty(deletedHeap)
  }

  property("2elementsEmptyHeap") = forAll{ (a: A,b: A) =>
    val twoElementHeap = insert(a,insert(b,empty))
    findMin(twoElementHeap) == (if(a>b)b else a)

  }
  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

}
