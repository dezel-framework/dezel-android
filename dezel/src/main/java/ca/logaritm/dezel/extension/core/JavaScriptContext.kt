package ca.logaritm.dezel.extension.core

import android.net.Uri
import ca.logaritm.dezel.application.ApplicationActivity
import ca.logaritm.dezel.core.JavaScriptContext
import ca.logaritm.dezel.core.JavaScriptValue
import java.net.URL

/**
 * The JavaScript context's activity.
 * @property activity
 * @since 0.1.0
 */
public val JavaScriptContext.activity: ApplicationActivity
	get() = this.attribute(ApplicationActivity.kApplicationActivityKey) as ApplicationActivity

/**
 * @method JavaScriptContext.createObject
 * @since 0.1.0
 * @hidden
 */
internal fun JavaScriptContext.createString(url: URL): JavaScriptValue {
	return this.createString(url.toString())
}

/**
 * @method JavaScriptContext.createString
 * @since 0.1.0
 * @hidden
 */
internal fun JavaScriptContext.createString(url: Uri): JavaScriptValue {
	return this.createString(url.toString())
}

/**
 * @method JavaScriptContext.createObject
 * @since 0.1.0
 * @hidden
 */
internal fun JavaScriptContext.createObject(dictionary: Map<String, String>): JavaScriptValue {

	val result = this.createEmptyObject()

	for ((k, v) in dictionary) {
		result.property(k, this.createString(v))
	}

	return result
}