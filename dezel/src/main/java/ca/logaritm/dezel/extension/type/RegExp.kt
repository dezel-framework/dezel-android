package ca.logaritm.dezel.extension.type

import java.util.regex.Pattern

/**
 * @extension RegularExpression
 * @since 0.1.0
 * @hidden
 */
internal object RegularExpression {

	/**
	 * @property isHTMLRegex
	 * @since 0.1.0
	 * @hidden
	 */
	private var isHTMLRegex = Pattern.compile("<[a-z][\\s\\S]*>", Pattern.CASE_INSENSITIVE)

	/**
	 * @method isHTML
	 * @since 0.1.0
	 * @hidden
	 */
	internal fun isHTML(string: String): Boolean {
		return isHTMLRegex.matcher(string).find(0)
	}
}