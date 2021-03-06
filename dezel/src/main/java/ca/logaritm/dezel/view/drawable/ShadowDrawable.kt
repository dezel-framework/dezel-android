package ca.logaritm.dezel.view.drawable

import android.graphics.*
import android.graphics.drawable.Drawable
import ca.logaritm.dezel.extension.Delegates
import ca.logaritm.dezel.extension.graphics.addOuterRoundedRect
import kotlin.math.ceil

/**
 * @class ShadowDrawable
 * @super Drawable
 * @since 0.1.0
 */
open class ShadowDrawable : Drawable() {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The shadow's color.
	 * @property shadowColor
	 * @since 0.1.0
	 */
	public var shadowColor: Int by Delegates.OnSet(Color.TRANSPARENT) {
		this.invalidateCache()
	}

	/**
	 * The shadow's blur.
	 * @property shadowBlur
	 * @since 0.1.0
	 */
	public var shadowBlur: Float by Delegates.OnSet(0f) {
		this.invalidateCache()
	}

	/**
	 * The shadows top offset.
	 * @property shadowOffsetTop
	 * @since 0.1.0
	 */
	public var shadowOffsetTop: Float = 0f

	/**
	 * The shadow's left offset.
	 * @property shadowOffsetLeft
	 * @since 0.1.0
	 */
	public var shadowOffsetLeft: Float = 0f

	/**
	 * The top left border radius.
	 * @property cornerTopLeftRadius
	 * @since 0.1.0
	 */
	public var cornerTopLeftRadius: Float by Delegates.OnSet(0f) {
		this.invalidateCache()
	}

	/**
	 * The top right border radius.
	 * @property cornerTopRightRadius
	 * @since 0.1.0
	 */
	public var cornerTopRightRadius: Float by Delegates.OnSet(0f) {
		this.invalidateCache()
	}

	/**
	 * The bottom left border radius.
	 * @property cornerBottomLeftRadius
	 * @since 0.1.0
	 */
	public var cornerBottomLeftRadius: Float by Delegates.OnSet(0f) {
		this.invalidateCache()
	}

	/**
	 * The bottom right border radius.
	 * @property cornerBottomRightRadius
	 * @since 0.1.0
	 */
	public var cornerBottomRightRadius: Float by Delegates.OnSet(0f) {
		this.invalidateCache()
	}

	/**
	 * @property shadowPaint
	 * @since 0.1.0
	 * @hidden
	 */
	private val shadowPaint: Paint = Paint()

	/**
	 * @property shadowCacheBitmap
	 * @since 0.1.0
	 * @hidden
	 */
	private var shadowCacheBitmap: Bitmap? = null

	/**
	 * @property shadowCacheCanvas
	 * @since 0.1.0
	 * @hidden
	 */
	private var shadowCacheCanvas: Canvas? = null

	/**
	 * @property shadowNineSlice
	 * @since 0.1.0
	 * @hidden
	 */
	private var shadowNineSlice: NineSliceDrawable? = null

	/**
	 * @property invalidShadowCache
	 * @since 0.1.0
	 * @hidden
	 */
	private var invalidShadowCache: Boolean = false

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * Initializes the drawable.
	 * @constructor
	 * @since 0.1.0
	 */
	init {
		this.shadowPaint.isAntiAlias = true
	}

	/**
	 * Invalidates the shadow cache.
	 * @method invalidateCache
	 * @since 0.1.0
	 */
	open fun invalidateCache() {
		this.invalidShadowCache = true
	}

	/**
	 * @inherited
	 * @method setAlpha
	 * @since 0.1.0
	 */
	override fun setAlpha(alpha: Int) {

	}

	/**
	 * @inherited
	 * @method setColorFilter
	 * @since 0.1.0
	 */
	override fun setColorFilter(cf: ColorFilter?) {

	}

	/**
	 * @inherited
	 * @method getOpacity
	 * @since 0.1.0
	 */
	override fun getOpacity() : Int {
		return PixelFormat.TRANSLUCENT
	}

