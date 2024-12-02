package day01

import fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class DayOneTest {
 private  lateinit var dayOne: DayOne
  @BeforeEach
  fun setup() {
   dayOne = DayOne(FileHelper.testFileForDay(1))
  }

@Test
 fun partA() {
  assertEquals(2, dayOne.partA())
 }

 @Test
 fun partB() {
  assertEquals(4, dayOne.partB())
 }
}