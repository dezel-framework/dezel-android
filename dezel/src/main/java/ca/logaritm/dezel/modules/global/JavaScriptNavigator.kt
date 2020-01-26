package ca.logaritm.dezel.modules.global

import ca.logaritm.dezel.core.JavaScriptContext
import ca.logaritm.dezel.core.JavaScriptGetterCallback
import ca.logaritm.dezel.core.JavaScriptObject

/**
 * @class JavaScriptNavigator
 * @super JavaScriptClass
 * @since 0.1.0
 */
open class JavaScriptNavigator(context: JavaScriptContext): JavaScriptObject(context) {

    //--------------------------------------------------------------------------
    // MARK: JS Properties
    //--------------------------------------------------------------------------

    /**
     * @method jsGet_userAgent
     * @since 0.1.0
     * @hidden
     */
    @Suppress("unused")
    open fun jsGet_userAgent(callback: JavaScriptGetterCallback) {
        callback.returns("Dezel/0.1.0")
    }
}