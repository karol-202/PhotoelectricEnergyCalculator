package pl.karol202.photoelectric

import java.math.BigDecimal
import java.math.BigDecimal.ROUND_HALF_UP

private val TWO = BigDecimal(2)
private const val SCALE = 5

fun BigDecimal.sqrt(): BigDecimal
{
	var x0 = BigDecimal.ZERO
	var x1 = BigDecimal(Math.sqrt(toDouble()))
	while(x0 != x1)
	{
		x0 = x1
		x1 = divide(x0, SCALE, ROUND_HALF_UP)
		x1 = (x0 + x1).divide(TWO, SCALE, ROUND_HALF_UP)
	}
	return x1
}