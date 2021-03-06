package ca.logaritm.dezel.extension.core

import ca.logaritm.dezel.core.*
import ca.logaritm.dezel.view.display.*
import ca.logaritm.dezel.view.display.value.FunctionValue
import ca.logaritm.dezel.view.display.value.Value
import ca.logaritm.dezel.view.display.value.ValueList
import ca.logaritm.dezel.view.display.value.VariableValue

/**
 * @method JavaScriptProperty.initialize
 * @since 0.1.0
 * @hidden
 */
fun JavaScriptProperty.initialize(values: ValueList) {

	val count = values.count
	if (count == 1) {

		val value = values.get(0)
		if (value == null) {
			return
		}

		when (value.type) {

			kValueTypeNull -> {
				this.resetInitialValue(JavaScriptProperty.Null)
				this.resetCurrentValue(JavaScriptProperty.Null)
			}

			kValueTypeString -> {
				val initial = value.string
				this.resetInitialValue(JavaScriptPropertyStringValue(initial))
				this.resetCurrentValue(JavaScriptPropertyStringValue(initial))
			}

			kValueTypeNumber -> {
				val initial = value.number
				this.resetInitialValue(JavaScriptPropertyNumberValue(initial))
				this.resetCurrentValue(JavaScriptPropertyNumberValue(initial))
			}

			kValueTypeBoolean -> {
				val initial = value.boolean
				this.resetInitialValue(JavaScriptPropertyBooleanValue(initial))
				this.resetCurrentValue(JavaScriptPropertyBooleanValue(initial))
			}

			kValueTypeVariable -> {
				this.resetInitialValue(this.createVariable(VariableValue(value.handle)))
				this.resetCurrentValue(this.createVariable(VariableValue(value.handle)))
			}

			kValueTypeFunction -> {
				this.resetInitialValue(this.createFunction(FunctionValue(value.handle)))
				this.resetCurrentValue(this.createFunction(FunctionValue(value.handle)))
			}
				 
			else -> {}
		}

	} else {

		/*
         * The parser returned multiple values. In this case we create a
         * composite value and reset the property with it.
         */

		val components = mutableListOf<JavaScriptPropertyValue>()

		for (i in 0 until count) {

			val value = values.get(i)
			if (value == null) {
				continue
			}

			when (value.type) {

				kValueTypeNull     -> components.add(JavaScriptProperty.Null)
				kValueTypeString   -> components.add(this.createString(value))
				kValueTypeNumber   -> components.add(this.createNumber(value))
				kValueTypeBoolean  -> components.add(this.createBoolean(value))
				kValueTypeVariable -> components.add(this.createVariable(VariableValue(value.handle)))
				kValueTypeFunction -> components.add(this.createFunction(FunctionValue(value.handle)))

				else -> {

				}
			}
		}

		this.resetInitialValue(JavaScriptPropertyCompositeValue(components))
		this.resetCurrentValue(JavaScriptPropertyCompositeValue(components))
	}
}

/**
 * @method JavaScriptProperty.reset
 * @since 0.1.0
 * @hidden
 */
fun JavaScriptProperty.reset(value: Double, unit: Int, lock: Any? = null) {

	when (unit) {

		kValueUnitNone -> this.reset(value, JavaScriptPropertyUnit.NONE, lock = lock)
		kValueUnitPX   -> this.reset(value, JavaScriptPropertyUnit.PX, lock = lock)
		kValueUnitPC   -> this.reset(value, JavaScriptPropertyUnit.PC, lock = lock)
		kValueUnitVW   -> this.reset(value, JavaScriptPropertyUnit.VW, lock = lock)
		kValueUnitVH   -> this.reset(value, JavaScriptPropertyUnit.VH, lock = lock)
		kValueUnitPW   -> this.reset(value, JavaScriptPropertyUnit.PW, lock = lock)
		kValueUnitPH   -> this.reset(value, JavaScriptPropertyUnit.PH, lock = lock)
		kValueUnitCW   -> this.reset(value, JavaScriptPropertyUnit.CW, lock = lock)
		kValueUnitCH   -> this.reset(value, JavaScriptPropertyUnit.CH, lock = lock)
		kValueUnitDeg   -> this.reset(value, JavaScriptPropertyUnit.DEG, lock = lock)
		kValueUnitRad   -> this.reset(value, JavaScriptPropertyUnit.RAD, lock = lock)

		else -> {
			this.reset(value, JavaScriptPropertyUnit.NONE, lock = lock)
		}
	}
}

