package day07

import java.io.File

class DaySeven(path: String) {
    private val grid: List<List<Char>> = File(path).readLines().map { line -> line.toCharArray().toList() }
    private val antennaeMap = mutableMapOf<Char, MutableList<Item>>()
    private val antinodes = mutableSetOf<Pair<Int, Int>>()

    fun partA(): Int {
        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if (c != '.') {
                    val l = antennaeMap.getOrDefault(c, mutableListOf())
                    l.add(Item(x, y, c))
                    antennaeMap[c] = l
                }
            }
        }

        antennaeMap.keys.forEach {
            for (a in antennaeMap[it]!!) {
                for (b in antennaeMap[it]!!) {
                    if (a.x == b.x && a.y == b.y) continue
                    antinodes.addAll(
                        getLinesBetween(a, b)
                        .filter { it.y in grid.indices && it.x in grid[0].indices }
                        .map { Pair(it.y, it.x) })
                }
            }
            println("Calculated antinodes for $it")
        }

        println("Total antinodes are ${antinodes.size}")
        return antinodes.size
    }

    fun partB(): Int {
        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if (c != '.') {
                    val l = antennaeMap.getOrDefault(c, mutableListOf())
                    l.add(Item(x, y, c))
                    antennaeMap[c] = l
                }
            }
        }

        antennaeMap.keys.forEach {
            for (a in antennaeMap[it]!!) {
                for (b in antennaeMap[it]!!) {
                    if (a.x == b.x && a.y == b.y) continue
                    antinodes.addAll(
                        getAllLinesFrom(a, b, grid.size, grid[0].size)
                        .map { Pair(it.y, it.x) })
                }
            }
        }

        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, _ -> if (antinodes.contains(Pair(y, x))) print("#") else print(".") }
            println()
        }

//        println(antinodes.sortedBy { it.second }.sortedBy { it.first })

        println("Total antinodes are ${antinodes.size}")
        return antinodes.size
    }

    private fun getAllLinesFrom(itemOne: Item, itemTwo: Item, yMax: Int, xMax: Int): List<Item> {
        val itemList = mutableListOf<Item>()
        itemList.add(itemOne)
        itemList.add(itemTwo)

        val (deltaY, deltaX) = getSlope(itemTwo, itemOne)

        var newY = itemTwo.y + deltaY
        var newX = itemTwo.x + deltaX
        while (newY in 0 until yMax && newX in 0 until xMax) {
            itemList.add(Item(newX, newY, itemOne.c))
            newY += deltaY
            newX += deltaX
        }

        newY = itemOne.y - deltaY
        newX = itemOne.x - deltaX
        while (newY in 0 until yMax && newX in 0 until xMax) {
            itemList.add(Item(newX, newY, itemOne.c))
            newY -= deltaY
            newX -= deltaX
        }

        return itemList
    }

    private fun getLinesBetween(itemOne: Item, itemTwo: Item): List<Item> {
        val y = itemTwo.y - itemOne.y
        val x = itemTwo.x - itemOne.x
        return listOf(
            Item(itemTwo.y + y, itemTwo.x + x, itemOne.c),
            Item(itemOne.y - y, itemOne.x - x, itemOne.c),
        )
    }

    companion object {
        fun getSlope(itemTwo: Item, itemOne: Item): Pair<Int, Int> {
            val dy = itemTwo.y - itemOne.y
            val dx = itemTwo.x - itemOne.x
            if (dy % dx == 0) {
                return Pair(dy / dx, 1)
            }
            if (dx % dy == 0) {
                return Pair(1, dx / dy)
            }
            return Pair(dy, dx)
        }
    }
}

data class Item(val x: Int, val y: Int, val c: Char)
