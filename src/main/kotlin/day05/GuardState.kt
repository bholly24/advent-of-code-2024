package day05

interface GuardState {
    fun getTurnCharacter(): Char
    fun getStraightCharacter(): Char
    fun getIndexDiffsToCheck(): Pair<Int, Int>
}
