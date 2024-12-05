package day04

import fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayFourTest {

    @Test
    fun partA() {
        val dayFour = DayFour(FileHelper.testFileForDay(4))
        assertEquals(143, dayFour.partA())
    }

    @Test
    fun partB() {
        val dayFour = DayFour(FileHelper.testFileForDay(4))
        assertEquals(123, dayFour.partB())
    }
}