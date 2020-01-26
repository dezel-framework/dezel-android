package ca.logaritm.dezel.extension.core

import ca.logaritm.dezel.core.JavaScriptValue
import java.net.URL

/**
 * @method JavaScriptValue.toURL
 * @since 0.1.0
 * @hidden
 */
internal fun JavaScriptValue.toURL(): URL? {
	try {
		return URL(this.string)
	} catch (e: Exception) {
		return null
	}
}

/**
 * @method JavaScriptValue.toDictionaryOfString
 * @since 0.1.0
 * @hidden
 */
internal fun JavaScriptValue.toDictionaryOfString(): MutableMap<String, String> {

	val dictionary = mutableMapOf<String, String>()

	this.forOwn { key, value ->
		dictionary[key] = value.string
	}

	return dictionary
}

/**
 * @method JavaScriptValue.toDictionaryOfNumber
 * @since 0.1.0
 * @hidden
 */
internal fun JavaScriptValue.toDictionaryOfNumber(): Map<String, Double> {

	val dictionary = mutableMapOf<String, Double>()

	this.forOwn { key, value ->
		dictionary.put(key, value.number)
	}

	return dictionary
}
