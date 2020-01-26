package ca.logaritm.dezel.view.display.value

import ca.logaritm.dezel.view.display.external.FunctionValueExternal

/**
 * @class FunctionValue
 * @super Value
 * @since 0.1.0
 */
public class FunctionValue(handle: Long): Value(handle) {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The function's name.
	 * @property name
	 * @since 0.1.0
	 */
	public val name: String
		get() = FunctionValueExternal.getName(this.handle)

	/**
	 * The function's arguments.
	 * @property arguments
	 * @since 0.1.0
	 */
	public val arguments: Int
		get() = FunctionValueExternal.getArguments(this.handle)

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * Returns an argument at an index.
	 * @method argument
	 * @since 0.1.0
	 */
	public fun argument(index: Int): ValueList {
		return ValueList(FunctionValueExternal.getArgument(this.handle, index))
	}
}