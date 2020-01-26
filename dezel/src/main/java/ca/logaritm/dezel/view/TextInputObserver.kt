package ca.logaritm.dezel.view

/**
 * @interface TextInputObserver
 * @since 0.1.0
 */
public interface TextInputObserver {

	/**
	 * Called when the text input's value changes.
	 * @method onChange
	 * @since 0.1.0
	 */
	fun onChange(textInput: TextInput, value: String)

	/**
	 * Called when the text input receives focus.
	 * @method onFocus
	 * @since 0.1.0
	 */
	fun onFocus(textInput: TextInput)

	/**
	 * Called when the text input loses focus.
	 * @method onBlur
	 * @since 0.1.0
	 */
	fun onBlur(textInput: TextInput)

}
