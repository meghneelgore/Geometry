package geometry.primitives

import java.lang.Math.abs

data class UnitCircleAngle(private val angle: Double) {
    operator fun compareTo(other: UnitCircleAngle): Int {
        return this.reducedAngle.compareTo(other.reducedAngle)
    }

    operator fun minus(other: UnitCircleAngle): Double {
        return this.reducedAngle - other.reducedAngle
    }

    val reducedAngle: Double
        get() = if (abs(angle) >= 2 * Math.PI) {
            val sign = angle >= 0
            var absAngle = abs(angle)
            while (absAngle >= 2 * Math.PI) {
                absAngle -= 2 * Math.PI
            }
            if (!sign) -absAngle else absAngle
        } else {
            angle
        }
}