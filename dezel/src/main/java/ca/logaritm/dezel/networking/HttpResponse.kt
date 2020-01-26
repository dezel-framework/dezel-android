package ca.logaritm.dezel.networking

import java.net.URL

/**
 * @class HttpResponse
 * @since 0.1.0
 */
open class HttpResponse(url: URL) {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The response's URL.
	 * @property url
	 * @since 0.1.0
	 */
	public var url: URL

	/**
	 * The response's data.
	 * @property data
	 * @since 0.1.0
	 */
	public var data: String = ""

	/**
	 * The response's header.
	 * @property headers
	 * @since 0.1.0
	 */
	public var headers: MutableMap<String, String> = mutableMapOf()

	/**
	 * The response's status code.
	 * @property statusCode
	 * @since 0.1.0
	 */
	public var statusCode: Int = 0

	/**
	 * The response's status text.
	 * @property statusText
	 * @since 0.1.0
	 */
	public var statusText: String = ""

	//--------------------------------------------------------------------------
	// Method
	//--------------------------------------------------------------------------

	/**
	 * Initializes the response.
	 * @constructor
	 * @since 0.1.0
	 */
	init {
		this.url = url
	}
}