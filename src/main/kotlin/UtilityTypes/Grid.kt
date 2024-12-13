package UtilityTypes

data class Grid<T>(val items: List<List<T>>) {
    val yMax = items.size
    val xMax = items[0].size

    fun get(coord: Coord): T {
        return items[coord.y][coord.x]
    }

    fun getNeighbors(point: Coord): List<Coord> {
        return listOf(
        Coord(point.x + 1, point.y),
        Coord(point.x - 1, point.y),
        Coord(point.x, point.y + 1),
        Coord(point.x, point.y - 1)
        ).filter(::isInBounds)
    }

    private fun isInBounds(c: Coord): Boolean = c.x in 0 until xMax && c.y in 0 until yMax
}
