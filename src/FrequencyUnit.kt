package pl.karol202.photoelectric

import java.math.BigDecimal

enum class FrequencyUnit(val text: String, val factor: BigDecimal)
{
	EXA_HZ("EHz", BigDecimal("1000000000000000000")),
	PETA_HZ("PHz", BigDecimal("1000000000000000")),
	TERA_HZ("THz", BigDecimal("1000000000000")),
	GIGA_HZ("GHz", BigDecimal("1000000000")),
	MEGA_HZ("MHz", BigDecimal("1000000")),
	KILO_HZ("kHz", BigDecimal("1000")),
	HZ("Hz", BigDecimal("1"));

	override fun toString() = text
}