package ca.logaritm.dezel.core

/**
 * @class JavaScriptPropertyValue
 * @since 0.1.0
 * @hidden
 */
open class JavaScriptPropertyValue(type: JavaScriptPropertyType = JavaScriptPropertyType.NULL, unit: JavaScriptPropertyUnit = JavaScriptPropertyUnit.NONE, value: JavaScriptValue? = null) {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The property value's type.
	 * @property type
	 * @since 0.1.0
	 */
	public var type: JavaScriptPropertyType = JavaScriptPropertyType.NULL

	/**
	 * The property value's unit.
	 * @property unit
	 * @since 0.1.0
	 */
	public var unit: JavaScriptPropertyUnit = JavaScriptPropertyUnit.NONE

	/**
	 * The property value's value.
	 * @property value
	 * @since 0.1.0
	 */
	public var value: JavaScriptValue? = null

	/**
	 * The property value's string value.
	 * @property string
	 * @since 0.1.0
	 */
	public val string: String by lazy {
		this.toString()
	}

	/**
	 * The property value's number value.
	 * @property number
	 * @since 0.1.0
	 */
	public val number: Double by lazy {
		this.toNumber()
	}

	/**
	 * The property value's boolean value.
	 * @property boolean
	 * @since 0.1.0
	 */
	public val boolean: Boolean by lazy {
		this.toBoolean()
	}

	/**
	 * Whether the property value is null.
	 * @property isNull
	 * @since 0.1.0
	 */
	public val isNull: Boolean
		get() = this.type == JavaScriptPropertyType.NULL

	/**
	 * Whether the property value is a string.
	 * @property isString
	 * @since 0.1.0
	 */
	public val isString: Boolean
		get() = this.type == JavaScriptPropertyType.STRING

	/**
	 * Whether the property value is a number.
	 * @property isNumber
	 * @since 0.1.0
	 */
	public val isNumber: Boolean
		get() = this.type == JavaScriptPropertyType.NUMBER

	/**
	 * Whether the property value is a boolean.
	 * @property isBoolean
	 * @since 0.1.0
	 */
	public val isBoolean: Boolean
		get() = this.type == JavaScriptPropertyType.BOOLEAN

	/**
	 * Whether the property value is an object.
	 * @property isObject
	 * @since 0.1.0
	 */
	public val isObject: Boolean
		get() = this.type == JavaScriptPropertyType.OBJECT

	/**
	 * Whether the property value is an array.
	 * @property isArray
	 * @since 0.1.0
	 */
	public val isArray: Boolean
		get() = this.type == JavaScriptPropertyType.ARRAY

	/**
	 * Whether the property value is a callback.
	 * @property isCallback
	 * @since 0.1.0
	 */
	public val isCallback: Boolean
		get() = this.type == JavaScriptPropertyType.CALLBACK

	/**
	 * Whether the property value is a variable.
	 * @property isVariable
	 * @since 0.1.0
	 */
	public val isVariable: Boolean
		get() = this.type == JavaScriptPropertyType.VARIABLE

	/**
	 * Whether the property value is a function.
	 * @property isFunction
	 * @since 0.1.0
	 */
	public val isFunction: Boolean
		get() = this.type == JavaScriptPropertyType.FUNCTION

	/**
	 * Whether the property value is a composite.
	 * @property isComposite
	 * @since 0.1.0
	 */
	public val isComposite: Boolean
		get() = this.type == JavaScriptPropertyType.COMPOSITE

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * @constructor
	 * @since 0.1.0
	 * @hidden
	 */
	init {

		this.type = type
		this.unit = unit

		/*
		 * The data parameter is the JavaScript value given initialy. It's
		 * useful to keep a reference instead of recreating a JavaScript value
		 * from the primitive values.
		 */

		this.value = value
	}

	/**
	 * Resets the property value.
	 * @method reset
	 * @since 0.1.0
	 */
	open fun reset(value: JavaScriptValue?) {
		this.value = value
	}

	/**
	 * Converts the property value to a string.
	 * @method toString
	 * @since 0.1.0
	 */
	override fun toString(): String {
		return ""
	}

	/**
	 * Converts the property value to a number.
	 * @method toNumber
	 * @since 0.1.0
	 */
	open fun toNumber(): Double {
		return 0.0
	}

	/**
	 * Converts the property value to a boolean.
	 * @method toBoolean
	 * @since 0.1.0
	 */
	open fun toBoolean(): Boolean {
		return false
	}

	/**
	 * Whether the property value equals a string.
	 * @method equals
	 * @since 0.1.0
	 */
	open fun equals(value: String): Boolean {
		return this.type == JavaScriptPropertyType.STRING && this.string == value
	}

	/**
	 * Whether the property value equals a number.
	 * @method equals
	 * @since 0.1.0
	 */
	open fun equals(value: Double): Boolean {
		return this.type == JavaScriptPropertyType.NUMBER && this.number == value
	}

	/**
	 * Whether the property value equals a number.
	 * @method equals
	 * @since 0.1.0
	 */
	open fun equals(value: Double, unit: JavaScriptPropertyUnit): Boolean {
		return this.type == JavaScriptPropertyType.NUMBER && this.number == value && this.unit == unit
	}

	/**
	 * Whether the property value equals a boolean.
	 * @method equals
	 * @since 0.1.0
	 */
	open fun equals(value: Boolean): Boolean {
		return this.type == JavaScriptPropertyType.BOOLEAN && this.boolean == value
	}

	/**
	 * Whether the property value equals a value.
	 * @method equals
	 * @since 0.1.0
	 */
	open fun equals(value: JavaScriptValue): Boolean {
		return this.value?.equals(value) ?: false
	}

	/**
	 * Casts the property value to a specified type.
	 * @method cast
	 * @since 0.1.0
	 */
	open fun <T> cast(type: Class<T>): T? {
		return this.value?.cast(type)
	}

	//--------------------------------------------------------------------------
	// Internal API
	//--------------------------------------------------------------------------

	/**
	 * @method toHandle
	 * @since 0.1.0
	 * @hidden
	 */
	internal fun toHandle(context: JavaScriptContext): Long? {

		val value = this.value
		if (value != null) {
			return value.toHandle(context)
		}

		if (this.unit == JavaScriptPropertyUnit.NONE) {

			when (this.type) {
				JavaScriptPropertyType.NULL    -> this.value = context.Null
				JavaScriptPropertyType.STRING  -> this.value = context.createString(this.string)
				JavaScriptPropertyType.NUMBER  -> this.value = context.createNumber(this.number)
				JavaScriptPropertyType.BOOLEAN -> this.value = context.createBoolean(this.boolean)
				else                           -> {}
			}

		} else {

			this.value = context.createString(this.string)

		}

		return this.value?.toHandle(context)
	}
}
