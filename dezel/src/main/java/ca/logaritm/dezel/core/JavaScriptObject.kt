package ca.logaritm.dezel.core

import ca.logaritm.dezel.core.external.JavaScriptValueExternal

/**
 * @class JavaScriptObject
 * @super JavaScriptValue
 * @since 0.1.0
 */
open class JavaScriptObject(context: JavaScriptContext): JavaScriptValue(context) {

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * @inherited
	 * @method dispose
	 * @since 0.1.0
	 */
	override fun dispose() {

		if (this.handle != 0L) {
			JavaScriptValueExternal.delAssociatedObject(this.context.handle, this.handle)
			JavaScriptValueExternal.setAssociatedObject(this.context.handle, this.handle, null)
		}

		super.dispose()
	}

	/**
	 * Assigns a custom attribute to the object.
	 * @method attribute
	 * @since 0.1.0
	 */
	open fun attribute(key: Any): Any? {
		return JavaScriptValueExternal.getAttribute(this.context.handle, this.handle, key.hashCode())
	}

	/**
	 * Returns a custom attribute from the object.
	 * @method attribute
	 * @since 0.1.0
	 */
	open fun attribute(key: Any, value: Any) {
		val hash = key.hashCode()
		JavaScriptValueExternal.delAttribute(this.context.handle, this.handle, hash)
		JavaScriptValueExternal.setAttribute(this.context.handle, this.handle, hash, value)
	}

	/**
	 * Assigns the object's finalize handler.
	 * @method finalize
	 * @since 0.1.0
	 */
	open fun finalize(handler: JavaScriptFinalizeHandler) {
		JavaScriptValueExternal.setFinalizeHandler(this.context.handle, this.handle, JavaScriptFinalizeWrapper(this.context, handler), this.context)
	}

	//--------------------------------------------------------------------------
	// Extensions
	//--------------------------------------------------------------------------

	/**
	 * @inherited
	 * @method onResetValue
	 * @since 0.1.0
	 */
	override fun onResetValue() {

		this.finalize { callback ->

			/*
			 * When an object is finalized on the JavaScript side we must
			 * dispose it from the native side because its technically no
			 * longer usable.
			 */

			val self = JavaScriptValueExternal.getAssociatedObject(callback.handle)
			if (self is JavaScriptValue) {
				self.dispose()
			}
		}
	}
}
