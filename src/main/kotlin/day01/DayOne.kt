package day01

import java.io.File
import kotlin.math.abs

class DayOne(filePath: String) {
    private val reports: List<MutableList<Int>> = File(filePath).readLines()
        .map { it.split(Regex("\\s+")).map(String::toInt).toMutableList() }

    fun partA(): Int {
        val count = reports.sumOf { if (isReportValid(it)) 1.toInt() else 0 }
        println("Total safe reports is $count")
        return count
    }

    fun partB(): Int {
        val count = reports.sumOf {
            if (isReportValid(it)) {
                1.toInt()
            } else {
                val badLists: List<List<Int>> = List(it.size)
                { index -> it.filterIndexed { myI, _ -> index != myI }}
                if (badLists.any { r -> isReportValid(r) }) 1 else 0
            }
        }

        println("Total count with problem dampener is $count")
        return count
    }

    private fun isReportValid(report: List<Int>): Boolean {
        var reportedTrendIsPositive: Boolean? = null
        return report.zipWithNext().all { (current, previous) ->
            val diff = current - previous
            if (reportedTrendIsPositive != null && diff > 0 != reportedTrendIsPositive) return false
            if (abs(diff) !in 1..3) return false

            reportedTrendIsPositive = current > previous
            true
        }
    }
}