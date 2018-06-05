package pl.karol202.photoelectric

import java.math.BigDecimal

enum class EnergyUnit(val text: String, val factor: BigDecimal)
{
	J      ("J", BigDecimal("1")),
	MILLI_J("mJ", BigDecimal("0.001")),
	MICRO_J("µJ", BigDecimal("0.000001")),
	NANO_J ("nJ", BigDecimal("0.000000001")),
	PICO_J ("pJ", BigDecimal("0.000000000001")),
	FEMTO_J("fJ", BigDecimal("0.000000000000001")),
	ATTO_J ("aJ", BigDecimal("0.000000000000000001")),
	ZEPTO_J("zJ", BigDecimal("0.000000000000000000001")),
	YOCTO_J("yJ", BigDecimal("0.000000000000000000000001"));

	override fun toString() = text
}