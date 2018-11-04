/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes

import com.google.common.collect.ImmutableList
import com.meghneelgore.geometry.primitives.Point
import com.meghneelgore.geometry.primitives.Segment
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

/**
 * @author Meghneel Gore meghneel.gore@gmail.com
 *
 *
 * Tests the [BasePolygon] class
 */
class TestBasePolygon {

    @Test
    fun testFindPerimeter() {
        val shape = Mockito.mock(BasePolygon::class.java, Mockito.CALLS_REAL_METHODS)

        val s1 = Segment(Point(0.0, 0.0), Point(1.0, 1.0))
        val s2 = Segment(Point(1.0, 1.0), Point(2.0, 1.0))
        val s3 = Segment(Point(2.0, 1.0), Point(0.0, 0.0))

        val segments = ImmutableList.of(s1, s2, s3)
        val perimeter = shape.findPerimeter(segments)
        Assert.assertEquals("Perimeter calculated wrongly", 4.650281539872885, perimeter, 0.0)
    }

    @Test
    fun testMakeSegments() {
        val points = ImmutableList.of(Point(0.0, 0.0), Point(1.0, 1.0), Point(2.0, 1.0))

        val s1 = Segment(Point(0.0, 0.0), Point(1.0, 1.0))
        val s2 = Segment(Point(1.0, 1.0), Point(2.0, 1.0))
        val s3 = Segment(Point(2.0, 1.0), Point(0.0, 0.0))
        val shape = Mockito.mock(BasePolygon::class.java, Mockito.CALLS_REAL_METHODS)

        val segments = shape.makeSegments(points)

        Assert.assertEquals("Wrong segment created", s1, segments[0])
        Assert.assertEquals("Wrong segment created", s2, segments[1])
        Assert.assertEquals("Wrong segment created", s3, segments[2])
    }

}
