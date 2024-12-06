package day05

import java.io.File

class DayFive(path: String) {
    private val immutableGrid = File(path).readLines().map { it.toCharArray().toList() }
    private var grid: List<MutableList<Char>> = immutableGrid.map { it.toMutableList() }
    private var indexGrid: List<List<IndexChar>> = immutableGrid.map { it.map { c -> IndexChar(c) } }
    private var indexChange = ""
    private val obstacle = '#'
    private val guardPathIndicator = 'X'

    fun partA(): Int {
        var ranOffTheGrid = false
        while (!ranOffTheGrid) {
            try {
                for (y in grid.indices) {
                    for (x in grid[0].indices) {
                        val state = guardStateFactory(grid[y][x]) ?: continue
                        val nextIndexDiff = state.getIndexDiffsToCheck()
                        var nextChar = grid[y + nextIndexDiff.first][x + nextIndexDiff.second]
                        if (nextChar == obstacle) {
                            grid[y][x] = state.getTurnCharacter()
                        } else {
                            grid[y + nextIndexDiff.first][x + nextIndexDiff.second] = state.getStraightCharacter()
                            grid[y][x] = guardPathIndicator
                        }
                    }
                }
            } catch (e: IndexOutOfBoundsException) {
                grid.forEach { println(it.joinToString("")) }
                ranOffTheGrid = true
            }
        }

        val cellsVisited = grid.sumOf { it.count { c -> c == guardPathIndicator } } + 1
        println("Cells visited: $cellsVisited")
        return cellsVisited
    }

    fun partB(): Int {
        var loopsCreated = 0
        for (y in indexGrid.indices) {
            for (x in indexGrid[0].indices) {
                indexGrid = immutableGrid.map { it.map { c -> IndexChar(c) } }
                if (indexGrid[y][x].char == '.') {
                    indexGrid[y][x].char = '#'
                    indexChange = "$y,$x"
                    if (guardCaughtInLoop(indexGrid)) {
                        loopsCreated++
                    }
                    indexGrid[y][x].char = '.'
                }
            }
        }
        println("Loops created : $loopsCreated")
        return loopsCreated
    }

    private fun guardCaughtInLoop(indexGrid: List<List<IndexChar>>): Boolean {
        var ranOffTheGrid = false
        var guardStuckInLoop = false

        while (!ranOffTheGrid || !guardStuckInLoop) {
            try {
                for (y in indexGrid.indices) {
                    for (x in indexGrid[0].indices) {
                        val c = indexGrid[y][x].char
                        when (c) {
                            '^' -> if (indexGrid[y - 1][x].char != '#') {
                                indexGrid[y - 1][x].char = '^'
                                indexGrid[y][x].index++
                                indexGrid[y][x].char = 'X'
                            } else {
                                indexGrid[y][x].char = '>'
                            }

                            '>' -> if (indexGrid[y][x + 1].char != '#') {
                                indexGrid[y][x + 1].char = '>'
                                indexGrid[y][x].char = 'X'
                                indexGrid[y][x].index++
                            } else {
                                indexGrid[y][x].char = 'V'
                            }

                            'V' -> if (indexGrid[y + 1][x].char != '#') {
                                indexGrid[y + 1][x].char = 'V'
                                indexGrid[y][x].char = 'X'
                                indexGrid[y][x].index++
                            } else {
                                indexGrid[y][x].char = '<'
                            }

                            '<' -> if (indexGrid[y][x - 1].char != '#') {
                                indexGrid[y][x - 1].char = '<'
                                indexGrid[y][x].char = 'X'
                                indexGrid[y][x].index++
                            } else {
                                indexGrid[y][x].char = '^'
                            }
                        }
                    }
                }
                val m = indexGrid.maxOf { it.maxOf { i -> i.index } }
                if (m > 4) {
                    return true
                }
            } catch (e: IndexOutOfBoundsException) {
                return false
            }
        }
        return false
    }

    private fun guardStateFactory(c: Char): GuardState? {
        return when (c) {
            '^' -> NorthGuardState()
            '>' -> EastGuardState()
            'V' -> SouthGuardState()
            '<' -> WestGuardState()
            else -> null
        }
    }
}


data class IndexChar(var char: Char, var index: Int = 0)

interface GuardState {
    fun getTurnCharacter(): Char
    fun getStraightCharacter(): Char
    fun getIndexDiffsToCheck(): Pair<Int, Int>
}

class NorthGuardState : GuardState {
    override fun getStraightCharacter(): Char = '^'
    override fun getTurnCharacter(): Char = '>'
    override fun getIndexDiffsToCheck() = Pair(-1, 0)
}

class EastGuardState : GuardState {
    override fun getStraightCharacter(): Char = '>'
    override fun getTurnCharacter(): Char = 'V'
    override fun getIndexDiffsToCheck() = Pair(0, 1)
}

class SouthGuardState : GuardState {
    override fun getStraightCharacter(): Char = 'V'
    override fun getTurnCharacter(): Char = '<'
    override fun getIndexDiffsToCheck() = Pair(1, 0)
}

class WestGuardState : GuardState {
    override fun getStraightCharacter(): Char = '<'
    override fun getTurnCharacter(): Char = '^'
    override fun getIndexDiffsToCheck() = Pair(0, -1)
}