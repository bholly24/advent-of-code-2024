package day04

import java.io.File

class DayFour(filePath: String) {
    private val orderInstructions = File(filePath).readLines()
        .filter { it.contains('|') }
        .map { it.split("|").map(String::toInt) }
    private val sequences = File(filePath).readLines().filter { it.contains(',') }

    fun partA(): Int {
        val totalCorrect = sequences.sumOf {
            val intSequence = it.split(",").map(String::toInt)
            if (allItemsInOrder(intSequence)) getMiddleItem(intSequence) else 0
        }
        println("Total correct is $totalCorrect")
        return totalCorrect
    }

    fun partB(): Int {
        var sumOfFixed = 0
        sequences.forEach { it ->
            val intSequence = it.split(",").map(String::toInt)
            val ok = allItemsInOrder(intSequence)
            if (!ok) {
                val updatedSequence = intSequence.map { it }.toMutableList()
                while(!allItemsInOrder(updatedSequence)) {
                    val ins = orderInstructions.first { instruction ->
                        !instructionIsInOrder(updatedSequence, instruction)
                    }
                    updatedSequence.removeAt(updatedSequence.indexOf(ins[0]))
                    updatedSequence.add(updatedSequence.indexOf(ins[1]), ins[0])
                }
                sumOfFixed += getMiddleItem(updatedSequence)
            }
        }
        println("Total correct after fixing is $sumOfFixed")
        return sumOfFixed
    }

    private fun getMiddleItem(updatedSequence: List<Int>) = updatedSequence[(updatedSequence.size - 1) / 2]

    private fun allItemsInOrder(intSequence: List<Int>): Boolean {
        return orderInstructions.all { instruction ->
            instructionIsInOrder(intSequence, instruction)
        }
    }

    private fun instructionIsInOrder(
        intSequence: List<Int>,
        instruction: List<Int>
    ): Boolean {
        val one = intSequence.indexOf(instruction[0])
        val two = intSequence.indexOf(instruction[1])
        return one == -1 || two == -1 || one < two
    }
}