import day00.DayZero
import day01.DayOne
import day02.Day2
import fileHelper.FileHelper
import printer.AdventPrinter

fun main() {
    AdventPrinter.introduction()

    AdventPrinter.partOne(0)
    val dayZero = DayZero(FileHelper.puzzleFileForDay(0))
    dayZero.partA()

    AdventPrinter.partTwo(0)
    dayZero.partB()

    AdventPrinter.partOne(1)
    val dayOne = DayOne(FileHelper.puzzleFileForDay(1))
    dayOne.partA()

    AdventPrinter.partTwo(1)
    dayOne.partB()

    AdventPrinter.partOne(2)
    val dayTwo = Day2(FileHelper.puzzleFileForDay(2))
    dayTwo.partA()

    AdventPrinter.partTwo(2)
    dayTwo.partB()
}
