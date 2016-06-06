
/**
  * Created by Chas on 6/5/16.
  */

val x = new Rational(1,3)
val y = new Rational(5,7)
val z = new Rational(3,2)

x.numerator
x.denominator

x- y - z
y + y

-y


class Rational(x: Int, y: Int) {
  require(y!=0, "denominator must be nonzero")

  def this(x:Int) = this(x,1)

  private def gcd( a: Int, b: Int): Int = if( b==0) a else gcd(b,a%b)

  val g = gcd(x, y)
  def numerator = x/g
  def denominator = y/g

  def < (that: Rational) = this.numerator * that.denominator < that.numerator * this.denominator

  def max( that: Rational) = if (this < that) that else this


  def + (that: Rational) =
    new Rational(
      numerator * that. denominator + that.numerator * denominator,
      denominator * that.denominator
    )

  override def toString = numerator + "/" + denominator


  def unary_- : Rational = new Rational(-numerator , denominator)

  def - (that: Rational) = this + -that
}

