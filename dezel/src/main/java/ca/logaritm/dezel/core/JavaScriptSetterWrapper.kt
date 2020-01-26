package ca.logaritm.dezel.core

/**
 * @class JavaScriptSetterWrapper
 * @since 0.1.0
 * @hidden
 */
internal class JavaScriptSetterWrapper(context: JavaScriptContext, handler: JavaScriptSetterHandler) {

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
	private val handler: JavaScriptSetterHandler

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
	public fun execute(callback: JavaScriptSetterCallback) {
		this.handler(callback)
	}
}