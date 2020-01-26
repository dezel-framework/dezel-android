package ca.logaritm.dezel.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Paint
import android.view.Gravity
import android.view.KeyEvent
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import ca.logaritm.dezel.application.ApplicationActivity
import ca.logaritm.dezel.extension.Delegates
import ca.logaritm.dezel.view.animation.Animatable
import ca.logaritm.dezel.view.graphic.Color
import ca.logaritm.dezel.view.text.font.FontManager
import ca.logaritm.dezel.view.type.TextAlign
import ca.logaritm.dezel.view.type.TextDecoration
import ca.logaritm.dezel.view.type.TextTransform
import android.text.InputType as AndroidInputType

/**
 * @class TextArea
 * @super EditText
 * @since 0.1.0
 */
open class TextArea(context: Context, observer: TextAreaObserver) : EditText(context), Animatable {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The text area's value.
	 * @property value
	 * @since 0.1.0
	 */
	open var value: String by Delegates.OnSet("") { value ->
		this.setString(value)
	}

	/**
	 * The text area's placeholder.
	 * @property placeholder
	 * @since 0.1.0
	 */
	open var placeholder: String by Delegates.OnSet("") { value ->
		this.setPlaceholderText(value)
	}

	/**
	 * The text area's placeholder color.
	 * @property placeholderColor
	 * @since 0.1.0
	 */
	open var placeholderColor: Int by Delegates.OnSet(Color.parse("gray")) { value ->
		this.setPlaceholderTextColor(value)
	}

	/**
	 * The text area's font family.
	 * @property fontFamily
	 * @since 0.1.0
	 */
	open var fontFamily: String by Delegates.OnSet("") {
		this.invalidateFont()
	}

	/**
	 * The text area's font weight.
	 * @property fontWeight
	 * @since 0.1.0
	 */
	open var fontWeight: String by Delegates.OnSet("normal") {
		this.invalidateFont()
	}

	/**
	 * The text area's font style.
	 * @property fontStyle
	 * @since 0.1.0
	 */
	open var fontStyle: String by Delegates.OnSet("normal") {
		this.invalidateFont()
	}

	/**
	 * The text area's font size.
	 * @property fontSize
	 * @since 0.1.0
	 */
	open var fontSize: Float by Delegates.OnSet(0.0f) { value ->
		this.textSize = value
	}

	/**
	 * The text area's text align.
	 * @property textAlign
	 * @since 0.1.0
	 */
	open var textAlign: TextAlign by Delegates.OnSet(TextAlign.TOP_LEFT) {
		this.updateGravity()
	}

	/**
	 * The text area's text kerning.
	 * @property textKerning
	 * @since 0.1.0
	 */
	open var textKerning: Float by Delegates.OnSet(0.0f) {
		this.invalidate()
	}

	/**
	 * The text area's text leading.
	 * @property textLeading
	 * @since 0.1.0
	 */
	open var textLeading: Float by Delegates.OnSet(0.0f) { value ->
		this.setLineSpacing(value, 0f)
	}

	/**
	 * The text area's text decoration.
	 * @property textDecoration
	 * @since 0.1.0
	 */
	open var textDecoration: TextDecoration by Delegates.OnSet(TextDecoration.NONE) { value ->

		this.paintFlags = this.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
		this.paintFlags = this.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()

		when (value) {
			TextDecoration.UNDERLINE   -> this.paintFlags = this.paintFlags or Paint.UNDERLINE_TEXT_FLAG
			TextDecoration.LINETHROUGH -> this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
			TextDecoration.NONE        -> {}
		}

		this.invalidate()
	}

	/**
	 * The text area's text transform.
	 * @property textTransform
	 * @since 0.1.0
	 */
	open var textTransform: TextTransform by Delegates.OnSet(TextTransform.NONE) {
		this.invalidate()
	}

	/**
	 * The text area's text shadow blur.
	 * @property textShadowBlur
	 * @since 0.1.0
	 */
	open var textShadowBlur: Float by Delegates.OnSet(0.0f) {
		this.invalidate()
	}

	/**
	 * The text area's text shadow color.
	 * @property textShadowColor
	 * @since 0.1.0
	 */
	open var textShadowColor: Int by Delegates.OnSet(Color.BLACK) {
		this.invalidate()
	}

	/**
	 * The text area's text top shadow offset.
	 * @property textShadowOffsetTop
	 * @since 0.1.0
	 */
	open var textShadowOffsetTop: Float by Delegates.OnSet(0.0f) {
		this.invalidate()
	}

