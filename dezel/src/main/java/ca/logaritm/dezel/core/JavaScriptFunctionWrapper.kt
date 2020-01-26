package ca.logaritm.dezel.core

/**
 * @class JavaScriptFunctionWrapper
 * @since 0.1.0
 * @hidden
 */
internal class JavaScriptFunctionWrapper(context: JavaScriptContext, handler: JavaScriptFunctionHandler) {

	/**
	 * @property context
	 * @since 0.1.0
	 * @hidden
	 */
	private val context: JavaScriptContext

	/**
	 * @property handler
	 * @since 0.1.0
	 * @hidden
	 */
	private val handler: JavaScriptFunctionHandler

	/**
	 * @constructor
	 * @since 0.1.0
	 * @hidden
	 */
	init {
		this.context = context
		this.handler = handler
	}

	/**
	 * @method execute
	 * @since 0.1.0
	 * @hidden
	 */
	public fun execute(callback: JavaScriptFunctionCallback) {
		this.handler(callback)
	}
}