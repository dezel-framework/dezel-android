package ca.logaritm.dezel.modules.platform

import android.os.Build
import ca.logaritm.dezel.core.JavaScriptClass
import ca.logaritm.dezel.core.JavaScriptContext
import ca.logaritm.dezel.core.JavaScriptGetterCallback

/**
 * @class JavaScriptPlatform
 * @super JavaScriptClass
 * @since 0.1.0
 */
open class JavaScriptPlatform(context: JavaScriptContext): JavaScriptClass(context) {

	//--------------------------------------------------------------------------
	// JS Functions
	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_name
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_name(callback: JavaScriptGetterCallback) {
		callback.returns("android")
	}

	/**
	 * @method jsGet_version
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_version(callback: JavaScriptGetterCallback) {
		callback.returns(Build.VERSION.RELEASE)
	}
}
