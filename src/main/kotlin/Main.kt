import day00.DayZero
import fileHelper.FileHelper
import printer.AdventPrinter

fun main() {
    AdventPrinter.introduction()

    AdventPrinter.partOne(0)
    val dayZero = DayZero(FileHelper.puzzleFileForDay(0))
    dayZero.partA()

    AdventPrinter.partTwo(0)
    dayZero.partB()

}
