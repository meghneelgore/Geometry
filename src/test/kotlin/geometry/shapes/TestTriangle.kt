/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package geometry.shapes

import com.google.common.collect.ImmutableList
import geometry.primitives.Point
import geometry.primitives.Segment
import junit.framework.Assert.assertEquals
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
        val pointsList = ImmutableList.of(p1, p2, p3)
        val triangle = Triangle(pointsList)
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
        val pointsList = ImmutableList.of(p1, p2, p3)
        Triangle(pointsList)
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
        val pointsList = ImmutableList.of(p1, p2, p3)
        var triangle = Triangle(pointsList)
        Assert.assertEquals("Area calculated wrongly", 4.5000000000000036, triangle.area, 0.0)
    }

    @Test
    fun testOverlaps() {
        var p1 = Point(1.0, 1.0)
        var p2 = Point(2.0, 1.0)
        var p3 = Point(10.0, 10.0)
        var pointsList = ImmutableList.of(p1, p2, p3)
        var t1 = Triangle(pointsList)

        p1 = Point(11.0, 11.0)
        p2 = Point(21.0, 11.0)
        p3 = Point(101.0, 101.0)
        pointsList = ImmutableList.of(p1, p2, p3)
        var t2 = Triangle(pointsList)
        Assert.assertFalse("Non-overlapping triangles calculated as overlapping", t1.overlaps(t2))

        p1 = Point(0.0, 0.0)
        p2 = Point(20.0, 0.0)
        p3 = Point(0.0, 20.0)
        pointsList = ImmutableList.of(p1, p2, p3)
        t1 = Triangle(pointsList)

        p1 = Point(1.0, 1.0)
        p2 = Point(21.0, 11.0)
        p3 = Point(101.0, 101.0)
        pointsList = ImmutableList.of(p1, p2, p3)
        t2 = Triangle(pointsList)

        Assert.assertTrue("Overlapping triangles calculated as non-overlapping", t1.overlaps(t2))
    }

    @Test
    fun testTranslateX() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(2.0, 1.0)
        val p3 = Point(10.0, 10.0)
        val pointsList = ImmutableList.of(p1, p2, p3)
        val t1 = Triangle(pointsList)
        val t2 = t1.translateX(1.0)

        assertEquals("Triangle translated wrongly", t2.pointsList[0].x, 2.0, 0.0)
        assertEquals("Triangle translated wrongly", t2.pointsList[1].x, 3.0, 0.0)
        assertEquals("Triangle translated wrongly", t2.pointsList[2].x, 11.0, 0.0)
    }

    @Test
    fun testTranslateY() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(2.0, 1.0)
        val p3 = Point(10.0, 10.0)
        val pointsList = ImmutableList.of(p1, p2, p3)
        val t1 = Triangle(pointsList)
        val t2 = t1.translateY(11.0)

        assertEquals("Triangle translated wrongly", t2.pointsList[0].y, 12.0, 0.0)
        assertEquals("Triangle translated wrongly", t2.pointsList[1].y, 12.0, 0.0)
        assertEquals("Triangle translated wrongly", t2.pointsList[2].y, 21.0, 0.0)
    }
}
