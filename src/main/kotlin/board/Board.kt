package board

import com.google.common.collect.ImmutableList
import geometry.primitives.Point
import geometry.primitives.UnitCircleAngle
import geometry.shapes.Sector
import geometry.shapes.Triangle
import rendering.CanvasRenderer
import rendering.GeometryCanvas
import java.awt.Graphics2D
import java.util.*
import kotlin.random.Random

class Board {

    val random = Random(Date().time)
    val triangle1 = Triangle(ImmutableList.of(Point(random.nextInt(0, 500).toDouble(), random.nextInt(0, 500).toDouble()), Point(random.nextInt(0, 500).toDouble(), 100.0), Point(random.nextInt(0, 500).toDouble(), random.nextInt(0, 500).toDouble())))
    val triangle2 = Triangle(ImmutableList.of(Point(random.nextInt(0, 500).toDouble(), random.nextInt(0, 500).toDouble()), Point(random.nextInt(0, 500).toDouble(), 100.0), Point(random.nextInt(0, 500).toDouble(), random.nextInt(0, 500).toDouble())))
    //    val circle = Circle(Point(random.nextInt(0, 500).toDouble(), random.nextInt(0, 500).toDouble()), 100.0)
    val sector = Sector(Point(300.0, 300.0), 100.0, UnitCircleAngle(random.nextDouble(0.0, Math.PI * 2)), UnitCircleAngle(random.nextDouble(0.0, Math.PI * 2)))
    var theta = 0.0

    init {
        CanvasRenderer(GeometryCanvas(this))
    }

    fun paint(graphics: Graphics2D) {


        triangle1.renderFilled(graphics)
        triangle1.scale(1.5).renderFilled(graphics)
        triangle2.translateX(Math.cos(theta)).translateY(Math.sin(theta)).rotateAround(theta, Point(300.0, 300.0)).render(graphics)
//        circle.rotate(theta).renderFilled(graphics)
//        circle.scale(theta % 2).translateX(100.0).rotateAround(theta, Point(300.0, 300.0)).render(graphics)
        sector.rotateAround(theta, Point(250.0, 250.0)).render(graphics)
        theta += 0.01
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val board = Board()

        }
    }
}