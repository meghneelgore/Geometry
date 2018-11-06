package geometry.shapes

import com.google.common.collect.ImmutableList
import geometry.primitives.Point
import geometry.primitives.Polygon
import java.awt.Graphics2D

data class GenericPolygon(override val pointsList: ImmutableList<Point>) : BasePolygon(pointsList) {


    override val area: Double = 5.0 // TODO

    override fun copyPolygon(pointsList: ImmutableList<Point>): Polygon {
        return copy(pointsList = pointsList)
    }


}