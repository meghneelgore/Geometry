/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.sorting

import com.google.common.collect.ImmutableList
import com.meghneelgore.geometry.primitives.Point
import java.util.*

/**
 * Given a list of Points (x, y), find the K points nearest to the origin
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
class KNearest {

    /**
     * Finds the K nearest points to the origin from the list of given poitns
     *
     * @param listPoints List of given points
     * @param k          Number of points to find
     *
     * @return List<Point> of k points nearest to the origin from the given list.
    </Point> */
    fun findKNearest(listPoints: List<Point>, k: Int): List<Point> {
        if (k > listPoints.size) throw IllegalArgumentException("K is greater than number of points")
        var count = 0
        val priorityQueue = PriorityQueue<Point> { o1, o2 ->
            val o1Dist = o1.distanceFromOrigin
            val o2Dist = o2.distanceFromOrigin
            java.lang.Double.compare(o2Dist, o1Dist)
        }

        for (p in listPoints) {
            priorityQueue.add(p)
            if (count++ >= k) {
                priorityQueue.remove()
            }
        }
        return ImmutableList.copyOf(priorityQueue)
    }
}
