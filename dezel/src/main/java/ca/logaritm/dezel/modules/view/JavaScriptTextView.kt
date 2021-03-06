package ca.logaritm.dezel.modules.view

import android.util.Log
import android.util.SizeF
import ca.logaritm.dezel.core.*
import ca.logaritm.dezel.extension.core.activity
import ca.logaritm.dezel.extension.type.ceiled
import ca.logaritm.dezel.extension.type.clamp
import ca.logaritm.dezel.extension.type.max
import ca.logaritm.dezel.view.TextView
import ca.logaritm.dezel.view.TextViewObserver
import ca.logaritm.dezel.view.display.DisplayNode
import ca.logaritm.dezel.view.graphic.Color
import ca.logaritm.dezel.view.graphic.Convert
import ca.logaritm.dezel.view.type.TextAlign
import ca.logaritm.dezel.view.type.TextDecoration
import ca.logaritm.dezel.view.type.TextOverflow
import ca.logaritm.dezel.view.type.TextTransform

/**
 * @class JavaScriptTextView
 * @super JavaScriptView
 * @since 0.1.0
 */
open class JavaScriptTextView(context: JavaScriptContext) : JavaScriptView(context), TextViewObserver {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * @property view
	 * @since 0.1.0
	 * @hidden
	 */
	private val view: TextView
		get() = this.content as TextView

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
	override fun createContentView(): TextView {
		return TextView(this.context.activity, this)
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

		val size = this.view.measure(
			Convert.toPx(bounds),
			Convert.toPx(min),
			Convert.toPx(max)
		)

		return Convert.toDp(size).ceiled()
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
	 * Updates the text view's font size.
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

		if (this.node.isWrappingContentWidth ||
			this.node.isWrappingContentHeight) {
			this.node.invalidateSize()
		}
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
	 * Called when a link is pressed.
	 * @since 0.1.0
	 * @hidden
	 */
	override fun onPressLink(textView: TextView, url: String) {
		this.callMethod("nativeOnPressLink", arrayOf(this.context.createString(url)))
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

			else -> {
				Log.d("Dezel", "Unrecognized value for textDecoration: $value")
			}
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

			else -> {
				Log.d("DEZEL", "Unrecognized value for textTransform: $value")
			}
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

	/**
	 * @method getTextOverflow
	 * @since 0.1.0
	 * @hidden
	 */
	private fun getTextOverflow(value: String): TextOverflow {

		when (value) {

			"clip"     -> return TextOverflow.CLIP
			"ellipsis" -> return TextOverflow.ELLIPSIS

			else -> {
				Log.d("DEZEL", "Unrecognized value for textOverflow: $value")
			}
		}

		return TextOverflow.ELLIPSIS
	}

	//--------------------------------------------------------------------------
	// JS Properties
	//--------------------------------------------------------------------------

	/**
	 * @property fontFamily
	 * @since 0.1.0
	 * @hidden
	 */
	val fontFamily by lazy {

		JavaScriptProperty("default") { value ->

			this.view.fontFamily = value.string

			if (this.node.isWrappingContentWidth ||
				this.node.isWrappingContentHeight) {
				this.node.invalidateSize()
			}
		}
	}

	/**
	 * @property fontWeight
	 * @since 0.1.0
	 * @hidden
	 */
	val fontWeight by lazy {

		JavaScriptProperty("normal") { value ->

			this.view.fontWeight = value.string

			if (this.node.isWrappingContentWidth ||
				this.node.isWrappingContentHeight) {
				this.node.invalidateSize()
			}
		}
	}

	/**
	 * @property fontStyle
	 * @since 0.1.0
	 * @hidden
	 */
	val fontStyle by lazy {

		JavaScriptProperty("normal") { value ->

			this.view.fontStyle = value.string

			if (this.node.isWrappingContentWidth ||
				this.node.isWrappingContentHeight) {
				this.node.invalidateSize()
			}
		}
	}

	/**
	 * @property fontSize
	 * @since 0.1.0
	 * @hidden
	 */
	val fontSize by lazy {
		JavaScriptProperty(17.0) {
			this.invalidateFontSize()
		}
	}

	/**
	 * @property minFontSize
	 * @since 0.1.0
	 * @hidden
	 */
	val minFontSize by lazy {
		JavaScriptProperty(0.0) {
			this.invalidateFontSize()
		}
	}

	/**
	 * @property maxFontSize
	 * @since 0.1.0
	 * @hidden
	 */
	val maxFontSize by lazy {
		JavaScriptProperty(Double.max) {
			this.invalidateFontSize()
		}
	}

	/**
	 * @property text
	 * @since 0.1.0
	 * @hidden
	 */
	val text by lazy {

		JavaScriptProperty("") { value ->

			this.view.text = value.string

			if (this.node.isWrappingContentWidth ||
				this.node.isWrappingContentHeight) {
				this.node.invalidateSize()
			}
		}
	}

	/**
	 * @property textAlign
	 * @since 0.1.0
	 * @hidden
	 */
	val textAlign by lazy {
		JavaScriptProperty("left") { value ->
			this.view.textAlign = this.getTextAlign(value.string)
		}
	}

	/**
	 * @property textBaseline
	 * @since 0.1.0
	 * @hidden
	 */
	val textBaseline by lazy {

		JavaScriptProperty(0.0) { value ->

			this.view.textBaseline = Convert.toPx(value.number)

			if (this.node.isWrappingContentWidth ||
				this.node.isWrappingContentHeight) {
				this.node.invalidateSize()
			}
		}
	}

	/**
	 * @property textKerning
	 * @since 0.1.0
	 * @hidden
	 */
	val textKerning by lazy {

		JavaScriptProperty(0.0) { value ->

			this.view.textKerning = Convert.toPx(value.number)

			if (this.node.isWrappingContentWidth ||
				this.node.isWrappingContentHeight) {
				this.node.invalidateSize()
			}
		}
	}

	/**
	 * @property textLeading
	 * @since 0.1.0
	 * @hidden
	 */
	val textLeading by lazy {

		JavaScriptProperty(0.0) { value ->

			this.view.textLeading = Convert.toPx(value.number)

			if (this.node.isWrappingContentWidth ||
				this.node.isWrappingContentHeight) {
				this.node.invalidateSize()
			}
		}
	}

	/**
	 * @property textDecoration
	 * @since 0.1.0
	 * @hidden
	 */
	val textDecoration by lazy {

		JavaScriptProperty("none") { value ->

			this.view.textDecoration = this.getTextDecoration(value.string)

			if (this.node.isWrappingContentWidth ||
				this.node.isWrappingContentHeight) {
				this.node.invalidateSize()
			}
		}
	}

	/**
	 * @property textTransform
	 * @since 0.1.0
	 * @hidden
	 */
	val textTransform by lazy {

		JavaScriptProperty("none") { value ->

			this.view.textTransform = this.getTextTransform(value.string)

			if (this.node.isWrappingContentWidth ||
				this.node.isWrappingContentHeight) {
				this.node.invalidateSize()
			}
		}
	}

	/**
	 * @property textOverflow
	 * @since 0.1.0
	 * @hidden
	 */
	val textOverflow by lazy {

		JavaScriptProperty("ellipsis") { value ->

			this.view.textOverflow = this.getTextOverflow(value.string)

			if (this.node.isWrappingContentWidth ||
				this.node.isWrappingContentHeight) {
				this.node.invalidateSize()
			}
		}
	}

	/**
	 * @property textColor
	 * @since 0.1.0
	 * @hidden
	 */
	val textColor by lazy {
		JavaScriptProperty("#000") { value ->
			this.view.textColor = Color.parse(value.string)
		}
	}

	/**
	 * @property textShadowBlur
	 * @since 0.1.0
	 * @hidden
	 */
	val textShadowBlur by lazy {
		JavaScriptProperty(0.0) { value ->
			this.view.textShadowBlur = Convert.toPx(value.number)
		}
	}

	/**
	 * @property textShadowColor
	 * @since 0.1.0
	 * @hidden
	 */
	val textShadowColor by lazy {
		JavaScriptProperty("#000") { value ->
			this.view.textShadowColor = Color.parse(value.string)
		}
	}

	/**
	 * @property textShadowOffsetTop
	 * @since 0.1.0
	 * @hidden
	 */
	val textShadowOffsetTop by lazy {
		JavaScriptProperty(0.0) { value ->
			this.view.textShadowOffsetTop = Convert.toPx(value.number)
		}
	}

	/**
	 * @property textShadowOffsetLeft
	 * @since 0.1.0
	 * @hidden
	 */
	val textShadowOffsetLeft by lazy {
		JavaScriptProperty(0.0) { value ->
			this.view.textShadowOffsetLeft = Convert.toPx(value.number)
		}
	}

	/**
	 * @property linkColor
	 * @since 0.1.0
	 * @hidden
	 */
	val linkColor by lazy {
		JavaScriptProperty("blue") { value ->
			this.view.linkColor = Color.parse(value.string)
		}
	}

	/**
	 * @property maxLines
	 * @since 0.1.0
	 * @hidden
	 */
	val maxLines by lazy {

		JavaScriptProperty(0.0) { value ->

			this.view.maxLines = value.number.toInt()

			if (this.node.isWrappingContentWidth ||
				this.node.isWrappingContentHeight) {
				this.node.invalidateSize()
			}
		}
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
		this.fontFamily.reset(callback.value, lock = this)
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
		this.fontWeight.reset(callback.value, lock = this)
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
		this.fontStyle.reset(callback.value, lock = this)
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
		this.fontSize.reset(callback.value, lock = this)
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
		this.minFontSize.reset(callback.value, lock = this)
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
		this.maxFontSize.reset(callback.value, lock = this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_text
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_text(callback: JavaScriptGetterCallback) {
		callback.returns(this.text)
	}

	/**
	 * @method jsSet_text
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_text(callback: JavaScriptSetterCallback) {
		this.text.reset(callback.value, lock = this)
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
		this.textAlign.reset(callback.value, lock = this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_textBaseline
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_textBaseline(callback: JavaScriptGetterCallback) {
		callback.returns(this.textBaseline)
	}

	/**
	 * @method jsSet_textBaseline
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_textBaseline(callback: JavaScriptSetterCallback) {
		this.textBaseline.reset(callback.value, lock = this)
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
		this.textKerning.reset(callback.value, lock = this)
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
		this.textLeading.reset(callback.value, lock = this)
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
		this.textDecoration.reset(callback.value, lock = this)
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
		this.textTransform.reset(callback.value, lock = this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_textOverflow
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_textOverflow(callback: JavaScriptGetterCallback) {
		callback.returns(this.textOverflow)
	}

	/**
	 * @method jsSet_textOverflow
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_textOverflow(callback: JavaScriptSetterCallback) {
		this.textOverflow.reset(callback.value, lock = this)
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
		this.textColor.reset(callback.value, lock = this)
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
		this.textShadowBlur.reset(callback.value, lock = this)
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
		this.textShadowColor.reset(callback.value, lock = this)
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
		this.textShadowOffsetTop.reset(callback.value, lock = this)
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
		this.textShadowOffsetLeft.reset(callback.value, lock = this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_linkColor
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_linkColor(callback: JavaScriptGetterCallback) {
		callback.returns(this.linkColor)
	}

	/**
	 * @method jsSet_linkColor
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_linkColor(callback: JavaScriptSetterCallback) {
		this.linkColor.reset(callback.value, lock = this)
	}

	//--------------------------------------------------------------------------

	/**
	 * @method jsGet_maxLines
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsGet_maxLines(callback: JavaScriptGetterCallback) {
		callback.returns(this.maxLines)
	}

	/**
	 * @method jsSet_maxLines
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	open fun jsSet_maxLines(callback: JavaScriptSetterCallback) {
		this.maxLines.reset(callback.value, lock = this)
	}
}
