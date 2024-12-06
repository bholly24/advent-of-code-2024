package day05

class NorthGuardState : GuardState {
    override fun getStraightCharacter(): Char = '^'
    override fun getTurnCharacter(): Char = '>'
    override fun getIndexDiffsToCheck() = Pair(-1, 0)
}