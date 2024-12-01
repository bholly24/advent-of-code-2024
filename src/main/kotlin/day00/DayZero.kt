package day00

import java.io.File
import kotlin.math.abs

class DayZero(filePath: String) {
    private var lineOne = mutableListOf<Int>()
    private var lineTwo = mutableListOf<Int>()

    init {
        getLinesFromFile(filePath)
    }

    fun partA(): Int {
        lineOne.sort()
        lineTwo.sort()
        val diff = lineOne.zip(lineTwo).sumOf { (a, b) -> abs(a - b) }

        println("Total diff is $diff")
        return diff
    }

    fun partB(): Int {
        val similarityScore = lineOne.sumOf { it * lineTwo.count { i -> it == i } }
        println("Total similarity score is $similarityScore")
        return similarityScore
    }

    private fun getLinesFromFile(filePath: String) {
        readLines(filePath).forEach {
            val digits = it.split(Regex("\\s+")).map(String::toInt)
            lineOne.add(digits[0])
            lineTwo.add(digits[1])
        }
    }

    private fun readLines(filePath: String): List<String> {
        return File(filePath).readLines();
    }
}
