package ca.logaritm.dezel.application

import android.app.Activity
import android.content.ComponentCallbacks2
import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.provider.Settings
import android.util.Log
import android.util.Size
import android.util.SizeF
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import ca.logaritm.dezel.BuildConfig
import ca.logaritm.dezel.application.keyboard.KeyboardObserver
import ca.logaritm.dezel.application.keyboard.KeyboardObserverListener
import ca.logaritm.dezel.application.source.Source
import ca.logaritm.dezel.application.source.Styles
import ca.logaritm.dezel.application.touch.Touch
import ca.logaritm.dezel.core.*
import ca.logaritm.dezel.extension.Delegates
import ca.logaritm.dezel.extension.view.removeFromParent
import ca.logaritm.dezel.modules.application.JavaScriptApplication
import ca.logaritm.dezel.modules.application.JavaScriptApplicationModule
import ca.logaritm.dezel.modules.device.JavaScriptDeviceModule
import ca.logaritm.dezel.modules.dialog.JavaScriptDialogModule
import ca.logaritm.dezel.modules.form.JavaScriptFormModule
import ca.logaritm.dezel.modules.global.JavaScriptGlobalModule
import ca.logaritm.dezel.modules.graphic.ImageLoader
import ca.logaritm.dezel.modules.graphic.JavaScriptGraphicModule
import ca.logaritm.dezel.modules.locale.JavaScriptLocaleModule
import ca.logaritm.dezel.modules.platform.JavaScriptPlatformModule
import ca.logaritm.dezel.modules.util.JavaScriptUtilModule
import ca.logaritm.dezel.modules.view.JavaScriptView
import ca.logaritm.dezel.modules.view.JavaScriptViewModule
import ca.logaritm.dezel.view.Synchronizer
import ca.logaritm.dezel.view.display.Display
import ca.logaritm.dezel.view.display.Stylesheet
import ca.logaritm.dezel.view.display.StylesheetListener
import ca.logaritm.dezel.view.graphic.Convert
import ca.logaritm.dezel.view.trait.TouchCancelable

/**
 * @class ApplicationActivity
 * @super Activity
 * @since 0.1.0
 */
open class ApplicationActivity : Activity(), StylesheetListener, KeyboardObserverListener {

	//--------------------------------------------------------------------------
	// Enum
	//--------------------------------------------------------------------------

	/**
	 * The application activity's state.
	 * @enum State
	 * @since 0.1.0
	 */
	public enum class State {
		FOREGROUND,
		BACKGROUND
	}

	//--------------------------------------------------------------------------
	// Static
	//--------------------------------------------------------------------------

	companion object {

		/**
		 * @const kApplicationControllerKey
		 * @since 0.1.0
		 * @hidden
		 */
		internal val kApplicationActivityKey = Object()

		/**
		 * @constructor
		 * @since 0.1.0
		 * @hidden
		 */
		init {
			System.loadLibrary("jsc")
			System.loadLibrary("dezel")
		}
	}

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The application activity's sources.
	 * @property sources
	 * @since 0.1.0
	 */
	open var sources: MutableList<Source> = mutableListOf()

	/**
	 * The application activity's modules.
	 * @property modules
	 * @since 0.1.0
	 */
	open var modules: MutableList<JavaScriptModule> = mutableListOf()

	/**
	 * The application activity's JavaScript context.
	 * @property context
	 * @since 0.1.0
	 */
	public var context: JavaScriptContext = JavaScriptContext()
		private set

	/**
	 * The application activity's display.
	 * @property display
	 * @since 0.1.0
	 */
	public var display: Display = Display()
		private set

	/**
	 * The application activity's stylesheet.
	 * @property stylesheet
	 * @since 0.1.0
	 */
	public var stylesheet: Stylesheet = Stylesheet()
		private set

	/**
	 * The application activity's application.
	 * @property application
	 * @since 0.1.0
	 */
	public var application: JavaScriptApplication? = null
		private set

