package ca.logaritm.dezel.modules.graphic

import android.graphics.Bitmap
import ca.logaritm.dezel.core.*
import ca.logaritm.dezel.extension.core.activity

/**
 * @class JavaScriptBitmap
 * @since 0.1.0
 */
open class JavaScriptBitmap(context: JavaScriptContext) : JavaScriptClass(context) {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The bitmap's image.
	 * @property bitmap
	 * @since 0.1.0
	 */
	open var bitmap: Bitmap? = null

	/**
	 * @property loader
	 * @since 0.1.0
	 * @hidden
	 */
	private var loader: ImageLoader = ImageLoader(context.activity)

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * Loads the specified bitmap.
	 * @method load
	 * @since 0.1.0
	 */
	open fun load(source: String) {

		this.bitmap = null
		this.loading.reset(false)
		this.loaded.reset(false)

		this.protect()

		this.loader.load(source) { image ->
			this.set(image)
			this.unprotect()
		}
	}

	//--------------------------------------------------------------------------
	// Private API
	//--------------------------------------------------------------------------

	/**
	 * @method set
	 * @since 0.1.0
	 * @hidden
	 */
	private fun set(image: Bitmap?) {

		if (image != null) {
			this.bitmap = image
			this.loading.reset(false)
			this.loaded.reset(true)
			this.callMethod("nativeOnLoad")
			return
		}

		this.bitmap = null
		this.loading.reset(false)
		this.loaded.reset(false)
		this.callMethod("nativeOnError")
	}

	//--------------------------------------------------------------------------
	// JS Properties
	//--------------------------------------------------------------------------

	/**
	 * @property source
	 * @since 0.1.0
	 * @hidden
	 */
	public val source by lazy {
		JavaScriptProperty() { value ->
			this.load(value.string)
		}
	}

	/**
	 * @property loaded
	 * @since 0.1.0
	 * @hidden
	 */
	public val loaded by lazy {
		JavaScriptProperty(false)
	}

	/**
	 * @property loading
	 * @since 0.1.0
	 * @hidden
	 */
	public val loading by lazy {
		JavaScriptProperty(false)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_width
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_width(callback: JavaScriptGetterCallback) {
		callback.returns(this.bitmap?.width?.toDouble() ?: 0.0)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_height
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_height(callback: JavaScriptGetterCallback) {
		callback.returns(this.bitmap?.height?.toDouble() ?: 0.0)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_source
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_source(callback: JavaScriptGetterCallback) {
		callback.returns(this.source)
	}

	/**
	 * @method jsSet_source
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_source(callback: JavaScriptSetterCallback) {
		this.source.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_loaded
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_loaded(callback: JavaScriptGetterCallback) {
		callback.returns(this.loaded)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_loading
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_loading(callback: JavaScriptGetterCallback) {
		callback.returns(this.loading)
	}
}