package ca.logaritm.dezel.modules.form

import android.util.Log
import android.util.SizeF
import ca.logaritm.dezel.core.*
import ca.logaritm.dezel.extension.core.activity
import ca.logaritm.dezel.extension.type.clamp
import ca.logaritm.dezel.extension.type.max
import ca.logaritm.dezel.modules.view.JavaScriptView
import ca.logaritm.dezel.view.TextArea
import ca.logaritm.dezel.view.TextAreaObserver
import ca.logaritm.dezel.view.display.DisplayNode
import ca.logaritm.dezel.view.graphic.Color
import ca.logaritm.dezel.view.graphic.Convert
import ca.logaritm.dezel.view.type.TextAlign
import ca.logaritm.dezel.view.type.TextDecoration
import ca.logaritm.dezel.view.type.TextTransform

/**
 * @class JavaScriptTextArea
 * @super JavaScriptView
 * @since 0.1.0
 */
open class JavaScriptTextArea(context: JavaScriptContext) : JavaScriptView(context), TextAreaObserver {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * @property view
	 * @since 0.1.0
	 * @hidden
	 */
	private val view: TextArea
		get() = this.content as TextArea

	/**
	 * @property invalidFontSize
	 * @since 0.1.0
	 * @hidden
	 */
	private var invalidFontSize: Boolean = false

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * @inherited
	 * @method createContentView
	 * @since 0.1.0
	 */
	override fun createContentView(): TextArea {
		return TextArea(this.context.activity, this)
	}

	/**
	 * @inherited
	 * @method update
	 * @since 0.1.0
	 */
	override fun update() {

		super.update()

		if (this.invalidFontSize) {
			this.invalidFontSize = false
			this.updateFontSize()
		}
	}

	/**
	 * Updates the text area's font size.
	 * @method updateFontSize
	 * @since 0.1.0
	 */
	open fun updateFontSize() {

		val value: Float

		when (this.fontSize.unit) {
			JavaScriptPropertyUnit.PX -> value = Convert.toPx(this.fontSize.number)
			JavaScriptPropertyUnit.VW -> value = Convert.toPx(this.fontSize.number / 100 * this.node.display.viewportWidth)
			JavaScriptPropertyUnit.VH -> value = Convert.toPx(this.fontSize.number / 100 * this.node.display.viewportHeight)
			else                      -> value = Convert.toPx(this.fontSize.number)
		}

		this.view.fontSize = value.clamp(
			Convert.toPx(this.minFontSize.number),
			Convert.toPx(this.maxFontSize.number)
		)
	}

	/**
	 * @inherited
	 * @method measure
	 * @since 0.1.0
	 */
	override fun measure(bounds: SizeF, min: SizeF, max: SizeF): SizeF {

		if (this.invalidFontSize) {
			this.invalidFontSize = false
			this.updateFontSize()
		}

		return SizeF(130f, 80f)
	}

	/**
	 * @inherited
	 * @method onResolvePadding
	 * @since 0.1.0
	 */
	override fun onResolvePadding(node: DisplayNode) {
		super.onResolvePadding(node)
		this.view.paddingTop = Convert.toPx(this.resolvedPaddingTop.toFloat())
		this.view.paddingLeft = Convert.toPx(this.resolvedPaddingLeft.toFloat())
		this.view.paddingRight = Convert.toPx(this.resolvedPaddingRight.toFloat())
		this.view.paddingBottom = Convert.toPx(this.resolvedPaddingBottom.toFloat())
	}

	/**
	 * Called when the text area's value changes.
	 * @method onChange
	 * @since 0.1.0
	 */
	override fun onChange(textArea: TextArea, value: String) {
		this.value.reset(value)
		this.callMethod("nativeOnChange", arrayOf(this.context.createString(value)), null)
	}

	/**
	 * Called when the text area receives focus.
	 * @method onFocus
	 * @since 0.1.0
	 */
	override fun onFocus(textArea: TextArea) {
		this.node.appendState("focus")
		this.callMethod("nativeOnFocus")
	}

	/**
	 * Called when the text area loses focus.
	 * @method onBlur
	 * @since 0.1.0
	 */
	override fun onBlur(textArea: TextArea) {
		this.node.removeState("focus")
		this.callMethod("nativeOnBlur")
	}

	//--------------------------------------------------------------------------
	// Private API
	//--------------------------------------------------------------------------

