package com.meghneelgore.geometry.shapes

import com.google.common.collect.ImmutableList
import com.meghneelgore.geometry.primitives.Point

data class GenericPolygon(val pointsList: ImmutableList<Point>) : BasePolygon(pointsList) {
    override val area: Double = 5.0 // TODO
}