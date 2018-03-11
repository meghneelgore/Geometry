/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.primitives;

import java.util.List;

import static com.meghneelgore.geometry.primitives.Point.Orientation.*;

/**
 * Class depicting a point in 2-d space.
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
public class Point {
    /**
     * x-value
     */
    private final double x;
    /**
     * y-value
     */
    private final double y;

    /**
     * Constructor.
     *
     * @param x x-value
     * @param y y-value
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gives the three-point-orientation of three points. Three points on a 2-D plane can have three possible
     * orientations, viz., clockwise ↻, counter-clockwise ↺, or collinear.
     *
     * @param p Point 1
     * @param q Point 2
     * @param r Point 3
     *
     * @return {@code CLOCKWISE}, {@code COUNTER_CLOCKWISE}, or {@code COLLINEAR} depending on the orientation
     */
    public static Orientation getThreePointOrientation(Point p, Point q, Point r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0) return COLLINEAR;
        if (val > 0) {
            return CLOCKWISE;
        }
        return COUNTER_CLOCKWISE;
    }

    /**
     * Convenience method for three-point orientation
     *
     * @param pointList List of points whose orientation is to be determined
     *
     * @return Orientation
     */
    public static Orientation getThreePointOrientation(List<Point> pointList) {
        if (pointList.size() != 3) throw new IllegalArgumentException();
        Point p = pointList.get(0);
        Point q = pointList.get(1);
        Point r = pointList.get(2);
        return getThreePointOrientation(p, q, r);
    }

    /**
     * Return this point's x-value
     *
     * @return x-value
     */
    public double getX() {
        return x;
    }

    /**
     * Return this point's y-value
     *
     * @return y-value
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the distance of this point from the origin (0, 0)
     *
     * @return The calculated distance
     */
    public double getDistanceFromOrigin() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * toString method
     *
     * @return String representation of the point
     */
    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    /**
     * Returns if the current object is equal to another point depicted by obj
     *
     * @param obj The other object
     *
     * @return true if both are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point p = (Point) obj;
            return this.x == p.x && this.y == p.y;
        }
        return false;
    }

    /**
     * Returns the hashcode for the point
     *
     * @return Hashcode for this point
     */
    @Override
    public int hashCode() {
        return Double.hashCode(x * y);
    }

    /**
     * Orientation values
     */
    public enum Orientation {
        COLLINEAR,
        CLOCKWISE,
        COUNTER_CLOCKWISE;
    }
}
