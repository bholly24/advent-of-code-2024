package day06

import profiling.MemoryProfiler
import java.io.File

class DaySix(path: String) {
    private val equations: List<EquationItems> = File(path).readLines().map { line -> parseLine(line) }

    fun partA(): Long {
        val total: Long = equations.sumOf { if (canBeMadeToWork(it, listOf('+', '*'), ::getOperatorOutput)) it.answer else 0L }
        println("Total is $total")
        return total
    }

    fun partB(): Long {
        val total: Long = equations.sumOf { if (canBeMadeToWork(it, listOf('+', '*', '|'), ::getOperatorOutputThreeOptions)) it.answer else 0L }
        println("Total with concat operator is $total")
        return total
    }

    fun getOperatorOutput(operators: List<NumberOperator>): Long {
        var total = 0L
        for (i in operators.indices) {
            if (i == operators.size - 1) break
            if (i == 0) total += operators[i].n
            if (operators[i].sign == '+') {
                total += operators[i + 1].n
            } else {
                total *= operators[i + 1].n
            }
        }
        return total
    }

    fun getOperatorOutputThreeOptions(operators: List<NumberOperator>): Long {
        var total = 0L
        for (i in operators.indices) {
            if (i == operators.size - 1) break
            if (i == 0) total += operators[i].n
            if (operators[i].sign == '+') {
                total += operators[i + 1].n
            } else if (operators[i].sign == '*') {
                total *= operators[i + 1].n
            } else {
                total = "$total${operators[i + 1].n}".toLong()
            }
        }
        return total
    }

    private fun parseLine(line: String): EquationItems {
        val s = line.split(":")
        return EquationItems(s[0].toLong(), s[1].trim().split(Regex("\\s+")).map(String::toLong))
    }

    private fun canBeMadeToWork(equationItem: EquationItems, operators: List<Char>, calculateTotal: (List<NumberOperator>) -> Long): Boolean {
        val possibleEquations = mutableListOf<MutableList<NumberOperator>>()
        for (i in equationItem.inputs) {
            if (possibleEquations.size == 0) {
                operators.forEach {
                    possibleEquations.add(mutableListOf(NumberOperator(i, it)))
                }
            } else {
                for (j in possibleEquations.size - 1 downTo 0) {
                    operators.forEachIndexed { i, _ ->
                        possibleEquations.add(j + i, possibleEquations[j].toMutableList())
                    }
                }
                possibleEquations.chunked(operators.size).forEach {
                    operators.forEachIndexed { index, o -> it[index].add(NumberOperator(i, o))
                    }
                }
            }
        }
        return possibleEquations.any { equationItem.answer == calculateTotal(it) }
    }
}

data class EquationItems(val answer: Long, val inputs: List<Long>)
data class NumberOperator(var n: Long, val sign: Char)
