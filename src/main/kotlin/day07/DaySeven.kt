package day07

import UtilityTypes.Coord
import UtilityTypes.Grid
import java.io.File

class DaySeven(path: String) {
    private val grid: Grid<Char> = Grid(File(path).readLines().map { line -> line.toCharArray().toList() })

    fun partA(): Int {
        val antinodeTotal = getAntinodeTotal(::getFirstAntinodes)
        println("Total antinodes for part a are $antinodeTotal")
        return antinodeTotal
    }

    fun partB(): Int {
        val antinodeTotal = getAntinodeTotal(::getAllAntinodesOnLine)
        println("Total antinodes for part b are $antinodeTotal")
        return antinodeTotal
    }

    private fun getAntinodeTotal(antinodeCalculator: (Item, Item) -> (List<Coord>)): Int {
        val antennaeMap = getAntennaMap()
        val antinodes = antennaeMap.keys.flatMap { key ->
            val antennae = antennaeMap[key] ?: emptyList()
            antennae.flatMap { a ->
                antennae
                    .filter { b -> a != b }
                    .flatMap { b -> antinodeCalculator(a, b) }
            }
        }.toSet()
        return antinodes.size
    }

    private fun getAllAntinodesOnLine(itemOne: Item, itemTwo: Item): List<Coord> {
        val itemList = mutableListOf<Item>()
        itemList.add(itemOne)
        itemList.add(itemTwo)

        val (deltaY, deltaX) = getReducedSlope(itemTwo, itemOne)

        var newY = itemTwo.y + deltaY
        var newX = itemTwo.x + deltaX
        while (newY in 0 until grid.yMax && newX in 0 until grid.xMax) {
            itemList.add(Item(newX, newY, itemOne.c))
            newY += deltaY
            newX += deltaX
        }

        newY = itemOne.y - deltaY
        newX = itemOne.x - deltaX
        while (newY in 0 until grid.yMax && newX in 0 until grid.xMax) {
            itemList.add(Item(newX, newY, itemOne.c))
            newY -= deltaY
            newX -= deltaX
        }
        return itemList.map { it.toCoord() }
    }

    private fun getFirstAntinodes(itemOne: Item, itemTwo: Item): List<Coord> {
        val deltaY = itemTwo.y - itemOne.y
        val deltaX = itemTwo.x - itemOne.x
        return listOf(
            Coord(itemTwo.x + deltaX, itemTwo.y + deltaY),
            Coord(itemOne.x - deltaX, itemOne.y - deltaY),
        )
            .filter { it.y in 0 until grid.yMax && it.x in 0 until grid.xMax }
    }

    private fun getAntennaMap(): Map<Char, List<Item>> {
        val antennaeMap = mutableMapOf<Char, MutableList<Item>>()
        grid.items.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if (c != '.') {
                    val l = antennaeMap.getOrDefault(c, mutableListOf())
                    l.add(Item(x, y, c))
                    antennaeMap[c] = l
                }
            }
        }
        return antennaeMap
    }

    companion object {
        fun getReducedSlope(itemTwo: Item, itemOne: Item): Pair<Int, Int> {
            val dy = itemTwo.y - itemOne.y
            val dx = itemTwo.x - itemOne.x
            if (dy % dx == 0) return Pair(dy / dx, 1)
            if (dx % dy == 0) return Pair(1, dx / dy)
            return Pair(dy, dx)
        }
    }
}

data class Item(val x: Int, val y: Int, val c: Char) {
    fun toCoord() = Coord(x, y)
}
