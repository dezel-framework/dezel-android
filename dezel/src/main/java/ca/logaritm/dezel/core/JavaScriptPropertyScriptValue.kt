package ca.logaritm.dezel.core

/**
 * @class JavaScriptPropertyScriptValue
 * @super JavaScriptPropertyValue
 * @since 0.1.0
 * @hidden
 */
open class JavaScriptPropertyScriptValue(value: JavaScriptValue) : JavaScriptPropertyValue() {

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * @constructor
	 * @since 0.1.0
	 * @hidden
	 */
	init {

		/*
		 * The value was parsed before being assigned here,
		 * there is no need to parse it again.
		 */

		val type: JavaScriptPropertyType

		when (true) {

			value.isNull      -> type = JavaScriptPropertyType.NULL
			value.isUndefined -> type = JavaScriptPropertyType.NULL
			value.isString    -> type = JavaScriptPropertyType.STRING
			value.isNumber    -> type = JavaScriptPropertyType.NUMBER
			value.isBoolean   -> type = JavaScriptPropertyType.BOOLEAN
			value.isObject    -> type = JavaScriptPropertyType.OBJECT
			value.isArray     -> type = JavaScriptPropertyType.ARRAY
			else              -> type = JavaScriptPropertyType.NULL
		}

		this.type = type
		this.unit = JavaScriptPropertyUnit.NONE

		this.value = value
	}

	/**
	 * @inherited
	 * @method toString
	 * @since 0.1.0
	 */
	override fun toString(): String {
		return this.value!!.string
	}

	/**
	 * @inherited
	 * @method toNumber
	 * @since 0.1.0
	 */
	override fun toNumber(): Double {
		return this.value!!.number
	}

	/**
	 * @inherited
	 * @method toBoolean
	 * @since 0.1.0
	 */
	override fun toBoolean(): Boolean {
		return this.value!!.boolean
	}
}