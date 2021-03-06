package ca.logaritm.dezel.core

import ca.logaritm.dezel.core.external.JavaScriptValueExternal
import java.lang.ref.ReferenceQueue

/**
 * @class JavaScriptValueReference
 * @since 0.1.0
 * @hidden
 */
public class JavaScriptValueReference(referent: JavaScriptValue, queue: ReferenceQueue<Any>): FinalizableReference<JavaScriptValue>(referent, queue) {

	companion object {

		/**
		 * @property refs
		 * @since 0.1.0
		 * @hidden
		 */
		private val refs: MutableMap<Int, JavaScriptValueReference> = mutableMapOf()

		/**
		 * @method protect
		 * @since 0.1.0
		 */
		public fun protect(referent: JavaScriptValue) {

			val hash = referent.hashCode()

			var reference = this.refs[hash]
			if (reference != null) {
				reference.protected = true
				return
			}

			reference = JavaScriptValueReference(referent, Finalizer.queue)
			reference.protected = true

			this.refs[hash] = reference
		}

		/**
		 * @method unprotect
		 * @since 0.1.0
		 */
		public fun unprotect(referent: JavaScriptValue) {

			val hash = referent.hashCode()

			val reference = this.refs[hash]
			if (reference != null) {
				reference.protected = false
			}
		}
	}

	/**
	 * @property protected
	 * @since 0.1.0
	 * @hidden
	 */
	private var protected: Boolean = false

	/**
	 * @property context
	 * @since 0.1.0
	 * @hidden
	 */
	private var context: JavaScriptContext? = referent.context

	/**
	 * @property handle
	 * @since 0.1.0
	 * @hidden
	 */
	private var handle: Long = referent.handle

	/**
	 * @property hash
	 * @since 0.1.0
	 * @hidden
	 */
	private var hash: Int = referent.hashCode()

	/**
	 * @method finally
	 * @since 0.1.0
	 */
	override fun finally() {

		if (this.protected) {
			val context = this.context
			if (context != null) {
				handler.post {
					JavaScriptValueExternal.unprotect(context.handle, this.handle)
				}
			}
		}

		this.context = null

		refs.remove(this.hash)
	}
}