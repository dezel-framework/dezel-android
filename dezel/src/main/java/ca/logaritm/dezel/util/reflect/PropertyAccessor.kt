package ca.logaritm.dezel.util.reflect

import java.lang.reflect.Method

/**
 * @class PropertyAccessor
 * @since 0.1.0
 */
public class PropertyAccessor(val clazz: Class<*>, val field: String) {

	//--------------------------------------------------------------------------
	// Static
	//--------------------------------------------------------------------------

	companion object {

		/**
		 * @property cache
		 * @since 0.1.0
		 * @hidden
		 */
		private var cache: MutableMap<Class<*>, MutableMap<String, PropertyAccessor>> = mutableMapOf()

		/**
		 * Dynamically assigns a value on an object.
		 * @method set
		 * @since 0.1.0
		 */
		public fun set(target: Any, field: String, value: Any?): Boolean {
			return this.accessor(target, field).set(target, value)
		}

		/**
		 * Dynamically returns a value from an objet.
		 * @method get
		 * @since 0.1.0
		 */
		public fun get(target: Any, field: String): Any? {
			return this.accessor(target, field).get(target)
		}

		/**
		 * @method accessor
		 * @since 0.1.0
		 * @hidden
		 */
		private fun accessor(target: Any, name: String): PropertyAccessor {
			val type = target.javaClass
			return this.cache
				.getOrPut(type, { mutableMapOf() })
				.getOrPut(name, { PropertyAccessor(type, name) })
		}
	}

	//--------------------------------------------------------------------------
	// Properties
	//--------------------------------------------------------------------------

	/**
	 * @property getter
	 * @since 0.1.0
	 * @hidden
	 */
	private var getter: Method? = null

	/**
	 * @property setter
	 * @since 0.1.0
	 * @hidden
	 */
	private var setter: Method? = null

	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------

	/**
	 * @constructor
	 * @since 0.1.0
	 * @hidden
	 */
	init {

		val getter = "get" + field[0].toUpperCase() + field.substring(1)
		val setter = "set" + field[0].toUpperCase() + field.substring(1)

		try {
			this.getter = this.clazz.getMethod(getter)
			this.setter = this.clazz.getMethod(setter, Any::class.java)
		} catch (e: NoSuchMethodException) {

		}
	}

	/**
	 * Executes the reflection setter.
	 * @method set
	 * @since 0.1.0
	 */
	public fun set(target: Any, value: Any?): Boolean {

		try {
			this.setter?.invoke(target, value)
		} catch (e: Exception) {
			return false
		}

		return true
	}

	/**
	 * Executes the reflection getter.
	 * @method get
	 * @since 0.1.0
	 */
	public fun get(target: Any): Any? {

		try {
			return this.getter?.invoke(target)
		} catch (e: Exception) {
			// TODO
		}

		return null
	}
}