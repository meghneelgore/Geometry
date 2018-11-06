package geometry.primitives

import java.awt.Graphics2D

interface Shape {
    /**
     * Renders the Shape
     */
    fun render(graphics: Graphics2D)

    /**
     * Renders the Shape filled
     */
    fun renderFilled(graphics: Graphics2D)

    /**
     * Translates the current Shape and returns a new Shape translated in the x direction
     */
    fun translateX(translation: Double): Shape

    /**
     * Translates the current Shape and returns a new Shape translated in the y direction
     */
    fun translateY(translation: Double): Shape

    /**
     * Rotates the current Shape by the given angle and returns a new Shape
     */
    fun rotate(theta: Double): Shape

    /**
     * Rotates the current Shape by the given angle around the given point and returns a new Shape
     */
    fun rotateAround(theta: Double, aroundPoint: Point = Point(0.0, 0.0)): Shape
}