package main.Day02

import java.lang.Integer.signum
import kotlin.math.abs
import main.readInput

fun main() {

    // Or read a large test input from the `src/Day02_shortList.txt` file:
    val shortInput = readInput("src/main/kotlin/main/Day02/Day02_shortList")
    val testInput = readInput("src/main/kotlin/main/Day02/Day02_list")

    check(countSafeReportsWithDampener(shortInput) == 4)
    check(isSafeReportWithDampener("6 7 4 2 1"))
    check(!isSafeReportWithDampener("1 2 7 8 9"))
    check(!isSafeReportWithDampener("9 7 6 2 1"))
    check(isSafeReportWithDampener("1 3 2 4 5"))
    check(isSafeReportWithDampener("8 6 4 4 1"))
    check(isSafeReportWithDampener("1 3 6 7 9"))
    check(!isSafeReportWithDampener("17 20 23 21 22 23 24 22"))
    check(isSafeReportWithDampener("24 27 30 33 35 37 38 41"))

    val nSafeReportsWholeFile = countAndPrintSafeReportsWithDampener(testInput)
    println("nSafeReportsWholeFile = $nSafeReportsWholeFile")
}

fun isSafeReportWithDampener(s: String): Boolean {
    val levels = s.split(" ").map { it.toInt() }
    return isSafeReportWithDampener(levels)
}

private fun isAnyDampenedVariationSafe(levels: List<Int>): Boolean {
    val resultsWithDampener = mutableListOf<Boolean>()
    for (i in levels.indices) {
        resultsWithDampener.add(isSafeReportWithDampener(levels.filterIndexed { index, _ -> index != i }, true))
    }
    return resultsWithDampener.any{ it }
}

private fun isSafeReportWithDampener(levels: List<Int>, dampeningActivated: Boolean = false): Boolean {
    var diff = 0
    if (dampeningActivated == true) print("🤠 Dampening on!")
    for (i in 0..levels.size - 2) {
        val previousDiff = diff
        diff = levels[i] - levels[i + 1]
        // if the signum of the diff changed, that's an indication that the new level changed the trend.
        if (signum(previousDiff) != 0 && signum(previousDiff) != signum(diff) || abs(diff) > 3 || abs(diff) < 1) {
            return if (!dampeningActivated) {
                isAnyDampenedVariationSafe(levels)
            } else {
                false
            }
        }
    }
    return true
}

fun countSafeReportsWithDampener(input: List<String>): Int {
    var nSafeReports = 0
    for (i in input.indices) {
        nSafeReports += if (isSafeReportWithDampener(input[i])) 1 else 0
    }
    return nSafeReports
}

fun countAndPrintSafeReportsWithDampener(input: List<String>): Int {
    var nSafeReports = 0
    for (i in input.indices) {
        val isSafeReportWithDampener = isSafeReportWithDampener(input[i])
        nSafeReports += if (isSafeReportWithDampener) 1 else 0
        println(input[i] + " is safe: $isSafeReportWithDampener" )
    }
    return nSafeReports
}
