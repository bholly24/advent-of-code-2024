package day08

import java.io.File

class DayEight(path: String) {
    private val file = File(path).readLines()[0].map { it.toString().toInt() }

    fun partA(): Long {
        var fileId = 0
        val l = mutableListOf<Int>()

        file.chunked(2).forEach {
            if (it[0] != 0) {
                for (i in 0 until it[0]) l.add(fileId)
                fileId++
            }
            if (it.size == 2 && it[1] != 0) {
                for (i in 0 until it[1]) l.add(-1)
            }
        }

        while (true) {
            val emptyIndex = l.indexOfFirst { it == -1 }
            if (emptyIndex == -1) break
            val lastNonEmptyIndex = l.indexOfLast { it != -1 }
            if (emptyIndex > lastNonEmptyIndex) break
            l[emptyIndex] = l[lastNonEmptyIndex]
            l[lastNonEmptyIndex] = -1
        }
        val checksum = getChecksum(l)
        println("Checksum is $checksum")
        return checksum
    }

    fun partB(): Long {
        var fileId = 0
        var index = 0
        var l = mutableListOf<MyFile>()

        file.chunked(2).forEach {
            if (it[0] != 0) {
                l.add(MyFile(fileId, it[0]))
                fileId++
                index += it[0]
            }
            if (it.size == 2 && it[1] != 0) {
                l.add(MyFile(-1, it[1]))
            }
        }
        while (true) {
            val lastNonEmptyIndex = l.indexOfLast { it.fileId != -1 && !it.hasBeenChecked }
            if (lastNonEmptyIndex == -1) break
            val lastNonEmpty = l[lastNonEmptyIndex]
            lastNonEmpty.hasBeenChecked = true

            val emptyIndex = l.indexOfFirst { it.fileId == -1 && it.size >= lastNonEmpty.size }
            if (emptyIndex == -1 || lastNonEmptyIndex < emptyIndex) continue

            val lastEmpty = l[emptyIndex]

            if (lastNonEmpty.size == lastEmpty.size) {
                l[emptyIndex] = lastNonEmpty
                l[lastNonEmptyIndex] = lastEmpty
            } else {
                l.add(lastNonEmptyIndex + 1, MyFile(-1, lastNonEmpty.size))
                l.removeAt(lastNonEmptyIndex)
                l[emptyIndex] = MyFile(-1, lastEmpty.size - lastNonEmpty.size)
                l.add(emptyIndex, lastNonEmpty)
            }
        }
        val checksum = getChecksum(l.flatMap { f -> List(f.size) { f.fileId } })
        println("Whole file moving checksum is $checksum")
        return checksum
    }

    private fun debugLine(l: List<MyFile>) =
        println(l.flatMap { f -> List(f.size) { if (f.fileId >= 0) f.fileId else "." } }.joinToString(""))

    private fun getChecksum(files: List<Int>): Long {
        return files.foldIndexed(0L) { index, acc, b -> acc + if (b > 0) (b * index) else 0 }
    }
}

data class MyFile(val fileId: Int, val size: Int, var hasBeenChecked: Boolean = false)
