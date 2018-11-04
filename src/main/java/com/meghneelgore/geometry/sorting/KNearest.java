/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.sorting;

import com.google.common.collect.ImmutableList;
import com.meghneelgore.geometry.primitives.Point;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a list of Points (x, y), find the K points nearest to the origin
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
public class KNearest {

    /**
     * Finds the K nearest points to the origin from the list of given poitns
     *
     * @param listPoints List of given points
     * @param k          Number of points to find
     *
     * @return List<Point> of k points nearest to the origin from the given list.
     */
    public List<Point> findKNearest(List<Point> listPoints, int k) {
        if (k > listPoints.size()) throw new IllegalArgumentException("K is greater than number of points");
        int count = 0;
        final Queue<Point> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            double o1Dist = o1.getDistanceFromOrigin();
            double o2Dist = o2.getDistanceFromOrigin();
            return Double.compare(o2Dist, o1Dist);
        });

        for (Point p : listPoints) {

            priorityQueue.add(p);
            if (count++ >= k) {
                priorityQueue.remove();
            }
        }
        return ImmutableList.copyOf(priorityQueue);
    }
}
