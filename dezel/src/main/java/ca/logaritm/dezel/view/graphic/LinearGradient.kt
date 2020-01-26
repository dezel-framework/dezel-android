package ca.logaritm.dezel.view.graphic

import ca.logaritm.dezel.core.JavaScriptProperty

/**
 * @class LinearGradient
 * @since 0.1.0
 */
open class LinearGradient(value: JavaScriptProperty) {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The linear gradient's colors.
	 * @property colors
	 * @since 0.1.0
	 */
	public var colors: MutableList<Int> = mutableListOf()
		private set

	/**
	 * The linear gradient's points.
	 * @property points
	 * @since 0.1.0
	 */
	public var points: MutableList<Float> = mutableListOf()
		private set

	/**
	 * The linear gradient's angle.
	 * @property angle
	 * @since 0.1.0
	 */
	public var angle: Float = 0f
		private set

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * Initializes the linear gradient.
	 * @constructor
	 * @since 0.1.0
	 */
	init {

	}
}
