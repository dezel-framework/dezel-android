package ca.logaritm.dezel.application.source

import android.content.Context
import ca.logaritm.dezel.application.ApplicationActivity

/**
 * @class Styles
 * @super Source
 * @since 0.1.0
 */
open class Styles(context: Context, path: String): Source(context, path) {

    /**
     * @inherited
     * @method apply
     * @since 0.1.0
     */
    override fun apply(application: ApplicationActivity) {
        application.stylesheet.evaluate(this.data, this.path)
    }
}