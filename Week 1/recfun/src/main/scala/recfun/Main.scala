package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int ={
      if (c == 0)1
      else (
        if(r == 0) 0
        else pascal(c,r-1)+ pascal(c-1,r-1))
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def currentCount(count: Int, chars: List[Char]): Int ={
        if (chars.isEmpty)count
        else if(count < 0)-1
        else if(chars.head == '(')currentCount(count+1,chars.tail)
        else if(chars.head == ')')currentCount(count-1,chars.tail)
        else currentCount(count,chars.tail)
      }
    if(currentCount(0,chars)== 0)true
    else false
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int ={
      if( money == 0)1
      else if ( coins.isEmpty || money <0)0
      else countChange(money - coins.head,coins)+countChange(money, coins.tail)
    }

}
