package ca.logaritm.dezel.modules.locale

import ca.logaritm.dezel.core.JavaScriptContext
import ca.logaritm.dezel.core.JavaScriptModule

/**
 * @class JavaScriptLocaleModule
 * @super JavaScriptModule
 * @since 0.1.0
 */
open class JavaScriptLocaleModule: JavaScriptModule() {

	/**
	 * @inherited
	 * @method configure
	 * @since 0.1.0
	 */
	override fun configure(context: JavaScriptContext) {
		context.registerClass("dezel.locale.Locale", JavaScriptLocale::class.java)
	}
}
