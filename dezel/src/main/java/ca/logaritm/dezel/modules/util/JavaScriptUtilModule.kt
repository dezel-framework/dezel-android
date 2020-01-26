package ca.logaritm.dezel.modules.util

import ca.logaritm.dezel.core.JavaScriptContext
import ca.logaritm.dezel.core.JavaScriptModule

/**
 * @class CoreModule
 * @since 0.1.0
 * @hidden
 */
open class JavaScriptUtilModule : JavaScriptModule() {

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * @inherited
	 * @method configure
	 * @since 0.1.0
	 */
	override fun configure(context: JavaScriptContext) {
		context.global.defineProperty(
			"__util__",
			value = context.createObject(JavaScriptUtil::class.java),
			getter = null,
			setter = null,
			writable = false,
			enumerable = false,
			configurable = false
		)
	}
}