	/**
	 * The application activity's view.
	 * @property view
	 * @since 0.1.0
	 */
	public lateinit var view: RelativeLayout
		private set

	/**
	 * The application activity's viewport.
	 * @property viewport
	 * @since 0.1.0
	 */
	public val viewport: SizeF
		get() = this.getViewportSize()

	/**
	 * @property loaded
	 * @since 0.1.0
	 * @hidden
	 */
	private var loaded: Boolean = false

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * Configures the application.
	 * @method configure
	 * @since 0.1.0
	 */
	open fun configure() {

	}

	/**
	 * Configures the application's stylesheet.
	 * @method configure
	 * @since 0.1.0
	 */
	open fun configure(stylesheet: Stylesheet) {

	}

	/**
	 * Evaluates a script file.
	 * @method evaluate
	 * @since 0.1.0
	 */
	open fun evaluate(source: String, url: String) {
		this.context.evaluate(source, url)
	}

	/**
	 * Registers the application.
	 * @method register
	 * @since 0.1.0
	 */
	open fun register(application: JavaScriptApplication) {

		this.application?.destroy()
		this.application = application

		if (this.loaded) {
			this.launch()
		}
	}

	/**
	 * Reloads the application.
	 * @method reload
	 * @since 0.1.0
	 */
	open fun reload() {

		this.loaded = false

		this.modules.forEach {
			it.onBeforeReload(this.context)
		}

		Synchronizer.main.reset()

		this.application?.destroy()
		this.application?.window?.wrapper?.removeFromParent()
		this.application = null

		this.sources.forEach {
			it.apply(this)
		}

		this.modules.forEach {
			it.onReload(this.context)
		}

		this.launch()
	}

	/**
	 * Reloads the application's styles.
	 * @method reloadStyles
	 * @since 0.1.0
	 */
	open fun reloadStyles() {

		this.stylesheet = Stylesheet()

		this.prepareStylesheet()

		this.sources.forEach {
			if (it is Styles) {
				it.apply(this)
			}
		}

		this.display.stylesheet = stylesheet
	}

	//--------------------------------------------------------------------------
	// Events
	//--------------------------------------------------------------------------

	/**
	 * Called when the application opens a resource URL.
	 * @method didOpenResourceURL
	 * @since 0.1.0
	 */
	open fun onOpenResourceURL(url: Uri) {
		this.application?.callMethod("nativeOnOpenResourceURL", arrayOf(this.context.createString(url.toString())))
	}

	/**
	 * Called when the application opens an universal URL.
	 * @method openUniversalURL
	 * @since 0.1.0
	 */
	open fun onOpenUniversalURL(url: Uri) {
		this.application?.callMethod("nativeOnOpenUniversalURL", arrayOf(this.context.createString(url.toString())))
	}

	/**
	 * Called when the application enters a background state.
	 * @method onEnterBackground
	 * @since 0.1.0
	 */
	open fun onEnterBackground() {
		this.application?.callMethod("nativeOnEnterBackground")
	}

	/**
	 * Called when the application enters a foreground state.
	 * @method onEnterForeground
	 * @since 0.1.0
	 */
	open fun onEnterForeground() {
		this.application?.callMethod("nativeOnEnterForeground")
	}

	//--------------------------------------------------------------------------
	// Activity
	//--------------------------------------------------------------------------

	/**
	 * The application's state.
	 * @property state
	 * @since 0.1.0
	 */
	public var state: State = State.FOREGROUND
		private set

