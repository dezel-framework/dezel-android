package ca.logaritm.dezel.view.display

/**
 * @interface StylesheetListener
 * @since 0.1.0
 */
interface StylesheetListener {

	/**
	 * Called when the stylesheet throws an error.
	 * @method onThrowError
	 * @since 0.1.0
	 */
	fun onThrowError(stylesheet: Stylesheet, error: String, col: Int, row: Int, url: String)

}