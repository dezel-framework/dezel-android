package ca.logaritm.dezel.modules.view

import ca.logaritm.dezel.core.JavaScriptContext
import ca.logaritm.dezel.core.JavaScriptFunctionCallback
import ca.logaritm.dezel.extension.view.removeFromParent
import ca.logaritm.dezel.view.geom.Point3D
import ca.logaritm.dezel.view.geom.Transform3D

/**
 * @class JavaScriptWindow
 * @super JavaScriptView
 * @since 0.1.0
 */
open class JavaScriptWindow(context: JavaScriptContext): JavaScriptView(context) {

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * Removes the window from the controller.
	 * @method remove
	 * @since 0.1.0
	 */
	public fun remove() {
		this.wrapper.removeFromParent()
	}

	/**
	 * Finds a view at specified coordinates.
	 * @method findViewAt
	 * @since 0.1.0
	 */
	open fun findViewAt(x: Double, y: Double, visible: Boolean = true, touchable: Boolean = true): JavaScriptView? {
		return this.findViewAt(Point3D(x, y, 0.0), Transform3D(), visible, touchable)
	}

	//--------------------------------------------------------------------------
	// JS Functions
	//--------------------------------------------------------------------------

	/**
	 * @method jsFunction_findViewAt
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsFunction_findViewAt(callback: JavaScriptFunctionCallback) {

		if (callback.arguments < 2) {
			throw Exception("Method JavaScriptWindow.findViewAt() requires 2 arguments.")
		}

		val x = callback.argument(0).number
		val y = callback.argument(1).number

		val view = this.findViewAt(x, y)
		if (view == null) {
			return
		}

		callback.returns(view)
	}
}