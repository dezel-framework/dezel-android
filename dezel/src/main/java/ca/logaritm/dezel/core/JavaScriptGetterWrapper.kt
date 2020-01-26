package ca.logaritm.dezel.core

/**
 * @class JavaScriptGetterWrapper
 * @since 0.1.0
 * @hidden
 */
internal class JavaScriptGetterWrapper(context: JavaScriptContext, handler: JavaScriptGetterHandler) {

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
	private val handler: JavaScriptGetterHandler

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
	public fun execute(callback: JavaScriptGetterCallback) {
		this.handler(callback)
	}
}