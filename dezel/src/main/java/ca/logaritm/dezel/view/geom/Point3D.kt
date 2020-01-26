package ca.logaritm.dezel.view.geom

/**
 * @class Point3D
 * @since 0.1.0
 */
public data class Point3D(var x: Double, var y: Double, var z: Double) {

	/**
	 * Rounds the point.
	 * @method round
	 * @since 0.1.0
	 */
	public fun round() {
		this.x = Math.round(this.x).toDouble()
		this.y = Math.round(this.y).toDouble()
		this.z = Math.round(this.z).toDouble()
	}

	/**
	 * Floors the point.
	 * @method floor
	 * @since 0.1.0
	 */
	public fun floor() {
		this.x = Math.floor(this.x)
		this.y = Math.floor(this.y)
		this.z = Math.floor(this.z)
	}

	/**
	 * Ceils the point.
	 * @method ceil
	 * @since 0.1.0
	 */
	public fun ceil() {
		this.x = Math.floor(this.x)
		this.y = Math.floor(this.y)
		this.z = Math.floor(this.z)
	}
}