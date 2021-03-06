package ca.logaritm.dezel.extension.graphics

import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.PointF
import android.graphics.RectF
import kotlin.math.*

private val measure: PathMeasure = PathMeasure()

/**
 * @method Path.getLastPoint()
 * @since 0.1.0
 * @hidden
 */
internal fun Path.getLastPoint(): PointF {

	val coordinates = floatArrayOf(0f, 0f)

	measure.setPath(this, false)
	measure.getPosTan(measure.length, coordinates, null)

	return PointF(
		round(coordinates[0] * 100) / 100f,
		round(coordinates[1] * 100) / 100f
	)
}

/**
 * @method Path.addArcTo
 * @since 0.1.0
 * @hidden
 */
internal fun Path.addArcTo(tangent1End: PointF, tangent2End: PointF, radius: Float) {

	val last = this.getLastPoint()
	val x0 = last.x.toDouble()
	val y0 = last.y.toDouble()
	val x1 = tangent1End.x.toDouble()
	val y1 = tangent1End.y.toDouble()
	val x2 = tangent2End.x.toDouble()
	val y2 = tangent2End.y.toDouble()
	val r = radius.toDouble()

	if (isColinear(x0, y0, x1, y1, x2, y2)) {
		this.lineTo(x1.toFloat(), y1.toFloat())
		return
	}

	val ccw = isCCW(x0, y0, x1, y1, x2, y2)
	val firstAngle = getAngle(x0, y0, x1, y1)
	val medianAngle = getAngle(x0, y0, x1, y1, x2, y2) / 2

	val center = getCenter(firstAngle, x1, y1, r, medianAngle, ccw)
	val contact = getTanContactCoords(x1, y1, firstAngle, medianAngle, r)

	val startAngle = getStartAngle(contact[0], contact[1], center[0], center[1])
	val sweepAngle = getSweepAngle(medianAngle, ccw)

	val rect = RectF(
		(center[0] - r).toFloat(),
		(center[1] - r).toFloat(),
		(center[0] + r).toFloat(),
		(center[1] + r).toFloat()
	)

	this.lineTo(
		contact[0].toFloat(),
		contact[1].toFloat()
	)

	this.addArc(rect, startAngle.toFloat(), sweepAngle.toFloat())
}

/**
 * @method Path.addOuterRoundedRect
 * @since 0.1.0
 * @hidden
 */
internal fun Path.addOuterRoundedRect(w: Float, h: Float, radiusTL: Float, radiusTR: Float, radiusBL: Float, radiusBR: Float) {

	val rect = RectF(0f, 0f, w, h)

	if (radiusTL == 0f && radiusTR == 0f &&
		radiusBL == 0f && radiusBR == 0f) {

		this.addRect(rect, Path.Direction.CW)

	} else {

		this.addRoundRect(rect, floatArrayOf(
			radiusTL, radiusTL,
			radiusTR, radiusTR,
			radiusBR, radiusBR,
			radiusBL, radiusBL
		), Path.Direction.CW)

	}
}

/**
 * @method Path.addInnerRoundedRect
 * @since 0.1.0
 * @hidden
 */
internal fun Path.addInnerRoundedRect(w: Float, h: Float, radiusTL: PointF, radiusTR: PointF, radiusBL: PointF, radiusBR: PointF, borderT: Float, borderL: Float, borderR: Float, borderB: Float) {

	val rect = RectF(
		borderL,
		borderT,
		borderL + w - borderL - borderR,
		borderT + h - borderT - borderB
	)

	if (radiusTL.equals(0f, 0f) && radiusTR.equals(0f, 0f) &&
		radiusBL.equals(0f, 0f) && radiusBR.equals(0f, 0f)) {

		this.addRect(rect, Path.Direction.CW)

	} else {

		this.addRoundRect(rect, floatArrayOf(
			radiusTL.x, radiusTL.y,
			radiusTR.x, radiusTR.y,
			radiusBR.x, radiusBR.y,
			radiusBL.x, radiusBL.y
		), Path.Direction.CW)

	}
}

