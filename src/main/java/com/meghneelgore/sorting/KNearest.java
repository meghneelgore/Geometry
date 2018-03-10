package com.meghneelgore.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a list of Points (x, y), find the K points nearest to the origin
 */
public class KNearest {

    /**
     * Class depicting a point in 2-d space
     */
    public static class Point {
        /**
         * x-value
         */
        private final double x;
        /**
         * y-value
         */
        private final double y;

        /**
         * Constructor.
         *
         * @param x x-value
         * @param y y-value
         */
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Gets the distance of this point from the origin (0, 0)
         *
         * @return The calculated distance
         */
        public double getDistanceFromOrigin() {
            return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }

        /**
         * toString method
         *
         * @return String representation of the point
         */
        @Override
        public String toString() {
            return "[" + x + ", " + y + "]";
        }

        /**
         * Returns if the current object is equal to another point depicted by obj
         *
         * @param obj The other object
         * @return true if both are equal
         */
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                Point p = (Point) obj;
                return this.x == p.x && this.y == p.y;
            }
            return false;
        }

        /**
         * Returns the hashcode for the point
         *
         * @return Hashcode for this point
         */
        @Override
        public int hashCode() {
            return new Double(x * y).hashCode();
        }
    }

    /**
     * Finds the K nearest points to the origin from the list of given poitns
     *
     * @param listPoints List of given points
     * @param k          Number of points to find
     * @return List<Point> of k points nearest to the origin from the given list.
     */
    public List<Point> findKNearest(List<Point> listPoints, int k) {
        if (k > listPoints.size()) throw new IllegalArgumentException("K is greater than number of points");
        int count = 0;
        final Queue<Point> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            double o1Dist = o1.getDistanceFromOrigin();
            double o2Dist = o2.getDistanceFromOrigin();
            if (o1Dist > o2Dist) return -1;
            if (o1Dist < o2Dist) return 1;
            return 0;
        });

        for (Point p : listPoints) {

            priorityQueue.add(p);
            if (count++ >= k) {
                priorityQueue.remove();
            }
        }

        return new ArrayList<>(priorityQueue);
    }


}
