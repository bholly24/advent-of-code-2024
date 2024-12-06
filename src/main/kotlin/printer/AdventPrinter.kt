package printer

object AdventPrinter {
    fun introduction() {
        divider()
        println("Advent of code entries for Brendan Holly")
        println("Happy Holidays!")
    }

    fun skippedForNow(day: Int) {
        divider()
        println("Day $day has been skipped for now")
    }

    fun tooSlowToRunInAdventLoop(day: Int, part: Char) {
        divider()
        println("Whoops, Day $day part $part is too slow to run in our advent loop :|")
    }

    fun partOne(day: Int) {
        divider()
        println("Day $day")
    }

    fun partTwo(day: Int) {
        println()
        println("Day $day Part 2")
    }

    private fun divider() {
        println("------------------------------------------------")
    }
}