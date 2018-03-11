/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes;

import com.google.common.collect.ImmutableList;
import com.meghneelgore.geometry.Point;

import static com.meghneelgore.geometry.Point.Orientation.COLLINEAR;
import static com.meghneelgore.geometry.Point.getThreePointOrientation;
import static java.lang.Math.*;

/**
 * Class depicting a line segment
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
public class Segment extends BaseShape {

    /**
     * Constructor
     *
     * @param p1 First point making up the segment
     * @param p2 Second point making up the segment
     */
    public Segment(Point p1, Point p2) {
        super(ImmutableList.of(p1, p2));
        if (p1.equals(p2)) throw new IllegalArgumentException();
    }

    /**
     * Returns the length of the segment
     *
     * @return The length of the segment
     */
    public double length() {
        Point p1 = pointsList.get(0);
        Point p2 = pointsList.get(1);
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
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
        final Point p1 = this.pointsList.get(0);
        final Point q1 = this.pointsList.get(1);
        final Point p2 = s.pointsList.get(0);
        final Point q2 = s.pointsList.get(1);

        // Find the four orientations needed for general and
        // special cases
        Point.Orientation o1 = getThreePointOrientation(p1, q1, p2);
        Point.Orientation o2 = getThreePointOrientation(p1, q1, q2);
        Point.Orientation o3 = getThreePointOrientation(p2, q2, p1);
        Point.Orientation o4 = getThreePointOrientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        // p1, q1 and p2 are collinear and p2 lies on segment p1q1
        if (o1 == COLLINEAR && onSegment(p2)) return true;

        // p1, q1 and q2 are collinear and q2 lies on segment p1q1
        if (o2 == COLLINEAR && onSegment(q2)) return true;

        // p2, q2 and p1 are collinear and p1 lies on segment p2q2
        if (o3 == COLLINEAR && s.onSegment(p1)) return true;

        // p2, q2 and q1 are collinear and q1 lies on segment p2q2
        if (o4 == COLLINEAR && s.onSegment(q1)) return true;

        return false; // Doesn't fall in any of the above cases
    }

    /**
     * Calculates the slope of the segment.
     *
     * @return slope of the segment.
     *
     * @throws IllegalStateException if segment is vertical
     */
    public double slope() {
        if (isVertical()) throw new IllegalStateException("Line segment is vertical with undefined slope");
        return getRise() / getRun();
    }

    /**
     * Finds the minor angle between this segment and another
     *
     * @param s2 The other segment
     *
     * @return Angle in radians between the two segments
     */
    public double findAngleWith(Segment s2) {
        final Segment s1 = this;
        double theta1 = atan2(s1.pointsList.get(0).getY() - s1.pointsList.get(1).getY(),
                s1.pointsList.get(0).getX() - s1.pointsList.get(1).getX());
        double theta2 = atan2(s2.pointsList.get(0).getY() - s2.pointsList.get(1).getY(),
                s2.pointsList.get(0).getX() - s2.pointsList.get(1).getX());
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
        return pointsList.get(1).getX() - pointsList.get(0).getX();
    }

    /**
     * @return The y-expanse of the segment
     */
    double getRise() {
        return pointsList.get(1).getY() - pointsList.get(0).getY();
    }


    /**
     * Determines if a point lies on the segment
     *
     * @param q The point
     *
     * @return true if and only if point q lies on the segment
     */
    boolean onSegment(Point q) {
        final Point p = pointsList.get(0);
        final Point r = pointsList.get(1);

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
            return this.pointsList.get(0).equals(that.pointsList.get(0)) && this.pointsList.get(1).equals(that.pointsList.get(1));
        }
        return false;
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

    @Override
    public String toString() {
        return "[" + pointsList.get(0) + " - " + pointsList.get(1) + "]";
    }
}
