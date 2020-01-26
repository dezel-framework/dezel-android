package ca.logaritm.dezel.core

/**
 * The JavaScript property unit enumeration.
 * @enum JavaScriptPropertyUnit
 * @since 0.1.0
 */
public enum class JavaScriptPropertyUnit {

	NONE,
	PX,
	PC,
	VW,
	VH,
	PW,
	PH,
	CW,
	CH,
	DEG,
	RAD;

	internal fun isRelativeToWindow(): Boolean {
		return (
			this == VW ||
			this == VH
		)
	}

	internal fun isRelativeToParent(): Boolean {
		return (
			this == PC ||
			this == PW ||
			this == PH ||
			this == CW ||
			this == CH
		)
	}

}