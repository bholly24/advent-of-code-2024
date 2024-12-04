package day03

import fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class DayThreeTest {

@Test
 fun partA() {
  val dayThree = DayThree(FileHelper.testFileForDay(3))
   assertEquals(18, dayThree.partA())
 }

  @Test
  fun partATestSmall() {
   val dayThree = DayThree(FileHelper.getAdditionalTestFile(3, "input-small"))
   assertEquals(7, dayThree.partA())
  }
}