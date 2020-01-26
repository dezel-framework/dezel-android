package ca.logaritm.dezel.view.text.span

import android.text.TextPaint
import android.text.style.CharacterStyle


/**
 * @class TextColorSpan
 * @super CharacterStyle
 * @since 0.1.0
 */
open class TextColorSpan(textColor: Int): CharacterStyle() {

	/**
	 * The text color.
	 * @property textColor
	 * @since 0.1.0
	 */
	public val textColor: Int = textColor

	/**
	 * @inherited
	 * @method updateDrawState
	 * @since 0.1.0
	 */
	override fun updateDrawState(paint: TextPaint) {
		paint.color = this.textColor
	}
}