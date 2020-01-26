package ca.logaritm.dezel.view.text.span

import android.text.TextPaint
import android.text.style.URLSpan
import ca.logaritm.dezel.view.type.TextDecoration

/**
 * @class LinkSpan
 * @super URLSpan
 * @since 0.1.0
 */
open class LinkSpan(url: String, color: Int, decoration: TextDecoration): URLSpan(url) {

	/**
	 * @property color
	 * @since 0.1.0
	 * @hidden
	 */
	private var color: Int = color

	/**
	 * @property decoration
	 * @since 0.1.0
	 * @hidden
	 */
	private var decoration: TextDecoration = decoration

	/**
	 * @inherited
	 * @method updateDrawState
	 * @since 0.1.0
	 */
	override fun updateDrawState(ds: TextPaint) {

		ds.color = this.color

		when (this.decoration) {
			TextDecoration.UNDERLINE   -> ds.isUnderlineText = true
			TextDecoration.LINETHROUGH -> ds.isStrikeThruText = true
			TextDecoration.NONE        -> {}
		}
	}
}