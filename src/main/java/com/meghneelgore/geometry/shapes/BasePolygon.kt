/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes

import com.google.common.collect.ImmutableList
import com.meghneelgore.geometry.primitives.Point
import com.meghneelgore.geometry.primitives.Segment
import com.meghneelgore.geometry.shapes.Polygon.PolygonType
import com.meghneelgore.geometry.shapes.Polygon.PolygonType.Convex

/**
 * Abstract base class for shapes.
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
     * List of points that make up the shape
     */
    protected val pointsList: ImmutableList<Point>


    /**
     * List of segments that make up the shape
     */
    override val segmentsList: ImmutableList<Segment>

    /**
     * Save the perimeter of the shape
     */
    /**
     * Returns the perimeter of the shape
     *
     * @return the perimeter
     */
    override val perimeter: Double

    /**
     * Polygon type of the polygon. Possible types are Complex, Convex, or Concave
     */
    override val polygonType: PolygonType

    init {
        this.pointsList = pointsList
        this.segmentsList = makeSegments(pointsList)
        this.perimeter = findPerimeter(segmentsList)
        this.polygonType = findPolygonType(segmentsList)
    }

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
        if (pointsList.size <= 1) throw IllegalArgumentException()

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

    internal fun findPolygonType(segmentsList: ImmutableList<Segment>): PolygonType {

        return Convex

    }
}
