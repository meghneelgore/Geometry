/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes;

/**
 * Interface defining the contract for shapes
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
public interface Shape {

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
     * @param shape The other shape
     *
     * @return true iff this shape overlaps the other
     */
    boolean overlaps(Shape shape);
}
