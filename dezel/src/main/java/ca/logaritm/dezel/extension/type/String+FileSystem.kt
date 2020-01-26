package ca.logaritm.dezel.extension.type

/**
 * @property String.fileExt
 * @since 0.1.0
 * @hidden
 */
internal val String.fileExt: String; get() {

    val e = this.lastIndexOf(".")
    if (e == -1) {
        return ""
    }

    return this.substring(e)
}

/**
 * @property String.fileName
 * @since 0.1.0
 * @hidden
 */
internal val String.fileName: String; get() {

    val s = this.lastIndexOf("/")
    val e = this.lastIndexOf(".")
    if (s == -1 ||
        e == -1) {
        return ""
    }

    return this.substring(s + 1, e)
}

/**
 * @property String.baseName
 * @since 0.1.0
 * @hidden
 */
internal val String.baseName: String; get() {

    val e = this.lastIndexOf("/")
    if (e == -1) {
        return this
    }

    return this.substring(0, e)
}