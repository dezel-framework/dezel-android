package ca.logaritm.dezel.application.source

import android.content.Context
import ca.logaritm.dezel.application.ApplicationActivity
import ca.logaritm.dezel.networking.RemoteFileLoader
import java.io.IOException

/**
 * @class Source
 * @since 0.1.0
 */
open abstract class Source(context: Context, path: String) {

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * The application context.
	 * @property context
	 * @since 0.1.0
	 */
	public var context: Context
		private set

	/**
	 * The source's path.
	 * @property path
	 * @since 0.1.0
	 */
	public var path: String
		private set

	/**
	 * The source's data.
	 * @property data
	 * @since 0.1.0
	 */
	public val data: String
		get() = this.read()

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * Initializes the source.
	 * @constructor
	 * @since 0.1.0
	 */
	init {
		this.context = context
		this.path = path
	}

	/**
	 * Applies the source to the application.
	 * @method apply
	 * @since 0.1.0
	 */
	abstract fun apply(application: ApplicationActivity)

	//--------------------------------------------------------------------------
	// Private API
	//--------------------------------------------------------------------------

	/**
	 * @method read
	 * @since 0.1.0
	 * @hidden
	 */
	private fun read(): String {

		try {

			if (this.path.startsWith("http://") ||
				this.path.startsWith("https://")) {
				return RemoteFileLoader().execute(this.path).get()
			}

			return this.context.assets.open(this.path).reader().use {
				it.readText()
			}

		} catch (e: IOException) {
			throw Exception("Cannot load source at location ${this.path}.")
		}
	}
}