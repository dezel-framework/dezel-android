package ca.logaritm.dezel.core

import ca.logaritm.dezel.extension.type.toNumber

/**
 * @class JavaScriptPropertyStringValue
 * @super JavaScriptPropertyValue
 * @since 0.1.0
 * @hidden
 */
open class JavaScriptPropertyStringValue(value: String): JavaScriptPropertyValue(JavaScriptPropertyType.STRING) {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * @property value
	 * @since 0.1.0
	 * @hidden
	 */
	private var data: String

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * @constructor
	 * @since 0.1.0
	 * @hidden
	 */
	init {
		this.data = value
	}

	/**
	 * @inherited
	 * @method toString
	 * @since 0.1.0
	 */
	override fun toString(): String {
		return this.data
	}

	/**
	 * @inherited
	 * @method toNumber
	 * @since 0.1.0
	 */
	override fun toNumber(): Double {
		return this.data.toNumber()
	}

	/**
	 * @inherited
	 * @method toBoolean
	 * @since 0.1.0
	 */
	override fun toBoolean(): Boolean {
		return this.data.isEmpty() == false
	}
}