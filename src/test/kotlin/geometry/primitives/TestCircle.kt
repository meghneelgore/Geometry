package geometry.primitives

import geometry.shapes.Circle
import junit.framework.TestCase.*
import org.junit.Test

class TestCircle {

    @Test
    fun testCircumferenceAndArea() {
        val c = Circle(Point(0.0, 0.0), 1.0)
        assertEquals("Wrong circumference calculated", 2 * Math.PI, c.circumference)
        assertEquals("Wrong area calculated", Math.PI, c.area)
    }

    @Test
    fun testInsideCircle() {
        val c = Circle(Point(0.0, 0.0), 1.0)
        assertTrue("Point inside circle found to be not so", c.insideCircle(Point(0.5, 0.5)))
        assertFalse("Point outside circle found to be inside it", c.insideCircle(Point(1.1, 1.1)))
        assertFalse("Point on circle found to be inside it", c.insideCircle(Point(Math.cos(1.0), Math.sin(1.0))))
    }

    @Test
    fun testOnCircle() {
        val c = Circle(Point(0.0, 0.0), 1.0)
        assertTrue("Point on circle found to be not so", c.onCircle(Point(Math.cos(1.0), Math.sin(1.0))))
        assertFalse("Point inside circle found to be on it", c.onCircle(Point(0.5, 0.5)))
        assertFalse("Point outside circle found to be on it", c.onCircle(Point(1.0, 1.0)))
    }

    @Test
    fun testOutsideCircle() {
        val c = Circle(Point(0.0, 0.0), 1.0)
        assertTrue("Point outside circle found to be not so", c.outsideCircle(Point(1.0, 1.0)))
        assertFalse("Point inside circle found to be outside it", c.outsideCircle(Point(0.5, 0.5)))
        assertFalse("Point on circle found to be outside it", c.outsideCircle(Point(Math.cos(1.0), Math.sin(1.0))))
    }
}