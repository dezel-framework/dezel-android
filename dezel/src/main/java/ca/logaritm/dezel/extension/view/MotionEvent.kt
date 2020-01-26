package ca.logaritm.dezel.extension.view

import android.view.MotionEvent

/**
 * @property MotionEvent.pointer
 * @since 0.1.0
 * @hidden
 */
internal val MotionEvent.pointer
    get() = this.getPointerId(this.actionIndex)