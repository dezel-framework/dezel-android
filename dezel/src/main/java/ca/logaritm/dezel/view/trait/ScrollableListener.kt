package ca.logaritm.dezel.view.trait

/**
 * @interface ScrollableListener
 * @since 0.1.0
 */
public interface ScrollableListener {

	/**
	 * Called when the view begins dragging.
	 * @method onDragStart
	 * @since 0.1.0
	 */
	fun onDragStart(scrollable: Scrollable)

	/**
	 * Called when the view finishes dragging.
	 * @method onDragEnd
	 * @since 0.1.0
	 */
	fun onDragEnd(scrollable: Scrollable)

	/**
	 * Called when the view is dragging.
	 * @method onDrag
	 * @since 0.1.0
	 */
	fun onDrag(scrollable: Scrollable)

	/**
	 * Called when the view begins scrolling.
	 * @method onScrollStart
	 * @since 0.1.0
	 */
	fun onScrollStart(scrollable: Scrollable)

	/**
	 * Called when the view finishes scrolling.
	 * @method onScrollEnd
	 * @since 0.1.0
	 */
	fun onScrollEnd(scrollable: Scrollable)

	/**
	 * Called when the view scrolls.
	 * @method onScroll
	 * @since 0.1.0
	 */
	fun onScroll(scrollable: Scrollable, top: Int, left: Int)

	/**
	 * Called when the view begins zooming.
	 * @method onZoomStart
	 * @since 0.1.0
	 */
	fun onZoomStart(scrollable: Scrollable)

	/**
	 * Called when the view finishes zooming.
	 * @method onZoomEnd
	 * @since 0.1.0
	 */
	fun onZoomEnd(scrollable: Scrollable, scale: Float)

	/**
	 * Called when the view zooms.
	 * @method onZoom
	 * @since 0.1.0
	 */
	fun onZoom(scrollable: Scrollable)
}
