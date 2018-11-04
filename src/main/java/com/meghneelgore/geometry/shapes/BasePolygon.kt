/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes

import com.google.common.collect.ImmutableList
import com.meghneelgore.geometry.primitives.Point
import com.meghneelgore.geometry.primitives.Segment
import com.meghneelgore.geometry.shapes.Polygon.PolygonType
import com.meghneelgore.geometry.shapes.Polygon.PolygonType.Concave
import com.meghneelgore.geometry.shapes.Polygon.PolygonType.Convex
import java.lang.Math.abs

/**
 * Abstract base class for Polygons.
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
abstract class BasePolygon
/**
 * Constructor.
 *
 * @param pointsList List of points making up the shape.
 */
protected constructor(pointsList: ImmutableList<Point>) : Polygon {

    /**
     * List of segments that make up the shape
     */
    override val segmentsList: ImmutableList<Segment> = makeSegments(pointsList)

    /**
     * Save the perimeter of the shape
     */
    override val perimeter: Double = findPerimeter(segmentsList)

    /**
     * Polygon type of the polygon. Possible types are Complex, Convex, or Concave
     */
    override val polygonType: PolygonType = findPolygonType(segmentsList)


    /**
     * Determines if this shape overlaps another
     *
     * @param polygon The other shape
     * @return true iff this shape overlaps the other
     */
    override fun overlaps(polygon: Polygon): Boolean {
        for (s1 in segmentsList) {
            for (s2 in polygon.segmentsList) {
                if (s1.intersectsWith(s2)) return true
            }
        }
        return false
    }

    /**
     * Creates all the segments that make up the shape
     *
     * @param pointsList List of points that make up the shape
     * @return List of Segments
     */
    fun makeSegments(pointsList: ImmutableList<Point>): ImmutableList<Segment> {
        if (pointsList.size <= 2) throw IllegalArgumentException()

        var i = 0
        val builder = ImmutableList.Builder<Segment>()
        while (i < pointsList.size - 1) {
            builder.add(Segment(pointsList[i], pointsList[i + 1]))
            i++
        }

        builder.add(Segment(pointsList[i], pointsList[0]))
        return builder.build()
    }

    /**
     * Finds the perimeter of the shape
     *
     * @param segmentsList List of segments that makes up the shape
     * @return Perimeter of the shape
     */
    fun findPerimeter(segmentsList: ImmutableList<Segment>): Double {
        var perimeter = 0.0
        for (s in segmentsList) {
            perimeter += s.length
        }
        return perimeter
    }

    /**
     * Finds whether the polygon is Convex or Concave
     */
    private fun findPolygonType(segmentsList: ImmutableList<Segment>): PolygonType {
        for (i in 0 until segmentsList.size - 1) {
            val segment1 = segmentsList[i]
            val segment2 = segmentsList[i + 1]
            if (abs(segment1.angleWith(segment2)) > Math.PI) return Concave
        }
        val segment1 = segmentsList[segmentsList.size - 1]
        val segment2 = segmentsList[0]
        return if (abs(segment1.angleWith(segment2)) > Math.PI) Concave else Convex
    }
}
