package ca.logaritm.dezel.modules.global

import ca.logaritm.dezel.core.JavaScriptContext
import ca.logaritm.dezel.core.JavaScriptFunctionCallback
import ca.logaritm.dezel.core.JavaScriptGetterCallback
import ca.logaritm.dezel.core.JavaScriptObject
import ca.logaritm.dezel.extension.core.activity

/**
 * @class JavaScriptLocation
 * @super JavaScriptClass
 * @since 0.1.0
 */
open class JavaScriptLocation(context: JavaScriptContext): JavaScriptObject(context) {

    //--------------------------------------------------------------------------
    // MARK: JS Properties
    //--------------------------------------------------------------------------

    /**
     * @method jsGet_protocol
     * @since 0.1.0
     * @hidden
     */
    @Suppress("unused")
    open fun jsGet_protocol(callback: JavaScriptGetterCallback) {
        callback.returns("http:")
    }

    //--------------------------------------------------------------------------

    /**
     * @method jsGet_hostname
     * @since 0.1.0
     * @hidden
     */
    @Suppress("unused")
    open fun jsGet_hostname(callback: JavaScriptGetterCallback) {
        callback.returns("localhost")
    }

    //--------------------------------------------------------------------------

    /**
     * @method jsGet_port
     * @since 0.1.0
     * @hidden
     */
    @Suppress("unused")
    open fun jsGet_port(callback: JavaScriptGetterCallback) {
        callback.returns("")
    }

    //--------------------------------------------------------------------------

    /**
     * @method jsGet_href
     * @since 0.1.0
     * @hidden
     */
    @Suppress("unused")
    open fun jsGet_href(callback: JavaScriptGetterCallback) {
        callback.returns("http://localhost")
    }

    //--------------------------------------------------------------------------

    /**
     * @method jsGet_search
     * @since 0.1.0
     * @hidden
     */
    @Suppress("unused")
    open fun jsGet_search(callback: JavaScriptGetterCallback) {
        callback.returns("")
    }

    //--------------------------------------------------------------------------
    // MARK: JS Methods
    //--------------------------------------------------------------------------

    /**
     * @method jsFunction_reload
     * @since 0.1.0
     * @hidden
     */
    @Suppress("unused")
    open fun jsFunction_reload(callback: JavaScriptFunctionCallback) {

        if (callback.arguments == 1) {
            val options = callback.argument(0)
            if (options.isObject &&
                options.property("mode").string == "styles") {
                this.context.activity.reloadStyles()
                return
            }
        }

        this.context.activity.reload()
    }
}
