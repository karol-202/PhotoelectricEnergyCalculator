package pl.karol202.photoelectric

import java.math.BigDecimal

enum class LengthUnit(val text: String, val factor: BigDecimal)
{
	M("m", BigDecimal("1")),
	MILLI_M("mm", BigDecimal("0.001")),
	MICRO_M("µm", BigDecimal("0.000001")),
	NANO_M("nm", BigDecimal("0.000000001")),
	PICO_M("pm", BigDecimal("0.000000000001"));

	override fun toString() = text
}