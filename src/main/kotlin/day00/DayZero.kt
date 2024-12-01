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
            val digits = it.split("   ")
                .map { i -> i.filter { d -> d.isDigit() } }
            lineOne.add(digits[0].toInt());
            lineTwo.add(digits[1].toInt())
        }
    }

    private fun readLines(filePath: String): List<String> {
        return File(filePath).readLines();
    }
}
