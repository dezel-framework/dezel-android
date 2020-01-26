package ca.logaritm.dezel.modules.device

import ca.logaritm.dezel.core.JavaScriptContext
import ca.logaritm.dezel.core.JavaScriptModule

/**
 * @class JavaScriptDeviceModule
 * @super JavaScriptModule
 * @since 0.1.0
 */
open class JavaScriptDeviceModule: JavaScriptModule() {

	/**
	 * @inherited
	 * @method configure
	 * @since 0.1.0
	 */
	override fun configure(context: JavaScriptContext) {
		context.registerClass("dezel.device.Device", JavaScriptDevice::class.java)
	}
}