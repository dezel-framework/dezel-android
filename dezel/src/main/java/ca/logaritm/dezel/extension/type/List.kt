package ca.logaritm.dezel.extension.type

/**
 * @property MutableList.last
 * @since 0.1.0
 * @hidden
 */
internal val <E>MutableList<E>.last: E?
	get() = if (this.size > 0) this.last() else null

/**
 * @method MutableList.pop
 * @since 0.1.0
 * @hidden
 */
internal fun <E> MutableList<E>.pop(): E? {

	if (this.size == 0) {
		return null
	}

	val last = this.last
	if (last == null) {
		return null
	}

	this.remove(last)

	return last
}

/**
 * @method MutableList.append
 * @since 0.1.0
 * @hidden
 */
internal fun <E>MutableList<E>.append(value: E) {
	this.add(value)
}

/**
 * @method MutableList.insert
 * @since 0.1.0
 * @hidden
 */
internal fun <E>MutableList<E>.insert(value: E, index: Int) {
	this.add(index, value)
}