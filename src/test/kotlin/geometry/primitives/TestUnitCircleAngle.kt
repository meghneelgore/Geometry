package geometry.primitives

import org.junit.Assert.assertEquals
import org.junit.Test

class TestUnitCircleAngle {

    @Test
    fun testUnitCircleAngle() {
        val unitCircleAngle1 = UnitCircleAngle(3 * Math.PI)
        assertEquals("Unit circle angle calculated wrongly", Math.PI, unitCircleAngle1.reducedAngle, 0.0)

        val unitCircleAngle2 = UnitCircleAngle(2 * Math.PI + 1)
        assertEquals("Unit circle angle calculated wrongly", 1.0, unitCircleAngle2.reducedAngle, 0.0)

        val unitCircleAngle3 = UnitCircleAngle(-3.5 * Math.PI)
        assertEquals("Unit circle angle calculated wrongly", -1.5 * Math.PI, unitCircleAngle3.reducedAngle, 0.0)
    }
}