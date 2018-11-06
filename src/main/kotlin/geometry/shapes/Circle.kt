package geometry.shapes

import geometry.primitives.Point
import geometry.primitives.Shape
import java.awt.Graphics2D

data class Circle(val center: Point, val radius: Double) : Shape {
    val circumference = 2 * Math.PI * radius
    val area = Math.PI * Math.pow(radius, 2.0)

    fun onCircle(point: Point): Boolean {
        val radialSegment = Segment(center, point)
        return radialSegment.length == radius
    }

    fun insideCircle(point: Point): Boolean {
        val radialSegment = Segment(center, point)
        return radialSegment.length < radius
    }

    fun outsideCircle(point: Point): Boolean {
        val radialSegment = Segment(center, point)
        return radialSegment.length > radius
    }

    override fun translateX(translation: Double): Shape {
        return copy(center = center.translateX(translation))
    }

    override fun translateY(translation: Double): Shape {
        return copy(center = center.translateY(translation))
    }

    override fun rotate(theta: Double): Shape {
        return copy(center = center.rotate(theta))
    }

    override fun rotateAround(theta: Double, aroundPoint: Point): Shape {
        return copy(center = center.rotateAround(theta, aroundPoint))
    }

    override fun render(graphics: Graphics2D) {
        graphics.drawArc(center.x.toInt(), center.y.toInt(), radius.toInt() * 2, radius.toInt() * 2, 0, 360)
    }

    override fun renderFilled(graphics: Graphics2D) {
        graphics.fillArc(center.x.toInt(), center.y.toInt(), radius.toInt() * 2, radius.toInt() * 2, 0, 360)
    }

    override fun scale(scaleFactor: Double): Shape {
        return copy(center = center.scale(scaleFactor), radius = radius * scaleFactor)
    }
}