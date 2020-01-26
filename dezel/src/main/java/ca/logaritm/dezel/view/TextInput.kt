package ca.logaritm.dezel.view

import android.animation.ValueAnimator
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Paint
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import ca.logaritm.dezel.application.ApplicationActivity
import ca.logaritm.dezel.extension.Delegates
import ca.logaritm.dezel.extension.util.format
import ca.logaritm.dezel.extension.util.iso
import ca.logaritm.dezel.util.date.DateParser
import ca.logaritm.dezel.view.animation.Animatable
import ca.logaritm.dezel.view.graphic.Color
import ca.logaritm.dezel.view.graphic.Convert
import ca.logaritm.dezel.view.text.font.FontManager
import ca.logaritm.dezel.view.type.*
import java.util.*
import android.text.InputType as AndroidInputType

/**
 * @class TextInput
 * @super EditText
 * @since 0.1.0
 */
open class TextInput(context: Context, observer: TextInputObserver) : EditText(context), Animatable, View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The text input's type.
	 * @property type
	 * @since 0.1.0
	 */
	open var type: InputType by Delegates.OnSet(InputType.TEXT) {
		this.updateType()
	}

	/**
	 * The text input's value.
	 * @property value
	 * @since 0.1.0
	 */
	open var value: String by Delegates.OnSet("") { value ->

		if (this.type == InputType.DATE ||
			this.type == InputType.TIME) {

			val date = DateParser.parse(value)

			this.date = date

			this.setString(
				date.format(
					this.format,
					this.locale
				)
			)

		} else {

			this.setString(value)

		}
	}

	/**
	 * The text input's placeholder.
	 * @property placeholder
	 * @since 0.1.0
	 */
	open var placeholder: String by Delegates.OnSet("") { value ->
		this.setPlaceholderText(value)
	}

	/**
	 * The text input's placeholder color.
	 * @property placeholderColor
	 * @since 0.1.0
	 */
	open var placeholderColor: Int by Delegates.OnSet(Color.parse("gray")) { value ->
		this.setPlaceholderTextColor(value)
	}

	/**
	 * The text input's format.
	 * @property format
	 * @since 0.1.0
	 */
	open var format: String by Delegates.OnSet("") {
		this.invalidate()
	}

	/**
	 * The text input's locale.
	 * @property locale
	 * @since 0.1.0
	 */
	open var locale: String by Delegates.OnSet("") {
		this.invalidate()
	}

	/**
	 * The text input's font family.
	 * @property fontFamily
	 * @since 0.1.0
	 */
	open var fontFamily: String by Delegates.OnSet("") {
		this.invalidateFont()
	}

	/**
	 * The text input's font weight.
	 * @property fontWeight
	 * @since 0.1.0
	 */
	open var fontWeight: String by Delegates.OnSet("normal") {
		this.invalidateFont()
	}

	/**
	 * The text input's font style.
	 * @property fontStyle
	 * @since 0.1.0
	 */
	open var fontStyle: String by Delegates.OnSet("normal") {
		this.invalidateFont()
	}

	/**
	 * The text input's font size.
	 * @property fontSize
	 * @since 0.1.0
	 */
	open var fontSize: Float by Delegates.OnSet(0.0f) { value ->
		this.textSize = Convert.toDp(value)
	}

	/**
	 * The text input's text align.
	 * @property textAlign
	 * @since 0.1.0
	 */
	open var textAlign: TextAlign by Delegates.OnSet(TextAlign.MIDDLE_LEFT) {
		this.updateGravity()
	}

	/**
	 * The text input's text kerning.
	 * @property textKerning
	 * @since 0.1.0
	 */
	open var textKerning: Float by Delegates.OnSet(0.0f) {
		this.invalidate()
	}

	/**
	 * The text input's text leading.
	 * @property textLeading
	 * @since 0.1.0
	 */
	open var textLeading: Float by Delegates.OnSet(0.0f) { value ->
		this.setLineSpacing(value, 0f)
	}

	/**
	 * The text input's text decoration.
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
	 * The text input's text transform.
	 * @property textTransform
	 * @since 0.1.0
	 */
	open var textTransform: TextTransform by Delegates.OnSet(TextTransform.NONE) {
		this.invalidate()
	}

	/**
	 * The text input's text shadow blur.
	 * @property textShadowBlur
	 * @since 0.1.0
	 */
	open var textShadowBlur: Float by Delegates.OnSet(0.0f) {
		this.invalidate()
	}

	/**
	 * The text input's text shadow color.
	 * @property textShadowColor
	 * @since 0.1.0
	 */
	open var textShadowColor: Int by Delegates.OnSet(Color.BLACK) {
		this.invalidate()
	}

	/**
	 * The text input's text top shadow offset.
	 * @property textShadowOffsetTop
	 * @since 0.1.0
	 */
	open var textShadowOffsetTop: Float by Delegates.OnSet(0.0f) {
		this.invalidate()
	}

	/**
	 * The text input's text left shadow offset.
	 * @property textShadowOffsetLeft
	 * @since 0.1.0
	 */
	open var textShadowOffsetLeft: Float by Delegates.OnSet(0.0f) {
		this.invalidate()
	}

	/**
	 * The text input's top padding.
	 * @property paddingTop
	 * @since 0.1.0
	 */
	open var paddingTop: Float  by Delegates.OnSet(0f) {
		this.invalidate()
	}

	/**
	 * The text input's left padding.
	 * @property paddingLeft
	 * @since 0.1.0
	 */
	open var paddingLeft: Float  by Delegates.OnSet(0f) {
		this.invalidate()
	}

	/**
	 * The text input's right padding.
	 * @property paddingRight
	 * @since 0.1.0
	 */
	open var paddingRight: Float by Delegates.OnSet(0f) {
		this.invalidate()
	}

	/**
	 * The text input's bottom padding.
	 * @property paddingBottom
	 * @since 0.1.0
	 */
	open var paddingBottom: Float by Delegates.OnSet(0f) {
		this.invalidate()
	}

	/**
	 * The text input's autocorrect status.
	 * @property autocorrect
	 * @since 0.1.0
	 */
	open var autocorrect: Boolean by Delegates.OnSet(true) {
		this.updateType()
	}

	/**
	 * The text input's autocapitalize status.
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
	private lateinit var observer: TextInputObserver

	/**
	 * @property date
	 * @since 0.1.0
	 * @hidden
	 */
	private var date: Date = Date()

	/**
	 * @property datePicker
	 * @since 0.1.0
	 * @hidden
	 */
	private var datePicker: DatePickerDialog? = null

	/**
	 * @property timePicker
	 * @since 0.1.0
	 * @hidden
	 */
	private var timePicker: TimePickerDialog? = null

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

	/**
	 * Initializes the text input.
	 * @constructor
	 * @since 0.1.0
	 */
	init {

		this.imeOptions = EditorInfo.IME_FLAG_NO_EXTRACT_UI

		this.background = null
		this.isFocusable = true
		this.isFocusableInTouchMode = true
		this.onFocusChangeListener = this.focusChangeListener

		this.textSize = 17f
		this.maxLines = 1

		this.setHorizontallyScrolling(true)
		this.setTextColor(Color.BLACK)
		this.setPlaceholderText("")
		this.setPlaceholderTextColor(Color.parse("gray"))
		this.setPadding(0, 0, 0, 0)

		this.setOnClickListener(this)

		this.observer = observer

		this.initialized = true
	}

	/**
	 * Moves the focus to the text input.
	 * @method focus
	 * @since 0.1.0
	 */
	open fun focus() {

		val focused = this.hasFocus()
		if (focused) {
			return
		}

		if (this.type == InputType.DATE) {
			this.presentDatePicker()
			return
		}

		if (this.type == InputType.TIME) {
			this.presentTimePicker()
			return
		}

		this.requestFocus()
	}

	/**
	 * Removes the focus from the text input.
	 * @method blur
	 * @since 0.1.0
	 */
	open fun blur() {

		val focused = this.hasFocus()
		if (focused == false) {
			return
		}

		if (this.type == InputType.DATE) {
			this.datePicker?.hide()
			return
		}

		if (this.type == InputType.TIME) {
			this.timePicker?.hide()
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
	override fun onTextChanged(text: CharSequence, start: Int, lengthBefore: Int, lengthAfter: Int) {

		if (this.initialized == false) {

			/*
			 * This method might be called from the parent constructor before
			 * the class will be fully initialized
			 */

			return
		}

		if (this.type == InputType.DATE ||
			this.type == InputType.TIME) {
			return
		}

		this.updateValue(text.toString())
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

	/**
	 * @inherited
	 * @method onClick
	 * @since 0.1.0
	 */
	override fun onClick(view: View) {

		if (this.type == InputType.DATE) {
			this.presentDatePicker()
			return
		}

		if (this.type == InputType.TIME) {
			this.presentTimePicker()
			return
		}
	}

	/**
	 * @inherited
	 * @method onDateSet
	 * @since 0.1.0
	 */
	override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {

		val calendar = Calendar.getInstance()
		calendar.set(Calendar.YEAR, year)
		calendar.set(Calendar.MONTH, month)
		calendar.set(Calendar.DAY_OF_MONTH, day)

		this.date = calendar.time

		this.updateValue(this.date.iso)

		this.observer?.onBlur(this)
	}

	/**
	 * @inherited
	 * @method onTimeSet
	 * @since 0.1.0
	 */
	override fun onTimeSet(view: TimePicker, hour: Int, minute: Int) {

		val calendar = Calendar.getInstance()
		calendar.set(Calendar.HOUR_OF_DAY, hour)
		calendar.set(Calendar.MINUTE, minute)

		this.date = calendar.time

		this.updateValue(this.date.iso)

		this.observer?.onBlur(this)
	}

	//--------------------------------------------------------------------------
	// Private API
	//--------------------------------------------------------------------------

	/**
	 * @method presentDatePicker
	 * @since 0.1.0
	 * @hidden
	 */
	private fun presentDatePicker() {

		var picker = this.datePicker
		if (picker != null) {
			return
		}

		val calendar = Calendar.getInstance()

		calendar.time = this.date

		picker = DatePickerDialog(
			this.context,
			this,
			calendar.get(Calendar.YEAR),
			calendar.get(Calendar.MONTH),
			calendar.get(Calendar.DAY_OF_MONTH)
		)

		picker.setOnCancelListener {
			this.observer?.onBlur(this)
		}

		picker.setOnDismissListener {
			this.datePicker = null
		}

		picker.show()

		this.datePicker = picker

		this.observer?.onFocus(this)
	}

	/**
	 * @method presentDatePicker
	 * @since 0.1.0
	 * @hidden
	 */
	private fun presentTimePicker() {

		var picker = this.timePicker
		if (picker != null) {
			return
		}

		val calendar = Calendar.getInstance()

		calendar.time = this.date

		picker = TimePickerDialog(
			this.context,
			this,
			calendar.get(Calendar.HOUR_OF_DAY),
			calendar.get(Calendar.MINUTE),
			true
		)

		picker.setOnCancelListener {
			this.observer?.onBlur(this)
		}

		picker.setOnDismissListener {
			this.datePicker = null
		}

		picker.show()

		this.timePicker = picker

		this.observer?.onFocus(this)
	}

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
	 * @method updateValue
	 * @since 0.1.0
	 * @hidden
	 */
	private fun updateValue(value: String) {

		if (this.initialized == false) {

			/*
			 * This method might be called from the parent constructor before
			 * the class will be fully initialized
			 */

			return
		}

		var normalized = value

		if (this.type == InputType.NUMBER) {

			/*
			 * In some locale such as french, the decimal separator is a comma
			 * which does not play nice with parsing
			 */

			normalized = normalized.replace(",", ".")
		}

		if (this.value == normalized) {
			return
		}

		this.value = normalized

		this.observer?.onChange(this, normalized)
	}

	/**
	 * @method updateType
	 * @since 0.1.0
	 * @hidden
	 */
	private fun updateType() {

		var type = AndroidInputType.TYPE_NULL

		this.isFocusable = true
		this.isFocusableInTouchMode = true
		this.transformationMethod = null

		when (this.type) {
			InputType.DATE     -> type = AndroidInputType.TYPE_NULL
			InputType.TIME     -> type = AndroidInputType.TYPE_NULL
			InputType.TEXT     -> type = AndroidInputType.TYPE_CLASS_TEXT
			InputType.EMAIL    -> type = AndroidInputType.TYPE_CLASS_TEXT or AndroidInputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
			InputType.PHONE    -> type = AndroidInputType.TYPE_CLASS_PHONE
			InputType.NUMBER   -> type = AndroidInputType.TYPE_CLASS_NUMBER or AndroidInputType.TYPE_NUMBER_FLAG_DECIMAL
			InputType.PASSWORD -> type = AndroidInputType.TYPE_CLASS_TEXT or AndroidInputType.TYPE_TEXT_VARIATION_PASSWORD
		}

		if (this.type == InputType.DATE ||
			this.type == InputType.TIME) {

			this.isFocusable = false
			this.isFocusableInTouchMode = false

		} else if (this.type == InputType.PASSWORD) {

			this.transformationMethod = PasswordTransformationMethod.getInstance()
			this.typeface = null

		} else {

			if (this.autocorrect == false) {
				type = type or AndroidInputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
			}

			if (this.autocapitalize == true) {
				type = type or AndroidInputType.TYPE_TEXT_FLAG_CAP_SENTENCES
			}
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

		this.gravity = v or h
	}
}
