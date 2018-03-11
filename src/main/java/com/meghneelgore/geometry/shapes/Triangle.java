/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes;

import com.google.common.collect.ImmutableList;
import com.meghneelgore.geometry.Point;

/**
 * Class depicting a triangle.
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
public class Triangle extends BaseShape {

    public Triangle(Point p1, Point p2, Point p3) {
        super(ImmutableList.of(p1, p2, p3));

        if (Point.getThreePointOrientation(p1, p2, p3) == Point.Orientation.COLLINEAR) {
            throw new IllegalArgumentException();
        }
    }


    public Triangle(ImmutableList<Point> pointsList) {
        super(pointsList);
    }

    /**
     * Determines if this shape overlaps another
     *
     * @param shape The other shape
     *
     * @return true iff this shape overlaps the other
     */
    @Override
    public boolean overlaps(Shape shape) {
        return false;
    }
}
