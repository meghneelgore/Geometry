package com.meghneelgore.geometry;

import static com.meghneelgore.geometry.Point.Orientation.COLLINEAR;
import static com.meghneelgore.geometry.Point.getThreePointOrientation;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 *
 */
public class Segment {
    private final Point p1;
    private final Point p2;


    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    /**
     * @param p1
     * @param p2
     */
    public Segment(Point p1, Point p2) {
        if (p1.equals(p2)) throw new IllegalArgumentException();
        this.p1 = p1;
        this.p2 = p2;
    }

    public double length() {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    /**
     * @param s
     * @return
     */
    public boolean isParallelTo(Segment s) {
        if (s == this) return true; //Minor optimization
        if (this.isVertical() && s.isVertical()) return true;
        if (this.isHorizontal() && s.isHorizontal()) return true;
        return this.slope() == s.slope();
    }

    public boolean isPerpendicularTo(Segment s) {
        if (this.isVertical() && s.isHorizontal()) return true;
        if (this.isHorizontal() && s.isVertical()) return true;
        return this.slope() == -1 / s.slope();
    }

    public boolean intersectsWith(Segment s) {
        final Point p1 = this.p1;
        final Point q1 = this.p2;
        final Point p2 = s.p1;
        final Point q2 = s.p2;

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
        if (o1 == COLLINEAR && onSegment(this, p2)) return true;

        // p1, q1 and q2 are collinear and q2 lies on segment p1q1
        if (o2 == COLLINEAR && onSegment(this, q2)) return true;

        // p2, q2 and p1 are collinear and p1 lies on segment p2q2
        if (o3 == COLLINEAR && onSegment(s, p1)) return true;

        // p2, q2 and q1 are collinear and q1 lies on segment p2q2
        if (o4 == COLLINEAR && onSegment(s, q1)) return true;

        return false; // Doesn't fall in any of the above cases
    }

    double slope() {
        if (isVertical()) throw new IllegalStateException("Line segment is vertical with undefinted slome");
        return getRise() / getRun();
    }

    boolean isVertical() {
        return getRun() == 0;
    }

    boolean isHorizontal() {
        return getRise() == 0;
    }

    double getRun() {
        return p2.getX() - p1.getX();
    }

    double getRise() {
        return p2.getY() - p1.getY();
    }


    boolean onSegment(Segment segment, Point q) {
        final Point p = segment.p1;
        final Point r = segment.p2;

        if (q.getX() <= max(p.getX(), r.getX()) && q.getX() >= min(p.getX(), r.getX()) &&
                q.getY() <= max(p.getY(), r.getY()) && q.getY() >= min(p.getY(), r.getY()))
            return true;

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Segment) {
            Segment that = (Segment) obj;
            return this.p1.equals(that.p1) && this.p1.equals(that.p2);
        }
        return false;
    }
}
