package day02

import java.io.File

class Day2(filePath: String) {
    private val instructions = File(filePath)
        .readLines()
        .joinToString("")

    fun partA(): Int {
        val sum = getSums(instructions)
        println("Total sum of multiplications is $sum")
        return sum
    }

    fun partB(): Int {
        val dont = Regex("don't\\(\\)")
        val canDo = Regex("do\\(\\)")

        val doLine = instructions.replace(Regex("$dont.*?$canDo|$dont.*"), "")

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
