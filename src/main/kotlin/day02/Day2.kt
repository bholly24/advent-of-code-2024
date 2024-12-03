package day02

import java.io.File

class Day2(filePath: String) {
    private val lines = File(filePath).readLines()

    fun partA(): Int {
        val sum = lines.sumOf { getSums(it) }
        println("Total sum of multiplications is $sum")
        return sum
    }

    fun partB(): Int {
        val dont = Regex("don't\\(\\)")
        val canDo = Regex("do\\(\\)")

        val doLine = lines
            .joinToString("")
            .replace(Regex("$dont.*?$canDo|$dont.*"), "")

        val sum = getSums(doLine)
        println("My sum with do/don't instructions is $sum")
        return sum
    }

    private fun getSums(line: String): Int {
        val mulPattern = Regex("mul\\((\\d+),(\\d+)\\)")
        return mulPattern.findAll(line)
            .sumOf { matchResult ->
                val (first, second) = matchResult.destructured
                first.toInt() * second.toInt()
            }
    }
}
