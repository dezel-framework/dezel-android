package ca.logaritm.dezel.view.text.span

import android.text.style.ParagraphStyle

/**
 * @class TextLeadingSpan
 * @super ParagraphStyle
 * @since 0.1.0
 */
open class TextLeadingSpan(textLeading: Float): ParagraphStyle {

	/**
	 * The text leading.
	 * @property textLeading
	 * @since 0.1.0
	 */
	public val textLeading: Float = textLeading

}