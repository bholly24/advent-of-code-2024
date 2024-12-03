package day02

import fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class Day2Test {
  val dayTwo = Day2(FileHelper.testFileForDay(2))

  // Test file augmented to handle issues like where(\\d+,\\d+)
@Test
 fun partA() {
  assertEquals(161 * 2, dayTwo.partA())
 }

  @Test
  fun partB() {
   val dayTwoTest = Day2(FileHelper.getAdditionalTestFile(2, "input-b"))
   assertEquals(48, dayTwoTest.partB())
  }
}