package com.meghneelgore.geometry.sorting

import com.meghneelgore.geometry.primitives.Point
import org.junit.Assert
import org.junit.Test

import java.util.ArrayList

class TestKNearest {

    @Test
    fun testFindKNearest() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(2.0, 2.0)
        val p3 = Point(3.0, 3.0)
        val p4 = Point(-1.5, -1.5)

        val input = ArrayList<Point>()
        input.add(p1)
        input.add(p2)
        input.add(p4)
        input.add(p3)

        val kNearest = KNearest()
        val list = kNearest.findKNearest(input, 2)

        Assert.assertEquals("Wrong number of points in list", 2, list.size.toLong())
        Assert.assertEquals("Wrong point in list", Point(-1.5, -1.5), list[0])
        Assert.assertEquals("Wrong point in list", Point(1.0, 1.0), list[1])
    }
}
