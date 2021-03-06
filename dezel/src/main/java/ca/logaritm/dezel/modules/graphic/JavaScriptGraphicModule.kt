package ca.logaritm.dezel.modules.graphic

import ca.logaritm.dezel.core.JavaScriptContext
import ca.logaritm.dezel.core.JavaScriptModule

/**
 * @class GraphicModule
 * @since 0.1.0
 * @hidden
 */
open class JavaScriptGraphicModule: JavaScriptModule() {

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * @inherited
	 * @method configure
	 * @since 0.1.0
	 */
	override fun configure(context: JavaScriptContext) {
		context.registerClass("dezel.graphic.Bitmap", JavaScriptBitmap::class.java)
		// context.registerClass("dezel.graphic.Canvas", JavaScriptCanvas::class.java)
	}
}
