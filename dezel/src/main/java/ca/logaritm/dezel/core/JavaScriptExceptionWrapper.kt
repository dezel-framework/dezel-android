package ca.logaritm.dezel.core

/**
 * @class JavaScriptExceptionWrapper
 * @since 0.1.0
 * @hidden
 */
internal class JavaScriptExceptionWrapper(context: JavaScriptContext, handler: JavaScriptExceptionHandler) {

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
	private val handler: JavaScriptExceptionHandler

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
	public fun execute(error: JavaScriptValue) {
		this.handler(error)
	}
}