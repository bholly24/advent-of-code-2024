package day11

import UtilityTypes.Coord
import UtilityTypes.Grid
import java.io.File

class DayEleven(path: String) {
    private val gardenGrid: Grid<Char> = Grid(File(path).readLines().map { it.toCharArray().toList() })
    private val plotCoords = mutableSetOf<Coord>()
    fun partA(): Int {
        val gardenPlotList = mutableListOf<GardenPlot>()
        var plotChar: Char? = null
        gardenGrid.items.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if (plotCoords.isEmpty()) {
                    plotChar = c
                    val newPoint = Coord(x, y)
                    plotCoords.add(newPoint)

                    val filtered = gardenGrid.getNeighbors(newPoint)
                        .filter { gardenGrid.get(it) == plotChar }

                    if (filtered.isNotEmpty()) {
                        getPlots(filtered, plotCoords, plotChar!!)
                    }
                    gardenPlotList.add(GardenPlot(plotCoords.toList(), getPlotPerimeter(plotCoords.toList())))
                    plotChar = null
                    plotCoords = mutableSetOf()
                }
            }
        }
        val totalPrice = gardenPlotList.sumOf { it.getPrice() }
        println("Total garden plot price is $totalPrice")
        return totalPrice
    }

    private fun getPlots(neighborList: List<Coord>, l: MutableSet<Coord>, plotChar: Char) {
        if (neighborList.isNotEmpty()) {
            neighborList.forEach {
                if (!l.contains(it)) {
                    l.add(it)
                    println(l.map { "${it.x},${it.y}" })
                    val newNeighbors = gardenGrid.getNeighbors(it)
                        .filter { gardenGrid.get(it) == plotChar && !l.contains(it) }
                    if (newNeighbors.isNotEmpty()) {
                        getPlots(newNeighbors, l, plotChar)
                    }
                }
            }
        }
    }

    private fun getPlotPerimeter(items: List<Coord>): Int =
        items.sumOf { 4 - gardenGrid.getNeighbors(it).size }
}

class GardenPlot(val items: List<Coord>, private val perimeter: Int) {
    fun getPrice() = items.size * perimeter
}