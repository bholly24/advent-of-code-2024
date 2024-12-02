package day01

import java.io.File
import kotlin.math.abs

class DayOne(filePath: String) {
    private val reports: List<MutableList<Int>>

    init {
        reports = File(filePath).readLines()
            .map { it.split(Regex("\\s+")).map(String::toInt).toMutableList() }
    }

    fun partA(): Int {
        val count = getTotalValidReports(reports)
        println("Count is $count")
        return count
    }

    fun partB(): Int {
        var count = 0

        for (index in reports.indices) {
            val report = reports[index]
            var isPositive: Boolean? = null
            for (i in report.indices) {
                if (i > 0) {
                    val isBad = violatesRules(report[i], report[i - 1], isPositive)
                    if (isBad) {
                        val badLists = mutableListOf<List<Int>>()
                        for (badI in report.indices) {
                            badLists.add(report.filterIndexed { myI, _ -> badI != myI })
                        }
                        if (getTotalValidReports(badLists) > 0) {
                            count++
                        }
                        break
                    } else {
                        isPositive = report[i] - report[i - 1] > 0
                    }
                    if (i == report.size - 1) {
                        count++
                    }
                }
            }
        }

        println("Total count is $count")
        return count
    }

    private fun violatesRules(item: Int, priorItem: Int, priorSign: Boolean?): Boolean {
        val diff = item - priorItem
        val isPositive = diff > 0
        if ((priorSign != null && priorSign != isPositive) || abs(diff) < 1 || abs(diff) > 3) {
            return true
        } else {
            return false
        }
    }

    private fun getTotalValidReports(reports: List<List<Int>>): Int {
        var count = 0

        for (index in reports.indices) {
            var report = reports[index]
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
                        count++
                    }
                }
            }
        }
        return count
    }
}