	/**
	 * @method invalidateFontSize
	 * @since 0.1.0
	 * @hidden
	 */
	private fun invalidateFontSize() {
		if (this.invalidFontSize == false) {
			this.invalidFontSize = true
			this.scheduleUpdate()
		}
	}

	/**
	 * @method getTextDecoration
	 * @since 0.1.0
	 * @hidden
	 */
	private fun getTextDecoration(value: String): TextDecoration {

		when (value) {
			"none"      -> return TextDecoration.NONE
			"underline" -> return TextDecoration.UNDERLINE
			else        -> Log.d("DEZEL", "Unrecognized value for textDecoration: $value")
		}

		return TextDecoration.NONE
	}

	/**
	 * @method getTextTransform
	 * @since 0.1.0
	 * @hidden
	 */
	private fun getTextTransform(value: String): TextTransform {

		when (value) {
			"none"       -> return TextTransform.NONE
			"uppercase"  -> return TextTransform.UPPERCASE
			"lowercase"  -> return TextTransform.LOWERCASE
			"capitalize" -> return TextTransform.CAPITALIZE
			else         -> Log.d("DEZEL", "Unrecognized value for textTransform: $value")
		}

		return TextTransform.NONE
	}

	/**
	 * @method getTextAlign
	 * @since 0.1.0
	 * @hidden
	 */
	private fun getTextAlign(value: String): TextAlign {

		when (value) {

			"top left"   -> return TextAlign.TOP_LEFT
			"top right"  -> return TextAlign.TOP_RIGHT
			"top center" -> return TextAlign.TOP_CENTER

			"left"   -> return TextAlign.MIDDLE_LEFT
			"right"  -> return TextAlign.MIDDLE_RIGHT
			"center" -> return TextAlign.MIDDLE_CENTER

			"bottom left"   -> return TextAlign.BOTTOM_LEFT
			"bottom right"  -> return TextAlign.BOTTOM_RIGHT
			"bottom center" -> return TextAlign.BOTTOM_CENTER

			else -> {
				Log.e("Dezel", "Unrecognized value for textAlign: $value")
			}
		}

		return TextAlign.MIDDLE_LEFT
	}

	//--------------------------------------------------------------------------
	// JS Properties
	//--------------------------------------------------------------------------

	/**
	 * @property value
	 * @since 0.1.0
	 * @hidden
	 */
	public val value by lazy {
		JavaScriptProperty("") { value ->
			this.view.value = value.string
		}
	}

	/**
	 * @property placeholder
	 * @since 0.1.0
	 */
	public val placeholder by lazy {
		JavaScriptProperty("") { value ->
			this.view.placeholder = value.string
		}
	}

	/**
	 * @property placeholderColor
	 * @since 0.1.0
	 * @hidden
	 */
	public val placeholderColor by lazy {
		JavaScriptProperty("gray") { value ->
			this.view.placeholderColor = Color.parse(value.string)
		}
	}

	/**
	 * @property autocorrect
	 * @since 0.1.0
	 * @hidden
	 */
	public val autocorrect by lazy {
		JavaScriptProperty(true) { value ->
			this.view.autocorrect = value.boolean
		}
	}

	/**
	 * @property autocapitalize
	 * @since 0.1.0
	 * @hidden
	 */
	public val autocapitalize by lazy {
		JavaScriptProperty(true) { value ->
			this.view.autocapitalize = value.boolean
		}
	}

	/**
	 * @property fontFamily
	 * @since 0.1.0
	 * @hidden
	 */
	public val fontFamily by lazy {
		JavaScriptProperty("default") { value ->
			this.view.fontFamily = value.string
		}
	}

	/**
	 * @property fontWeight
	 * @since 0.1.0
	 * @hidden
	 */
	public val fontWeight by lazy {
		JavaScriptProperty("normal") { value ->
			this.view.fontWeight = value.string
		}
	}

	/**
	 * @property fontStyle
	 * @since 0.1.0
	 * @hidden
	 */
	public val fontStyle by lazy {
		JavaScriptProperty("normal") { value ->
			this.view.fontStyle = value.string
		}
	}

	/**
	 * @property fontSize
	 * @since 0.1.0
	 * @hidden
	 */
	public val fontSize by lazy {
		JavaScriptProperty(17.0) {
			this.invalidateFontSize()
		}
	}

	/**
	 * @property minFontSize
	 * @since 0.1.0
	 * @hidden
	 */
	public val minFontSize by lazy {
		JavaScriptProperty(0.0) {
			this.invalidateFontSize()
		}
	}

