package ca.logaritm.dezel.util.geom

/**
 * @class Rect
 * @since 0.1.0
 */
public class Rect(x: Float = 0f, y: Float = 0f, width: Float = 0f, height: Float = 0f) {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The rect's x position.
	 * @property x
	 * @since 0.1.0
	 */
	public var x: Float = 0f

	/**
	 * The rect's y position.
	 * @property y
	 * @since 0.1.0
	 */
	public var y: Float = 0f

	/**
	 * The rect's width.
	 * @property width
	 * @since 0.1.0
	 */
	public var width: Float = 0f

	/**
	 * The rect's height.
	 * @property height
	 * @since 0.1.0
	 */
	public var height: Float = 0f

	/**
	 * The rect's size.
	 * @property size
	 * @since 0.1.0
	 */
	public val size: Size
		get() = Size(this.width, this.height)

	//--------------------------------------------------------------------------
	// Method
	//--------------------------------------------------------------------------

	/**
	 * Initializes the rect.
	 * @constructor
	 * @since 0.1.0
	 */
	init {
		this.x = x
		this.y = y
		this.width = width
		this.height = height
	}

	/**
	 * @inherited
	 * @method toString
	 * @since 0.1.0
	 */
	override fun toString(): String {
		return "x:" + this.x + " y:" + this.y + " width:" + this.width + " height:" + this.height
	}
}
