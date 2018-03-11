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

    /**
     * Constructor
     *
     * @param p1 Point 1
     * @param p2 Point 2
     * @param p3 Point 3
     *
     * @throws IllegalArgumentException if the points are collinear
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(ImmutableList.of(p1, p2, p3));

        if (Point.getThreePointOrientation(p1, p2, p3) == Point.Orientation.COLLINEAR) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Constructor
     *
     * @param pointsList List of points that makes up a triangle
     *
     * @throws IllegalArgumentException if the points are collinear
     * @throws IllegalArgumentException if pointsList contains any number of points other than 3
     */
    public Triangle(ImmutableList<Point> pointsList) {
        super(pointsList);
        if (Point.getThreePointOrientation(pointsList) == Point.Orientation.COLLINEAR) {
            throw new IllegalArgumentException();
        }
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
