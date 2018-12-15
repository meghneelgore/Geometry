package board

import geometry.primitives.Point
import rendering.CanvasRenderer
import rendering.GeometryCanvas
import java.awt.Graphics2D
import java.util.*
import kotlin.random.Random

class Board {



    init {

        CanvasRenderer(GeometryCanvas(this))
    }

    fun paint(graphics: Graphics2D) {
        val pointsList: MutableList<Point?> = MutableList(0) { null }
        val random = Random(Date().time)
        val p1 = Point(random.nextDouble(from = 0.0, until = 600.0), random.nextDouble(from = 0.0, until = 600.0))
        val p2 = Point(random.nextDouble(from = 0.0, until = 600.0), random.nextDouble(from = 0.0, until = 600.0))
        val p3 = Point(random.nextDouble(from = 0.0, until = 600.0), random.nextDouble(from = 0.0, until = 600.0))
        var currentPoint = p1
        for (i in 1..10000) {
            val rand = random.nextInt(from = 0, until = 5)
            when (rand) {
                0 -> currentPoint = Point.midPoint(p1, currentPoint) / 3.0
                1 -> currentPoint = Point.midPoint(p2, currentPoint) / 3.0
                2 -> currentPoint = Point.midPoint(p3, currentPoint) / 3.0

            }
            currentPoint.render(graphics)
            //pointsList.add(currentPoint)
        }
//        for (p in pointsList) {
//            p?.render(graphics)
//        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Board()

        }
    }
}

