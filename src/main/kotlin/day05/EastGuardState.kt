package day05

class EastGuardState : GuardState {
    override fun getStraightCharacter(): Char = '>'
    override fun getTurnCharacter(): Char = 'V'
    override fun getIndexDiffsToCheck() = Pair(0, 1)
}