	/**
	 * The text area's text left shadow offset.
	 * @property textShadowOffsetLeft
	 * @since 0.1.0
	 */
	open var textShadowOffsetLeft: Float by Delegates.OnSet(0.0f) {
		this.invalidate()
	}

	/**
	 * The text area's top padding.
	 * @property paddingTop
	 * @since 0.1.0
	 */
	open var paddingTop: Float  by Delegates.OnSet(0f) {
		this.invalidate()
	}

	/**
	 * The text area's left padding.
	 * @property paddingLeft
	 * @since 0.1.0
	 */
	open var paddingLeft: Float  by Delegates.OnSet(0f) {
		this.invalidate()
	}

	/**
	 * The text area's right padding.
	 * @property paddingRight
	 * @since 0.1.0
	 */
	open var paddingRight: Float by Delegates.OnSet(0f) {
		this.invalidate()
	}

	/**
	 * The text area's bottom padding.
	 * @property paddingBottom
	 * @since 0.1.0
	 */
	open var paddingBottom: Float by Delegates.OnSet(0f) {
		this.invalidate()
	}

	/**
	 * The text area's autocorrect status.
	 * @property autocorrect
	 * @since 0.1.0
	 */
	open var autocorrect: Boolean by Delegates.OnSet(true) {
		this.updateType()
	}

	/**
	 * The text area's autocapitalize status.
	 * @property autocapitalize
	 * @since 0.1.0
	 */
	open var autocapitalize: Boolean by Delegates.OnSet(true) {
		this.updateType()
	}

	/**
	 * @property observer
	 * @since 0.1.0
	 * @hidden
	 */
	private lateinit var observer: TextAreaObserver

	/**
	 * @property invalidFont
	 * @since 0.1.0
	 * @hidden
	 */
	private var invalidFont: Boolean = true

	/**
	 * @property focusChangeListener
	 * @since 0.1.0
	 * @hidden
	 */
	private val focusChangeListener: OnFocusChangeListener = OnFocusChangeListener { v, hasFocus ->

		if (hasFocus) {

			val application = this.context
			if (application is ApplicationActivity) {
				application.presentSoftKeyboard(v)
			}

			this.observer.onFocus(this)

		} else {

			val application = this.context
			if (application is ApplicationActivity) {
				application.dismissSoftKeyboard(v)
			}

			this.observer.onBlur(this)
		}
	}

	/**
	 * @property initialized
	 * @since 0.1.0
	 * @hidden
	 */
	private var initialized: Boolean = false

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * Initializes the text area.
	 * @constructor
	 * @since 0.1.0
	 */
	init {

		this.inputType = AndroidInputType.TYPE_CLASS_TEXT or AndroidInputType.TYPE_TEXT_FLAG_MULTI_LINE

		this.background = null
		this.isFocusable = true
		this.isFocusableInTouchMode = true
		this.onFocusChangeListener = this.focusChangeListener

		this.textSize = 17f

		this.setTextColor(Color.BLACK)
		this.setSingleLine(false)
		this.setPlaceholderText("")
		this.setPlaceholderTextColor(Color.parse("gray"))
		this.setPadding(0, 0, 0, 0)

		this.imeOptions = EditorInfo.IME_FLAG_NO_ENTER_ACTION

		this.updateGravity()

		this.observer = observer

		this.initialized = true
	}

	/**
	 * Moves the focus to the text area.
	 * @method focus
	 * @since 0.1.0
	 */
	open fun focus() {

		val focused = this.hasFocus()
		if (focused) {
			return
		}

		this.requestFocus()
	}

	/**
	 * Removes te focus from the text area.
	 * @method blur
	 * @since 0.1.0
	 */
	open fun blur() {

		val focused = this.hasFocus()
		if (focused == false) {
			return
		}

		this.clearFocus()
	}

	/**
	 * @inherited
	 * @method onKeyPreIme
	 * @since 0.1.0
	 */
	override fun onKeyPreIme(keyCode: Int, event: KeyEvent): Boolean {

		if (event.keyCode == KeyEvent.KEYCODE_BACK) {
			this.clearFocus()
			return false
		}

		return super.onKeyPreIme(keyCode, event)
	}

