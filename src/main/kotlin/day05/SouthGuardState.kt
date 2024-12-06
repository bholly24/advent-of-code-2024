package day05

class SouthGuardState : GuardState {
    override fun getStraightCharacter(): Char = 'V'
    override fun getTurnCharacter(): Char = '<'
    override fun getIndexDiffsToCheck() = Pair(1, 0)
}