	/**
	 * @inherited
	 * @method onCreate
	 * @since 0.1.0
	 */
	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)

		Convert.density = this.resources.displayMetrics.density

		ImageLoader.setup(this)

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

			this.window.setFlags(
				WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
				WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
			)

			this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

		} else {

			this.window.setFlags(
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
			)

			this.window.setFlags(
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
			)
		}

		this.view = RelativeLayout(this)
		this.view.isFocusable = true
		this.view.isFocusableInTouchMode = true
		this.view.setBackgroundColor(Color.BLACK)

		this.setContentView(this.view)

		this.keyboardObserver = KeyboardObserver(this, this.view)

		this.statusBar = View(this)
		this.statusBar.layoutParams = RelativeLayout.LayoutParams(
			this.getStatusBarWidth(),
			this.getStatusBarHeight()
		)

		this.view.addView(this.statusBar)

		this.window.decorView.setOnSystemUiVisibilityChangeListener {

			val viewport = this.viewport
			this.display.viewportWidth = viewport.width.toDouble()
			this.display.viewportHeight = viewport.height.toDouble()

			val application = this.application
			if (application != null) {
				application.window.width.reset(viewport.width.toDouble(), JavaScriptPropertyUnit.PX)
				application.window.height.reset(viewport.height.toDouble(), JavaScriptPropertyUnit.PX)
			}
		}

		this.context.attribute(kApplicationActivityKey, this)
		this.context.global.property("_DEV_", this.isDev())
		this.context.global.property("_SIM_", this.isSim())

		this.context.handleError { error ->

			var file = "<no file>"
			var line = "<no line>"
			var stack = ""

			if (error.isObject) {
				file = error.property("sourceURL").string
				line = error.property("line").string
				stack = error.property("stack").string
			}

			val message =
				"${error.string} \n" +
				"File: $file \n" +
				"Line: $line \n" +
				"Stack Trace: \n" +
				stack
			Log.e("DEZEL", "TEST HERE")
			throw JavaScriptException(message)
		}

		this.prepareStylesheet()

		this.display.scale = Convert.density.toDouble()
		this.display.viewportWidth = viewport.width.toDouble()
		this.display.viewportHeight = viewport.height.toDouble()
		this.display.stylesheet = this.stylesheet

		this.configure()

		this.modules.add(JavaScriptUtilModule())
		this.modules.add(JavaScriptGlobalModule())
		this.modules.add(JavaScriptPlatformModule())
		this.modules.add(JavaScriptLocaleModule())
		this.modules.add(JavaScriptDeviceModule())
		this.modules.add(JavaScriptDialogModule())
		this.modules.add(JavaScriptGraphicModule())
		this.modules.add(JavaScriptViewModule())
		this.modules.add(JavaScriptFormModule())
		this.modules.add(JavaScriptApplicationModule())

		this.modules.forEach {
			it.configure(this.context)
		}

		this.sources.forEach {
			it.apply(this)
		}

		this.loaded = true

		this.launch()
	}

	/**
	 * @inherited
	 * @method onPause
	 * @since 0.1.0
	 */
	override fun onPause() {

		super.onPause()

		this.keyboardObserver.listener = null

		if (this.state == State.FOREGROUND) {
			this.state = State.BACKGROUND
			this.onEnterBackground()
		}
	}

	/**
	 * @inherited
	 * @method onResume
	 * @since 0.1.0
	 */
	override fun onResume() {

		super.onResume()

		this.keyboardObserver.listener = this

		if (this.state == State.BACKGROUND) {
			this.state = State.FOREGROUND
			this.onEnterForeground()
		}
	}

	/**
	 * @method onDestroy
	 * @since 0.1.0
	 */
	override fun onDestroy() {

		this.modules.forEach {
			it.dispose(this.context)
		}

		this.state = State.BACKGROUND

		this.keyboardObserver.listener = null
		this.keyboardObserver.close()

		super.onDestroy()
	}

	/**
	 * @method onTrimMemory
	 * @since 0.1.0
	 */
	override fun onTrimMemory(level: Int) {

		when (level) {

			ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE,
			ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW,
			ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL -> {
				this.application?.callMethod("nativeOnLowMemory")
			}

			else -> {

			}
		}

		super.onTrimMemory(level)
	}

	/**
	 * @method onNewIntent
	 * @since 0.1.0
	 */
	override fun onNewIntent(intent: Intent?) {

		when (intent?.action) {

			Intent.ACTION_VIEW -> {
				val uri = intent.data
				if (uri is Uri) {
					this.onOpenUniversalURL(uri)
				}
			}

			Intent.ACTION_SEND -> {
				val uri = intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM)
				if (uri is Uri) {
					this.onOpenResourceURL(uri)
				}
			}

			else -> {
				// Nothing
			}
		}
	}

	/**
	 * @inherited
	 * @method onRequestPermissionsResult
	 * @since 0.1.0
	 */
	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, results: IntArray) {
		for (i in permissions.indices) {
			val intent = Intent("dezel.application.PERMISSION_CHANGED")
			intent.putExtra("permission", permissions[i])
			intent.putExtra("result", results[i])
			LocalBroadcastManager.getInstance(this).sendBroadcastSync(intent)
		}
	}

	/**
	 * @inherited
	 * @method onBackPressed
	 * @since 0.1.0
	 */
	override fun onBackPressed() {

		val application = this.application
		if (application == null) {
			super.onBackPressed()
			return
		}

		val handled = this.context.createReturnValue()

		application.callMethod("nativeOnBack", null, handled)

		if (handled.boolean == true) {
			return
		}

		super.onBackPressed()
	}

	//--------------------------------------------------------------------------
	// Touch Management
	//--------------------------------------------------------------------------

	/**
	 * @inherited
	 * @method dispatchTouchEvent
	 * @since 0.1.0
	 */
	override fun dispatchTouchEvent(event: MotionEvent) : Boolean {

		val pointer = event.getPointerId(event.actionIndex)

		val k = event.findPointerIndex(pointer)
		val x = Convert.toDp(event.getX(k)).toDouble()
		val y = Convert.toDp(event.getY(k)).toDouble()

		val touch: Touch?

		if (event.actionMasked == MotionEvent.ACTION_DOWN ||
			event.actionMasked == MotionEvent.ACTION_POINTER_DOWN) {

			val target = this.application?.window?.findViewAt(x, y)
			if (target == null) {
				throw Exception("Unexpected error.")
			}

			touch = Touch.acquire(event, target)

		} else {

			touch = Touch.get(event)

		}

		if (touch == null) {
			throw Exception("Unexpected error.")
		}

		touch.x = x
		touch.y = y

		val touches = listOf(touch)

		when (event.actionMasked) {
			MotionEvent.ACTION_CANCEL       -> this.dispatchTouchCancel(touches)
			MotionEvent.ACTION_DOWN         -> this.dispatchTouchStart(touches)
			MotionEvent.ACTION_POINTER_DOWN -> this.dispatchTouchStart(touches)
			MotionEvent.ACTION_MOVE         -> this.dispatchTouchMove(touches)
			MotionEvent.ACTION_UP           -> this.dispatchTouchEnd(touches)
			MotionEvent.ACTION_POINTER_UP   -> this.dispatchTouchEnd(touches)
		}

		val dispatch = super.dispatchTouchEvent(event)

		for (touch in touches) {

			if (touch.disposed) {
				continue
			}

			if (touch.canceled) {
				touch.disposed = true
				this.cancelTouchEvent(touch.target, event)
				continue
			}

			if (touch.captured) {
				touch.disposed = true
				this.cancelTouchEvent(touch.target, event, skip = touch.receiver)
				continue
			}
		}

		if (event.actionMasked == MotionEvent.ACTION_UP ||
			event.actionMasked == MotionEvent.ACTION_POINTER_UP) {
			Touch.release(event)
		}

		return dispatch
	}

	/**
	 * Convenience method to dispatch a touch cancel event.
	 * @method dispatchTouchCancel
	 * @since 0.1.0
	 */
	open fun dispatchTouchCancel(touches: List<Touch>) {
		this.dispatchTouchEvent("touchcancel", touches)
	}

	/**
	 * Convenience method to dispatch a touch start event.
	 * @method dispatchTouchStart
	 * @since 0.1.0
	 */
	open fun dispatchTouchStart(touches: List<Touch>) {
		this.dispatchTouchEvent("touchstart", touches)
	}

	/**
	 * Convenience method to dispatch a touch move event.
	 * @method dispatchTouchMove
	 * @since 0.1.0
	 */
	open fun dispatchTouchMove(touches: List<Touch>) {
		this.dispatchTouchEvent("touchmove", touches)
	}

	/**
	 * Convenience method to dispatch a touch end event.
	 * @method dispatchTouchEnd
	 * @since 0.1.0
	 */
	open fun dispatchTouchEnd(touches: List<Touch>) {
		this.dispatchTouchEvent("touchend", touches)
	}

	/**
	 * @method dispatchTouchEvent
	 * @since 0.1.0
	 * @hidden
	 */
	private fun dispatchTouchEvent(type: String, touches: List<Touch>) {

		val application = this.application
		if (application == null) {
			return
		}

		val array = context.createEmptyArray()

		for ((i, t) in touches.withIndex()) {

			val touch = this.context.createEmptyObject()

			touch.property("x", t.x)
			touch.property("y", t.y)
			touch.property("id", t.id)
			touch.property("target", t.target)
			touch.property("canceled", t.canceled)
			touch.property("captured", t.captured)

			array.property(i, touch)
		}

		when (type) {

			"touchcancel" -> application.callMethod("nativeOnTouchCancel", arrayOf(array))
			"touchstart"  -> application.callMethod("nativeOnTouchStart", arrayOf(array))
			"touchmove"   -> application.callMethod("nativeOnTouchMove", arrayOf(array))
			"touchend"    -> application.callMethod("nativeOnTouchEnd", arrayOf(array))

			else -> {
				throw Exception("Invalid touch type $type")
			}
		}

		for ((i, t) in touches.withIndex()) {

			val touch = array.property(i)
			t.canceled = touch.property("canceled").boolean
			t.captured = touch.property("captured").boolean

			val receiver = touch.property("receiver")
			if (receiver.isNull ||
				receiver.isUndefined) {
				continue
			}

			if (t.captured &&
				t.receiver == null) {
				t.receiver = receiver.cast(JavaScriptView::class.java)
			}
		}
	}

	/**
	 * @method cancelTouchEvent
	 * @since 0.1.0
	 * @hidden
	 */
	private fun cancelTouchEvent(target: JavaScriptView, event: MotionEvent, skip: JavaScriptView? = null) {

		val cancel = MotionEvent.obtain(event)

		cancel.action = MotionEvent.ACTION_CANCEL

		/**
		 * Manually dispatch a touch cancel event starting from the touch
		 * target and bubbles
		 */

		var view = target

		while (true) {

			if (view != skip) {

				view.wrapper.onInterceptTouchEvent(cancel)
				view.wrapper.onTouchEvent(cancel)
				view.wrapper.onInterceptTouchEvent(cancel)
				view.content.onTouchEvent(cancel)

				val content = view.content
				if (content is TouchCancelable) {
					content.cancelTouchEvent(cancel)
				}
			}

			val parent = view.parent
			if (parent == null) {
				break
			}

			view = parent
		}

		cancel.recycle()
	}

	//--------------------------------------------------------------------------
	// Keyboard Management
	//--------------------------------------------------------------------------

	/**
	 * @property keyboardObserver
	 * @since 0.1.0
	 * @hidden
	 */
	private lateinit var keyboardObserver: KeyboardObserver

	/**
	 * @property keyboardManager
	 * @since 0.1.0
	 * @hidden
	 */
	private val keyboardManager: InputMethodManager
		get() = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager

	/**
	 * @property keyboardPresenting
	 * @since 0.1.0
	 * @hidden
	 */
	private var keyboardPresenting: Boolean = false

	/**
	 * @property keyboardDismissing
	 * @since 0.1.0
	 * @hidden
	 */
	private var keyboardDismissing: Boolean = false

	/**
	 * @property keyboardVisible
	 * @since 0.1.0
	 * @hidden
	 */
	private var keyboardVisible: Boolean = false

	/**
	 * @property keyboardHeight
	 * @since 0.1.0
	 * @hidden
	 */
	private var keyboardHeight: Int = 0

	/**
	 * @property keyboardTarget
	 * @since 0.1.0
	 * @hidden
	 */
	private var keyboardTarget: View? = null

	/**
	 * @property keyboardHandler
	 * @since 0.1.0
	 * @hidden
	 */
	private var keyboardHandler: Handler = Handler()

	/**
	 * Called when the keyboard height changes.
	 * @method onKeyboardHeightChanged
	 * @since 0.1.0
	 */
	override fun onKeyboardHeightChanged(height: Int, orientation: Int) {

		this.keyboardVisible = height > 0

		if (this.keyboardPresenting || this.keyboardDismissing) {

			if (this.keyboardPresenting) {
				this.keyboardPresenting = false
				this.onBeforeKeyboardShow(height)
				this.onKeyboardShow(height)
			}

			if (this.keyboardDismissing) {
				this.keyboardDismissing = false
				this.onBeforeKeyboardHide(this.keyboardHeight)
				this.onKeyboardHide(this.keyboardHeight)
			}

		} else {

			if (height == 0 && this.keyboardHeight > 0) {
				this.onBeforeKeyboardHide(this.keyboardHeight)
				this.onKeyboardHide(this.keyboardHeight)
			}

		}

		if (this.keyboardHeight != height) {
			this.onBeforeKeyboardResize(this.keyboardHeight)
			this.onKeyboardResize(this.keyboardHeight)
		}

		this.keyboardHeight = height
	}

	/**
	 * Presents the soft keyboard.
	 * @method presentSoftKeyboard
	 * @since 0.1.0
	 */
	open fun presentSoftKeyboard(source: View) {
		this.keyboardTarget = source
		if (this.keyboardVisible == false) {
			this.keyboardManager.showSoftInput(source, InputMethodManager.SHOW_IMPLICIT)
			this.keyboardPresenting = true
		}
	}

	/**
	 * Dismisses the soft keyboard.
	 * @method dismissSoftKeyboard
	 * @since 0.1.0
	 */
	open fun dismissSoftKeyboard(source: View) {
		if (this.keyboardVisible) {
			this.view.post {
				if (this.keyboardTarget == source) {
					this.keyboardTarget = null
					this.keyboardManager.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
					this.keyboardDismissing = true
				}
			}
		}
	}

	/**
	 * Called before the keybord is shown.
	 * @method onBeforeKeyboardShow
	 * @since 0.1.0
	 */
	open fun onBeforeKeyboardShow(height: Int) {
		this.dispatchKeyboardEvent("beforekeyboardshow", height)
	}

	/**
	 * Called after the keyboard is shown.
	 * @method onKeyboardShow
	 * @since 0.1.0
	 */
	open fun onKeyboardShow(height: Int) {
		this.dispatchKeyboardEvent("keyboardshow", height)
	}

	/**
	 * Called before the keyboard is hidden.
	 * @method onBeforeKeyboardHide
	 * @since 0.1.0
	 */
	open fun onBeforeKeyboardHide(height: Int) {
		this.dispatchKeyboardEvent("beforekeyboardhide", height)
	}

	/**
	 * Called after the keyboard is hidden.
	 * @method onKeyboardHide
	 * @since 0.1.0
	 */
	open fun onKeyboardHide(height: Int) {
		this.dispatchKeyboardEvent("keyboardhide", height)
	}

	/**
	 * Called before the keyboard resizes.
	 * @method onBeforeKeyboardResize
	 * @since 0.1.0
	 */
	open fun onBeforeKeyboardResize(height: Int) {
		this.dispatchKeyboardEvent("beforekeyboardresize", height)
	}

	/**
	 * Called after the keyboard resizes.
	 * @method onKeyboardResize
	 * @since 0.1.0
	 */
	open fun onKeyboardResize(height: Int) {
		this.dispatchKeyboardEvent("keyboardresize", height)
	}

	/**
	 * @method dispatchKeyboardEvent
	 * @since 0.1.0
	 * @hidden
	 */
	private fun dispatchKeyboardEvent(name: String, height: Int, delay: Long = 0) {

		val application = this.application
		if (application == null) {
			return
		}

		this.keyboardHandler.postDelayed({

			val args = arrayOf<JavaScriptValue?>(
				this.context.createNumber(Convert.toDp(height.toFloat())),
				this.context.createNumber(250.0),
				this.context.createString("cubic-bezier(0.25,0.1,0.25,1)")
			)

			val method: String

			when (name) {

				"beforekeyboardshow"   -> method = "nativeOnBeforeKeyboardShow"
				"keyboardshow"         -> method = "nativeOnKeyboardShow"
				"beforekeyboardhide"   -> method = "nativeOnBeforeKeyboardHide"
				"keyboardhide"         -> method = "nativeOnKeyboardHide"
				"beforekeyboardresize" -> method = "nativeOnBeforeKeyboardResize"
				"keyboardresize"       -> method = "nativeOnKeyboardResize"

				else -> {
					throw Exception()
				}
			}

			application.callMethod(method, args, null)

		}, delay)
	}

	//--------------------------------------------------------------------------
	// Status Bar Management
	//--------------------------------------------------------------------------

	/**
	 * The application activity's status bar visibility status.
	 * @property statusBarVisible
	 * @since 0.1.0
	 */
	open var statusBarVisible: Boolean by Delegates.OnSet(true) {
		this.updateStatusBar()
	}

	/**
	 * The application activity's status bar background color.
	 * @property statusBarForegroundColor
	 * @since 0.1.0
	 */
	open var statusBarForegroundColor: Int by Delegates.OnSet(Color.BLACK) {
		this.updateStatusBar()
	}

	/**
	 * The application activity's status bar background color.
	 * @property statusBarBackgroundColor
	 * @since 0.1.0
	 */
	open var statusBarBackgroundColor: Int by Delegates.OnSet(Color.TRANSPARENT) {
		this.updateStatusBar()
	}

	/**
	 * @property statusBar
	 * @since 0.1.0
	 * @hidden
	 */
	private lateinit var statusBar: View

	/**
	 * @method updateStatusBar
	 * @since 0.1.0
	 * @hidden
	 */
	private fun updateStatusBar() {

		if (this.statusBarVisible == false) {
			this.statusBar.visibility = View.GONE
			this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
			return
		}

		this.statusBar.visibility = View.VISIBLE

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

			var visibility = View.SYSTEM_UI_FLAG_VISIBLE

			if (this.statusBarForegroundColor == Color.WHITE) {
				visibility = visibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
			} else {
				visibility = visibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
			}

			this.window.decorView.systemUiVisibility = visibility
		}

		this.statusBar.setBackgroundColor(this.statusBarBackgroundColor)
	}

	/**
	 * @method getStatusBarWidth
	 * @since 0.1.0
	 * @hidden
	 */
	private fun getStatusBarWidth(): Int {
		return RelativeLayout.LayoutParams.MATCH_PARENT
	}

	/**
	 * @method getStatusBarHeight
	 * @since 0.1.0
	 * @hidden
	 */
	private fun getStatusBarHeight(): Int {

		val resource = this.resources.getIdentifier("status_bar_height", "dimen", "android")
		if (resource > 0) {
			return this.resources.getDimensionPixelSize(resource)
		}

		return 0
	}

	//--------------------------------------------------------------------------
	// Stylesheet Delegate
	//--------------------------------------------------------------------------

	/**
	 * @method didThrowError
	 * @since 0.1.0
	 * @hidden
	 */
	override fun onThrowError(stylesheet: Stylesheet, error: String, col: Int, row: Int, url: String) {
		Log.e("Dezel", "Stylesheet error $error")
	}

	//--------------------------------------------------------------------------
	// Private API
	//--------------------------------------------------------------------------

	/**
	 * @method prepareStylesheet
	 * @since 0.1.0
	 * @hidden
	 */
	private fun prepareStylesheet() {

		val insetT = this.getSafeAreaTopInset()
		val insetL = 0f
		val insetR = 0f
		val insetB = this.getSafeAreaBottomInset()

		this.stylesheet.listener = this
		this.stylesheet.setVariable("safe-area-inset-top", "${insetT}px")
		this.stylesheet.setVariable("safe-area-inset-left", "${insetL}px")
		this.stylesheet.setVariable("safe-area-inset-right", "${insetR}px")
		this.stylesheet.setVariable("safe-area-inset-bottom", "${insetB}px")

		this.configure(this.stylesheet)
	}

	/**
	 * @method launch
	 * @since 0.1.0
	 * @hidden
	 */
	private fun launch() {

		val application = this.application
		if (application == null) {
			return
		}

		val viewport = this.viewport

		this.display.window = application.window.node

		application.window.width.reset(viewport.width.toDouble(), JavaScriptPropertyUnit.PX, lock = this)
		application.window.height.reset(viewport.height.toDouble(), JavaScriptPropertyUnit.PX, lock = this)
		application.callMethod("nativeOnCreate")

		this.view.addView(application.window.wrapper)
	}

	/**
	 * @method getViewportSize
	 * @since 0.1.0
	 * @hidden
	 */
	private fun getViewportSize(): SizeF {

		val viewport = Point()

		this.windowManager.defaultDisplay.getRealSize(viewport)

		if (this.hasNavigationBar()) {
			viewport.y = viewport.y - this.getNavigationBarSize().height
		}

		val w = Convert.toDp(viewport.x)
		val h = Convert.toDp(viewport.y)

		return SizeF(w, h)
	}

	/**
	 * @method hasNavigationBar
	 * @since 0.1.0
	 * @hidden
	 */
	private fun hasNavigationBar(): Boolean {

		if (Build.FINGERPRINT.startsWith("generic")) {
			return true
		}

		val samsung = Build.MANUFACTURER == "samsung"

		if (samsung) {
			try {
				return Settings.Global.getInt(this.contentResolver, "navigationbar_hide_bar_enabled") == 0
			} catch (e: Settings.SettingNotFoundException) {
				// ?
			}
		}

		val size = Point()
		val real = Point()

		this.windowManager.defaultDisplay.getSize(size)
		this.windowManager.defaultDisplay.getRealSize(real)

		return (
			size.x < real.x ||
			size.y < real.y
		)
	}

	/**
	 * @method getNavigationBarSize
	 * @since 0.1.0
	 * @hidden
	 */
	private fun getNavigationBarSize(): Size {

		val size = Point()
		val real = Point()

		this.windowManager.defaultDisplay.getSize(size)
		this.windowManager.defaultDisplay.getRealSize(real)

		return Size(real.x - size.x, real.y - size.y)
	}

	/**
	 * @method getSafeAreaTopInset
	 * @since 0.1.0
	 * @hidden
	 */
	private fun getSafeAreaTopInset(): Double {
		return Convert.toDp(this.getStatusBarHeight()).toDouble()
	}

	/**
	 * @method getSafeAreaBottomInset
	 * @since 0.1.0
	 * @hidden
	 */
	private fun getSafeAreaBottomInset(): Double {
		return 0.0
	}

	/**
	 * @method isDev
	 * @since 0.1.0
	 * @hidden
	 */
	private fun isDev(): Boolean {
		return BuildConfig.DEBUG
	}

	/**
	 * @method isSim
	 * @since 0.1.0
	 * @hidden
	 */
	private fun isSim(): Boolean {
		return Build.HARDWARE == "goldfish"
	}
}
