package ca.logaritm.dezel.modules.platform

import ca.logaritm.dezel.core.JavaScriptContext
import ca.logaritm.dezel.core.JavaScriptModule

/**
 * @class JavaScriptPlatformModule
 * @super JavaScriptModule
 * @since 0.1.0
 */
public class JavaScriptPlatformModule: JavaScriptModule() {

	/**
	 * @inherited
	 * @method configure
	 * @since 0.1.0
	 */
	override fun configure(context: JavaScriptContext) {
		context.registerClass("dezel.platform.Platform", JavaScriptPlatform::class.java)
	}
}
