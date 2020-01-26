package ca.logaritm.dezel.view.text.span

import android.text.TextPaint
import android.text.style.MetricAffectingSpan
import ca.logaritm.dezel.view.graphic.Convert

/**
 * @class TextKerningSpan
 * @super MetricAffectingSpan
 * @since 0.1.0
 */
open class TextKerningSpan(textKerning: Float): MetricAffectingSpan() {

	/**
	 * The text kerning.
	 * @property textKerning
	 * @since 0.1.0
	 */
	public val textKerning: Float = textKerning

	/**
	 * @inherited
	 * @method updateMeasureState
	 * @since 0.1.0
	 */
	override fun updateMeasureState(paint: TextPaint) {
		if (this.textKerning != 0f) {
			paint.letterSpacing = this.textKerning / Convert.toPx(16f)
		}
	}

	/**
	 * @inherited
	 * @method updateDrawState
	 * @since 0.1.0
	 */
	override fun updateDrawState(paint: TextPaint) {
		if (this.textKerning != 0f) {
			paint.letterSpacing = this.textKerning / Convert.toPx(16f)
		}
	}
}