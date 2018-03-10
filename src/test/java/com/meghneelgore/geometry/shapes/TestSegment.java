package com.meghneelgore.geometry.shapes;

import com.meghneelgore.geometry.Point;
import org.junit.Assert;
import org.junit.Test;

public class TestSegment {

    @Test(expected = IllegalArgumentException.class)
    public void testSegmentWithEqualPoints() {
        Segment s = new Segment(new Point(0, 1), new Point(0, 1));
    }

    @Test
    public void testSegment() {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(2.2, 1.1);
        Segment s = new Segment(p1, p2);
        Assert.assertEquals("Initialized point different from retrieved", p1, s.pointsList.get(0));
        Assert.assertEquals("Initialized point different from retrieved", p2, s.pointsList.get(1));
    }

    @Test
    public void testLength() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);

        Segment segment = new Segment(p1, p2);

        double length = segment.length();
        Assert.assertEquals("Calculated length is not right", 1.4142135623730951, length, 0.0);
    }

    @Test
    public void testIsParallelTo() {

        //Test slope branch
        Point p1 = new Point(0, 1);
        Point p2 = new Point(1, 0);
        Segment segment1 = new Segment(p1, p2);
        p1 = new Point(0, 2);
        p2 = new Point(2, 0);
        Segment segment2 = new Segment(p1, p2);

        Assert.assertTrue("Parallel segments calculated as non-parallel", segment1.isParallelTo(segment2));

        //Test vertical branch
        p1 = new Point(10, 1);
        p2 = new Point(10, 0);
        segment1 = new Segment(p1, p2);
        p1 = new Point(0, 2);
        p2 = new Point(0, 3);
        segment2 = new Segment(p1, p2);

        Assert.assertTrue("Parallel segments calculated as non-parallel", segment1.isParallelTo(segment2));

        //Test horizontal branch
        p1 = new Point(10, 1);
        p2 = new Point(100, 1);
        segment1 = new Segment(p1, p2);
        p1 = new Point(1, 2);
        p2 = new Point(-29, 2);
        segment2 = new Segment(p1, p2);

        Assert.assertTrue("Parallel segments calculated as non-parallel", segment1.isParallelTo(segment2));
    }

    @Test
    public void testIsPerpendicular() {
        //Test slope branch
        Point p1 = new Point(0, 1);
        Point p2 = new Point(1, 0);
        Segment segment1 = new Segment(p1, p2);
        p1 = new Point(0, 0);
        p2 = new Point(1, 1);
        Segment segment2 = new Segment(p1, p2);

        Assert.assertTrue("Perpendicular segments calculated as non-perpendicular", segment1.isPerpendicularTo(segment2));

        p1 = new Point(0, 1);
        p2 = new Point(1, 0);
        segment1 = new Segment(p1, p2);
        p1 = new Point(0, 0);
        p2 = new Point(1, 2);
        segment2 = new Segment(p1, p2);

        Assert.assertFalse("Non-perpendicular segments calculated as perpendicular", segment1.isPerpendicularTo(segment2));

    }

    @Test
    public void testIntersectsWith() {
        Point p1 = new Point(0, 1);
        Point p2 = new Point(1, 0);
        Segment segment1 = new Segment(p1, p2);
        p1 = new Point(0, 0);
        p2 = new Point(1, 1);
        Segment segment2 = new Segment(p1, p2);

        Assert.assertTrue("Intersecting segments calculated as non-intersecting", segment1.intersectsWith(segment2));

        p1 = new Point(0, 1);
        p2 = new Point(1, 0);
        segment1 = new Segment(p1, p2);
        p1 = new Point(10, 10);
        p2 = new Point(111, 111);
        segment2 = new Segment(p1, p2);

        Assert.assertFalse("Non-intersecting segments calculated as intersecting", segment1.intersectsWith(segment2));

        p1 = new Point(0, 0);
        p2 = new Point(3, 3);
        segment1 = new Segment(p1, p2);
        p1 = new Point(2, 2);
        p2 = new Point(4, 4);
        segment2 = new Segment(p1, p2);

        Assert.assertTrue("Intersecting segments calculated as non-intersecting", segment1.intersectsWith(segment2));
    }

}
