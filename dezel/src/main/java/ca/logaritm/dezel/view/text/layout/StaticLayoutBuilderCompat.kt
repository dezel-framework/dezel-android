package ca.logaritm.dezel.view.text.layout

import android.text.*
import java.lang.reflect.Constructor

/**
 * @class StaticLayoutBuilderCompat
 * @since 0.1.0
 */
public object StaticLayoutBuilderCompat  {

	/**
	 * @property constructor
	 * @since 0.1.0
	 * @hidden
	 */
	private var constructor: Constructor<StaticLayout> = StaticLayout::class.java.getDeclaredConstructor(
		CharSequence::class.java,
		Int::class.java,
		Int::class.java,
		TextPaint::class.java,
		Int::class.java,
		Layout.Alignment::class.java,
		TextDirectionHeuristic::class.java,
		Float::class.java,
		Float::class.java,
		Boolean::class.java,
		TextUtils.TruncateAt::class.java,
		Int::class.java,
		Int::class.java
	)

	/**
	 * Initializes the layout builer compat.
	 * @constructor
	 * @since 0.1.0
	 */
	init {
		this.constructor.isAccessible = true
	}

	/**
	 * Creates a new layout builder compat.
	 * @method create
	 * @since 0.1.0
	 */
	public fun create(text: CharSequence, bs: Int, be: Int, paint: TextPaint, width: Int, sm: Float, sa: Float, ellipsize: TextUtils.TruncateAt?, lines: Int): StaticLayout {
		return this.constructor.newInstance(text, bs, be, paint, width, Layout.Alignment.ALIGN_NORMAL, TextDirectionHeuristics.FIRSTSTRONG_LTR, sm, sa, false, ellipsize, width, lines)
	}
}