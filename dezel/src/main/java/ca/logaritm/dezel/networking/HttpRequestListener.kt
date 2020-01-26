package ca.logaritm.dezel.networking

/**
 * @interface HttpRequestListener
 * @since 0.1.0
 */
public interface HttpRequestListener {

	/**
	 * Called when the request fails.
	 * @method onError
	 * @since 0.1.0
	 */
	fun onError(request: HttpRequest, response: HttpResponse)

	/**
	 * Called when the request times out.
	 * @method onError
	 * @since 0.1.0
	 */
	fun onTimeout(request: HttpRequest, response: HttpResponse)

	/**
	 * Called when the request progresses.
	 * @method onProgress
	 * @since 0.1.0
	 */
	fun onProgress(request: HttpRequest, loaded: Int, length: Int)

	/**
	 * Called when the request completes.
	 * @method onComplete
	 * @since 0.1.0
	 */
	fun onComplete(request: HttpRequest, response: HttpResponse)


}