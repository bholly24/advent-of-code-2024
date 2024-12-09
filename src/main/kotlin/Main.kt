import day00.DayZero
import day01.DayOne
import day02.Day2
import day03.DayThree
import day04.DayFour
import day05.DayFive
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


    AdventPrinter.partOne(3)
    val dayThree = DayThree(FileHelper.puzzleFileForDay(3))
    dayThree.partA()

    AdventPrinter.partTwo(3)
    dayThree.partB()

    AdventPrinter.partOne(4)
    val dayFour = DayFour(FileHelper.puzzleFileForDay(4))
    dayFour.partA()
    AdventPrinter.partTwo(4)
    dayFour.partB()

    AdventPrinter.partOne(5)
    val dayFive = DayFive(FileHelper.puzzleFileForDay(5))
    dayFive.partA()

    AdventPrinter.partTwo(5)
    dayFive.partB()
}
