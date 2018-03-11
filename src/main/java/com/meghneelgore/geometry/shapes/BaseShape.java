/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes;

import com.google.common.collect.ImmutableList;
import com.meghneelgore.geometry.primitives.Point;
import com.meghneelgore.geometry.primitives.Segment;

/**
 * Abstract base class for shapes.
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
public abstract class BaseShape implements Shape {

    /**
     * List of points that make up the shape
     */
    protected final ImmutableList<Point> pointsList;


    /**
     * List of segments that make up the shape
     */
    protected final ImmutableList<Segment> segmentsList;

    /**
     * Save the perimeter of the shape
     */
    protected final double perimeter;

    /**
     * Constructor.
     *
     * @param pointsList List of points making up the shape.
     */
    protected BaseShape(ImmutableList<Point> pointsList) {
        this.pointsList = pointsList;
        this.segmentsList = makeSegments(pointsList);
        this.perimeter = findPerimeter(segmentsList);
    }

    /**
     * Finds the perimeter of the shape
     *
     * @param segmentsList List of segments that makes up the shape
     *
     * @return Perimeter of the shape
     */
    double findPerimeter(ImmutableList<Segment> segmentsList) {
        double perimeter = 0;
        for (Segment s : segmentsList) {
            perimeter += s.getLength();
        }
        return perimeter;
    }

    /**
     * Creates all the segments that make up the shape
     *
     * @param pointsList List of points that make up the shape
     *
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
     * Returns the perimeter of the shape
     *
     * @return the perimeter
     */
    @Override
    public double getPerimeter() {
        return perimeter;
    }

    public ImmutableList<Segment> getSegmentsList() {
        return segmentsList;
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
        for (Segment s1 : segmentsList) {
            for (Segment s2 : ((BaseShape) shape).getSegmentsList()) {
                if (s1.intersectsWith(s2)) return true;
            }
        }
        return false;
    }
}
