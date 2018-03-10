package com.meghneelgore.geometry.shapes;

import com.google.common.collect.ImmutableList;
import com.meghneelgore.geometry.Point;

public abstract class BaseShape implements Shape {
    protected final ImmutableList<Point> pointsList;

    protected BaseShape(ImmutableList<Point> pointsList) {
        this.pointsList = pointsList;
    }
}
