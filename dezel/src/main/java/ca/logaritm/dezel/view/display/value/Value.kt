package ca.logaritm.dezel.view.display.value

import ca.logaritm.dezel.view.display.external.ValueExternal

/**
 * @class Value
 * @since 0.1.0
 */
open class Value(handle: Long) {

	//--------------------------------------------------------------------------
	// Static
	//--------------------------------------------------------------------------

	companion object {

		/**
		 * Parses a string.
		 * @method parse
		 * @since 0.1.0
		 */
		public fun parse(source: String): ValueList? {

			val values = ValueExternal.parse(source)
			if (values == 0L) {
				return null
			}

			return ValueList(values)
		}
	}

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The value's type.
	 * @property type
	 * @since 0.1.0
	 */
	public val type: Int
		get() = ValueExternal.getType(this.handle)

	/**
	 * The value's unit.
	 * @property unit
	 * @since 0.1.0
	 */
	public val unit: Int
		get() = ValueExternal.getUnit(this.handle)

	/**
	 * The value as a string.
	 * @property string
	 * @since 0.1.0
	 */
	public val string: String
		get() = ValueExternal.getString(this.handle)

	/**
	 * The value as a number.
	 * @property number
	 * @since 0.1.0
	 */
	public val number: Double
		get() = ValueExternal.getNumber(this.handle)

	/**
	 * The value as a boolean.
	 * @property boolean
	 * @since 0.1.0
	 */
	public val boolean: Boolean
		get() = ValueExternal.getBoolean(this.handle)

	/**
	 * The value's handle.
	 * @property handle
	 * @since 0.1.0
	 */
	public var handle: Long = 0L
		private set

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * Initializes the value.
	 * @constructor
	 * @since 0.1.0
	 */
	init {
		this.handle = handle
	}
}