	/**
	 * @inherited
	 * @method onTextChanged
	 * @since 0.1.0
	 */
	override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
		if (this.initialized) {
			this.value = text.toString()
			this.observer?.onChange(this, text.toString())
		}
	}

	/**
	 * @inherited
	 * @method onPreDraw
	 * @since 0.1.0
	 */
	override fun onPreDraw(): Boolean {

		if (this.invalidFont) {
			this.invalidFont = false
			this.typeface = FontManager.get(this.fontFamily, this.fontWeight, this.fontStyle)
		}

		this.setPadding(
			this.paddingLeft.toInt(),
			this.paddingTop.toInt(),
			this.paddingRight.toInt(),
			this.paddingBottom.toInt()
		)

		this.setShadowLayer(
			this.textShadowBlur,
			this.textShadowOffsetLeft,
			this.textShadowOffsetTop,
			this.shadowColor
		)

		return super.onPreDraw()
	}

	//--------------------------------------------------------------------------
	// Animations
	//--------------------------------------------------------------------------

	/**
	 * @inherited
	 * @property animatable
	 * @since 0.1.0
	 */
	override val animatable: List<String> = listOf()

	/**
	 * @inherited
	 * @property animations
	 * @since 0.1.0
	 */
	override var animations: MutableMap<String, ValueAnimator> = mutableMapOf()

	/**
	 * @inherited
	 * @method onBeforeAnimate
	 * @since 0.1.0
	 */
	override fun animate(property: String, initialValue: Any, currentValue: Any): ValueAnimator? {
		return null
	}

	/**
	 * @inherited
	 * @method onBeforeAnimate
	 * @since 0.1.0
	 */
	override fun onBeforeAnimate(property: String) {

	}

	/**
	 * @inherited
	 * @method onBeginTransition
	 * @since 0.1.0
	 */
	override fun onBeginTransition() {

	}

	/**
	 * @inherited
	 * @method onCommitTransition
	 * @since 0.1.0
	 */
	override fun onCommitTransition() {

	}

	/**
	 * @inherited
	 * @method onFinishTransition
	 * @since 0.1.0
	 */
	override fun onFinishTransition() {

	}

	//--------------------------------------------------------------------------
	// Private API
	//--------------------------------------------------------------------------

	/**
	 * @method invalidateFont
	 * @since 0.1.0
	 * @hidden
	 */
	private fun invalidateFont() {
		if (this.invalidFont == false) {
			this.invalidFont = true
			this.invalidate()
		}
	}

	/**
	 * @method setPlaceholderText
	 * @since 0.1.0
	 * @hidden
	 */
	private fun setPlaceholderText(text: String) {
		this.hint = text
	}

	/**
	 * @method setPlaceholderTextColor
	 * @since 0.1.0
	 * @hidden
	 */
	private fun setPlaceholderTextColor(color: Int) {
		this.setHintTextColor(color)
	}

	/**
	 * @method getString
	 * @since 0.1.0
	 * @hidden
	 */
	private fun getString(): String {
		return this.text.toString()
	}

	/**
	 * @method setString
	 * @since 0.1.0
	 * @hidden
	 */
	private fun setString(value: String) {
		if (this.getString() != value) {
			this.setTextKeepState(value)
		}
	}

	/**
	 * @method updateType
	 * @since 0.1.0
	 * @hidden
	 */
	private fun updateType() {

		var type = AndroidInputType.TYPE_CLASS_TEXT or AndroidInputType.TYPE_TEXT_FLAG_MULTI_LINE

		if (this.autocorrect == false) {
			type = type or AndroidInputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
		}

		if (this.autocapitalize == true) {
			type = type or AndroidInputType.TYPE_TEXT_FLAG_CAP_SENTENCES
		}

		this.inputType = type
	}

	/**
	 * @method updateGravity
	 * @since 0.1.0
	 * @hidden
	 */
	private fun updateGravity() {

		val h: Int
		val v: Int

		when (this.textAlign) {

			TextAlign.TOP_LEFT -> {
				h = Gravity.LEFT
				v = Gravity.TOP
			}

			TextAlign.TOP_RIGHT -> {
				h = Gravity.RIGHT
				v = Gravity.TOP
			}

			TextAlign.TOP_CENTER -> {
				h = Gravity.CENTER
				v = Gravity.TOP
			}

			TextAlign.MIDDLE_LEFT -> {
				h = Gravity.LEFT
				v = Gravity.CENTER_VERTICAL
			}

			TextAlign.MIDDLE_RIGHT -> {
				h = Gravity.RIGHT
				v = Gravity.CENTER_VERTICAL
			}

			TextAlign.MIDDLE_CENTER -> {
				h = Gravity.CENTER
				v = Gravity.CENTER_VERTICAL
			}

			TextAlign.BOTTOM_LEFT -> {
				h = Gravity.LEFT
				v = Gravity.BOTTOM
			}

			TextAlign.BOTTOM_RIGHT -> {
				h = Gravity.RIGHT
				v = Gravity.BOTTOM
			}

			TextAlign.BOTTOM_CENTER -> {
				h = Gravity.CENTER
				v = Gravity.BOTTOM
			}
		}

		this.gravity = h or v
	}
}
