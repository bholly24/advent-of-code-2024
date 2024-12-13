package day09

import UtilityTypes.Coord
import UtilityTypes.Grid
import java.io.File

class DayNine(path: String) {
    val nines = mutableMapOf<Coord, Set<Coord>>()
    var totalIndistinct = 0
    val trailMarkerIndices = mutableListOf<Coord>()
    private val grid: Grid<Int> = Grid(
        File(path).readLines().map { it.toCharArray().map { it.toString().toInt() } }
    )
    val pointGrid: Grid<TrailPoint> = Grid(grid.items.mapIndexed { y, row ->
        row.mapIndexed { x, i ->
            val point = TrailPoint(i, x, y)
            when (i) {
                0 -> {
                    point.nextPoints = getDiffNeighbors(point, 1)
                    trailMarkerIndices.add(Coord(x, y))
                }
                else -> point.nextPoints = getDiffNeighbors(point, 1)
            }
            point
        }
    })

    fun partA(): Int {
        trailMarkerIndices.forEach {
            getTotal(it, pointGrid.get(it), pointGrid)
        }

        val total = nines.map { nines[it.key]!!.size }.sum()

        println("My total is $total")
        return total
    }

    fun partB(): Int {
        trailMarkerIndices.forEach {
            getTotalIndistinct(it, pointGrid.get(it), pointGrid)
        }

        println("Total indistinct is $totalIndistinct")
        return totalIndistinct
    }

    private fun getTotal(start: Coord, point: TrailPoint, pointGrid: Grid<TrailPoint>) {
        if (point.value == 9) {
            if (nines.containsKey(start)) {
                val l = nines.get(start)!!.toMutableSet()
                l.add(point.coord)
                nines[start] = l
            } else {
                nines[start] = setOf(point.coord)
            }
        }
        point.nextPoints.forEach {
            getTotal(start, pointGrid.get(it), pointGrid)
        }
    }


    private fun getTotalIndistinct(start: Coord, point: TrailPoint, pointGrid: Grid<TrailPoint>) {
        if (point.value == 9) {
           totalIndistinct += 1
        }
        point.nextPoints.forEach {
            getTotalIndistinct(start, pointGrid.get(it), pointGrid)
        }
    }

    private fun getDiffNeighbors(point: TrailPoint, diff: Int): List<Coord> {
        return listOf(
            Coord(point.x + 1, point.y),
            Coord(point.x - 1, point.y),
            Coord(point.x, point.y + 1),
            Coord(point.x, point.y - 1)
        ).filter { it.x in 0 until grid.xMax && it.y in 0 until grid.yMax && grid.items[it.y][it.x] == point.value + diff }
    }
}

data class TrailPoint(
    val value: Int,
    val x: Int,
    val y: Int,
    var nextPoints: List<Coord> = listOf(),
    var previousPoints: List<Coord> = listOf(),
    val coord: Coord = Coord(x, y)
)
