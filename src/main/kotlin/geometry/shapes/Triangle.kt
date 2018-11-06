/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package geometry.shapes

import com.google.common.collect.ImmutableList
import geometry.primitives.Point
import geometry.primitives.Polygon

/**
 * Class depicting a triangle.
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
data class Triangle(override val pointsList: ImmutableList<Point>) : BasePolygon(pointsList) {

    /**
     * Calculates area of the triangle using Heron's formula.
     * The formula is given as Area = Math.sqrt(p * (p − a) * (p − b) *	(p − c))
     *
     * Where p is half the perimeter or (a + b + c) / 2
     *
     * @return Area of triangle in sq units
     */
    override val area: Double
        get() {
            val halfPerimeter = perimeter / 2
            var productOfDifferences = 1.0
            for (s in segmentsList) {
                productOfDifferences *= halfPerimeter - s.length
            }
            productOfDifferences *= halfPerimeter
            return Math.sqrt(productOfDifferences)
        }

    init {
        if (pointsList.size != 3) throw IllegalArgumentException()
        if (Point.getThreePointOrientation(pointsList) === Point.Orientation.COLLINEAR) throw IllegalArgumentException()
    }

    /**
     * Creates a copy of Triangle with new points
     */
    override fun copyPolygon(pointsList: ImmutableList<Point>): Polygon {
        return copy(pointsList = pointsList)
    }
}
