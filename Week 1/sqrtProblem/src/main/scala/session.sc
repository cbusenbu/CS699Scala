1+2

def abs(x: Double) = if (x< 0) -x else x






def sqrt(x: Double):Double = {
  def improve(guess: Double) =
    (guess + x / guess) / 2

  def isGoodEnough(guess: Double ) =
    abs(guess * guess - x) / x < 0.001

  def sqrtIter(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else sqrtIter(improve(guess))
  sqrtIter(1.0)
}
sqrt(2)
sqrt(4)
sqrt(1e-6)
sqrt(1e60)