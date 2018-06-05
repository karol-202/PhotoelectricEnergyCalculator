package pl.karol202.photoelectric

import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import java.math.BigDecimal
import java.math.MathContext
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

fun main(args: Array<String>)
{
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
	FrameMain()
}

class FrameMain
{
	class TextFieldListener(private val frameMain: FrameMain) : DocumentListener
	{
		override fun changedUpdate(e: DocumentEvent?)
		{
			frameMain.updateWave()
		}

		override fun insertUpdate(e: DocumentEvent?)
		{
			frameMain.updateWave()
		}

		override fun removeUpdate(e: DocumentEvent?)
		{
			frameMain.updateWave()
		}
	}

	private val lightSpeed = BigDecimal(300000000) //In m/s
	private val planckConstant = BigDecimal("0.000000000000000000000000000000000663") //In J*s
	private val massOfElectron = BigDecimal("0.000000000000000000000000000000911") //In kg
	private val electronvolt = BigDecimal("0.0000000000000000001602") //In J

	private val mathContext = MathContext(5)

	private val frame = JFrame("Kalkulator efektu fotoelektrycznego")
	private val buttonGroupWave = ButtonGroup()

	private val textFieldFrequency = JTextField(20)
	private val comboBoxFrequencyUnit = JComboBox<FrequencyUnit>(FrequencyUnit.values())
	private val radioButtonFrequency = JRadioButton()

	private val textFieldWavelength = JTextField(20)
	private val comboBoxWavelengthUnit = JComboBox<LengthUnit>(LengthUnit.values())
	private val radioButtonWavelength = JRadioButton()

	private val textFieldWorkFunction = JTextField(20)
	private val comboBoxWorkFunctionUnit = JComboBox<WorkFunctionUnit>(WorkFunctionUnit.values())

	private val buttonCalculate = JButton("Oblicz")

	private val textFieldPhotonEnergy = JTextField(20)
	private val comboBoxPhotonEnergyUnit = JComboBox<EnergyUnit>(EnergyUnit.values())

	private val labelPhotonEffect = JLabel(" ")

	private val textFieldKineticEnergy = JTextField(20)
	private val comboBoxKineticEnergyUnit = JComboBox<EnergyUnit>(EnergyUnit.values())

	private val textFieldPhotonSpeed = JTextField(20)
	private val comboBoxPhotonSpeedUnit = JComboBox<SpeedUnit>(SpeedUnit.values())

	private var muteTextFieldUpdate = false

	private var photonEnergy: BigDecimal? = null
	private var photonEffect: Boolean? = null
	private var kineticEnergy: BigDecimal? = null
	private var photonSpeed: BigDecimal? = null

	init
	{
		initComponents()
		initFrame()
	}

	private fun initFrame()
	{
		frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		frame.isVisible = true
		frame.pack()
	}

	private fun initComponents()
	{
		frame.layout = GridBagLayout()
		buttonGroupWave.add(radioButtonFrequency)
		buttonGroupWave.add(radioButtonWavelength)

		//Wave frequency
		textFieldFrequency.document.addDocumentListener(TextFieldListener(this))
		comboBoxFrequencyUnit.selectedItem = FrequencyUnit.TERA_HZ
		comboBoxFrequencyUnit.addActionListener { updateWave() }
		radioButtonFrequency.isSelected = true
		radioButtonFrequency.addActionListener { updateActiveTextFields() }

		frame.add(JLabel("Częstotliwość promieniowania"),
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
		frame.add(radioButtonFrequency,
				GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.NONE,
						Insets(0, 0, 0, 4), 0, 0))

		//Wave length
		textFieldWavelength.document.addDocumentListener(TextFieldListener(this))
		comboBoxWavelengthUnit.selectedItem = LengthUnit.NANO_M
		comboBoxWavelengthUnit.addActionListener { updateWave() }
		radioButtonWavelength.addActionListener { updateActiveTextFields() }

		frame.add(JLabel("Długość fali"),
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
		frame.add(radioButtonWavelength,
				GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.NONE,
						Insets(0, 0, 0, 4), 0, 0))

		//Work function
		comboBoxWorkFunctionUnit.selectedItem = WorkFunctionUnit.EV

		frame.add(JLabel("Praca wyjścia"),
				GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.LINE_START, GridBagConstraints.NONE,
						Insets(0, 8, 0, 0), 0, 0))

