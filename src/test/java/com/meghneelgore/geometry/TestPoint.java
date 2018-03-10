package com.meghneelgore.geometry;

import org.junit.Assert;
import org.junit.Test;

public class TestPoint {

    @Test
    public void testGetXGetY() {
        final Point p = new Point(3.4, 5.3);
        Assert.assertEquals("Set x-value is wrong!", 3.4, p.getX(), 0.0);
        Assert.assertEquals("Set y-value is wrong!", 5.3, p.getY(), 0.0);
    }

    @Test
    public void testGetDistanceFromOrigin() {
        final Point p = new Point(3.4, 5.3);
        Assert.assertEquals("Distance calculated from origin is wrong", 6.29682, p.getDistanceFromOrigin(), 0.00001);
    }

    @Test
    public void testEquals() {
        final Point p1 = new Point(3.4234234267567567, 44.7929477839365267895948);
        final Point p2 = new Point(3.4234234267567567, 44.7929477839365267895948);
        Assert.assertEquals("Two points at same coordinates not equal", p1, p2);
    }

}