/**
 * @method JavaScriptProperty.reset
 * @since 0.1.0
 * @hisdden
 */
fun JavaScriptProperty.reset(values: ValueList, lock: Any? = null) {

	val count = values.count
	if (count == 1) {

		val value = values.get(0)
		if (value == null) {
			return
		}

		when (value.type) {

			kValueTypeNull     -> this.resetWithNull(lock = lock)
			kValueTypeString   -> this.resetWithString(value, lock = lock)
			kValueTypeNumber   -> this.resetWithNumber(value, lock = lock)
			kValueTypeBoolean  -> this.resetWithBoolean(value, lock = lock)
			kValueTypeVariable -> this.resetWithVariable(value, lock = lock)
			kValueTypeFunction -> this.resetWithFunction(value, lock = lock)

			else -> {

			}
		}

	} else {

		/*
		 * The parser returned multiple values. In this we create a
		 * composite value and reset the property with it.
		 */

		val components = mutableListOf<JavaScriptPropertyValue>()

		for (i in 0 until count) {

			val value = values.get(i)
			if (value == null) {
				continue
			}

			when (value.type) {

				kValueTypeNull     -> components.add(JavaScriptProperty.Null)
				kValueTypeString   -> components.add(this.createString(value))
				kValueTypeNumber   -> components.add(this.createNumber(value))
				kValueTypeBoolean  -> components.add(this.createBoolean(value))
				kValueTypeVariable -> components.add(this.createVariable(VariableValue(value.handle)))
				kValueTypeFunction -> components.add(this.createFunction(FunctionValue(value.handle)))

				else -> {

				}
			}
		}

		this.reset(JavaScriptPropertyCompositeValue(components), lock = lock)
	}
}

/**
 * @method JavaScriptProperty.resetWithNull
 * @since 0.1.0
 * @hidden
 */
fun JavaScriptProperty.resetWithNull(lock: Any? = null) {
	this.reset(lock = lock)
}

/**
 * @method JavaScriptProperty.resetWithString
 * @since 0.1.0
 * @hidden
 */
fun JavaScriptProperty.resetWithString(value: Value, lock: Any? = null) {
	this.reset(value.string, lock = lock)
}

/**
 * @method JavaScriptProperty.resetWithNumber
 * @since 0.1.0
 * @hidden
 */
fun JavaScriptProperty.resetWithNumber(value: Value, lock: Any? = null) {

	when (value.unit) {

		kValueUnitNone -> this.reset(value.number, JavaScriptPropertyUnit.NONE, lock = lock)
		kValueUnitPX   -> this.reset(value.number, JavaScriptPropertyUnit.PX, lock = lock)
		kValueUnitPC   -> this.reset(value.number, JavaScriptPropertyUnit.PC, lock = lock)
		kValueUnitVW   -> this.reset(value.number, JavaScriptPropertyUnit.VW, lock = lock)
		kValueUnitVH   -> this.reset(value.number, JavaScriptPropertyUnit.VH, lock = lock)
		kValueUnitPW   -> this.reset(value.number, JavaScriptPropertyUnit.PW, lock = lock)
		kValueUnitPH   -> this.reset(value.number, JavaScriptPropertyUnit.PH, lock = lock)
		kValueUnitCW   -> this.reset(value.number, JavaScriptPropertyUnit.CW, lock = lock)
		kValueUnitCH   -> this.reset(value.number, JavaScriptPropertyUnit.CH, lock = lock)
		kValueUnitDeg  -> this.reset(value.number, JavaScriptPropertyUnit.DEG, lock = lock)
		kValueUnitRad  -> this.reset(value.number, JavaScriptPropertyUnit.RAD, lock = lock)

		else -> {
			this.reset(value.number, JavaScriptPropertyUnit.NONE, lock = lock)
		}
	}
}

