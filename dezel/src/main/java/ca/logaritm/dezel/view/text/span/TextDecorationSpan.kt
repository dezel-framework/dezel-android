package ca.logaritm.dezel.view.text.span

import android.text.TextPaint
import android.text.style.CharacterStyle
import ca.logaritm.dezel.view.type.TextDecoration

/**
 * @class TextDecorationSpan
 * @super CharacterStyle
 * @since 0.1.0
 */
open class TextDecorationSpan(textDecoration: TextDecoration): CharacterStyle() {

	/**
	 * The text decoration.
	 * @property textDecoration
	 * @since 0.1.0
	 */
	public val textDecoration: TextDecoration = textDecoration

	/**
	 * @inherited
	 * @method updateDrawState
	 * @since 0.1.0
	 */
	override fun updateDrawState(paint: TextPaint) {

		when (this.textDecoration) {

			TextDecoration.NONE        -> {
				paint.isUnderlineText = false
				paint.isStrikeThruText = false
			}

			TextDecoration.UNDERLINE   -> {
				paint.isUnderlineText = true
				paint.isStrikeThruText = false
			}

			TextDecoration.LINETHROUGH -> {
				paint.isUnderlineText = false
				paint.isStrikeThruText = true
			}
		}
	}
}