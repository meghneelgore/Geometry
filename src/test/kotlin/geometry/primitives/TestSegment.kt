/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package geometry.primitives

import geometry.primitives.Point
import geometry.primitives.Segment
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TestSegment {

    @Test(expected = IllegalArgumentException::class)
    fun testSegmentWithEqualPoints() {
        val s = Segment(Point(0.0, 1.0), Point(0.0, 1.0))
    }

    @Test
    fun testSegment() {
        val p1 = Point(1.1, 2.2)
        val p2 = Point(2.2, 1.1)
        val s = Segment(p1, p2)
        assertEquals("Initialized point different from retrieved", p1, s.p1)
        assertEquals("Initialized point different from retrieved", p2, s.p2)
    }

    @Test
    fun testLength() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(2.0, 2.0)
        val segment = Segment(p1, p2)
        val length = segment.length
        assertEquals("Calculated getLength is not right", 1.4142135623730951, length, 0.0)
    }

    @Test
    fun testIsParallelTo() {
        //Test slope branch
        var p1 = Point(0.0, 1.0)
        var p2 = Point(1.0, 0.0)
        var segment1 = Segment(p1, p2)
        p1 = Point(0.0, 2.0)
        p2 = Point(2.0, 0.0)
        var segment2 = Segment(p1, p2)

        assertTrue("Parallel segments calculated as non-parallel", segment1.isParallelTo(segment2))

        //Test vertical branch
        p1 = Point(10.0, 1.0)
        p2 = Point(10.0, 0.0)
        segment1 = Segment(p1, p2)
        p1 = Point(0.0, 2.0)
        p2 = Point(0.0, 3.0)
        segment2 = Segment(p1, p2)

        assertTrue("Parallel segments calculated as non-parallel", segment1.isParallelTo(segment2))

        //Test horizontal branch
        p1 = Point(10.0, 1.0)
        p2 = Point(100.0, 1.0)
        segment1 = Segment(p1, p2)
        p1 = Point(1.0, 2.0)
        p2 = Point(-29.0, 2.0)
        segment2 = Segment(p1, p2)

        assertTrue("Parallel segments calculated as non-parallel", segment1.isParallelTo(segment2))
    }

    @Test
    fun testIsPerpendicular() {
        //Test slope branch
        var p1 = Point(0.0, 1.0)
        var p2 = Point(1.0, 0.0)
        var segment1 = Segment(p1, p2)
        p1 = Point(0.0, 0.0)
        p2 = Point(1.0, 1.0)
        var segment2 = Segment(p1, p2)

        assertTrue("Perpendicular segments calculated as non-perpendicular", segment1.isPerpendicularTo(segment2))

        p1 = Point(0.0, 1.0)
        p2 = Point(1.0, 0.0)
        segment1 = Segment(p1, p2)
        p1 = Point(0.0, 0.0)
        p2 = Point(1.0, 2.0)
        segment2 = Segment(p1, p2)

        Assert.assertFalse("Non-perpendicular segments calculated as perpendicular", segment1.isPerpendicularTo(segment2))

    }

    @Test
    fun testIntersectsWith() {
        var p1 = Point(0.0, 1.0)
        var p2 = Point(1.0, 0.0)
        var segment1 = Segment(p1, p2)
        p1 = Point(0.0, 0.0)
        p2 = Point(1.0, 1.0)
        var segment2 = Segment(p1, p2)

        assertTrue("Intersecting segments calculated as non-intersecting", segment1.intersectsWith(segment2))

        p1 = Point(0.0, 1.0)
        p2 = Point(1.0, 0.0)
        segment1 = Segment(p1, p2)
        p1 = Point(10.0, 10.0)
        p2 = Point(111.0, 111.0)
        segment2 = Segment(p1, p2)

        Assert.assertFalse("Non-intersecting segments calculated as intersecting", segment1.intersectsWith(segment2))

        p1 = Point(0.0, 0.0)
        p2 = Point(3.0, 3.0)
        segment1 = Segment(p1, p2)
        p1 = Point(2.0, 2.0)
        p2 = Point(4.0, 4.0)
        segment2 = Segment(p1, p2)

        assertTrue("Intersecting segments calculated as non-intersecting", segment1.intersectsWith(segment2))

        p1 = Point(1.0, 1.0)
        p2 = Point(3.0, 1.0)
        segment1 = Segment(p1, p2)
        p1 = Point(2.0, 1.0)
        p2 = Point(4.0, 7.0)
        segment2 = Segment(p1, p2)

        assertTrue("Intersecting segments calculated as non-intersecting", segment1.intersectsWith(segment2))
    }

    @Test
    fun testSlopeForVerticalSegment() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(1.0, 2.0)
        val s1 = Segment(p1, p2)
        assertEquals("Vertical segment slope calculated incorrectly", Double.POSITIVE_INFINITY, s1.slope, 0.0)
    }

    @Test
    fun testSlopeForNonVerticalSegment() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(2.0, 2.0)
        val s1 = Segment(p1, p2)
        val slope = s1.slope
        assertEquals("Calculated slope is wrong", 1.0, slope, 0.0)
    }

    @Test
    fun testFindAngleWith() {
        var p1 = Point(1.0, 1.0)
        var p2 = Point(2.0, 2.0)
        var s1 = Segment(p1, p2)
        p1 = Point(3.0, 3.0)
        p2 = Point(4.0, 4.0)
        var s2 = Segment(p1, p2)

        assertEquals("Angle calculated wrongly", 0.0, s1.angleWith(s2), 0.0)

        p1 = Point(0.0, 0.0)
        p2 = Point(2.0, 2.0)
        s1 = Segment(p1, p2)
        p1 = Point(2.0, 0.0)
        p2 = Point(0.0, 2.0)
        s2 = Segment(p1, p2)

        assertEquals("Angle calculated wrongly", -Math.PI / 2, s1.angleWith(s2), 0.0)
    }

    @Test
    fun testEquals() {
        var p1 = Point(1.0, 1.0)
        var p2 = Point(2.0, 2.0)
        val s1 = Segment(p1, p2)
        p1 = Point(1.0, 1.0)
        p2 = Point(2.0, 2.0)
        val s2 = Segment(p1, p2)

        assertTrue("Equal segments not computed as equal", s1 == s2)
    }


}
