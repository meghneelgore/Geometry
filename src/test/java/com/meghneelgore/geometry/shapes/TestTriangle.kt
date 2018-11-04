/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes

import com.google.common.collect.ImmutableList
import com.meghneelgore.geometry.primitives.Point
import com.meghneelgore.geometry.primitives.Segment
import org.junit.Assert
import org.junit.Test

/**
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
class TestTriangle {

    @Test
    fun testTriangleConstructor() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(2.0, 1.0)
        val p3 = Point(10.0, 10.0)
        val triangle = Triangle(p1, p2, p3)
        val s1 = Segment(p1, p2)
        val s2 = Segment(p2, p3)
        val s3 = Segment(p3, p1)

        Assert.assertEquals("Segments not created correctly", triangle.segmentsList[0], s1)
        Assert.assertEquals("Segments not created correctly", triangle.segmentsList[1], s2)
        Assert.assertEquals("Segments not created correctly", triangle.segmentsList[2], s3)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testTriangleConstructorWithCollinearPoints() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(2.0, 2.0)
        val p3 = Point(3.0, 3.0)
        Triangle(p1, p2, p3)
    }

    @Test
    fun testTriangleConstructorWithList() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(2.0, 1.0)
        val p3 = Point(10.0, 10.0)
        val points = ImmutableList.of(p1, p2, p3)

        val triangle = Triangle(points)
        val s1 = Segment(p1, p2)
        val s2 = Segment(p2, p3)
        val s3 = Segment(p3, p1)

        Assert.assertEquals("Segments not created correctly", triangle.segmentsList[0], s1)
        Assert.assertEquals("Segments not created correctly", triangle.segmentsList[1], s2)
        Assert.assertEquals("Segments not created correctly", triangle.segmentsList[2], s3)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testTriangleConstructorWithListOfPointsMoreThan3() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(2.0, 1.0)
        val p3 = Point(10.0, 10.0)
        val p4 = Point(10.0, 10.0)

        val points = ImmutableList.of(p1, p2, p3, p4)
        Triangle(points)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testTriangleConstructorWithListOfPointsLessThan3() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(2.0, 1.0)

        val points = ImmutableList.of(p1, p2)
        Triangle(points)
    }

    @Test
    fun testGetArea() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(2.0, 1.0)
        val p3 = Point(10.0, 10.0)
        val triangle = Triangle(p1, p2, p3)

        Assert.assertEquals("Area calculated wrongly", 4.5000000000000036, triangle.area, 0.0)
    }

    @Test
    fun testOverlaps() {
        var p1 = Point(1.0, 1.0)
        var p2 = Point(2.0, 1.0)
        var p3 = Point(10.0, 10.0)
        var t1 = Triangle(p1, p2, p3)

        p1 = Point(11.0, 11.0)
        p2 = Point(21.0, 11.0)
        p3 = Point(101.0, 101.0)
        var t2 = Triangle(p1, p2, p3)

        Assert.assertFalse("Non-overlapping triangles calculated as overlapping", t1.overlaps(t2))

        p1 = Point(0.0, 0.0)
        p2 = Point(20.0, 0.0)
        p3 = Point(0.0, 20.0)
        t1 = Triangle(p1, p2, p3)

        p1 = Point(1.0, 1.0)
        p2 = Point(21.0, 11.0)
        p3 = Point(101.0, 101.0)
        t2 = Triangle(p1, p2, p3)

        Assert.assertTrue("Overlapping triangles calculated as non-overlapping", t1.overlaps(t2))


    }
}
