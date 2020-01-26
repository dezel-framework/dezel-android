package ca.logaritm.dezel.extension.widget

import android.graphics.drawable.Drawable
import android.widget.Button

/**
 * @method Button.setDrawableLeft
 * @since 0.1.0
 * @hidden
 */
internal fun Button.setDrawableLeft(drawable: Drawable) {
    this.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
}

/**
 * @method Button.setDrawablePadding
 * @since 0.1.0
 * @hidden
 */
internal fun Button.setDrawablePadding(padding: Int) {
    this.compoundDrawablePadding = padding
}