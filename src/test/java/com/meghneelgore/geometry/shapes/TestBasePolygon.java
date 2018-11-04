/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes;

import com.google.common.collect.ImmutableList;
import com.meghneelgore.geometry.primitives.Point;
import com.meghneelgore.geometry.primitives.Segment;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Meghneel Gore meghneel.gore@gmail.com
 * <p>
 * Tests the {@link BasePolygon} class
 */
public class TestBasePolygon {

    @Test
    public void testFindPerimeter() {
        BasePolygon shape = Mockito.mock(BasePolygon.class, Mockito.CALLS_REAL_METHODS);

        Segment s1 = new Segment(new Point(0, 0), new Point(1, 1));
        Segment s2 = new Segment(new Point(1, 1), new Point(2, 1));
        Segment s3 = new Segment(new Point(2, 1), new Point(0, 0));

        ImmutableList<Segment> segments = ImmutableList.of(s1, s2, s3);
        double perimeter = shape.findPerimeter(segments);
        Assert.assertEquals("Perimeter calculated wrongly", 4.650281539872885, perimeter, 0);
    }

    @Test
    public void testMakeSegments() {
        ImmutableList<Point> points = ImmutableList.of
                (new Point(0, 0), new Point(1, 1), new Point(2, 1));

        Segment s1 = new Segment(new Point(0, 0), new Point(1, 1));
        Segment s2 = new Segment(new Point(1, 1), new Point(2, 1));
        Segment s3 = new Segment(new Point(2, 1), new Point(0, 0));
        BasePolygon shape = Mockito.mock(BasePolygon.class, Mockito.CALLS_REAL_METHODS);

        ImmutableList<Segment> segments = shape.makeSegments(points);

        Assert.assertEquals("Wrong segment created", s1, segments.get(0));
        Assert.assertEquals("Wrong segment created", s2, segments.get(1));
        Assert.assertEquals("Wrong segment created", s3, segments.get(2));
    }

}
