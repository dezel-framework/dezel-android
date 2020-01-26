package ca.logaritm.dezel.extension.type

import java.security.MessageDigest

/**
 * @const md
 * @since 0.1.0
 * @hidden
 */
private val md = MessageDigest.getInstance("MD5")

/**
 * @method String.md5
 * @since 0.1.0
 * @hidden
 */
internal fun String.md5(): String {
    return md.digest(this.toByteArray()).joinToString("") {
        String.format("%02x", it)
    }
}