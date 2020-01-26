package ca.logaritm.dezel.modules.util

import ca.logaritm.dezel.core.JavaScriptClass
import ca.logaritm.dezel.core.JavaScriptContext
import ca.logaritm.dezel.core.JavaScriptFunctionCallback
import ca.logaritm.dezel.extension.core.activity
import ca.logaritm.dezel.modules.application.JavaScriptApplication

/**
 * @class JavaScriptUtil
 * @super JavaScriptClass
 * @since 0.1.0
 */
open class JavaScriptUtil(context: JavaScriptContext): JavaScriptClass(context) {

	//--------------------------------------------------------------------------
	// JS Functions
	//--------------------------------------------------------------------------

	/**
	 * @property jsFunction_importClass
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsFunction_importClass(callback: JavaScriptFunctionCallback) {
		this.context.classes[callback.argument(0).string]?.let {
			callback.returns(it)
		}
	}

	/**
	 * @property jsFunction_importObject
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsFunction_importObject(callback: JavaScriptFunctionCallback) {
		this.context.objects[callback.argument(0).string]?.let {
			callback.returns(it)
		}
	}

	/**
	 * @property jsFunction_registerApplication
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsFunction_registerApplication(callback: JavaScriptFunctionCallback) {
		callback.argument(0).cast(JavaScriptApplication::class.java)?.let {
			this.context.activity.register(it)
		}
	}

	/**
	 * @property jsFunction_reload
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsFunction_reload(callback: JavaScriptFunctionCallback) {
		this.context.activity.reload()
	}

	/**
	 * @property jsFunction_reloadStyles
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsFunction_reloadStyles(callback: JavaScriptFunctionCallback) {
		this.context.activity.reloadStyles()
	}
}