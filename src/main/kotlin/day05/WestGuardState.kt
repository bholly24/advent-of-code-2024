package day05

class WestGuardState : GuardState {
    override fun getStraightCharacter(): Char = '<'
    override fun getTurnCharacter(): Char = '^'
    override fun getIndexDiffsToCheck() = Pair(0, -1)
}