	/**
	 * @property maxFontSize
	 * @since 0.1.0
	 * @hidden
	 */
	public val maxFontSize by lazy {
		JavaScriptProperty(Double.max) {
			this.invalidateFontSize()
		}
	}

	/**
	 * @property textAlign
	 * @since 0.1.0
	 * @hidden
	 */
	public val textAlign by lazy {
		JavaScriptProperty("start") { value ->
			this.view.textAlign = this.getTextAlign(value.string)
		}
	}

	/**
	 * @property textKerning
	 * @since 0.1.0
	 * @hidden
	 */
	public val textKerning by lazy {
		JavaScriptProperty(0.0) { value ->
			this.view.textKerning = value.number.toFloat()
		}
	}

	/**
	 * @property textLeading
	 * @since 0.1.0
	 * @hidden
	 */
	public val textLeading by lazy {
		JavaScriptProperty(0.0) { value ->
			this.view.textLeading = value.number.toFloat()
		}
	}

	/**
	 * @property textDecoration
	 * @since 0.1.0
	 * @hidden
	 */
	public val textDecoration by lazy {
		JavaScriptProperty("none") { value ->
			this.view.textDecoration = this.getTextDecoration(value.string)
		}
	}

	/**
	 * @property textTransform
	 * @since 0.1.0
	 * @hidden
	 */
	public val textTransform by lazy {
		JavaScriptProperty("none") { value ->
			this.view.textTransform = this.getTextTransform(value.string)
		}
	}

	/**
	 * @property textColor
	 * @since 0.1.0
	 * @hidden
	 */
	public val textColor by lazy {
		JavaScriptProperty("#000") { value ->
			this.view.setTextColor(Color.parse(value.string))
		}
	}

	/**
	 * @property textShadowBlur
	 * @since 0.1.0
	 * @hidden
	 */
	public val textShadowBlur by lazy {
		JavaScriptProperty(0.0) { value ->
			this.view.textShadowBlur = value.number.toFloat()
		}
	}

	/**
	 * @property textShadowColor
	 * @since 0.1.0
	 * @hidden
	 */
	public val textShadowColor by lazy {
		JavaScriptProperty("#000") { value ->
			this.view.textShadowColor = Color.parse(value.string)
		}
	}

	/**
	 * @property textShadowOffsetTop
	 * @since 0.1.0
	 * @hidden
	 */
	public val textShadowOffsetTop by lazy {
		JavaScriptProperty(0.0) { value ->
			this.view.textShadowOffsetTop = value.number.toFloat()
		}
	}

