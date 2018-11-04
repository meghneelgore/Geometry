/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes;

import com.google.common.collect.ImmutableList;
import com.meghneelgore.geometry.primitives.Point;
import com.meghneelgore.geometry.primitives.Segment;

import java.util.List;

/**
 * Abstract base class for shapes.
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
public abstract class BasePolygon implements Polygon {

    /**
     * List of points that make up the shape
     */
    protected final List<Point> pointsList;


    /**
     * List of segments that make up the shape
     */
    protected final List<Segment> segmentsList;

    /**
     * Save the perimeter of the shape
     */
    protected final double perimeter;

    /**
     * Constructor.
     *
     * @param pointsList List of points making up the shape.
     */
    protected BasePolygon(ImmutableList<Point> pointsList) {
        this.pointsList = pointsList;
        this.segmentsList = makeSegments(pointsList);
        this.perimeter = findPerimeter(segmentsList);
    }

    /**
     * Returns the perimeter of the shape
     *
     * @return the perimeter
     */
    @Override
    public double getPerimeter() {
        return perimeter;
    }

    /**
     * Determines if this shape overlaps another
     *
     * @param shape The other shape
     * @return true iff this shape overlaps the other
     */
    @Override
    public boolean overlaps(Polygon shape) {
        for (Segment s1 : segmentsList) {
            for (Segment s2 : ((BasePolygon) shape).segmentsList) {
                if (s1.intersectsWith(s2)) return true;
            }
        }
        return false;
    }

    /**
     * Returns the {@code PolygonType} of the polygon
     *
     * @return {@code PolygonType.CONVEX} if the polygon is convex, {@code PolygonType.CONCAVE} if the polygon is concave, {@code PolygonType.COMPLEX} otherwise
     */
    @Override
    public PolygonType getPolygonType() {
        return PolygonType.CONVEX; // TODO fix this
    }

    /**
     * Creates all the segments that make up the shape
     *
     * @param pointsList List of points that make up the shape
     * @return List of Segments
     */
    ImmutableList<Segment> makeSegments(ImmutableList<Point> pointsList) {
        if (pointsList.size() <= 1) throw new IllegalArgumentException();

        int i = 0;
        ImmutableList.Builder<Segment> builder = new ImmutableList.Builder<>();
        for (; i < pointsList.size() - 1; i++) {
            builder.add(new Segment(pointsList.get(i), pointsList.get(i + 1)));
        }

        builder.add(new Segment(pointsList.get(i), pointsList.get(0)));
        return builder.build();
    }

    /**
     * Finds the perimeter of the shape
     *
     * @param segmentsList List of segments that makes up the shape
     * @return Perimeter of the shape
     */
    double findPerimeter(List<Segment> segmentsList) {
        double perimeter = 0;
        for (Segment s : segmentsList) {
            perimeter += s.getLength();
        }
        return perimeter;
    }
}
