package day00

import java.io.File
import kotlin.math.abs

class DayZero(filePath: String) {
    private var lineOne: List<Int>
    private var lineTwo: List<Int>

    init {
        val lines = File(filePath).readLines()
            .map {  it.split(Regex("\\s+")).map(String::toInt) }
        lineOne = lines.map {it[0] }.sorted()
        lineTwo = lines.map { it[1] }.sorted()
    }

    fun partA(): Int {
        val diff = lineOne.zip(lineTwo).sumOf { (a, b) -> abs(a - b) }

        println("Total diff is $diff")
        return diff
    }

    fun partB(): Int {
        val similarityScore = lineOne.sumOf { it * lineTwo.count { i -> it == i } }
        println("Total similarity score is $similarityScore")
        return similarityScore
    }
}
