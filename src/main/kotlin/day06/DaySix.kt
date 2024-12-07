package day06

import java.io.File

class DaySix(path: String) {
    private val equations: List<EquationItems> = File(path).readLines().map { line -> parseLine(line) }

    fun partA(): Long {
        val total: Long = equations.sumOf { if (canBeMadeToWork(it)) it.answer else 0L }
        println("Total is $total")
        return total
    }

    fun partB(): Long {
        val total: Long = equations.sumOf { if (canBeMadeToWorkThreeOperators(it)) it.answer else 0L }
        println("Total with concat operator is $total")
        return total
    }

    fun canBeMadeToWork(equationItem: EquationItems): Boolean {
        val possibleEquations = mutableListOf<MutableList<NumberOperator>>()
        for (i in equationItem.inputs) {
            if (possibleEquations.size == 0) {
                possibleEquations.add(mutableListOf(NumberOperator(i, '+')))
                possibleEquations.add(mutableListOf(NumberOperator(i, '*')))
            } else {
                for (j in possibleEquations.size - 1 downTo 0) {
                    possibleEquations.add(j + 1, possibleEquations[j].toMutableList())
                }
                possibleEquations.chunked(2).forEach {
                    it[0].add(NumberOperator(i, '+'))
                    it[1].add(NumberOperator(i, '*'))
                }
            }
        }
        return possibleEquations.any { equationItem.answer == getOperatorOutput(it) }
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

    private fun canBeMadeToWorkThreeOperators(equationItem: EquationItems): Boolean {
        val possibleEquations = mutableListOf<MutableList<NumberOperator>>()
        for (i in equationItem.inputs) {
            if (possibleEquations.size == 0) {
                possibleEquations.add(mutableListOf(NumberOperator(i, '+')))
                possibleEquations.add(mutableListOf(NumberOperator(i, '*')))
                possibleEquations.add(mutableListOf(NumberOperator(i, '|')))
            } else {
                for (j in possibleEquations.size - 1 downTo 0) {
                    possibleEquations.add(j + 1, possibleEquations[j].toMutableList())
                    possibleEquations.add(j + 2, possibleEquations[j].toMutableList())
                }
                possibleEquations.chunked(3).forEach {
                    it[0].add(NumberOperator(i, '+'))
                    it[1].add(NumberOperator(i, '*'))
                    it[2].add(NumberOperator(i, '|'))
                }
            }
        }
        return possibleEquations.any { equationItem.answer == getOperatorOutputThreeOptions(it) }
    }
}

data class EquationItems(val answer: Long, val inputs: List<Long>)
data class NumberOperator(var n: Long, val sign: Char)
