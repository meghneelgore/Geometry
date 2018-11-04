/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes

import com.google.common.collect.ImmutableList
import com.meghneelgore.geometry.primitives.Point

/**
 * Class depicting a triangle.
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
data class Triangle(val pointsList: ImmutableList<Point>) : BasePolygon(pointsList) {

    /**
     * Area of the triangle
     */
    override val area: Double

    init {
        if (pointsList.size != 3) throw IllegalArgumentException()
        if (Point.getThreePointOrientation(pointsList) === Point.Orientation.COLLINEAR) throw IllegalArgumentException()
        this.area = area()
    }

    /**
     * Calculates area of the triangle using Heron's formula.
     * The formula is given as Area	= Math.sqrt(p * (p − a) * (p − b) *	(p − c))
     *
     * Where p is half the perimeter or (a + b + c) / 2
     *
     * @return Area of triangle in sq units
     */
    private fun area(): Double {
        val halfPerimeter = perimeter / 2
        var productOfDifferences = 1.0
        for (s in segmentsList) {
            productOfDifferences *= halfPerimeter - s.length
        }
        productOfDifferences *= halfPerimeter
        return Math.sqrt(productOfDifferences)
    }
}
