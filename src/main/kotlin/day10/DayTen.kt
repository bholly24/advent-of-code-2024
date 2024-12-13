package day10

import java.io.File

class DayTen(path: String) {
    private val startingStones: List<Long> = File(path).readLines()[0].split(" ").map { it.toLong() }

    fun partA(): Long {
        return getMemoizedStones(listOf(10, 10, 5))
    }

    fun partB(): Long {
        return getMemoizedStones(listOf(25, 25, 25))
    }

    private fun getMemoizedStones(breaks: List<Int>): Long {
        val stones = startingStones
        var totalFromFirstBreak = 0L
        var total = 0L
        val firstMemoizationMap = mutableMapOf<Long, Long>()
        val secondMemoizationMap = mutableMapOf<Long, Long>()

        stones.forEach {
            var firstBreakStones = listOf(it)
            for (i in 0 until breaks[0]) firstBreakStones = calculateStones(firstBreakStones)

            firstBreakStones.forEach { firstBreakStone ->
                totalFromFirstBreak = 0L
                if (firstMemoizationMap.containsKey(firstBreakStone)) {
                    total += firstMemoizationMap.getValue(firstBreakStone)
                } else {
                    var secondBreakStones = listOf(firstBreakStone)
                    for (i in 0 until breaks[1]) {
                        secondBreakStones = calculateStones(secondBreakStones)
                    }
                    secondBreakStones.forEach { secondBreakStone ->
                        if (secondMemoizationMap.containsKey(secondBreakStone)) {
                            total += secondMemoizationMap.getValue(secondBreakStone)
                            totalFromFirstBreak += secondMemoizationMap.getValue(secondBreakStone)
                        } else {
                            var finalBreakStones = listOf(secondBreakStone)
                            for (i in 0 until breaks[2]) {
                                finalBreakStones = calculateStones(finalBreakStones)
                            }
                            if (secondMemoizationMap.containsKey(secondBreakStone)) throw Error("Reassignment is a bug")
                            secondMemoizationMap[secondBreakStone] = finalBreakStones.size.toLong()
                            total += finalBreakStones.size
                            totalFromFirstBreak += finalBreakStones.size
                        }
                    }
                    if (firstMemoizationMap[firstBreakStone] != null) throw Error("Reassignment is a bug")
                    firstMemoizationMap[firstBreakStone] = totalFromFirstBreak
                }
            }
        }

        println("Stone number for ${breaks.sum()} iterations is $total")
        return total
    }

    private fun calculateStones(stones: List<Long>): List<Long> {
        return stones.flatMap {
            when {
                it == 0L -> listOf(1)
                it.toString().length % 2 == 0 -> {
                    val split = it.toString()
                    listOf(
                        split.substring(0 until split.length / 2).toLong(),
                        split.substring(split.length / 2).toLong()
                    )
                }

                else -> listOf(it * 2024L)
            }
        }
    }
}