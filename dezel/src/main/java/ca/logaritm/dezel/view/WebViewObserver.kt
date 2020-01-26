package ca.logaritm.dezel.view

import android.util.Size

/**
 * @protocol WebViewObserver
 * @since 0.1.0
 */
public interface WebViewObserver {

	/**
	 * Called before the web view loads and URL.
	 * @method onBeforeLoad
	 * @since 0.1.0
	 */
	fun onBeforeLoad(webView: WebView, url: String): Boolean

	/**
	 * Called when the web view finishes loading.
	 * @method onLoad
	 * @since 0.1.0
	 */
	fun onLoad(webView: WebView)

	/**
	 * Called when the web view updates its content size.
	 * @method onUpdateContentSize
	 * @since 0.1.0
	 */
	fun onUpdateContentSize(webView: WebView, size: Size)
}