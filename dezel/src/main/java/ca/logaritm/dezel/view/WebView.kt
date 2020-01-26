package ca.logaritm.dezel.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.util.Size
import android.util.SizeF
import android.view.MotionEvent
import android.view.View
import android.webkit.*
import android.widget.RelativeLayout
import ca.logaritm.dezel.extension.Delegates
import ca.logaritm.dezel.view.trait.Scrollable
import ca.logaritm.dezel.view.trait.ScrollableListener
import ca.logaritm.dezel.view.type.Overscroll
import ca.logaritm.dezel.view.type.Scrollbars
import android.webkit.WebView as AndroidWebView

/**
 * @class WebView
 * @super AndroidWebView
 * @since 0.1.0
 */
@SuppressLint("ViewConstructor")
open class WebView(context: Context, observer: WebViewObserver) : AndroidWebView(context), Scrollable {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The scroll listener.
	 * @property scrollableListener
	 * @since 0.1.0
	 */
	override var scrollableListener: ScrollableListener? = null

	/**
	 * @inherited
	 * @property scrollable
	 * @since 0.1.0
	 */
	override var scrollable: Boolean = false

	/**
	 * @inherited
	 * @property scrollbars
	 * @since 0.1.0
	 */
	override var scrollbars: Scrollbars by Delegates.OnSet(Scrollbars.NONE) { value ->

		when (value) {

			Scrollbars.NONE -> {
				this.isVerticalScrollBarEnabled = false
				this.isHorizontalScrollBarEnabled = false
			}

			Scrollbars.BOTH -> {
				this.isVerticalScrollBarEnabled = true
				this.isHorizontalScrollBarEnabled = true
			}

			Scrollbars.VERTICAL -> {
				this.isVerticalScrollBarEnabled = true
				this.isHorizontalScrollBarEnabled = false
			}

			Scrollbars.HORIZONTAL -> {
				this.isVerticalScrollBarEnabled = false
				this.isHorizontalScrollBarEnabled = true
			}
		}
	}

	/**
	 * @inherited
	 * @property overscroll
	 * @since 0.1.0
	 */
	override var overscroll: Overscroll by Delegates.OnSet(Overscroll.AUTO) { value ->
		when (value) {
			Overscroll.AUTO     -> { this.overScrollMode = View.OVER_SCROLL_IF_CONTENT_SCROLLS }
			Overscroll.NEVER    -> { this.overScrollMode = View.OVER_SCROLL_NEVER }
			Overscroll.ALWAYS   -> { this.overScrollMode = View.OVER_SCROLL_ALWAYS }
			Overscroll.ALWAYS_X -> { this.overScrollMode = View.OVER_SCROLL_ALWAYS }
			Overscroll.ALWAYS_Y -> { this.overScrollMode = View.OVER_SCROLL_ALWAYS }
		}
	}

	/**
	 * @inherited
	 * @property scrollTop
	 * @since 0.1.0
	 */
	override var scrollTop: Int
		get() = this.scrollY
		set(value) {
			this.scrollY = value
		}

	/**
	 * @inherited
	 * @property scrollLeft
	 * @since 0.1.0
	 */
	override var scrollLeft: Int
		get() = this.scrollX
		set(value) {
			this.scrollX = value
		}

	/**
	 * @inherited
	 * @property scrollWidth
	 * @since 0.1.0
	 */
	override var scrollWidth: Int by Delegates.OnSet(0) {
		throw Exception("JavaScriptWebView scrollWidth is not supported")
	}

	/**
	 * @inherited
	 * @property scrollHeight
	 * @since 0.1.0
	 */
	override var scrollHeight: Int by Delegates.OnSet(0) {
		throw Exception("JavaScriptWebView scrollHeight is not supported")
	}

	/**
	 * @inherited
	 * @property scrollInertia
	 * @since 0.1.0
	 */
	override var scrollInertia: Boolean by Delegates.OnSet(true) {
		throw Exception("JavaScriptWebView scrollInertia is not supported")
	}

	/**
	 * @inherited
	 * @property contentInsetTop
	 * @since 0.1.0
	 */
	override var contentInsetTop: Int by Delegates.OnSet(0) {
		throw Exception("JavaScriptWebView contentInsetTop is not supported")
	}

	/**
	 * @inherited
	 * @property contentInsetLeft
	 * @since 0.1.0
	 */
	override var contentInsetLeft: Int by Delegates.OnSet(0) {
		throw Exception("JavaScriptWebView contentInsetLeft is not supported")
	}

	/**
	 * @inherited
	 * @property contentInsetRight
	 * @since 0.1.0
	 */
	override var contentInsetRight: Int by Delegates.OnSet(0) {
		throw Exception("JavaScriptWebView contentInsetRight is not supported")
	}

	/**
	 * @inherited
	 * @property contentInsetBottom
	 * @since 0.1.0
	 */
	override var contentInsetBottom:  Int by Delegates.OnSet(0) {
		throw Exception("JavaScriptWebView contentInsetBottom is not supported")
	}

	/**
	 * @inherited
	 * @property paged
	 * @since 0.1.0
	 */
	override var paged: Boolean by Delegates.OnSet(false) {
		throw Exception("JavaScriptWebView paged is not supported")
	}

