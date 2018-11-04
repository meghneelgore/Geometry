/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes

import com.google.common.collect.ImmutableList
import com.meghneelgore.geometry.primitives.Point
import com.meghneelgore.geometry.primitives.Segment
import com.meghneelgore.geometry.shapes.Polygon.PolygonType.Concave
import com.meghneelgore.geometry.shapes.Polygon.PolygonType.Convex
import org.junit.Assert.assertEquals
import org.junit.Ignore
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
        assertEquals("Perimeter calculated wrongly", 4.650281539872885, perimeter, 0.0)
    }

    @Test
    fun testMakeSegments() {
        val points = ImmutableList.of(Point(0.0, 0.0), Point(1.0, 1.0), Point(2.0, 1.0))

        val s1 = Segment(Point(0.0, 0.0), Point(1.0, 1.0))
        val s2 = Segment(Point(1.0, 1.0), Point(2.0, 1.0))
        val s3 = Segment(Point(2.0, 1.0), Point(0.0, 0.0))
        val shape = Mockito.mock(BasePolygon::class.java, Mockito.CALLS_REAL_METHODS)

        val segments = shape.makeSegments(points)

        assertEquals("Wrong segment created", s1, segments[0])
        assertEquals("Wrong segment created", s2, segments[1])
        assertEquals("Wrong segment created", s3, segments[2])
    }

    @Test
    @Ignore
    fun testPolygonTypeConcave() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(1.0, 1.0)
        val p4 = Point(0.5, 1.0)
        val p5 = Point(0.5, 0.5)
        val p6 = Point(0.0, 0.5)
        val points = ImmutableList.of(p1, p2, p3, p4, p5, p6)

        val poly1 = GenericPolygon(points)

        assertEquals("Concave polygon computed as convex", Concave, poly1.polygonType)
    }

    @Test
    @Ignore
    fun testPolygonTypeConvex() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(1.0, 1.0)
        val p4 = Point(0.0, 1.0)
        val points = ImmutableList.of(p1, p2, p3, p4)

        val poly1 = GenericPolygon(points)

        assertEquals("Convex polygon computed as concave", Convex, poly1.polygonType)
    }


}
