import day00.DayZero
import day01.DayOne
import day02.Day2
import day03.DayThree
import day04.DayFour
import day05.DayFive
import day06.DaySix
import day07.DaySeven
import day08.DayEight
import day09.DayNine
import day10.DayTen
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

//    AdventPrinter.partOne(5)
//    val dayFive = DayFive(FileHelper.puzzleFileForDay(5))
//    dayFive.partA()
//
//    AdventPrinter.partTwo(5)
//    dayFive.partB()

//    AdventPrinter.partOne(6)
//    val daySix = DaySix(FileHelper.puzzleFileForDay(6))
//    daySix.partA()
//
//    AdventPrinter.partTwo(6)
//    AdventPrinter.tooSlowToRunInAdventLoop(6, 'b')

//    daySix.partB()
//    AdventPrinter.partOne(7)
//    var daySeven = DaySeven(FileHelper.puzzleFileForDay(7))
//    daySeven.partA()
//
//    AdventPrinter.partTwo(7)
//    daySeven = DaySeven(FileHelper.puzzleFileForDay(7))
//    daySeven.partB()
//
//    AdventPrinter.partOne(8)
//    val dayEight = DayEight(FileHelper.puzzleFileForDay(8))
//    dayEight.partA()

//    AdventPrinter.partTwo(8)
//    dayEight.partB()

    AdventPrinter.partOne(9)
    val dayNine = DayNine(FileHelper.puzzleFileForDay(9))
    dayNine.partA()

    dayNine.partB()

    AdventPrinter.partOne(10)
    val dayTen = DayTen(FileHelper.puzzleFileForDay(10))
    dayTen.partA()

    AdventPrinter.partTwo(10)
    dayTen.partB()
}
