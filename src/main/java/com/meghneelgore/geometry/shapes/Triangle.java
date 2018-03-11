/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes;

import com.google.common.collect.ImmutableList;
import com.meghneelgore.geometry.primitives.Point;
import com.meghneelgore.geometry.primitives.Segment;

/**
 * Class depicting a triangle.
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
public class Triangle extends BaseShape {

    /**
     * Area of the triangle
     */
    private final double area;

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
        this.area = area();
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
        this.area = area();
    }

    /**
     * Returns the area of the triangle
     *
     * @return Area
     */
    @Override
    public double getArea() {
        return area;
    }


    /**
     * Calculates area of the triangle using Heron's formula.
     * <p>
     * The formula is given as Area	= Math.sqrt(p * (p − a) * (p − b) *	(p − c))
     * <p>
     * Where p is half the perimeter or (a + b + c) / 2
     *
     * @return Area of triangle in sq units
     */
    double area() {
        final double halfPerimeter = perimeter / 2;
        double productOfDifferences = 1;
        for (Segment s : segmentsList) {
            productOfDifferences *= halfPerimeter - s.getLength();
        }
        productOfDifferences *= halfPerimeter;
        return Math.sqrt(productOfDifferences);
    }
}
