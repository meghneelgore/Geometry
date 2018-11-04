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
class Triangle : BasePolygon {

    /**
     * Area of the triangle
     */
    override val area: Double

    /**
     * Constructor
     *
     * @param p1 Point 1
     * @param p2 Point 2
     * @param p3 Point 3
     * @throws IllegalArgumentException if the points are collinear
     */
    constructor(p1: Point, p2: Point, p3: Point) : super(ImmutableList.of<Point>(p1, p2, p3)) {
        if (Point.getThreePointOrientation(p1, p2, p3) === Point.Orientation.COLLINEAR) {
            throw IllegalArgumentException("Cannot have a triangle with collinear points")
        }
        this.area = area()
    }

    /**
     * Constructor
     *
     * @param pointsList List of points that makes up a triangle
     * @throws IllegalArgumentException if the points are collinear
     * @throws IllegalArgumentException if pointsList contains any number of points other than 3
     */
    constructor(pointsList: ImmutableList<Point>) : super(pointsList) {
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
