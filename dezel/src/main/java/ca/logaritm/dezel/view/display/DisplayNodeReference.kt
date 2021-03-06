package ca.logaritm.dezel.view.display

import ca.logaritm.dezel.core.FinalizableReference
import ca.logaritm.dezel.core.Finalizer
import ca.logaritm.dezel.view.display.external.DisplayNodeExternal
import java.lang.ref.ReferenceQueue

/**
 * @class DisplayNodeReference
 * @super FinalizableReference
 * @since 0.1.0
 */
public class DisplayNodeReference(referent: DisplayNode, queue: ReferenceQueue<Any>): FinalizableReference<DisplayNode>(referent, queue) {

	companion object {

		/**
		 * @property refs
		 * @since 0.1.0
		 * @hidden
		 */
		private val refs: MutableSet<DisplayNodeReference> = mutableSetOf()

		/**
		 * @method register
		 * @since 0.1.0
		 */
		public fun register(referent: DisplayNode) {
			refs.add(DisplayNodeReference(referent, Finalizer.queue))
		}
	}

	/**
	 * @property handle
	 * @since 0.1.0
	 * @hidden
	 */
	private val handle = referent.handle

	/**
	 * @inherited
	 * @method finally
	 * @since 0.1.0
	 */
	override fun finally() {
		refs.remove(this)
		DisplayNodeExternal.delete(this.handle)
	}
}