	/**
	 * @property textShadowOffsetLeft
	 * @since 0.1.0
	 * @hidden
	 */
	public val textShadowOffsetLeft by lazy {
		JavaScriptProperty(0.0) { value ->
			this.view.textShadowOffsetLeft = value.number.toFloat()
		}
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_value
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_value(callback: JavaScriptGetterCallback) {
		callback.returns(this.value)
	}

	/**
	 * @method jsSet_value
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_value(callback: JavaScriptSetterCallback) {
		this.value.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_placeholder
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_placeholder(callback: JavaScriptGetterCallback) {
		callback.returns(this.placeholder)
	}

	/**
	 * @method jsSet_placeholder
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_placeholder(callback: JavaScriptSetterCallback) {
		this.placeholder.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_placeholderColor
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_placeholderColor(callback: JavaScriptGetterCallback) {
		callback.returns(this.placeholderColor)
	}

	/**
	 * @method jsSet_placeholderColor
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_placeholderColor(callback: JavaScriptSetterCallback) {
		this.placeholderColor.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_autocorrect
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_autocorrect(callback: JavaScriptGetterCallback) {
		callback.returns(this.autocorrect)
	}

	/**
	 * @method jsSet_autocorrect
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_autocorrect(callback: JavaScriptSetterCallback) {
		this.autocorrect.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_autocapitalize
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_autocapitalize(callback: JavaScriptGetterCallback) {
		callback.returns(this.autocapitalize)
	}

	/**
	 * @method jsSet_autocapitalize
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_autocapitalize(callback: JavaScriptSetterCallback) {
		this.autocapitalize.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_fontFamily
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_fontFamily(callback: JavaScriptGetterCallback) {
		callback.returns(this.fontFamily)
	}

	/**
	 * @method jsSet_fontFamily
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_fontFamily(callback: JavaScriptSetterCallback) {
		this.fontFamily.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_fontWeight
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_fontWeight(callback: JavaScriptGetterCallback) {
		callback.returns(this.fontWeight)
	}

	/**
	 * @method jsSet_fontWeight
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_fontWeight(callback: JavaScriptSetterCallback) {
		this.fontWeight.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_fontStyle
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_fontStyle(callback: JavaScriptGetterCallback) {
		callback.returns(this.fontStyle)
	}

	/**
	 * @method jsSet_fontStyle
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_fontStyle(callback: JavaScriptSetterCallback) {
		this.fontStyle.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_fontSize
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_fontSize(callback: JavaScriptGetterCallback) {
		callback.returns(this.fontSize)
	}

	/**
	 * @method jsSet_fontSize
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_fontSize(callback: JavaScriptSetterCallback) {
		this.fontSize.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_minFontSize
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_minFontSize(callback: JavaScriptGetterCallback) {
		callback.returns(this.minFontSize)
	}

	/**
	 * @method jsSet_minFontSize
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_minFontSize(callback: JavaScriptSetterCallback) {
		this.minFontSize.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_maxFontSize
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_maxFontSize(callback: JavaScriptGetterCallback) {
		callback.returns(this.maxFontSize)
	}

	/**
	 * @method jsSet_maxFontSize
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_maxFontSize(callback: JavaScriptSetterCallback) {
		this.maxFontSize.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_textAlign
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_textAlign(callback: JavaScriptGetterCallback) {
		callback.returns(this.textAlign)
	}

	/**
	 * @method jsSet_textAlign
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_textAlign(callback: JavaScriptSetterCallback) {
		this.textAlign.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_textKerning
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_textKerning(callback: JavaScriptGetterCallback) {
		callback.returns(this.textKerning)
	}

	/**
	 * @method jsSet_textKerning
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_textKerning(callback: JavaScriptSetterCallback) {
		this.textKerning.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_textLeading
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_textLeading(callback: JavaScriptGetterCallback) {
		callback.returns(this.textLeading)
	}

	/**
	 * @method jsSet_textLeading
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_textLeading(callback: JavaScriptSetterCallback) {
		this.textLeading.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_textDecoration
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_textDecoration(callback: JavaScriptGetterCallback) {
		callback.returns(this.textDecoration)
	}

	/**
	 * @method jsSet_textDecoration
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_textDecoration(callback: JavaScriptSetterCallback) {
		this.textDecoration.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_textTransform
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_textTransform(callback: JavaScriptGetterCallback) {
		callback.returns(this.textTransform)
	}

	/**
	 * @method jsSet_textTransform
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_textTransform(callback: JavaScriptSetterCallback) {
		this.textTransform.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_textColor
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_textColor(callback: JavaScriptGetterCallback) {
		callback.returns(this.textColor)
	}

	/**
	 * @method jsSet_textColor
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_textColor(callback: JavaScriptSetterCallback) {
		this.textColor.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_textShadowBlur
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_textShadowBlur(callback: JavaScriptGetterCallback) {
		callback.returns(this.textShadowBlur)
	}

	/**
	 * @method jsSet_textShadowBlur
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_textShadowBlur(callback: JavaScriptSetterCallback) {
		this.textShadowBlur.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_textShadowColor
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_textShadowColor(callback: JavaScriptGetterCallback) {
		callback.returns(this.textShadowColor)
	}

	/**
	 * @method jsSet_textShadowColor
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_textShadowColor(callback: JavaScriptSetterCallback) {
		this.textShadowColor.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_textShadowOffsetTop
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_textShadowOffsetTop(callback: JavaScriptGetterCallback) {
		callback.returns(this.textShadowOffsetTop)
	}

	/**
	 * @method jsSet_textShadowOffsetTop
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_textShadowOffsetTop(callback: JavaScriptSetterCallback) {
		this.fontFamily.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_textShadowOffsetLeft
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_textShadowOffsetLeft(callback: JavaScriptGetterCallback) {
		callback.returns(this.textShadowOffsetLeft)
	}

	/**
	 * @method jsSet_textShadowOffsetLeft
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_textShadowOffsetLeft(callback: JavaScriptSetterCallback) {
		this.textShadowOffsetLeft.reset(callback.value, this)
	}

	//--------------------------------------------------------------------------
	// JS Functions
	//--------------------------------------------------------------------------

	/**
	 * @method jsFunction_focus
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsFunction_focus(callback: JavaScriptFunctionCallback) {
		this.view.focus()
	}

	/**
	 * @method jsFunction_blur
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsFunction_blur(callback: JavaScriptFunctionCallback) {
		this.view.blur()
	}
}
