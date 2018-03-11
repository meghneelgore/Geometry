/*
 * Copyright (c) 2018.  Meghneel Gore (meghneel.gore@gmail.com)
 */

package com.meghneelgore.geometry.shapes;

import com.google.common.collect.ImmutableList;
import com.meghneelgore.geometry.Point;

/**
 * Abstract base class for shapes.
 *
 * @author Meghneel Gore meghneel.gore@gmail.com
 */
public abstract class BaseShape implements Shape {
    protected final ImmutableList<Point> pointsList;

    /**
     * Constructor.
     *
     * @param pointsList List of points making up the shape.
     */
    protected BaseShape(ImmutableList<Point> pointsList) {
        this.pointsList = pointsList;
    }
}
