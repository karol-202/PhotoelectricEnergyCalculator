package pl.karol202.photoelectric

import java.math.BigDecimal

enum class SpeedUnit(val text: String, val factor: BigDecimal)
{
	KILO_M_PER_S("km/s", BigDecimal("1000")),
	M_PER_S("m/s", BigDecimal("1")),
	MILLI_M_PER_S("mm/s", BigDecimal("0.001"));

	override fun toString() = text
}