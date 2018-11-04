/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package geometry.shapes

import com.google.common.collect.ImmutableList
import geometry.primitives.Point
import geometry.primitives.Segment

/**
 * Interface defining the contract for shapes
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
interface Polygon {

    /**
     * The list of points making up the polygon in an anticlockwise direction
     */
    val pointsList: ImmutableList<Point>

    /**
     * The list of segments making up the polygon
     */
    val segmentsList: ImmutableList<Segment>

    /**
     * Returns the perimeter of the shape
     *
     * @return Perimeter in units
     */
    val perimeter: Double

    /**
     * Returns the area of the shape
     *
     * @return Area in square units
     */
    val area: Double

    /**
     * Returns the `PolygonType` of the polygon
     *
     * @return `PolygonType.Convex` if the polygon is convex, `PolygonType.CONCAVE` if the polygon is concave, `PolygonType.Complex` otherwise
     */
    val polygonType: PolygonType

    /**
     * Determines if this shape overlaps another
     *
     * @param polygon The other polygon
     * @return true iff this shape overlaps the other
     */
    fun overlaps(polygon: Polygon): Boolean

    /**
     * Translates the current polygon and returns a new Polygon translated in the x direction
     */
    fun translateX(translation: Double): Polygon

    /**
     * Translates the current polygon and returns a new Polygon translated in the y direction
     */
    fun translateY(translation: Double): Polygon


    enum class PolygonType {
        Convex,
        Concave,
        Complex
    }
}
