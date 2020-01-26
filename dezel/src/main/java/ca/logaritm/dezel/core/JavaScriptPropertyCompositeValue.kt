package ca.logaritm.dezel.core

/**
 * @class JavaScriptPropertyCompositeValue
 * @super JavaScriptPropertyValue
 * @since 0.1.0
 * @hidden
 */
open class JavaScriptPropertyCompositeValue(values: MutableList<JavaScriptPropertyValue>): JavaScriptPropertyValue(JavaScriptPropertyType.VARIABLE) {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The composite's values.
	 * @property values
	 * @since 0.1.0
	 */
	public var values: MutableList<JavaScriptPropertyValue>
		private set

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * @constructor
	 * @since 0.1.0
	 * @hidden
	 */
	init {
		this.values = values
	}

	/**
	 * @inherited
	 * @method toString
	 * @since 0.1.0
	 */
	override fun toString(): String {
		throw Exception("JavaScriptPropertyVariableValue is not convertible to type string.")
	}

	/**
	 * @inherited
	 * @method toNumber
	 * @since 0.1.0
	 */
	override fun toNumber(): Double {
		throw Exception("JavaScriptPropertyVariableValue is not convertible to type number.")
	}

	/**
	 * @inherited
	 * @method toBoolean
	 * @since 0.1.0
	 */
	override fun toBoolean(): Boolean {
		throw Exception("JavaScriptPropertyVariableValue is not convertible to type boolean.")
	}
}