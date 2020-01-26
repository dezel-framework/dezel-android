package ca.logaritm.dezel.view.text.span

import android.text.TextPaint
import android.text.style.MetricAffectingSpan
import ca.logaritm.dezel.view.text.font.Font

/**
 * @class FontSpan
 * @super MetricAffectingSpan
 * @since 0.1.0
 */
open class FontSpan(font: Font): MetricAffectingSpan() {

	/**
	 * The font typeface.
	 * @property font
	 * @since 0.1.0
	 */
	public val font: Font = font

	/**
	 * @inherited
	 * @method updateMeasureState
	 * @since 0.1.0
	 */
	override fun updateMeasureState(paint: TextPaint) {
		paint.typeface = this.font.typeface
		paint.textSize = this.font.size
	}

	/**
	 * @inherited
	 * @method updateDrawState
	 * @since 0.1.0
	 */
	override fun updateDrawState(paint: TextPaint) {
		paint.typeface = this.font.typeface
		paint.textSize = this.font.size
	}
}