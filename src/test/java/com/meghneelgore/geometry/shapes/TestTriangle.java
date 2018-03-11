/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes;

import com.google.common.collect.ImmutableList;
import com.meghneelgore.geometry.primitives.Point;
import com.meghneelgore.geometry.primitives.Segment;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
public class TestTriangle {

    @Test
    public void testTriangleConstructor() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 1);
        Point p3 = new Point(10, 10);
        Triangle triangle = new Triangle(p1, p2, p3);
        Segment s1 = new Segment(p1, p2);
        Segment s2 = new Segment(p2, p3);
        Segment s3 = new Segment(p3, p1);

        Assert.assertEquals("Segments not created correctly", triangle.segmentsList.get(0), s1);
        Assert.assertEquals("Segments not created correctly", triangle.segmentsList.get(1), s2);
        Assert.assertEquals("Segments not created correctly", triangle.segmentsList.get(2), s3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTriangleConstructorWithCollinearPoints() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Point p3 = new Point(3, 3);
        new Triangle(p1, p2, p3);
    }

    @Test
    public void testTriangleConstructorWithList() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 1);
        Point p3 = new Point(10, 10);
        ImmutableList<Point> points = ImmutableList.of(p1, p2, p3);

        Triangle triangle = new Triangle(points);
        Segment s1 = new Segment(p1, p2);
        Segment s2 = new Segment(p2, p3);
        Segment s3 = new Segment(p3, p1);

        Assert.assertEquals("Segments not created correctly", triangle.segmentsList.get(0), s1);
        Assert.assertEquals("Segments not created correctly", triangle.segmentsList.get(1), s2);
        Assert.assertEquals("Segments not created correctly", triangle.segmentsList.get(2), s3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTriangleConstructorWithListOfPointsMoreThan3() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 1);
        Point p3 = new Point(10, 10);
        Point p4 = new Point(10, 10);

        ImmutableList<Point> points = ImmutableList.of(p1, p2, p3, p4);
        new Triangle(points);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTriangleConstructorWithListOfPointsLessThan3() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 1);

        ImmutableList<Point> points = ImmutableList.of(p1, p2);
        new Triangle(points);
    }

    @Test
    public void testGetArea() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 1);
        Point p3 = new Point(10, 10);
        Triangle triangle = new Triangle(p1, p2, p3);

        Assert.assertEquals("Area calculated wrongly", 4.5000000000000036, triangle.getArea(), 0);
    }

    @Test
    public void testOverlaps() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 1);
        Point p3 = new Point(10, 10);
        Triangle t1 = new Triangle(p1, p2, p3);

        p1 = new Point(11, 11);
        p2 = new Point(21, 11);
        p3 = new Point(101, 101);
        Triangle t2 = new Triangle(p1, p2, p3);

        Assert.assertFalse("Non-overlapping triangles calculated as overlapping", t1.overlaps(t2));

        p1 = new Point(0, 0);
        p2 = new Point(20, 0);
        p3 = new Point(0, 20);
        t1 = new Triangle(p1, p2, p3);

        p1 = new Point(1, 1);
        p2 = new Point(21, 11);
        p3 = new Point(101, 101);
        t2 = new Triangle(p1, p2, p3);

        Assert.assertTrue("Overlapping triangles calculated as non-overlapping", t1.overlaps(t2));


    }
}
