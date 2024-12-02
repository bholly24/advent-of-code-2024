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
                val badLists: List<List<Int>> = it
                    .mapIndexed { index, i -> it.filterIndexed { myI, _ -> index != myI }}
                if (badLists.any { r -> isReportValid(r) }) 1 else 0
            }
        }

        println("Total count with problem dampener is $count")
        return count
    }

    private fun violatesRules(item: Int, priorItem: Int, priorSign: Boolean?): Boolean {
        val diff = item - priorItem
        val isPositive = diff > 0
        return (priorSign != null && priorSign != isPositive) || abs(diff) < 1 || abs(diff) > 3
    }

    private fun isReportValid(report: List<Int>): Boolean {
        var isPositive: Boolean? = null

        for (i in report.indices) {
            if (i > 0) {
                val violates = violatesRules(report[i], report[i - 1], isPositive)
                if (violates) {
                    break
                } else {
                    isPositive = report[i] - report[i - 1] > 0
                }
                if (i == report.size - 1) {
                    return true
                }
            }
        }
        return false
    }
}