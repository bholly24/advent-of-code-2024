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
        val line = lines.reduce { a, b -> a + b }
        val newLine = line.replace(Regex("don't\\(\\).*?do\\(\\)"), "").replace(Regex("don't\\(\\).*"), "")
        val sum = getSums(newLine)

        println("My sum with do/don't instructions is $sum")
        return sum
    }

    private fun getSums(line: String): Int {
        return line.split(Regex("mul(?=\\(\\d+,\\d+\\))"))
            .sumOf { s ->
                if (!(s.startsWith("(") && s.contains(","))) {
                    0
                } else {
                    val spl = s.split(",")
                    val dOne = spl[0].replace("(", "").toInt()
                    val dTwo = spl[1].split(")")[0].toInt()
                    dOne * dTwo
                }
            }
    }
}
