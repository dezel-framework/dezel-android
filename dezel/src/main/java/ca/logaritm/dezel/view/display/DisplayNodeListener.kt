package ca.logaritm.dezel.view.display

import android.util.SizeF
import ca.logaritm.dezel.core.JavaScriptProperty

/**
 * @protocol DisplayNodeListener
 * @since 0.1.0
 */
public interface DisplayNodeListener {

	/**
	 * Called when the display node gets invalidated.
	 * @method onInvalidate
	 * @since 0.1.0
	 */
	fun onInvalidate(node: DisplayNode)

	/**
	 * Called when the display node resolves its size.
	 * @method onResolveSize
	 * @since 0.1.0
	 */
	fun onResolveSize(node: DisplayNode)

	/**
	 * Called when the display node resolves its origin.
	 * @method onResolveOrigin
	 * @since 0.1.0
	 */
	fun onResolveOrigin(node: DisplayNode)

	/**
	 * Called when the display node resolves its inner size.
	 * @method onResolveInnerSize
	 * @since 0.1.0
	 */
	fun onResolveInnerSize(node: DisplayNode)

	/**
	 * Called when the display node resolves its content size.
	 * @method onResolveContentSize
	 * @since 0.1.0
	 */
	fun onResolveContentSize(node: DisplayNode)

	/**
	 * Called when the display node resolves its margin.
	 * @method onResolveMargins
	 * @since 0.1.0
	 */
	fun onResolveMargins(node: DisplayNode)

	/**
	 * Called when the display node resolves its border.
	 * @method onResolveBorders
	 * @since 0.1.0
	 */
	fun onResolveBorders(node: DisplayNode)

	/**
	 * Called when the display node resolves its padding.
	 * @method onResolvePadding
	 * @since 0.1.0
	 */
	fun onResolvePadding(node: DisplayNode)

	/**
	 * Called when the display node is about to resolve its layout.
	 * @method onPrepareLayout
	 * @since 0.1.0
	 */
	fun onPrepareLayout(node: DisplayNode)

	/**
	 * Called when the display node resolves its layout.
	 * @method onResolveLayout
	 * @since 0.1.0
	 */
	fun onResolveLayout(node: DisplayNode)

	/**
	 * Measures the display node's intrinsic size.
	 * @method measure
	 * @since 0.1.0
	 */
	fun measure(node: DisplayNode, bounds: SizeF, min: SizeF, max: SizeF): SizeF?

	/**
	 * Returns the property given by the specified name.
	 * @method resolve
	 * @since 0.1.0
	 */
	fun resolve(node: DisplayNode, property: String): JavaScriptProperty?
}
