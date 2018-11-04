package geometry.primitives

data class Circle(val center: Point, val radius: Double) {
    val circumference = 2 * Math.PI * radius
    val area = Math.PI * Math.pow(radius, 2.0)

    fun onCircle(point: Point): Boolean {
        val radialSegment = Segment(center, point)
        return radialSegment.length == radius
    }

    fun insideCircle(point: Point): Boolean {
        val radialSegment = Segment(center, point)
        return radialSegment.length < radius
    }

    fun outsideCircle(point: Point): Boolean {
        val radialSegment = Segment(center, point)
        return radialSegment.length > radius
    }
}