package ca.logaritm.dezel.view.display.value

import ca.logaritm.dezel.view.display.external.ValueListExternal

/**
 * @class ValueList
 * @since 0.1.0
 */
public class ValueList(handle: Long) {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The value list's count.
	 * @property count
	 * @since 0.1.0
	 */
	public val count: Int
		get() = ValueListExternal.getCount(this.handle)

	/**
	 * The value list's handle.
	 * @property handle
	 * @since 0.1.0
	 */
	public var handle: Long = 0L
		private set

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * Initializes the value.
	 * @constructor
	 * @since 0.1.0
	 */
	init {
		this.handle = handle
	}

	/**
	 * Returns a value at a specified index.
	 * @method get
	 * @since 0.1.0
	 */
	public fun get(index: Int): Value {
		return Value(ValueListExternal.getValue(this.handle, index))
	}

	/**
	 * @method delete
	 * @since 0.1.0
	 * @hidden
	 */
	internal fun delete() {
		ValueListExternal.delete(this.handle)
	}
}