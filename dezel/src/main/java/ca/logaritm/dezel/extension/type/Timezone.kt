package ca.logaritm.dezel.extension.type

import java.util.*

/**
 * @object TimeZone
 * @since 0.1.0
 * @hidden
 */
internal object Timezone {

	/**
	 * @property utc
	 * @since 0.1.0
	 * @hidden
	 */
	internal val utc: TimeZone
		get() = TimeZone.getTimeZone("UTC")
}
