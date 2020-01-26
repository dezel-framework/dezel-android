package ca.logaritm.dezel.extension.core

import ca.logaritm.dezel.core.JavaScriptPropertyNumberValue
import ca.logaritm.dezel.core.JavaScriptPropertyUnit
import ca.logaritm.dezel.view.display.*

/**
 * @constructor
 * @since 0.1.0
 * @hidden
 */
fun JavaScriptPropertyNumberValue(value: Double, unit: Int): JavaScriptPropertyNumberValue {

	when (unit) {

		kValueUnitNone -> JavaScriptPropertyNumberValue(value, JavaScriptPropertyUnit.NONE)
		kValueUnitPX   -> JavaScriptPropertyNumberValue(value, JavaScriptPropertyUnit.PX)
		kValueUnitPC   -> JavaScriptPropertyNumberValue(value, JavaScriptPropertyUnit.PC)
		kValueUnitVW   -> JavaScriptPropertyNumberValue(value, JavaScriptPropertyUnit.VW)
		kValueUnitVH   -> JavaScriptPropertyNumberValue(value, JavaScriptPropertyUnit.VH)
		kValueUnitPW   -> JavaScriptPropertyNumberValue(value, JavaScriptPropertyUnit.PW)
		kValueUnitPH   -> JavaScriptPropertyNumberValue(value, JavaScriptPropertyUnit.PH)
		kValueUnitCW   -> JavaScriptPropertyNumberValue(value, JavaScriptPropertyUnit.CW)
		kValueUnitCH   -> JavaScriptPropertyNumberValue(value, JavaScriptPropertyUnit.CH)
		kValueUnitDeg  -> JavaScriptPropertyNumberValue(value, JavaScriptPropertyUnit.DEG)
		kValueUnitRad  -> JavaScriptPropertyNumberValue(value, JavaScriptPropertyUnit.RAD)

		else -> {

		}
	}

	return JavaScriptPropertyNumberValue(value, JavaScriptPropertyUnit.NONE)
}
