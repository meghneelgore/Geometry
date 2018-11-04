/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes;

/**
 * Interface defining the contract for shapes
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
public interface Polygon {

    /**
     * Returns the perimeter of the shape
     *
     * @return Perimeter in units
     */
    double getPerimeter();

    /**
     * Returns the area of the shape
     *
     * @return Area in square units
     */
    double getArea();

    /**
     * Determines if this shape overlaps another
     *
     * @param polygon The other polygon
     * @return true iff this shape overlaps the other
     */
    boolean overlaps(Polygon polygon);

    /**
     * Returns the {@code PolygonType} of the polygon
     *
     * @return {@code PolygonType.CONVEX} if the polygon is convex, {@code PolygonType.CONCAVE} if the polygon is concave, {@code PolygonType.COMPLEX} otherwise
     */
    PolygonType getPolygonType();

    enum PolygonType {
        CONVEX,
        COCAVE,
        COMPLEX
    }
}
