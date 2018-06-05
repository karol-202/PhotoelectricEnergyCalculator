package pl.karol202.photoelectric

import java.math.BigDecimal

enum class WorkFunctionUnit(val text: String, val factor: BigDecimal)
{
	KILO_EV("keV", BigDecimal("1000")),
	EV("eV", BigDecimal("1")),
	MILLI_EV("meV", BigDecimal("0.001"));

	override fun toString() = text
}