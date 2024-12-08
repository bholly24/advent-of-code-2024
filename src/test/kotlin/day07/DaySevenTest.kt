package day07

import fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DaySevenTest {

    @Test
    fun partA() {
        val daySeven = DaySeven(FileHelper.testFileForDay(7))
        assertEquals(14, daySeven.partA())
    }

    @Test
    fun partB() {
        val daySeven = DaySeven(FileHelper.testFileForDay(7))
        assertEquals(34, daySeven.partB())
    }

    @Test
    fun partBSmall() {
        val daySeven = DaySeven(FileHelper.getAdditionalTestFile(7, "input-small"))
        assertEquals(9, daySeven.partB())
    }
}