		frame.add(textFieldWorkFunction,
				GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
						Insets(4, 8, 4, 4), 0, 0))

		frame.add(comboBoxWorkFunctionUnit,
				GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
						Insets(0, 0, 0, 4), 0, 0))

		//Calculate button
		buttonCalculate.addActionListener { calculateAll() }
		frame.add(buttonCalculate,
				GridBagConstraints(0, 3, 4, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						Insets(4, 8, 4, 8), 0, 4))

		//Photon energy
		textFieldPhotonEnergy.isEditable = false
		comboBoxPhotonEnergyUnit.selectedItem = EnergyUnit.ZEPTO_J
		comboBoxPhotonEnergyUnit.addActionListener { updateOutput() }

		frame.add(JLabel("Energia fotonu"),
				GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
						GridBagConstraints.LINE_START, GridBagConstraints.NONE,
						Insets(0, 8, 0, 0), 0, 0))
		frame.add(textFieldPhotonEnergy,
				GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
						Insets(4, 8, 4, 4), 0, 0))
		frame.add(comboBoxPhotonEnergyUnit,
				GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
						Insets(0, 0, 0, 4), 0, 0))

		//Photon effect
		frame.add(labelPhotonEffect,
				GridBagConstraints(0, 5, 4, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.NONE,
						Insets(4, 8, 4, 8), 0, 0))

		//Photon kinetic energy
		textFieldKineticEnergy.isEditable = false
		comboBoxKineticEnergyUnit.selectedItem = EnergyUnit.ZEPTO_J
		comboBoxKineticEnergyUnit.addActionListener { updateOutput() }

		frame.add(JLabel("Energia kinetyczna fotonu"),
				GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
						GridBagConstraints.LINE_START, GridBagConstraints.NONE,
						Insets(0, 8, 0, 0), 0, 0))
		frame.add(textFieldKineticEnergy,
				GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
						Insets(4, 8, 4, 4), 0, 0))
		frame.add(comboBoxKineticEnergyUnit,
				GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
						Insets(0, 0, 0, 4), 0, 0))

		//Photon speed
		textFieldPhotonSpeed.isEditable = false
		comboBoxPhotonSpeedUnit.selectedItem = SpeedUnit.KILO_M_PER_S
		comboBoxPhotonSpeedUnit.addActionListener { updateOutput() }

		frame.add(JLabel("Prędkość fotonu"),
				GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
						GridBagConstraints.LINE_START, GridBagConstraints.NONE,
						Insets(0, 8, 0, 0), 0, 0))
		frame.add(textFieldPhotonSpeed,
				GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
						Insets(4, 8, 4, 4), 0, 0))
		frame.add(comboBoxPhotonSpeedUnit,
				GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
						Insets(0, 0, 0, 4), 0, 0))

		updateActiveTextFields()
	}

	private fun updateWave()
	{
		if(muteTextFieldUpdate) return
		if(radioButtonFrequency.isSelected)
		{
			val wavelength = calculateWavelength(getFrequencyInHertzFromField()) ?: return
			val wavelengthInGivenUnit = wavelength.divide(getWavelengthUnitFactor())
			SwingUtilities.invokeLater {
				muteTextFieldUpdate = true
				textFieldWavelength.text = wavelengthInGivenUnit.toPlainString()
				muteTextFieldUpdate = false
			}
		}
		else if(radioButtonWavelength.isSelected)
		{
			val frequency = calculateFrequency(getWavelengthInMetersFromField()) ?: return
			val frequencyInGivenUnit = frequency.divide(getFrequencyUnitFactor())
			SwingUtilities.invokeLater {
				muteTextFieldUpdate = true
				textFieldFrequency.text = frequencyInGivenUnit.toPlainString()
				muteTextFieldUpdate = false
			}
		}
	}

	private fun calculateFrequency(wavelength: BigDecimal?) = if(wavelength != null && wavelength.compareTo(BigDecimal.ZERO) != 0)
																  lightSpeed.divide(wavelength, mathContext)
															  else null

	private fun calculateWavelength(frequency: BigDecimal?) = if(frequency != null && frequency.compareTo(BigDecimal.ZERO) != 0)
																  lightSpeed.divide(frequency, mathContext)
															  else null

	private fun updateActiveTextFields()
	{
		textFieldFrequency.isEditable = radioButtonFrequency.isSelected
		textFieldWavelength.isEditable = radioButtonWavelength.isSelected
	}

	private fun calculateAll()
	{
		val frequency = getFrequencyInHertz() ?: return
		val workFunction = getWorkFunctionInJoule() ?: return

		val photonEnergy = frequency * planckConstant
		this.photonEnergy = photonEnergy

		val photonEffect = photonEnergy >= workFunction
		this.photonEffect = photonEffect

		val kineticEnergy = if(photonEffect) photonEnergy - workFunction else BigDecimal.ZERO
		this.kineticEnergy = kineticEnergy

		val photonSpeed = if(photonEffect) (BigDecimal(2) * kineticEnergy / massOfElectron).sqrt() else BigDecimal.ZERO
		this.photonSpeed = photonSpeed

		updateOutput()
	}

	private fun getFrequencyInHertz() = when
	{
		radioButtonFrequency.isSelected -> getFrequencyInHertzFromField()
		radioButtonWavelength.isSelected -> calculateFrequency(getWavelengthInMetersFromField())
		else -> null
	}

	private fun getWorkFunctionInJoule(): BigDecimal?
	{
		val workFunctionInElectronvolts = getWorkFunctionInElectronvoltsFromField() ?: return null
		return workFunctionInElectronvolts * electronvolt
	}

	private fun updateOutput()
	{
		textFieldPhotonEnergy.text = photonEnergy?.divide(getPhotonEnergyUnitFactor())?.toPlainString()
		labelPhotonEffect.text = when(photonEffect) {
			true -> "Elektron wybity"
			false -> "Elektron nie wybity"
			null -> " "
		}
		textFieldKineticEnergy.text = kineticEnergy?.divide(getKineticEnergyUnitFactor())?.toPlainString()
		textFieldPhotonSpeed.text = photonSpeed?.divide(getPhotonSpeedUnitFactor())?.toPlainString()
	}

	private fun getFrequencyInHertzFromField(): BigDecimal?
	{
		val double = textFieldFrequency.text.toDoubleOrNull() ?: return null
		return BigDecimal(double, mathContext) * getFrequencyUnitFactor()
	}

	private fun getWavelengthInMetersFromField(): BigDecimal?
	{
		val double = textFieldWavelength.text.toDoubleOrNull() ?: return null
		return BigDecimal(double, mathContext) * getWavelengthUnitFactor()
	}

	private fun getWorkFunctionInElectronvoltsFromField(): BigDecimal?
	{
		val double = textFieldWorkFunction.text.toDoubleOrNull() ?: return null
		return BigDecimal(double, mathContext) * getWorkFunctionUnitFactor()
	}

	private fun getFrequencyUnitFactor() = (comboBoxFrequencyUnit.selectedItem as FrequencyUnit).factor

	private fun getWavelengthUnitFactor() = (comboBoxWavelengthUnit.selectedItem as LengthUnit).factor

	private fun getWorkFunctionUnitFactor() = (comboBoxWorkFunctionUnit.selectedItem as WorkFunctionUnit).factor

	private fun getPhotonEnergyUnitFactor() = (comboBoxPhotonEnergyUnit.selectedItem as EnergyUnit).factor

	private fun getKineticEnergyUnitFactor() = (comboBoxKineticEnergyUnit.selectedItem as EnergyUnit).factor

	private fun getPhotonSpeedUnitFactor() = (comboBoxPhotonSpeedUnit.selectedItem as SpeedUnit).factor
}