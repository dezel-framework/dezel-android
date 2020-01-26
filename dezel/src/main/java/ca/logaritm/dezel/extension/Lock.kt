package ca.logaritm.dezel.extension

/**
 * Indicate whether an object is locked.
 * @function isLocked
 * @since 0.1.0
 */
public fun isLocked(lock: Any?, key: Any?): Boolean {

	if (lock == null) {
		return false
	}

	return lock !== key
}
