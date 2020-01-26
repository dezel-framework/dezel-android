package ca.logaritm.dezel.extension.view

import android.view.View
import android.view.ViewGroup

/**
 * @property visible
 * @since 0.1.0
 * @hidden
 */
internal var View.visible: Boolean
	get() = this.visibility != View.GONE
	set(value) {
		this.visibility = if (value) View.VISIBLE else View.GONE
	}

/**
 * @method JavaScriptView.removeFromParent
 * @since 0.1.0
 * @hidden
 */
internal fun View.removeFromParent() {
	val parent = this.parent
	if (parent is ViewGroup) {
		parent.removeView(this)
	}
}

/**
 * @method JavaScriptView.setMeasuredFrame
 * @since 0.1.0
 * @hidden
 */
internal fun View.setMeasuredFrame(t: Int, l: Int, w: Int, h: Int) {

	this.forceLayout()

	this.measure(
		View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.EXACTLY),
		View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY)
	)

	this.layout(l, t, l + w, t + h)
}