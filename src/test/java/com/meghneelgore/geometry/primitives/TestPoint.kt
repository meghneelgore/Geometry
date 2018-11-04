/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.primitives

import org.junit.Assert
import org.junit.Test

class TestPoint {

    @Test
    fun testGetXGetY() {
        val p = Point(3.4, 5.3)
        Assert.assertEquals("Set x-value is wrong!", 3.4, p.x, 0.0)
        Assert.assertEquals("Set y-value is wrong!", 5.3, p.y, 0.0)
    }

    @Test
    fun testGetDistanceFromOrigin() {
        val p = Point(3.4, 5.3)
        Assert.assertEquals("Distance calculated from origin is wrong", 6.29682, p.distanceFromOrigin, 0.00001)
    }

    @Test
    fun testEquals() {
        val p1 = Point(3.4234234267567567, 44.7929477839365267895948)
        val p2 = Point(3.4234234267567567, 44.7929477839365267895948)
        Assert.assertEquals("Two points at same coordinates not equal", p1, p2)
    }
}