	/**
	 * @inhrited
	 * @method draw
	 * @since 0.1.0
	 */
	override fun draw(canvas: Canvas) {
		this.draw(canvas, null)
	}

	/**
	 * Draws with the specified xfermode.
	 * @method draw
	 * @since 0.1.0
	 */
	open fun draw(canvas: Canvas, xfermode: Xfermode ?) {

		if (this.invalidShadowCache) {
			this.invalidShadowCache = false
			this.drawShadowCache()
		}

		val shadowNineSlice = this.shadowNineSlice
		if (shadowNineSlice == null) {
			return
		}

		val shadowBlur = ceil(this.shadowBlur)
		val shadowOffsetT = this.shadowOffsetTop
		val shadowOffsetL = this.shadowOffsetLeft

		canvas.save()
		canvas.translate(shadowOffsetL, shadowOffsetT)

		val bounds = this.bounds
		val drawableW = bounds.width() + (shadowBlur * 2f).toInt()
		val drawableH = bounds.height() + (shadowBlur * 2f).toInt()

		shadowNineSlice.setBounds(0, 0, drawableW, drawableH)
		shadowNineSlice.paint.xfermode = xfermode
		shadowNineSlice.draw(canvas)
		shadowNineSlice.paint.xfermode = null

		canvas.restore()
	}

	/**
	 * @method drawShadowCache
	 * @since 0.1.0
	 * @hidden
	 */
	private fun drawShadowCache() {

		val shadowBlur = ceil(this.shadowBlur)

		if (Color.alpha(this.shadowColor) > 0) {

			/*
			 * Basically finds the smallest bitmap that contains the pattern that
			 * will be nine-scaled
			 */

			val cornerRadiusTL = this.cornerTopLeftRadius
			val cornerRadiusTR = this.cornerTopRightRadius
			val cornerRadiusBL = this.cornerBottomLeftRadius
			val cornerRadiusBR = this.cornerBottomRightRadius

			val inner = Math.max(
				Math.max(
					Math.max(cornerRadiusTL, cornerRadiusTR),
					Math.max(cornerRadiusBR, cornerRadiusBL)
				),
				shadowBlur
			)

			val shapeW = inner * 2f + 3f
			val shapeH = inner * 2f + 3f
			val cacheW = inner * 2f + shadowBlur * 2f + 3f
			val cacheH = inner * 2f + shadowBlur * 2f + 3f

			val shapeT = cacheH / 2 - shapeH / 2
			val shapeL = cacheW / 2 - shapeW / 2

			val shadowCacheBitmap = Bitmap.createBitmap(cacheW.toInt(), cacheH.toInt(), Bitmap.Config.ARGB_8888)
			val shadowCacheCanvas = Canvas(shadowCacheBitmap)

			shadowCacheCanvas.save()

			if (shadowBlur > 0f) {

				this.shadowPaint.color = Color.BLACK
				this.shadowPaint.setShadowLayer(shadowBlur, cacheW, cacheH, this.shadowColor)

				shadowCacheCanvas.translate(
					shapeL - cacheW,
					shapeT - cacheH
				)

			} else {

				this.shadowPaint.color = this.shadowColor
				this.shadowPaint.clearShadowLayer()

			}

			val path = Path()

			path.addOuterRoundedRect(
				shapeW,
				shapeH,
				cornerRadiusTL,
				cornerRadiusTR,
				cornerRadiusBL,
				cornerRadiusBR
			)

			shadowCacheCanvas.drawPath(path, this.shadowPaint)
			shadowCacheCanvas.restore()

			val offsetX = shadowBlur + inner + 1f
			val offsetY = shadowBlur + inner + 1f

			this.shadowNineSlice = NineSliceDrawable(
				shadowCacheBitmap,
				offsetY.toInt(),
				offsetX.toInt(),
				offsetX.toInt() + 1,
				offsetX.toInt() + 1
			)

			this.shadowCacheBitmap = shadowCacheBitmap
			this.shadowCacheCanvas = shadowCacheCanvas

		} else {
			this.shadowCacheBitmap = null
			this.shadowCacheCanvas = null
		}
	}
}
