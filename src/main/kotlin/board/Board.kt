package board

import com.google.common.collect.ImmutableList
import geometry.shapes.Circle
import geometry.primitives.Point
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
    val circle = Circle(Point(random.nextInt(0, 500).toDouble(), random.nextInt(0, 500).toDouble()), 100.0)
    var theta = 0.0

    init {


        val canvasRenderer = CanvasRenderer(GeometryCanvas(this))
    }

    fun paint(graphics: Graphics2D) {


        triangle1.translateX(10 * Math.cos(theta)).translateY(10 * Math.sin(theta)).render(graphics)
        triangle2.translateX(Math.cos(theta)).translateY(Math.sin(theta)).rotateAround(theta, Point(300.0, 300.0)).render(graphics)
        circle.rotate(theta).render(graphics)
        circle.translateX(10.0).rotateAround(theta, Point(300.0, 300.0)).render(graphics)
        theta += 0.02

//        triangle1.translateX(random.nextInt(10, 20).toDouble()).translateY(random.nextInt(10, 20).toDouble()).render(graphics)
//        triangle2.translateX(random.nextInt(10, 20).toDouble()).translateY(random.nextInt(10, 20).toDouble()).render(graphics)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val board = Board()

        }
    }
}