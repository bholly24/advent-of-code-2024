package day08

import fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayEightTest {

    @Test
    fun partA() {
        val dayEight = DayEight(FileHelper.testFileForDay(8))
        assertEquals(1928, dayEight.partA())
    }

    @Test
    fun partB() {
        val dayEight = DayEight(FileHelper.testFileForDay(8))
        assertEquals(2858, dayEight.partB())
    }
}