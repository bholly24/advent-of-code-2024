package day05

import java.io.File

class DayFive(path: String) {
    private val immutableGrid = File(path).readLines().map { it.toCharArray().toList() }
    private var grid: List<MutableList<Char>> = immutableGrid.map { it.toMutableList() }
    private var indexGrid: List<List<IndexChar>> = immutableGrid.map { it.map { c -> IndexChar(c) } }
    private val obstacle = '#'
    private val guardPathIndicator = 'X'
    private val untrammeledPathIndicator = '.'

    fun partA(): Int {
        var guardIndex = Pair(0, 0)
        for (y in grid.indices) {
            for (x in grid[0].indices) {
                if (grid[y][x] == '^') {
                    guardIndex = Pair(y, x)
                    break
                }
            }
        }
        while (true) {
            val y = guardIndex.first
            val x = guardIndex.second
            val state = guardStateFactory(grid[y][x]) ?: throw Error("This should be a guard")
            val nextIndexDiff = state.getIndexDiffsToCheck()
            val nextY = y + nextIndexDiff.first
            val nextX = x + nextIndexDiff.second
            if (nextY !in grid.indices || nextX !in grid[0].indices) {
                break
            }
            val nextChar = grid[y + nextIndexDiff.first][x + nextIndexDiff.second]
            if (nextChar == obstacle) {
                grid[y][x] = state.getTurnCharacter()
            } else {
                grid[nextY][nextX] = state.getStraightCharacter()
                guardIndex = Pair(nextY, nextX)
                grid[y][x] = guardPathIndicator
            }
        }

        val cellsVisited = grid.sumOf { it.count { c -> c == guardPathIndicator } } + 1
        println("Cells visited: $cellsVisited")
        return cellsVisited
    }

    fun partB(): Int {
        // Ensure grid has been run through to try and slightly optimize by only traveling the trammeled path
        partA()
        var loopsCreated = 0
        for (y in indexGrid.indices) {
            for (x in indexGrid[0].indices) {
                indexGrid = immutableGrid.map { it.map { c -> IndexChar(c) } }
                if (grid[y][x] != untrammeledPathIndicator &&
                    grid[y][x] != obstacle &&
                    indexGrid[y][x].char == untrammeledPathIndicator
                ) {
                    indexGrid[y][x].char = obstacle
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
        var guardIndex = Pair(0, 0)
        for (y in immutableGrid.indices) {
            for (x in immutableGrid[0].indices) {
                if (immutableGrid[y][x] == '^') {
                    guardIndex = Pair(y, x)
                    break
                }
            }
        }
        while (true) {
            val y = guardIndex.first
            val x = guardIndex.second

            val state = guardStateFactory(indexGrid[y][x].char) ?: throw Error("This should be a guard")
            val nextIndexDiff = state.getIndexDiffsToCheck()
            val nextY = y + nextIndexDiff.first
            val nextX = x + nextIndexDiff.second

            if (nextY !in grid.indices || nextX !in grid[0].indices) return false
            val nextChar = indexGrid[nextY][nextX].char

            if (nextChar == obstacle) {
                indexGrid[y][x].char = state.getTurnCharacter()
            } else {
                indexGrid[nextY][nextX].char = state.getStraightCharacter()
                guardIndex = Pair(nextY, nextX)
                indexGrid[y][x].char = guardPathIndicator
                indexGrid[y][x].index++
                if (indexGrid[y][x].guardOrientations.contains(state.getStraightCharacter())) {
                    return true
                } else {
                    indexGrid[y][x].guardOrientations.add(state.getStraightCharacter())
                }
            }
        }
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