/**
 * @function getTanContactCoords
 * @since 0.1.0
 * @hidden
 */
internal fun getTanContactCoords(x: Double, y: Double, angle: Double, medianAngle: Double, r: Double): DoubleArray {

	val adjacent = r / tan(Math.toRadians(medianAngle))

	val coords = doubleArrayOf(0.0, 0.0)
	coords[0] = adjacent * cos(Math.toRadians(angle)) + x
	coords[1] = adjacent * sin(Math.PI * 2 - Math.toRadians(angle)) + y

	return coords
}

/**
 * @function getCenter
 * @since 0.1.0
 * @hidden
 */
private fun getCenter(angle: Double, x1: Double, y1: Double, r: Double, medianAngle: Double, ccw: Boolean): DoubleArray {

	val hyp = r / sin(Math.toRadians(medianAngle))
	val rad = if (ccw) Math.toRadians(angle - medianAngle) else Math.toRadians(angle + medianAngle)

	val coords = doubleArrayOf(0.0, 0.0)
	coords[0] = hyp * cos(rad) + x1
	coords[1] = hyp * sin(Math.PI * 2 - rad) + y1

	return coords
}

/**
 * @function getAngle
 * @since 0.1.0
 * @hidden
 */
private fun getAngle(x0: Double, y0: Double, x1: Double, y1: Double): Double {

	val deltaX = x0 - x1
	val deltaY = y0 - y1

	var angle = Math.toDegrees(atan2(deltaY, deltaX))

	if (angle > 0) {
		angle = 360 - angle
	} else if (angle < 0) {
		angle = abs(angle)
	}

	return angle
}

/**
 * @function getAngle
 * @since 0.1.0
 * @hidden
 */
private fun getAngle(x0: Double, y0: Double, x1: Double, y1: Double, x2: Double, y2: Double): Double {

	val a = sqrt((x1 - x0).pow(2.0) + (y1 - y0).pow(2.0))
	val b = sqrt((x1 - x2).pow(2.0) + (y1 - y2).pow(2.0))
	val c = sqrt((x0 - x2).pow(2.0) + (y0 - y2).pow(2.0))

	// angle between P0-P1 and P1-P2
	return Math.toDegrees(acos((a.pow(2.0) + b.pow(2.0) - c.pow(2.0)) / (2.0 * a * b)))
}

/**
 * @function getStartAngle
 * @since 0.1.0
 * @hidden
 */
private fun getStartAngle(x0: Double, y0: Double, x1: Double, y1: Double): Double {
	return 360 - getAngle(x0, y0, x1, y1)
}

/**
 * @function getSweepAngle
 * @since 0.1.0
 * @hidden
 */
private fun getSweepAngle(angle: Double, turnsLeft: Boolean): Double {

	var a = (90 - angle) * 2

	if (turnsLeft) {
		a = -a
	}

	return a
}

/**
 * @function isCCW
 * @since 0.1.0
 * @hidden
 */
fun isCCW(x0: Double, y0: Double, x1: Double, y1: Double, x2: Double, y2: Double): Boolean {
	return cross(x0, y0, x1, y1, x2, y2) > 0
}

/**
 * @function isColinear
 * @since 0.1.0
 * @hidden
 */
private fun isColinear(x0: Double, y0: Double, x1: Double, y1: Double, x2: Double, y2: Double): Boolean {
	return cross(x0, y0, x1, y1, x2, y2) == 0.0
}

/**
 * @function cross
 * @since 0.1.0
 * @hidden
 */
private fun cross(x0: Double, y0: Double, x1: Double, y1: Double, x2: Double, y2: Double): Double {
	return (x0 - x1) * (y2 - y1) - (y0 - y1) * (x2 - x1)
}