package com.meghneelgore.geometry.shapes;

import com.google.common.collect.ImmutableList;
import com.meghneelgore.geometry.Point;

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

    @Override
    public boolean overlaps(Shape shape) {
        return false;
    }
}
