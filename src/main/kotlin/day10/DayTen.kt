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
        var lTotal = 0L
        var total = 0L
        val firstMemoizationMap = mutableMapOf<Long, Long>()
        val secondMemoizationMap = mutableMapOf<Long, Long>()

        stones.forEach {
            var l = listOf(it)
            for (i in 0 until breaks[0]) {
                l = calculateStones(l)
            }

            l.forEach { s ->
                lTotal = 0L
                if (firstMemoizationMap.containsKey(s)) {
                    total += firstMemoizationMap.getValue(s)
                } else {
                    var sl = listOf(s)
                    for (i in 0 until breaks[1]) {
                        sl = calculateStones(sl)
                    }
                    sl.forEach { sQ ->
                        if (secondMemoizationMap.containsKey(sQ)) {
                            total += secondMemoizationMap.getValue(sQ)
                            lTotal += secondMemoizationMap.getValue(sQ)
                        } else {
                            var sQL = listOf(sQ)
                            for (i in 0 until breaks[2]) {
                                sQL = calculateStones(sQL)
                            }
                            if (secondMemoizationMap.containsKey(sQ)) throw Error("Reassignment is a bug")
                            secondMemoizationMap[sQ] = sQL.size.toLong()
                            total += sQL.size
                            lTotal += sQL.size
                        }
                    }
                    if (firstMemoizationMap[s] != null) throw Error("Reassignment is a bug")
                    firstMemoizationMap[s] = lTotal
                }
            }
        }

        println("Stone number for ${breaks.sum()} iterations is $total")
        return total
    }

    private fun calculateStones(stones: List<Long>): List<Long> {
        val newStones = mutableListOf<Long>()
        stones.forEach {
            if (it == 0L) {
                newStones.add(1)
            } else if ((it.toString().length % 2) == 0) {
                var s = it.toString()
                newStones.add(s.substring(0 until s.length / 2).toLong())
                newStones.add(s.substring(s.length / 2).toLong())
            } else {
                newStones.add(it * 2024L)
            }
        }
        return newStones
    }
}