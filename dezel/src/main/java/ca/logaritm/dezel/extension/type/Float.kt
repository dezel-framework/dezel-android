package ca.logaritm.dezel.extension.type

/**
 * @property Float.Companion.min
 * @since 0.1.0
 * @hidden
 */
internal val Float.Companion.min: Float
	get() = -MAX_VALUE

/**
 * @property Float.Companion.max
 * @since 0.1.0
 * @hidden
 */
internal val Float.Companion.max: Float
	get() = +MAX_VALUE

/**
 * @method Float.Companion.ofDouble
 * @since 0.1.0
 * @hidden
 */
internal fun Float.Companion.ofDouble(value: Double): Float {
	if (value < Float.min.toDouble()) return Float.min
	if (value > Float.max.toDouble()) return Float.max
	return value.toFloat()
}

/**
 * @method Float.clamped
 * @since 0.1.0
 * @hidden
 */
internal fun Float.clamp(min: Float, max: Float): Float {
	return if (this < min) min else if (this > max) max else this
}

/**
 * @method Float.toDeg()
 * @since 0.1.0
 * @hidden
 */
internal fun Float.toDeg(): Float {
	return this * Math.PI.toFloat() / 180
}

/**
 * @method Float.toRad()
 * @since 0.1.0
 * @hidden
 */
internal fun Float.toRad(): Float {
	return this / Math.PI.toFloat() * 180
}