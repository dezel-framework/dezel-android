package ca.logaritm.dezel.view.type

import ca.logaritm.dezel.core.JavaScriptProperty
import ca.logaritm.dezel.core.JavaScriptPropertyType

/**
 * @enum Scrollbars
 * @since 0.1.0
 */
public enum class Scrollbars {

	NONE,
	BOTH,
	VERTICAL,
	HORIZONTAL;

	companion object {

		/**
		 * @method get
		 * @since 0.1.0
		 */
		public fun get(value: JavaScriptProperty): Scrollbars {
			return when (value.type) {
				JavaScriptPropertyType.NUMBER  -> this.get(value.number)
				JavaScriptPropertyType.STRING  -> this.get(value.string)
				JavaScriptPropertyType.BOOLEAN -> this.get(value.boolean)
				else                           -> Scrollbars.NONE
			}
		}

		/**
		 * @method get
		 * @since 0.1.0
		 */
		public fun get(value: String): Scrollbars {
			return when (value) {
				"none"       -> Scrollbars.NONE
				"both"       -> Scrollbars.BOTH
				"vertical"   -> Scrollbars.VERTICAL
				"horizontal" -> Scrollbars.HORIZONTAL
				else         -> Scrollbars.NONE
			}
		}

		/**
		 * @method get
		 * @since 0.1.0
		 */
		public fun get(value: Double): Scrollbars {
			return if (value == 0.0) Scrollbars.NONE else Scrollbars.BOTH
		}

		/**
		 * @method get
		 * @since 0.1.0
		 */
		public fun get(value: Boolean): Scrollbars {
			return if (value == true)  Scrollbars.BOTH else Scrollbars.NONE
		}
	}
}