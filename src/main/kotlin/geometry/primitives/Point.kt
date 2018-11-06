/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package geometry.primitives

import geometry.primitives.Point.Orientation.*

/**
 * Class depicting a point in 2-d space.
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
data class Point(val x: Double, val y: Double) {

    /**
     * Gets the distance of this point from the origin (0, 0)
     *
     * @return The calculated distance
     */
    val distanceFromOrigin: Double = Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0))

    /**
     * Creates a new point translated in the x direction
     */
    fun translateX(translation: Double): Point {
        return copy(x = this.x + translation)
    }

    /**
     * Creates a new point translated in the y direction
     */
    fun translateY(translation: Double): Point {
        return copy(y = this.y + translation)
    }

    /**
     * Creates a new point rotated around the origin by angle theta
     */
    fun rotate(theta: Double): Point {
        return copy(x = x * Math.cos(theta) - this.y * Math.sin(theta), y = y * Math.cos(theta) + x * Math.sin(theta))
    }

    /**
     * Creates a new point rotated around the given point
     */
    fun rotateAround(theta: Double, aroundPoint: Point): Point {
        return translateX(-aroundPoint.x).translateY(-aroundPoint.y).rotate(theta).translateX(aroundPoint.x).translateY(aroundPoint.y)
    }

    fun scale(scaleFactor: Double): Point {
        return copy(x = x * scaleFactor, y = y * scaleFactor)
    }

    /**
     * toString method
     *
     * @return String representation of the point
     */
    override fun toString(): String {
        return "[$x, $y]"
    }

    /**
     * Orientation values
     */
    enum class Orientation {
        COLLINEAR,
        CLOCKWISE,
        COUNTER_CLOCKWISE
    }

    companion object {

        /**
         * Gives the three-point-orientation of three points. Three points on a 2-D plane can have three possible
         * orientations, viz., clockwise ↻, counter-clockwise ↺, or collinear.
         *
         * @param p Point 1
         * @param q Point 2
         * @param r Point 3
         *
         * @return `CLOCKWISE`, `COUNTER_CLOCKWISE`, or `COLLINEAR` depending on the orientation
         */
        fun getThreePointOrientation(p: Point, q: Point, r: Point): Orientation {
            val `val` = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y)

            if (`val` == 0.0) return COLLINEAR
            return if (`val` > 0) {
                CLOCKWISE
            } else COUNTER_CLOCKWISE
        }

        /**
         * Convenience method for three-point orientation
         *
         * @param pointList List of points whose orientation is to be determined
         *
         * @return Orientation
         */
        fun getThreePointOrientation(pointList: List<Point>): Orientation {
            if (pointList.size != 3) throw IllegalArgumentException()
            val p = pointList[0]
            val q = pointList[1]
            val r = pointList[2]
            return getThreePointOrientation(p, q, r)
        }
    }
}
