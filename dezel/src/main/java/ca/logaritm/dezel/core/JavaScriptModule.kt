package ca.logaritm.dezel.core

/**
 * @class JavaScriptModule
 * @since 0.1.0
 */
open class JavaScriptModule {

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * Configures the module.
	 * @method configure
	 * @since 0.1.0
	 */
	open fun configure(context: JavaScriptContext) {

	}

	/**
	 * Disposes the module.
	 * @method dispose
	 * @since 0.1.0
	 */
	open fun dispose(context: JavaScriptContext) {

	}

	/**
	 * Called before the module's context is reloaded.
	 * @method onBeforeReload
	 * @since 0.1.0
	 */
	open fun onBeforeReload(context: JavaScriptContext) {

	}

	/**
	 * Called after the module's context is reloaded.
	 * @method onReload
	 * @since 0.1.0
	 */
	open fun onReload(context: JavaScriptContext) {

	}
}
