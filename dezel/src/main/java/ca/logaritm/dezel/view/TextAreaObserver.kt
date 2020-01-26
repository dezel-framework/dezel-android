package ca.logaritm.dezel.view

/**
 * @interface TextAreaObserver
 * @since 0.1.0
 */
public interface TextAreaObserver {

	/**
	 * Called when the text area's value changes.
	 * @method onChange
	 * @since 0.1.0
	 */
	fun onChange(textArea: TextArea, value: String)

	/**
	 * Called when the text area's receives focus.
	 * @method onFocus
	 * @since 0.1.0
	 */
	fun onFocus(textArea: TextArea)

	/**
	 * Called when the text area's loses focus.
	 * @method onBlur
	 * @since 0.1.0
	 */
	fun onBlur(textArea: TextArea)

}