	/**
	 * @inherited
	 * @property zoomable
	 * @since 0.1.0
	 */
	override var zoomable: Boolean by Delegates.OnSet(false) { value ->
		this.settings.setSupportZoom(value)
	}

	/**
	 * @inherited
	 * @property minZoom
	 * @since 0.1.0
	 */
	override var minZoom: Float by Delegates.OnSet(1.0f) {
		throw Exception("JavaScriptWebView minZoom is not supported")
	}

	/**
	 * @inherited
	 * @property maxZoom
	 * @since 0.1.0
	 */
	override var maxZoom: Float by Delegates.OnSet(1.0f) {
		throw Exception("JavaScriptWebView maxZoom is not supported")
	}

	/**
	 * @inherited
	 * @property zoomedView
	 * @since 0.1.0
	 */
	override var zoomedView: View? by Delegates.OnSetOptional(null) {
		// TODO
	}

	/**
	 * @property observer
	 * @since 0.1.0
	 * @hidden
	 */
	private lateinit var observer: WebViewObserver

	/**
	 * @property webViewWebClient
	 * @since 0.1.0
	 * @hidden
	 */
	private var webViewWebClient: WebViewClient = object: WebViewClient() {

		/**
		 * @method onPageFinished
		 * @since 0.1.0
		 * @hidden
		 */
		override fun onPageFinished(view: AndroidWebView, url: String) {
			val w = this@WebView.computeHorizontalScrollRange()
			val h = this@WebView.computeVerticalScrollRange()
			this@WebView.observer.onUpdateContentSize(this@WebView, Size(w, h))
			this@WebView.observer.onLoad(this@WebView)
		}

		/**
		 * @method onReceivedSslError
		 * @since 0.1.0
		 * @hidden
		 */
		override fun onReceivedSslError(view: android.webkit.WebView, handler: SslErrorHandler, error: SslError) {
			handler.proceed()
		}

		/**
		 * @method shouldOverrideUrlLoading
		 * @since 0.1.0
		 * @hidden
		 */
		override fun shouldOverrideUrlLoading(view: AndroidWebView, url: String): Boolean {
			return this.shouldOverrideUrlLoading(url)
		}

		/**
		 * @method shouldOverrideUrlLoading
		 * @since 0.1.0
		 * @hidden
		 */
		override fun shouldOverrideUrlLoading(view: android.webkit.WebView, request: WebResourceRequest): Boolean {
			return this.shouldOverrideUrlLoading(request.url.toString())
		}

		/**
		 * @method shouldOverrideUrlLoading
		 * @since 0.1.0
		 * @hidden
		 */
		private fun shouldOverrideUrlLoading(url: String): Boolean {

			if (url.startsWith("tel:")) {
				context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(url)))
				return true
			}

			if (url.startsWith("mailto:")) {
				context.startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse(url)))
				return true
			}

			val allow = this@WebView.observer.onBeforeLoad(this@WebView, url)
			if (allow == false) {
				return true
			}

			return false
		}
	}

	/**
	 * @property webViewChromeClient
	 * @since 0.1.0
	 * @hidden
	 */
	private val webViewChromeClient: WebChromeClient = object: WebChromeClient() {

		/**
		 * @method onGeolocationPermissionsShowPrompt
		 * @since 0.1.0
		 * @hidden
		 */
		override fun onGeolocationPermissionsShowPrompt(origin: String?, callback: GeolocationPermissions.Callback) {
			callback(origin, true, false)
		}
	}

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * Initializes the web view.
	 * @constructor
	 * @since 0.1.0
	 */
	init {

		this.observer = observer

		this.settings.allowFileAccess = true
		this.settings.allowFileAccessFromFileURLs = true
		this.settings.allowContentAccess = true
		this.settings.javaScriptEnabled = true
		this.settings.javaScriptCanOpenWindowsAutomatically = true
		this.settings.databaseEnabled = true
		this.settings.domStorageEnabled = true
		this.settings.setGeolocationEnabled(true)

		/*
		 * Setting this is very important because it will have an effect
		 * on the height of the html element inside the web page.
		 */

		this.layoutParams = RelativeLayout.LayoutParams(
			RelativeLayout.LayoutParams.MATCH_PARENT,
			RelativeLayout.LayoutParams.MATCH_PARENT
		)

		this.webViewClient = this.webViewWebClient
		this.webChromeClient = this.webViewChromeClient
	}

	/**
	 * Measures the web view's intrinsic size.
	 * @method measure
	 * @since 0.1.0
	 */
	open fun measure(bounds: SizeF, min: SizeF, max: SizeF): SizeF {

		var w = bounds.width
		if (w == 0f) {
			w = this.computeHorizontalScrollRange().toFloat()
		}

		var h = bounds.height
		if (h == 0f) {
			h = this.computeVerticalScrollRange().toFloat()
		}

		return SizeF(w, h)
	}

	/**
	 * @inherited
	 * @method onInterceptTouchEvent
	 * @since 0.1.0
	 */
	override fun onInterceptTouchEvent(event: MotionEvent): Boolean {

		if (this.scrollable == false) {
			return false
		}

		return super.onInterceptTouchEvent(event)
	}
}