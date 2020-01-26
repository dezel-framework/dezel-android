package ca.logaritm.dezel.view.trait

import android.view.View
import ca.logaritm.dezel.view.type.Overscroll
import ca.logaritm.dezel.view.type.Scrollbars

/**
 * @interface Scrollable
 * @since 0.1.0
 */
public interface Scrollable {

	/**
	 * The scroll listener.
	 * @property scrollableListener
	 * @since 0.1.0
	 */
	var scrollableListener: ScrollableListener?

	/**
	 * Whether the view is scrollable.
	 * @property scrollable
	 * @since 0.1.0
	 */
	var scrollable: Boolean

	/**
	 * The view's scrollbars mode.
	 * @property scrollbars
	 * @since 0.1.0
	 */
	var scrollbars: Scrollbars

	/**
	 * The view's overscroll mode.
	 * @property overscroll
	 * @since 0.1.0
	 */
	var overscroll: Overscroll

	/**
	 * The view's scroll top.
	 * @property scrollTop
	 * @since 0.1.0
	 */
	var scrollTop: Int

	/**
	 * The view's scroll left.
	 * @property scrollLeft
	 * @since 0.1.0
	 */
	var scrollLeft: Int

	/**
	 * The view's scroll width.
	 * @property scrollWidth
	 * @since 0.1.0
	 */
	var scrollWidth: Int

	/**
	 * The view's scroll height.
	 * @property scrollHeight
	 * @since 0.1.0
	 */
	var scrollHeight: Int

	/**
	 * The view's scroll inertia.
	 * @property scrollInertia
	 * @since 0.1.0
	 */
	var scrollInertia: Boolean

	/**
	 * The view's top content inset.
	 * @property contentInsetTop
	 * @since 0.1.0
	 */
	var contentInsetTop: Int

	/**
	 * The view's left content inset.
	 * @property contentInsetLeft
	 * @since 0.1.0
	 */
	var contentInsetLeft: Int

	/**
	 * The view's right content inset.
	 * @property contentInsetRight
	 * @since 0.1.0
	 */
	var contentInsetRight: Int

	/**
	 * The view's bottom content inset.
	 * @property contentInsetBottom
	 * @since 0.1.0
	 */
	var contentInsetBottom: Int

	/**
	 * Whether the view is paged.
	 * @property paged
	 * @since 0.1.0
	 */
	var paged: Boolean

	/**
	 * Whether the view is zoomable.
	 * @property zoomable
	 * @since 0.1.0
	 */
	var zoomable: Boolean

	/**
	 * The view's minimum zoom factor.
	 * @property minZoom
	 * @since 0.1.0
	 */
	var minZoom: Float

	/**
	 * The view's maximum zoom fator.
	 * @property maxZoom
	 * @since 0.1.0
	 */
	var maxZoom: Float

	/**
	 * The view's zoomed view.
	 * @property zoomedView
	 * @since 0.1.0
	 */
	var zoomedView: View?

	/**
	 * Smooth scrolls to the specified location.
	 * @method scrollTo
	 * @since 0.1.0
	 */
	fun scrollTo(x: Int, y: Int)
}