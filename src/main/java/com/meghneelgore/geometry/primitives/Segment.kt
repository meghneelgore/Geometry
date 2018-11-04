/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.primitives

import com.meghneelgore.geometry.primitives.Point.Companion.getThreePointOrientation
import com.meghneelgore.geometry.primitives.Point.Orientation.COLLINEAR
import java.lang.Math.*
import java.util.*

/**
 * Class depicting a line segment
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
class Segment(val p1: Point, val p2: Point) {

    /**
     * Slope of the segment
     */
    private val slope: Double

    /**
     * Length of the segment.
     */
    val length: Double = Math.sqrt(Math.pow(p1.x - p2.x, 2.0) + Math.pow(p1.y - p2.y, 2.0))

    /**
     * @return The x-expanse of the segment
     */
    internal val run: Double = p2.x - p1.x


    /**
     * @return The y-expanse of the segment
     */
    internal val rise: Double = p2.y - p1.y

    /**
     * @return true if and only if the segment is vertical
     */
    internal val isVertical: Boolean = run == 0.0

    /**
     * @return true if and only if the segment is horizontal
     */
    internal val isHorizontal: Boolean = rise == 0.0


    init {
        if (p1 == p2) throw IllegalArgumentException()
        if (!isVertical) {
            this.slope = slope()
        } else {
            this.slope = java.lang.Long.MIN_VALUE.toDouble()
        }
    }

    /**
     * Returns the slope of the segment
     *
     * @return Slope
     *
     * @throws IllegalStateException if segment is vertical
     */
    fun getSlope(): Double {
        if (!isVertical) {
            return slope
        }
        throw IllegalStateException()
    }

    /**
     * Calculates the slope of the segment.
     *
     * @return slope of the segment.
     *
     * @throws IllegalStateException if segment is vertical
     */
    internal fun slope(): Double {
        if (isVertical) throw IllegalStateException("Line segment is vertical with undefined slope")
        return rise / run
    }

    /**
     * Determines if this segment is parallel to another one.
     *
     * @param s The other segment
     *
     * @return true if both this and s are parallel to each other. false otherwise.
     */
    fun isParallelTo(s: Segment): Boolean {
        if (s === this) return true //Minor optimization
        if (this.isVertical && s.isVertical) return true
        return if (this.isHorizontal && s.isHorizontal) true else this.slope() == s.slope()
    }

    /**
     * Determines if this segment is perpendicular to another one.
     *
     * @param s The other segment.
     *
     * @return true if both this and s are perpendicular to each other. false otherwise.
     */
    fun isPerpendicularTo(s: Segment): Boolean {
        if (this.isVertical && s.isHorizontal) return true
        return if (this.isHorizontal && s.isVertical) true else this.slope() == -1 / s.slope()
    }

    /**
     * Determines whether this segment intersects another.
     *
     * @param segment The other segment.
     *
     * @return true if this segment intersects the other. false otherwise.
     */
    fun intersectsWith(segment: Segment): Boolean {
        val r1 = segment.p1
        val r2 = segment.p2

        // Find the four orientations needed for general and
        // special cases
        val o1 = getThreePointOrientation(p1, p2, r1)
        val o2 = getThreePointOrientation(p1, p2, r2)
        val o3 = getThreePointOrientation(r1, r2, p1)
        val o4 = getThreePointOrientation(r1, r2, p2)

        // General case
        if (o1 != o2 && o3 != o4)
            return true

        // Special Cases
        // p1, p2 and r1 are collinear and r1 lies on segment p1p2
        if (o1 == COLLINEAR && onSegment(r1)) return true

        // p1, p2 and r2 are collinear and r2 lies on segment p1p2
        if (o2 == COLLINEAR && onSegment(r2)) return true

        // r1, r2 and p1 are collinear and p1 lies on segment r1r2
        if (o3 == COLLINEAR && segment.onSegment(p1)) return true

        // r1, r2 and p2 are collinear and p2 lies on segment r1r2
        return if (o4 == COLLINEAR && segment.onSegment(p2)) true else false

    }


    /**
     * Finds the minor angle between this segment and another
     *
     * @param s2 The other segment
     *
     * @return The minor angle in radians between the two segments
     */
    fun angleWith(s2: Segment): Double {
        val diff = abs(anticlockwiseAngleWith(s2))
        return min(diff, abs(Math.PI * 2 - diff))
    }

    /**
     * Finds the anticlockwise angle between this and another Segment
     *
     * @param segment2 The other segment
     *
     * @return The anticlockwise angle in radians
     */
    fun anticlockwiseAngleWith(segment2: Segment): Double {
        val segment1 = this
        val theta1 = atan2(segment1.p1.y - segment1.p2.y, segment1.p1.x - segment1.p2.x)
        val theta2 = atan2(segment2.p1.y - segment2.p2.y, segment2.p1.x - segment2.p2.x)
        return theta1 - theta2
    }

    /**
     * Determines if a point lies on the segment
     *
     * @param q The point
     *
     * @return true if and only if point q lies on the segment
     */
    internal fun onSegment(q: Point): Boolean {
        val p = p1
        val r = p2

        return q.x <= max(p.x, r.x) && q.x >= min(p.x, r.x) &&
                q.y <= max(p.y, r.y) && q.y >= min(p.y, r.y)

    }

    /**
     * @param other Some other object
     *
     * @return true iff other is of type Segment and the points of this segment and other are equal
     */
    override fun equals(other: Any?): Boolean {
        if (other is Segment) {
            val that = other as Segment?
            return this.p1 == that!!.p1 && this.p2 == that.p2
        }
        return false
    }

    /**
     * Calculates a hashcode for this object
     *
     * @return hashcode
     */
    override fun hashCode(): Int {
        return Objects.hash(p1, p2)
    }

    override fun toString(): String {
        return "[$p1 - $p2]"
    }
}
