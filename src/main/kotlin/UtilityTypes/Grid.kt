package UtilityTypes

data class Grid<T>(val items: List<List<T>>) {
    val yMax = items.size
    val xMax = items[0].size
}
