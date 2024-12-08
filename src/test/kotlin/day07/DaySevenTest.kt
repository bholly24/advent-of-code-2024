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

    @Test
    fun getSlopeOfOne() {
        val s = DaySeven.getSlope(Item(3, 3, 'a'), Item(1, 1, 'a'))
        assertEquals(1, s.first)
        assertEquals(1, s.second)

        val d = DaySeven.getSlope(Item(2, 2, 'a'), Item(1, 1, 'a'))
        assertEquals(1, d.first)
        assertEquals(1, d.second)
    }

    @Test
    fun getReducedSlope() {
        val s = DaySeven.getSlope(Item(4, 8, 'a'), Item(1, 2, 'a'))
        assertEquals(2, s.first)
        assertEquals(1, s.second)
    }
}