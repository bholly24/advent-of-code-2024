package day09

import fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class DayNineTest {

@Test
 fun partA() {
  val dayNine = DayNine(FileHelper.testFileForDay(9))
  assertEquals(36, dayNine.partA())
 }

  @Test
  fun partASmall() {
   val dayNine = DayNine(FileHelper.getAdditionalTestFile(9, "input-small"))
   assertEquals(1, dayNine.partA())
  }

  @Test
  fun partB() {
   val dayNine = DayNine(FileHelper.testFileForDay(9))
   assertEquals(81, dayNine.partB())
  }
}