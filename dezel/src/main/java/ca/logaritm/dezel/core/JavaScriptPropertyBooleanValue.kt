package ca.logaritm.dezel.core

/**
 * @class JavaScriptPropertyBooleanValue
 * @super JavaScriptPropertyValue
 * @since 0.1.0
 * @hidden
 */
open class JavaScriptPropertyBooleanValue(value: Boolean): JavaScriptPropertyValue(JavaScriptPropertyType.BOOLEAN) {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * @property data
	 * @since 0.1.0
	 * @hidden
	 */
	private var data: Boolean

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
		return if (this.data) "true" else "false"
	}

	/**
	 * @inherited
	 * @method toNumber
	 * @since 0.1.0
	 */
	override fun toNumber(): Double {
		return if (this.data) 1.0 else 0.0
	}

	/**
	 * @inherited
	 * @method toBoolean
	 * @since 0.1.0
	 */
	override fun toBoolean(): Boolean {
		return this.data
	}
}