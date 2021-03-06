package ca.logaritm.dezel.modules.locale

import android.text.TextUtils
import android.view.View
import ca.logaritm.dezel.core.JavaScriptClass
import ca.logaritm.dezel.core.JavaScriptContext
import ca.logaritm.dezel.core.JavaScriptGetterCallback
import ca.logaritm.dezel.core.JavaScriptProperty
import java.util.*

/**
 * @class JavaScriptLocale
 * @super JavaScriptClass
 * @since 0.1.0
 */
open class JavaScriptLocale(context: JavaScriptContext): JavaScriptClass(context) {

	//--------------------------------------------------------------------------
	// JS Properties
	//--------------------------------------------------------------------------

	/**
	 * @property language
	 * @since 0.1.0
	 * @hidden
	 */
	public val language by lazy {
		JavaScriptProperty()
	}

	/**
	 * @property region
	 * @since 0.1.0
	 * @hidden
	 */
	public val region by lazy {
		JavaScriptProperty()
	}

	/**
	 * @property ltr
	 * @since 0.1.0
	 * @hidden
	 */
	public val ltr by lazy {
		JavaScriptProperty()
	}

	/**
	 * @property ltr
	 * @since 0.1.0
	 * @hidden
	 */
	public val rtl by lazy {
		JavaScriptProperty()
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_language
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_language(callback: JavaScriptGetterCallback) {
		callback.returns(this.language)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_region
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_region(callback: JavaScriptGetterCallback) {
		callback.returns(this.region)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_ltr
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_ltr(callback: JavaScriptGetterCallback) {
		callback.returns(this.ltr)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_rtl
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_rtl(callback: JavaScriptGetterCallback) {
		callback.returns(this.rtl)
	}

	//--------------------------------------------------------------------------
	// Initializer
	//--------------------------------------------------------------------------

	/**
	 * @constructor
	 * @since 0.1.0
	 */
	init {

		val locale = Locale.getDefault()

		this.region.reset(locale.country)
		this.language.reset(locale.language)

		this.ltr.reset(TextUtils.getLayoutDirectionFromLocale(locale) == View.LAYOUT_DIRECTION_LTR)
		this.rtl.reset(TextUtils.getLayoutDirectionFromLocale(locale) == View.LAYOUT_DIRECTION_RTL)
	}
}
