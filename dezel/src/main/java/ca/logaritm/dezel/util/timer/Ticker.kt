package ca.logaritm.dezel.util.timer

/**
 * @interface Ticker
 * @since 0.1.0
 */
public interface Ticker {

	/**
	 * Called when the timer times out or reaches its interval.
	 * @method onTick
	 * @since 0.1.0
	 */
	fun onTick()
}