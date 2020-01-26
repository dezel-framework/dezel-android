package ca.logaritm.dezel.view.trait

import android.view.MotionEvent

/**
 * @interface TouchCancelable
 * @since 0.1.0
 */
public interface TouchCancelable {

    /**
     * Cancels the touch event.
     * @method cancelTouchEvent
     * @since 0.1.0
     */
    fun cancelTouchEvent(event: MotionEvent)

}