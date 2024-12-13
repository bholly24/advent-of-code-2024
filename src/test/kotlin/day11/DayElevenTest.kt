package day11

import fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayElevenTest {

    @Test
    fun partA() {
        val dayEleven = DayEleven(FileHelper.testFileForDay(11))
        assertEquals(1930, dayEleven.partA())
    }
}