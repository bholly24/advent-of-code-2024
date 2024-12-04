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
                    st += lines[newY][x]
                }
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
                val c = lines[y][x]
                if (c == 'A') {
                    val ym = y + 1 < ymax
                    val ymin = y - 1 >= 0
                    val xm = x + 1 < xmax
                    val xmin = x - 1 >= 0

                    val candidateChars = mutableListOf<Char>()

                    if (ym && xm) {
                        candidateChars.add(lines[y + 1][x + 1])
                    }
                    if (ym && xmin) {
                        candidateChars.add(lines[y + 1][x - 1])
                    }
                    if (ymin && xm) {
                        candidateChars.add(lines[y - 1][x + 1])
                    }
                    if (ymin && xmin) {
                        candidateChars.add(lines[y - 1][x - 1])
                    }
                    if (candidateChars.count { it == 'S' } == 2 && candidateChars.count { it == 'M' } == 2) {
                        if (candidateChars[0] == candidateChars[1] || candidateChars[0] == candidateChars[2]) {
                            sum += 1
                        }
                    }
                }
            }
        }
        println("The total X-MAS patterns found is $sum")
        return sum
    }

    private fun countByLines(lines: List<String>): Int {
        val xmasForward = Regex("XMAS")
        val xmasBackward = Regex("SAMX")
        val xmasCounter = lines.sumOf {
            xmasForward.findAll(it).count() + xmasBackward.findAll(it).count()
        }
        return xmasCounter
    }
}