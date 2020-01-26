package ca.logaritm.dezel.core

/**
 * @class JavaScriptPropertyVariableValue
 * @super JavaScriptPropertyValue
 * @since 0.1.0
 * @hidden
 */
open class JavaScriptPropertyVariableValue(name: String, values: MutableList<JavaScriptPropertyValue>): JavaScriptPropertyValue(JavaScriptPropertyType.VARIABLE) {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The variable's name.
	 * @property name
	 * @since 0.1.0
	 */
	public var name: String
		private set

	/**
	 * The variable's value.
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
		this.name = name
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