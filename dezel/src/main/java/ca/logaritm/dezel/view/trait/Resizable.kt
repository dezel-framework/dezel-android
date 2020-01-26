package ca.logaritm.dezel.view.trait

/**
 * @interface Resizable
 * @since 0.1.0
 */
public interface Resizable {

	/**
	 * Called when the wrapper view resizes.
	 * @method onResize
	 * @since 0.1.0
	 */
	fun onResize(t: Int, l: Int, w: Int, h: Int)
}