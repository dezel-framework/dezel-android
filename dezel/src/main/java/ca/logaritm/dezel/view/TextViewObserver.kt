package ca.logaritm.dezel.view

/**
 * @interface TextViewObserver
 * @since 0.1.0
 */
public interface TextViewObserver {

	/**
	 * Called when a link is pressed.
	 * @method onPressLink
	 * @since 0.1.0
	 */
	fun onPressLink(textView: TextView, url: String)

}
