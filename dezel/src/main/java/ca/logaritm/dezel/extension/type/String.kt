package ca.logaritm.dezel.extension.type

import ca.logaritm.dezel.view.graphic.Color
import java.util.*
import java.util.regex.Pattern

public val spaces = Pattern.compile("[\t ]+").toRegex()

/**
 * @property isHTML
 * @since 0.1.0
 * @hidden
 */
internal val String.isHTML: Boolean
	get() = RegularExpression.isHTML(this)

/**
 * @method String.until
 * @since 0.1.0
 * @hidden
 */
internal fun String.until(char: Char): String {
	return this.substringBefore(char)
}

/**
 * @method String.normalize
 * @since 0.1.0
 * @hidden
 */
internal fun String.normalize(): String {
	return (
		this.trim()
			.replace("\\n", "\n")
			.replace(spaces, " ")
			.replace("\n ", "\n")
	)
}

/**
 * @method String.toLocale
 * @since 0.1.0
 * @hidden
 */
internal fun String.toLocale(): Locale {

	val components = this.split("-")
	if (components.size == 2) {
		return Locale(components[0], components[1])
	}

	return Locale.getDefault()
}

/**
 * @method toNumber
 * @since 0.1.0
 * @hidden
 */
internal fun String.toNumber(): Double {

	if (this == "") {
		return 0.0
	}

	var limit = 0
	val chars = StringBuilder()

	for (char in this) {

		limit += 1

		if (char.isDigit() == false &&
			char != '+' &&
			char != '-' &&
			char != '.') {
			break
		}

		chars.append(char)
	}

	if (limit == 1) {
		return Double.NaN
	}

	return chars.toString().toDouble()
}

/**
 * @method String.toColor
 * @since 0.1.0
 * @hidden
 */
public fun String.toColor(): Int {
	return Color.parse(this)
}

