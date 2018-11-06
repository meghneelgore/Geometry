/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package geometry.shapes

import com.google.common.collect.ImmutableList
import geometry.primitives.Point
import geometry.primitives.Polygon
import geometry.primitives.Polygon.PolygonType
import geometry.primitives.Polygon.PolygonType.Concave
import geometry.primitives.Polygon.PolygonType.Convex
import java.awt.Graphics2D
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

    private val xCoordinates = IntArray(pointsList.size)

    private val yCoordinates = IntArray(pointsList.size)
    /**
     * List of segments that make up the shape
     */
    final override val segmentsList: ImmutableList<Segment> = makeSegments(pointsList)

    /**
     * Save the perimeter of the shape
     */
    final override val perimeter: Double = findPerimeter(segmentsList)

    /**
     * Polygon type of the polygon. Possible types are Complex, Convex, or Concave
     */
    final override val polygonType: PolygonType = findPolygonType(segmentsList)

    init {
        var i = 0
        pointsList.forEach { point ->
            xCoordinates[i] = point.x.toInt()
            yCoordinates[i] = point.y.toInt()
            i++
        }
    }

    /**
     * Returns a copy of this polygon that is translated in the x direction.
     */
    override fun translateX(translation: Double): Polygon {
        val builder = ImmutableList.builder<Point>()
        pointsList.forEach { point -> builder.add(point.translateX(translation)) }
        return this.copyPolygon(builder.build())
    }

    /**
     * Returns a copy of this polygon that is translated in the y direction.
     */
    override fun translateY(translation: Double): Polygon {
        val builder = ImmutableList.builder<Point>()
        pointsList.forEach { point -> builder.add(point.translateY(translation)) }
        return copyPolygon(builder.build())
    }

    /**
     * Returns a copy of this polygon that is rotated by angle theta.
     */
    override fun rotate(theta: Double): Polygon {
        val builder = ImmutableList.builder<Point>()
        pointsList.forEach { point -> builder.add(point.rotate(theta)) }
        return copyPolygon(builder.build())
    }

    /**
     * Returns a copy of this polygon that is rotated by angle theta around a given point
     */
    override fun rotateAround(theta: Double, aroundPoint: Point): Polygon {
        val builder = ImmutableList.builder<Point>()
        pointsList.forEach { point -> builder.add(point.rotateAround(theta, aroundPoint)) }
        return copyPolygon(builder.build())
    }

    /**
     * Creates a copy of the polygon with a new points list
     */
    abstract fun copyPolygon(pointsList: ImmutableList<Point>): Polygon


    /**
     * Determines if this shape overlaps another
     *
     * @param polygon The other shape
     * @return true iff this shape overlaps the other
     */
    override fun overlaps(polygon: Polygon): Boolean {
        segmentsList.forEach { s1 -> polygon.segmentsList.forEach { s2 -> if (s1.intersectsWith(s2)) return true } }
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

    override fun render(graphics: Graphics2D) {
        segmentsList.forEach { segment -> segment.render(graphics) }
    }

    override fun renderFilled(graphics: Graphics2D) {
        graphics.fillPolygon(xCoordinates, yCoordinates, pointsList.size)
    }
}
