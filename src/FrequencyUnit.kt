package pl.karol202.photoelectric

import java.math.BigDecimal

enum class FrequencyUnit(val text: String, val factor: BigDecimal)
{
	HZ("Hz", BigDecimal("1")),
	KHZ("kHz", BigDecimal("1000")),
	MHZ("MHz", BigDecimal("1000000")),
	GHZ("GHz", BigDecimal("1000000000")),
	THZ("THz", BigDecimal("1000000000000")),
	PHZ("PHz", BigDecimal("1000000000000000")),
	EHZ("EHz", BigDecimal("1000000000000000000"));

	override fun toString() = text
}