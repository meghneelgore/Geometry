/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.primitives;

import java.util.Objects;

import static com.meghneelgore.geometry.primitives.Point.Orientation.COLLINEAR;
import static com.meghneelgore.geometry.primitives.Point.getThreePointOrientation;
import static java.lang.Math.*;

/**
 * Class depicting a line segment
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
public class Segment {

    /**
     * Slope of the segment
     */
    private final double slope;

    /**
     * Length of the segment.
     */
    private final double length;

    private final Point p1;

    private final Point p2;

    /**
     * Constructor
     *
     * @param p1 First point making up the segment
     * @param p2 Second point making up the segment
     * @throws IllegalArgumentException if both points are the same
     */
    public Segment(Point p1, Point p2) {
        if (p1.equals(p2)) throw new IllegalArgumentException();
        this.p1 = p1;
        this.p2 = p2;

        if (!isVertical()) {
            this.slope = slope();
        } else {
            this.slope = Long.MIN_VALUE;
        }
        this.length = length();
    }

    /**
     * Returns the slope of the segment
     *
     * @return Slope
     *
     * @throws IllegalStateException if segment is vertical
     */
    public double getSlope() {
        if (!isVertical()) {
            return slope;
        }
        throw new IllegalStateException();
    }

    /**
     * Returns the length of the segment
     *
     * @return Length
     */
    public double getLength() {
        return length;
    }

    /**
     * Returns the length of the segment
     *
     * @return The length of the segment
     */
    double length() {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    /**
     * Calculates the slope of the segment.
     *
     * @return slope of the segment.
     *
     * @throws IllegalStateException if segment is vertical
     */
    double slope() {
        if (isVertical()) throw new IllegalStateException("Line segment is vertical with undefined slope");
        return getRise() / getRun();
    }

    /**
     * Determines if this segment is parallel to another one.
     *
     * @param s The other segment
     *
     * @return true if both this and s are parallel to each other. false otherwise.
     */
    public boolean isParallelTo(Segment s) {
        if (s == this) return true; //Minor optimization
        if (this.isVertical() && s.isVertical()) return true;
        if (this.isHorizontal() && s.isHorizontal()) return true;
        return this.slope() == s.slope();
    }

    /**
     * Determines if this segment is perpendicular to another one.
     *
     * @param s The other segment.
     *
     * @return true if both this and s are perpendicular to each other. false otherwise.
     */
    public boolean isPerpendicularTo(Segment s) {
        if (this.isVertical() && s.isHorizontal()) return true;
        if (this.isHorizontal() && s.isVertical()) return true;
        return this.slope() == -1 / s.slope();
    }

    /**
     * Determines whether this segment intersects another.
     *
     * @param s The other segment.
     *
     * @return true if this segment intersects the other. false otherwise.
     */
    public boolean intersectsWith(Segment s) {
        final Point r1 = s.p1;
        final Point r2 = s.p2;

        // Find the four orientations needed for general and
        // special cases
        Point.Orientation o1 = getThreePointOrientation(p1, p2, r1);
        Point.Orientation o2 = getThreePointOrientation(p1, p2, r2);
        Point.Orientation o3 = getThreePointOrientation(r1, r2, p1);
        Point.Orientation o4 = getThreePointOrientation(r1, r2, p2);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        // p1, p2 and r1 are collinear and r1 lies on segment p1p2
        if (o1 == COLLINEAR && onSegment(r1)) return true;

        // p1, p2 and r2 are collinear and r2 lies on segment p1p2
        if (o2 == COLLINEAR && onSegment(r2)) return true;

        // r1, r2 and p1 are collinear and p1 lies on segment r1r2
        if (o3 == COLLINEAR && s.onSegment(p1)) return true;

        // r1, r2 and p2 are collinear and p2 lies on segment r1r2
        if (o4 == COLLINEAR && s.onSegment(p2)) return true;

        return false; // Doesn't fall in any of the above cases
    }


    /**
     * Finds the minor angle between this segment and another
     *
     * @param s2 The other segment
     *
     * @return The minor angle in radians between the two segments
     */
    public double findAngleWith(Segment s2) {
        final Segment s1 = this;
        double theta1 = atan2(s1.p1.getY() - s1.p2.getY(),
                s1.p1.getX() - s1.p2.getX());
        double theta2 = atan2(s2.p1.getY() - s2.p2.getY(),
                s2.p1.getX() - s2.p2.getX());
        double diff = abs(theta1 - theta2);
        return min(diff, abs(Math.PI * 2 - diff));
    }

    /**
     * @return true if and only if the segment is vertical
     */
    boolean isVertical() {
        return getRun() == 0;
    }

    /**
     * @return true if and only if the segment is horizontal
     */
    boolean isHorizontal() {
        return getRise() == 0;
    }

    /**
     * @return The x-expanse of the segment
     */
    double getRun() {
        return p2.getX() - p1.getX();
    }

    /**
     * @return The y-expanse of the segment
     */
    double getRise() {
        return p2.getY() - p1.getY();
    }


    /**
     * Determines if a point lies on the segment
     *
     * @param q The point
     *
     * @return true if and only if point q lies on the segment
     */
    boolean onSegment(Point q) {
        final Point p = p1;
        final Point r = p2;

        if (q.getX() <= max(p.getX(), r.getX()) && q.getX() >= min(p.getX(), r.getX()) &&
                q.getY() <= max(p.getY(), r.getY()) && q.getY() >= min(p.getY(), r.getY()))
            return true;

        return false;
    }

    /**
     * @param obj Some other object
     *
     * @return true iff obj is of type Segment and the points of this segment and obj are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Segment) {
            Segment that = (Segment) obj;
            return this.p1.equals(that.p1) && this.p2.equals(that.p2);
        }
        return false;
    }

    /**
     * Calculates a hashcode for this object
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(p1, p2);
    }

    @Override
    public String toString() {
        return "[" + p1 + " - " + p2 + "]";
    }

    /**
     * Getter for P1
     *
     * @return P1
     */
    public Point getP1() {
        return p1;
    }

    /**
     * Getter for P2
     *
     * @return P2
     */
    public Point getP2() {
        return p2;
    }
}
