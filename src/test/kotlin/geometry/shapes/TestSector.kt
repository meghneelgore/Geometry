package geometry.shapes

import geometry.primitives.Point
import geometry.primitives.UnitCircleAngle
import junit.framework.Assert.assertEquals
import org.junit.Test

class TestSector {

    @Test(expected = IllegalArgumentException::class)
    fun testStartAngleGreaterThanEndAngle() {
        Sector(Point(0.0, 0.0), 10.0, UnitCircleAngle(0.0), UnitCircleAngle(-Math.PI / 2))
    }

    @Test
    fun testTheta() {
        val sector = Sector(Point(0.0, 0.0), 10.0, UnitCircleAngle(0.0), UnitCircleAngle(Math.PI / 2))
        assertEquals("Wrong theta calculated", Math.PI / 2, sector.theta)
    }

    @Test
    fun testArea() {
        val sector = Sector(Point(0.0, 0.0), 10.0, UnitCircleAngle(Math.PI / 2), UnitCircleAngle(Math.PI))
        assertEquals("Wrong area calculated", 78.53981633974483, sector.area, 0.0)

    }
}