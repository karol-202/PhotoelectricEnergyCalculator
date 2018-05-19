package pl.karol202.photoelectric

import java.math.BigDecimal

enum class LengthUnit(val text: String, val factor: BigDecimal)
{
	M("m", BigDecimal("1")),
	MM("mm", BigDecimal("0.001")),
	UM("µm", BigDecimal("0.000001")),
	NM("nm", BigDecimal("0.000000001"));

	override fun toString() = text
}