/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes

import com.google.common.collect.ImmutableList
import com.meghneelgore.geometry.primitives.Segment

/**
 * Interface defining the contract for shapes
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
interface Polygon {

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

    enum class PolygonType {
        Convex,
        Concave,
        Complex
    }
}
