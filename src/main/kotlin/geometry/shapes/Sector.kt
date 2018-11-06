package geometry.shapes

import geometry.primitives.Point
import geometry.primitives.Shape
import geometry.primitives.UnitCircleAngle
import java.awt.Graphics2D

data class Sector(val center: Point, val radius: Double, val startAngle: UnitCircleAngle, val endAngle: UnitCircleAngle) : Shape {
    init {
        if (startAngle > endAngle) throw IllegalArgumentException()
    }

    val theta = endAngle - startAngle

    val area = 0.5 * Math.pow(radius, 2.0) * theta

    val arcLength = radius * theta

    val perimeter = 2 * radius + arcLength

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
        graphics.drawArc(center.x.toInt(), center.y.toInt(), radius.toInt() * 2, radius.toInt() * 2, startAngle.toDegrees.toInt(), endAngle.toDegrees.toInt())
    }

    override fun renderFilled(graphics: Graphics2D) {
        graphics.fillArc(center.x.toInt(), center.y.toInt(), radius.toInt() * 2, radius.toInt() * 2, startAngle.toDegrees.toInt(), endAngle.toDegrees.toInt())
    }
}