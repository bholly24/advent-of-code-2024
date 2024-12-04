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
                if (y == 0) {
                    columnArray.add(c.toString())
                } else {
                    columnArray[x] += c.toString()
                }
            }
        }

        val diagonalArray = mutableListOf<String>()
        for (y in 1 until lines.size) {
            var st = ""
            for (x in lines[0].indices) {
                if (y + x < ymax) {
                    st += lines[y + x][x]
                }
            }
            diagonalArray.add(st)
        }

        for (x in lines[0].indices) {
            var st = ""
            for (y in lines.indices) {
                if (y + x < xmax) {
                    st += lines[y][x + y]
                }
            }
            diagonalArray.add(st)
        }

        for (x in lines[0].length - 1 downTo 0) {
            var st = ""
            for (y in lines.indices) {
                if (x - y >= 0) {
                    println("$y,${x - y}")
                    st += lines[y][x - y]
                }
            }
            diagonalArray.add(st)
        }

        for (y in 1 until lines.size - 1) {
            var st = ""
            for (x in lines[0].length - 1 downTo 0) {
                val newY = y + (xmax - x - 1)
                if (newY < ymax) {
                    println("${newY}, ${x}: ${lines[newY][x]}")
                    st += lines[newY][x]
                }
            }
            diagonalArray.add(st)
        }
        val l = countByLines(lines)
        val c = countByLines(columnArray)
        val d = countByLines(diagonalArray)
        val xmasCounter = l + c + d

        println("Counter: $xmasCounter")
        return xmasCounter
    }

    private fun countByLines(lines: List<String>): Int {
        val xmasForward = Regex("XMAS")
        val xmasBackward = Regex("SAMX")
        val doubleXmas = Regex("XMASAMX|SAMXMAS")
        val xmasCounter = lines.sumOf {
            var c = xmasForward.findAll(it).count() + xmasBackward.findAll(it).count()
//            if (it.contains(doubleXmas)) {
//                c++
//            }
            println("$c for $it")
            c
        }
        return xmasCounter
    }
}
