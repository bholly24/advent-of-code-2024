package day00

import fileHelper.FileHelper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class DayZeroTest {
    private lateinit var dayZero: DayZero;

    @BeforeEach
    fun setup() {
        dayZero = DayZero(FileHelper.testFileForDay(0))
    }

    @Test
    fun doesSomething() {
        assertEquals(11, dayZero.partA())
    }

    @Test
    fun partB() {
        assertEquals(31, dayZero.partB())
    }
}