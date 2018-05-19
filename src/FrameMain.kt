package pl.karol202.photoelectric

import java.awt.*
import javax.swing.*

fun main(args: Array<String>)
{
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
	FrameMain()
}

class FrameMain
{
	val frame = JFrame("Kalkulator efektu fotoelektrycznego")

	val textFieldFrequency = JTextField(10)
	val comboBoxFrequencyUnit = JComboBox<FrequencyUnit>(FrequencyUnit.values())

	val textFieldWavelength = JTextField(10)
	val comboBoxWavelengthUnit = JComboBox<LengthUnit>(LengthUnit.values())

	val textFieldWorkFunction = JTextField(10)

	init
	{
		initComponents()
		initFrame()
	}

	private fun initFrame()
	{
		frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		//frame.size = Dimension(800, 600)
		frame.isVisible = true
		frame.pack()
	}

	private fun initComponents()
	{
		frame.layout = GridBagLayout()

		//Wave frequency
		frame.add(JLabel("Częstotliwość promieniowania:"),
				GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.LINE_START, GridBagConstraints.NONE,
						Insets(0, 8, 0, 0), 0, 0))

		frame.add(textFieldFrequency,
				GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
						Insets(4, 8, 4, 4), 0, 0))

		frame.add(comboBoxFrequencyUnit,
				GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
						Insets(0, 0, 0, 4), 0, 0))

		//Wave length
		frame.add(JLabel("Długość fali:"),
				GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.LINE_START, GridBagConstraints.NONE,
						Insets(0, 8, 0, 0), 0, 0))

		frame.add(textFieldWavelength,
				GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
						Insets(4, 8, 4, 4), 0, 0))

		frame.add(comboBoxWavelengthUnit,
				GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
						Insets(0, 0, 0, 4), 0, 0))

		//Work function
		frame.add(JLabel("Praca wyjścia:"),
				GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.LINE_START, GridBagConstraints.NONE,
						Insets(0, 8, 0, 0), 0, 0))

		frame.add(textFieldWorkFunction,
				GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.NONE,
						Insets(4, 8, 4, 4), 0, 0))

		frame.add(JLabel("eV"),
				GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.LINE_START, GridBagConstraints.NONE,
						Insets(0, 4, 0, 4), 0, 0))
	}
}