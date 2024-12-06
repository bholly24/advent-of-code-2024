package day05

import java.io.File

class DayFive(path: String) {
    private val immutableGrid = File(path).readLines().map { it.toCharArray().toList() }
    private var grid: List<MutableList<Char>> = immutableGrid.map { it.toMutableList() }
    private var indexGrid: List<List<IndexChar>> = immutableGrid.map { it.map { c -> IndexChar(c) } }
    private var indexChange = ""
    private val obstacle = '#'
    private val guardPathIndicator = 'X'
    private val untrammeledPathIndicator = '.'

    fun partA(): Int {
        var ranOffTheGrid = false
        while (!ranOffTheGrid) {
            try {
                for (y in grid.indices) {
                    for (x in grid[0].indices) {
                        val state = guardStateFactory(grid[y][x]) ?: continue
                        val nextIndexDiff = state.getIndexDiffsToCheck()
                        val nextChar = grid[y + nextIndexDiff.first][x + nextIndexDiff.second]
                        if (nextChar == obstacle) {
                            grid[y][x] = state.getTurnCharacter()
                        } else {
                            grid[y + nextIndexDiff.first][x + nextIndexDiff.second] = state.getStraightCharacter()
                            grid[y][x] = guardPathIndicator
                        }
                    }
                }
            } catch (e: IndexOutOfBoundsException) {
                ranOffTheGrid = true
            }
        }

        val cellsVisited = grid.sumOf { it.count { c -> c == guardPathIndicator } } + 1
        println("Cells visited: $cellsVisited")
        return cellsVisited
    }

    fun partB(): Int {
        partA()
        var loopsCreated = 0
        for (y in indexGrid.indices) {
            for (x in indexGrid[0].indices) {
                indexGrid = immutableGrid.map { it.map { c -> IndexChar(c) } }
                if (grid[y][x] != untrammeledPathIndicator &&
                    grid[y][x] != obstacle &&
                    indexGrid[y][x].char == untrammeledPathIndicator) {
                    indexGrid[y][x].char = obstacle
                    indexChange = "$y,$x"
                    if (guardCaughtInLoop(indexGrid)) {
                        loopsCreated++
                    }
                    indexGrid[y][x].char = untrammeledPathIndicator
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
                        val state = guardStateFactory(indexGrid[y][x].char) ?: continue
                        val nextIndexDiff = state.getIndexDiffsToCheck()
                        val nextChar = indexGrid[y + nextIndexDiff.first][x + nextIndexDiff.second].char
                        if (nextChar == obstacle) {
                            indexGrid[y][x].char = state.getTurnCharacter()
                        } else {
                            indexGrid[y + nextIndexDiff.first][x + nextIndexDiff.second].char = state.getStraightCharacter()
                            indexGrid[y][x].char = guardPathIndicator
                            indexGrid[y][x].index++
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
