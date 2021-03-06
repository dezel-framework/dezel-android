package ca.logaritm.dezel.view.display

import ca.logaritm.dezel.view.display.external.DisplayExternal

/**
 * @class Display
 * @since 0.1.0
 */
public class Display {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The display's window.
	 * @property window
	 * @since 0.1.0
	 */
	public var window: DisplayNode? = null
		set(value) = DisplayExternal.setWindow(this.handle, value?.handle ?: 0)

	/**
	 * The display's scale factor.
	 * @property scale
	 * @since 0.1.0
	 */
	public var scale: Double = 1.0
		set(value) = DisplayExternal.setScale(this.handle, value)

	/**
	 * The display's viewport width.
	 * @property viewportWidth
	 * @since 0.1.0
	 */
	public var viewportWidth: Double = 0.0
		set(value) = DisplayExternal.setViewportWidth(this.handle, value)

	/**
	 * The display's viewport height.
	 * @property viewportHeight
	 * @since 0.1.0
	 */
	public var viewportHeight: Double = 0.0
		set(value) = DisplayExternal.setViewportHeight(this.handle, value)

	/**
	 * The display's stylesheet.
	 * @property stylesheet
	 * @since 0.1.0
	 */
	public var stylesheet: Stylesheet? = null
		set(value) = DisplayExternal.setStylesheet(this.handle, value?.handle ?: 0)

	/**
	 * Whether the display is invalid.
	 * @property invalid
	 * @since 0.1.0
	 */
	public val invalid: Boolean
		get() = DisplayExternal.isInvalid(this.handle)

	/**
	 * Whether the display is resolving.
	 * @property resolving
	 * @since 0.1.0
	 */
	public val resolving: Boolean
		get() = DisplayExternal.isResolving(this.handle)

	/**
	 * @property handle
	 * @since 0.1.0
	 * @hidden
	 */
	public var handle: Long
		private set

	/**
	 * @property prepareCallback
	 * @since 0.1.0
	 * @hidden
	 */
	private var prepareCallback: MutableList<() -> Unit> = mutableListOf()

	/**
	 * @property resolveCallback
	 * @since 0.1.0
	 * @hidden
	 */
	private var resolveCallback: MutableList<() -> Unit> = mutableListOf()

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * @constructor
	 * @since 0.1.0
	 * @hidden
	 */
	init {
		this.handle = DisplayExternal.create(this)
		DisplayReference.register(this)
	}

	/**
	 * Resolves the display.
	 * @method resolve
	 * @since 0.1.0
	 */
	public fun resolve() {
		// TODO
	}

	/**
	 * Registers a callback that executes when the display is about to resolve.
	 * @method registerPrepareCallback
	 * @since 0.1.0
	 */
	public fun registerPrepareCallback(callback: (() -> Unit)) {
		this.prepareCallback.add(callback)
	}

	/**
	 * Registers a callback that executes when the display is resolved.
	 * @method registerResolveCallback
	 * @since 0.1.0
	 */
	public fun registerResolveCallback(callback: (() -> Unit)) {
		this.resolveCallback.add(callback)
	}

	//--------------------------------------------------------------------------
	// Private API
	//--------------------------------------------------------------------------

	/**
	 * @method onPrepare
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	private fun onPrepare() {
		this.dispatchPrepareCallback()
	}

	/**
	 * @method onResolve
	 * @since 0.1.0
	 * @hidden
	 */
	@Suppress("unused")
	private fun onResolve() {
		this.dispatchResolveCallback()
	}

	/**
	 * @method dispatchPrepareCallback
	 * @since 0.1.0
	 * @hidden
	 */
	private fun dispatchPrepareCallback() {
		this.prepareCallback.forEach { it() }
		this.prepareCallback.clear()
	}

	/**
	 * @method dispatchResolveCallback
	 * @since 0.1.0
	 * @hidden
	 */
	private fun dispatchResolveCallback() {
		this.resolveCallback.forEach { it() }
		this.resolveCallback.clear()
	}
}