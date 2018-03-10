package com.meghneelgore.geometry;

/**
 * Class depicting a point in 2-d space
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
}
