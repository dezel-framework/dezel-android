package ca.logaritm.dezel.application.source

import android.content.Context
import android.util.Log
import ca.logaritm.dezel.application.ApplicationActivity
import ca.logaritm.dezel.core.JavaScriptException
import java.lang.Exception

/**
 * @class Script
 * @super Source
 * @since 0.1.0
 */
open class Script(context: Context, path: String): Source(context, path) {

    /**
     * @inherited
     * @method apply
     * @since 0.1.0
     */
    override fun apply(application: ApplicationActivity) {
        application.context.evaluate(this.data, this.path)
    }
}