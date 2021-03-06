package ca.logaritm.dezel.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import ca.logaritm.dezel.R
import ca.logaritm.dezel.extension.Delegates
import ca.logaritm.dezel.view.animation.Animatable
import ca.logaritm.dezel.view.graphic.Color

/**
 * @class SpinnerView
 * @super FrameLayout
 * @since 0.1.0
 */
open class SpinnerView(context: Context) : FrameLayout(context), Animatable {

	//----------------------------------------------------------------------
	//  Properties
	//----------------------------------------------------------------------

	/**
	 * Whether the spinner view is spinning.
	 * @property spin
	 * @since 0.1.0
	 */
	open var spin: Boolean by Delegates.OnSet(false) { value ->
		if (value) {
			this.showActiveDrawable()
			this.hideCircleDrawable()
		} else {
			this.hideActiveDrawable()
			this.showCircleDrawable()
		}
	}

	/**
	 * The spinner view's tint.
	 * @property tint
	 * @since 0.1.0
	 */
	open var tint: Int by Delegates.OnSet(Color.GRAY) { value ->
		this.activeSpinnerDrawable.setColorFilter(value, PorterDuff.Mode.SRC_IN)
		this.circleSpinnerDrawable.setColorFilter(value, PorterDuff.Mode.SRC_IN)
	}

	/**
	 * @property progressBar
	 * @since 0.1.0
	 * @hidden
	 */
	private var progressBar: ProgressBar = ProgressBar(context, null, android.R.attr.progressBarStyleSmall)

	/**
	 * @property activeSpinnerDrawable
	 * @since 0.1.0
	 * @hidden
	 */
	private var activeSpinnerDrawable: Drawable

	/**
	 * @property circleSpinnerDrawable
	 * @since 0.1.0
	 * @hidden
	 */
	private var circleSpinnerDrawable: Drawable

	/**
	 * @property circleSpinnerDrawableBounds
	 * @since 0.1.0
	 * @hidden
	 */
	private var circleSpinnerDrawableBounds: Rect

	//----------------------------------------------------------------------
	//  Methods
	//----------------------------------------------------------------------

	/**
	 * Initializes the spinner view.
	 * @constructor
	 * @since 0.1.0
	 */
	init {

		this.progressBar.visibility = View.GONE

		this.addView(this.progressBar)

		this.activeSpinnerDrawable = this.getActiveDrawable()
		this.circleSpinnerDrawable = this.getCircleDrawable()
		this.activeSpinnerDrawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)
		this.circleSpinnerDrawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)

		this.circleSpinnerDrawableBounds = Rect(0, 0, this.width, this.height)

		this.setWillNotDraw(false)
	}

	/**
	 * @inherited
	 * @method onMeasure
	 * @since 0.1.0
	 */
	override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec)
		this.progressBar.measure(widthMeasureSpec, heightMeasureSpec)
	}

	/**
	 * @inherited
	 * @method onDraw
	 * @since 0.1.0
	 */
	override fun onDraw(canvas: Canvas) {

		super.onDraw(canvas)

		if (this.circleSpinnerDrawable.isVisible) {
			this.circleSpinnerDrawable.bounds.set(0, 0, this.width, this.height)
			this.circleSpinnerDrawable.draw(canvas)
		}
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
	 * @method showActiveDrawable
	 * @since 0.1.0
	 * @hidden
	 */
	private fun showActiveDrawable() {
		this.progressBar.visibility = View.VISIBLE
		this.progressBar.top = 0
		this.progressBar.left = 0
		this.progressBar.right = this.width
		this.progressBar.bottom = this.height
	}

	/**
	 * @method hideActiveDrawable
	 * @since 0.1.0
	 * @hidden
	 */
	private fun hideActiveDrawable() {
		this.progressBar.visibility = View.GONE
	}

	/**
	 * @method showCircleDrawable
	 * @since 0.1.0
	 * @hidden
	 */
	private fun showCircleDrawable() {
		this.circleSpinnerDrawable.setVisible(true, false)
		this.invalidate()
	}

	/**
	 * @method hideCircleDrawable
	 * @since 0.1.0
	 * @hidden
	 */
	private fun hideCircleDrawable() {
		this.circleSpinnerDrawable.setVisible(false, false)
		this.invalidate()
	}

	/**
	 * @method getActiveDrawable
	 * @since 0.1.0
	 * @hidden
	 */
	private fun getActiveDrawable(): Drawable {
		return this.progressBar.indeterminateDrawable
	}

	/**
	 * @method getCircleDrawable
	 * @since 0.1.0
	 * @hidden
	 */
	private fun getCircleDrawable(): Drawable {
		return this.context.getDrawable(R.drawable.spinner_view)!!
	}
}
