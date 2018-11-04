package geometry.shapes

import geometry.primitives.Point
import geometry.primitives.UnitCircleAngle

data class Sector(val center: Point, val radius: Double, val startAngle: UnitCircleAngle, val endAngle: UnitCircleAngle) {
    init {
        if (startAngle > endAngle) throw IllegalArgumentException()
    }

    val theta = endAngle - startAngle

    val area: Double = 0.5 * Math.pow(radius, 2.0) * theta
}