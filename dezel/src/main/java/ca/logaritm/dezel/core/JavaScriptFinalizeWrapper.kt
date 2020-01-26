package ca.logaritm.dezel.core

/**
 * @class JavaScriptFinalizeWrapper
 * @since 0.1.0
 * @hidden
 */
internal class JavaScriptFinalizeWrapper(context: JavaScriptContext, handler: JavaScriptFinalizeHandler) {

	/**
	 * @property context
	 * @since 0.1.0
	 * @hidden
	 */
	private var context: JavaScriptContext

	/**
	 * @property handler
	 * @since 0.1.0
	 * @hidden
	 */
	private val handler: JavaScriptFinalizeHandler

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
	public fun execute(handler: JavaScriptFinalizeCallback) {
		this.handler(handler)
	}
}