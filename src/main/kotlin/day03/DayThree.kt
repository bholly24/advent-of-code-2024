package day03

import java.io.File

class DayThree(path: String) {
    private val lines = File(path).readLines()
    private val xmax = lines[0].length
    private val ymax = lines.size

    fun partA(): Int {
        val columnArray = mutableListOf<String>()
        lines.forEachIndexed { y, s ->
            s.forEachIndexed { x, c ->
                if (y == 0) columnArray.add(c.toString()) else columnArray[x] += c.toString()
            }
        }

        val diagonalArray = mutableListOf<String>()
        for (y in 1 until lines.size) {
            var st = ""
            for (x in lines[0].indices) {
                if (y + x < ymax) st += lines[y + x][x]
            }
            diagonalArray.add(st)
        }

        for (x in lines[0].indices) {
            var st = ""
            for (y in lines.indices) {
                if (y + x < xmax) st += lines[y][x + y]
            }
            diagonalArray.add(st)
        }

        for (x in lines[0].length - 1 downTo 0) {
            var st = ""
            for (y in lines.indices) {
                if (x - y >= 0) st += lines[y][x - y]
            }
            diagonalArray.add(st)
        }

        for (y in 1 until lines.size - 1) {
            var st = ""
            for (x in lines[0].length - 1 downTo 0) {
                val newY = y + (xmax - x - 1)
                if (newY < ymax) st += lines[newY][x]
            }
            diagonalArray.add(st)
        }

        val xmasCounter = countByLines(lines) + countByLines(columnArray) + countByLines(diagonalArray)
        println("XMAS counter total is  $xmasCounter")
        return xmasCounter
    }

    fun partB(): Int {
        var sum = 0
        for (y in lines.indices) {
            for (x in lines[0].indices) {
                if (lines[y][x] != 'A' || !diagonalsAreInBounds(x, y)) continue
                val charList = listOf(
                    lines[y + 1][x + 1],
                    lines[y + 1][x - 1],
                    lines[y - 1][x + 1],
                    lines[y - 1][x - 1]
                )
                if (listIsValidMAS(charList)) sum += 1
            }
        }
        println("The total X-MAS patterns found is $sum")
        return sum
    }

    private fun listIsValidMAS(charList: List<Char>): Boolean {
        return charList.count { it == 'S' } == 2 &&
                charList.count { it == 'M' } == 2 &&
                (charList[0] == charList[1] || charList[0] == charList[2])
    }

    private fun countByLines(lines: List<String>): Int {
        val xmasForward = Regex("XMAS")
        val xmasBackward = Regex("SAMX")
        val xmasCounter = lines.sumOf {
            xmasForward.findAll(it).count() + xmasBackward.findAll(it).count()
        }
        return xmasCounter
    }

    private fun diagonalsAreInBounds(x: Int, y: Int): Boolean {
        return listOf(y + 1 < ymax, y - 1 >= 0, x + 1 < xmax, x - 1 >= 0).all { it }
    }
}