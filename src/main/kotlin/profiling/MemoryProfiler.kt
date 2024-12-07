package profiling

object MemoryProfiler {
    // Run before and after object creation or at an interval to measure runtime memory
    fun printMemoryUsage() {
        val runtime = Runtime.getRuntime()
        val usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024)
        val maxMemory = runtime.maxMemory() / (1024 * 1024)
        println("Used memory: ${usedMemory}MB / ${maxMemory}MB")
    }
}