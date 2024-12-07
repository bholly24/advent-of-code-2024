package day06

import fileHelper.FileHelper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DaySixTest {

    @Test
    fun partA() {
        val daySix = DaySix(FileHelper.testFileForDay(6))
        assertEquals(3749, daySix.partA())
    }

    @Test
    fun partB() {
        val daySix = DaySix(FileHelper.testFileForDay(6))
        assertEquals(11387, daySix.partB())
    }

    @Test
    fun operatorOutput() {
        val output = listOf(NumberOperator(81L, '+'), NumberOperator(40L, '*'), NumberOperator(27, '+'))
        val daySix = DaySix(FileHelper.testFileForDay(6))
        assertEquals(
            3267,
            daySix.getOperatorOutput(output)
        )
    }
}