package day05

import fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayFiveTest {

    @Test
    fun partA() {
        val dayFive = DayFive(FileHelper.testFileForDay(5))
        assertEquals(41, dayFive.partA())
    }

    @Test
    fun partB() {
        val dayFive = DayFive(FileHelper.testFileForDay(5))
        assertEquals(6, dayFive.partB())
    }
}