/**
 * @method JavaScriptProperty.resetWithBoolean
 * @since 0.1.0
 * @hidden
 */
fun JavaScriptProperty.resetWithBoolean(value: Value, lock: Any? = null) {
	this.reset(value.boolean, lock = lock)
}

/**
 * @method JavaScriptProperty.resetWithVariable
 * @since 0.1.0
 * @hidden
 */
fun JavaScriptProperty.resetWithVariable(value: Value, lock: Any? = null) {
	this.reset(this.createVariable(value as VariableValue), lock = lock)
}

/**
 * @method JavaScriptProperty.resetWithFunction
 * @since 0.1.0
 * @hidden
 */
fun JavaScriptProperty.resetWithFunction(value: Value, lock: Any? = null) {
	this.reset(this.createFunction(FunctionValue(value.handle)), lock = lock)
}

/**
 * @method JavaScriptProperty.createString
 * @since 0.1.0
 * @hidden
 */
fun JavaScriptProperty.createString(value: Value): JavaScriptPropertyStringValue {
	return JavaScriptPropertyStringValue(value.string)
}

/**
 * @method JavaScriptProperty.createNumber
 * @since 0.1.0
 * @hidden
 */
fun JavaScriptProperty.createNumber(value: Value): JavaScriptPropertyNumberValue {
	return JavaScriptPropertyNumberValue(value.number, value.unit)
}

/**
 * @method JavaScriptProperty.createBoolean
 * @since 0.1.0
 * @hidden
 */
fun JavaScriptProperty.createBoolean(value: Value): JavaScriptPropertyBooleanValue {
	return JavaScriptPropertyBooleanValue(value.boolean)
}

/**
 * @method JavaScriptProperty.createVariable
 * @since 0.1.0
 * @hidden
 */
fun JavaScriptProperty.createVariable(value: VariableValue): JavaScriptPropertyVariableValue {
	throw Exception("Not yet supported")
}

/**
 * @method JavaScriptProperty.createFunction
 * @since 0.1.0
 * @hidden
 */
fun JavaScriptProperty.createFunction(value: FunctionValue): JavaScriptPropertyFunctionValue {

	val name = value.name

	var arguments = mutableListOf<JavaScriptPropertyValue>()

	for (i in 0 until value.arguments) {

		val values = value.argument(i)

		val count = values.count
		if (count == 1) {

			val value = values.get(0)
			if (value == null) {
				continue
			}

			when (value.type) {

				kValueTypeNull     -> arguments.add(JavaScriptProperty.Null)
				kValueTypeString   -> arguments.add(this.createString(value))
				kValueTypeNumber   -> arguments.add(this.createNumber(value))
				kValueTypeBoolean  -> arguments.add(this.createBoolean(value))
				kValueTypeVariable -> arguments.add(this.createVariable(VariableValue(value.handle)))
				kValueTypeFunction -> arguments.add(this.createFunction(FunctionValue(value.handle)))

				else -> {

				}
			}

		} else {

			/*
			 * The parser returned multiple values. In this we create a
			 * composite value and reset the property with it.
			 */

			val composite = mutableListOf<JavaScriptPropertyValue>()

			for (i in 0 until count) {

				val value = values.get(i)
				if (value == null) {
					continue
				}

				when (value.type) {

					kValueTypeNull     -> composite.add(JavaScriptProperty.Null)
					kValueTypeString   -> composite.add(this.createString(value))
					kValueTypeNumber   -> composite.add(this.createNumber(value))
					kValueTypeBoolean  -> composite.add(this.createBoolean(value))
					kValueTypeVariable -> composite.add(this.createVariable(VariableValue(value.handle)))
					kValueTypeFunction -> composite.add(this.createFunction(FunctionValue(value.handle)))

					else ->{

					}
				}
			}

			arguments.add(JavaScriptPropertyCompositeValue(composite))
		}
	}

	return JavaScriptPropertyFunctionValue(name, arguments)
}