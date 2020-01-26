package ca.logaritm.dezel.view.text.font

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import ca.logaritm.dezel.view.graphic.Convert

/**
 * @class Font
 * @since 0.1.0
 */
open class Font(family: String, weight: String, style: String, size: Float) {

	//--------------------------------------------------------------------------
	// Static
	//--------------------------------------------------------------------------

	companion object {

		/**
		 * The default font.
		 * @property DEFAULT
		 * @since 0.1.0
		 */
		public val DEFAULT = Font("", "", "normal", Convert.toPx(17f))

	}

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The font's family.
	 * @property family
	 * @since 0.1.0
	 */
	public val family: String = family

	/**
	 * The font's weight.
	 * @property weight
	 * @since 0.1.0
	 */
	public val weight: String = weight

	/**
	 * The font's style.
	 * @property style
	 * @since 0.1.0
	 */
	public val style: String = style

	/**
	 * The font's size.
	 * @property size
	 * @since 0.1.0
	 */
	public val size: Float = size

	/**
	 * The font's typeface.
	 * @property typeface
	 * @since 0.1.0
	 */
	public val typeface: Typeface = FontManager.get(family, weight, style)

	/**
	 * The font's metrics.
	 * @property metrics
	 * @since 0.1.0
	 */
	public val metrics: Paint.FontMetrics
		get() = this.paint.fontMetrics

	/**
	 * @property paint
	 * @since 0.1.0
	 * @hidden
	 */
	private val paint: TextPaint = TextPaint()

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * Initializes the font.
	 * @constructor
	 * @since 0.1.0
	 */
	init {
		this.paint.typeface = this.typeface
		this.paint.textSize = this.size
	}

	/**
	 * Returns the bold variant of this font.
	 * @method withBold
	 * @since 0.1.0
	 */
	public fun withBold(): Font {
		return Font(this.family, "bold", this.style, this.size)
	}

	/**
	 * Returns the italic variant of this font.
	 * @method withItalic
	 * @since 0.1.0
	 */
	public fun withItalic(): Font {
		return Font(this.family, this.weight, "italic", this.size)
	}
}