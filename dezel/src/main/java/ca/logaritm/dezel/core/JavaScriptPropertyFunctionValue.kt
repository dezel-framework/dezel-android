package ca.logaritm.dezel.core

/**
 * @class JavaScriptPropertyFunctionValue
 * @super JavaScriptPropertyValue
 * @since 0.1.0
 * @hidden
 */
open class JavaScriptPropertyFunctionValue(name: String, arguments: MutableList<JavaScriptPropertyValue>): JavaScriptPropertyValue(JavaScriptPropertyType.VARIABLE) {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The function's name.
	 * @property name
	 * @since 0.1.0
	 */
	public var name: String
		private set

	/**
	 * The function's argument.
	 * @property arguments
	 * @since 0.1.0
	 */
	public var arguments: MutableList<JavaScriptPropertyValue>
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
		this.arguments = arguments
	}

	/**
	 * @inherited
	 * @method toString
	 * @since 0.1.0
	 */
	override fun toString(): String {
		throw Exception("JavaScriptPropertyFunctionValue is not convertible to type string.")
	}

	/**
	 * @inherited
	 * @method toNumber
	 * @since 0.1.0
	 */
	override fun toNumber(): Double {
		throw Exception("JavaScriptPropertyFunctionValue is not convertible to type number.")
	}

	/**
	 * @inherited
	 * @method toBoolean
	 * @since 0.1.0
	 */
	override fun toBoolean(): Boolean {
		throw Exception("JavaScriptPropertyFunctionValue is not convertible to type boolean.")
	}
}