package main.Day02

import java.lang.Integer.signum
import kotlin.math.abs
import main.readInput

fun main() {

    // Or read a large test input from the `src/Day02_shortList.txt` file:
    val shortInput = readInput("src/main/kotlin/main/Day02/Day02_shortList")
    val testInput = readInput("src/main/kotlin/main/Day02/Day02_list")

    check(countSafeReports(shortInput) == 2)
    check(!isSafeReport("6 7 4 2 1"))
    check(!isSafeReport("1 2 7 8 9"))
    check(!isSafeReport("9 7 6 2 1"))
    check(!isSafeReport("1 3 2 4 5"))
    check(!isSafeReport("8 6 4 4 1"))
    check(isSafeReport("1 3 6 7 9"))
    check(!isSafeReport("17 20 23 21 22 23 24 22"))
    check(isSafeReport("24 27 30 33 35 37 38 41"))

    val nSafeReportsWholeFile = countAndPrintSafeReports(testInput)
    println("nSafeReportsWholeFile = $nSafeReportsWholeFile")
}

fun isSafeReport(s: String): Boolean {

    val levels = s.split(" ").map { it.toInt() }
    var diff = 0

    for (i in 0..levels.size - 2) {
        val previousDiff = diff
        diff = levels[i] - levels[i + 1]
        // if the signum of the diff changed, that's an indication that the new level changed the trend.
        if (signum(previousDiff) != 0 && signum(previousDiff) != signum(diff)) {
            return false
        } else if (abs(diff) > 3 || abs(diff) < 1) {
            return false
        }
    }
    return true
}

fun countSafeReports(input: List<String>): Int {
    var nSafeReports = 0
    for (i in input.indices) {
        nSafeReports += if (isSafeReport(input[i])) 1 else 0
    }
    return nSafeReports
}

fun countAndPrintSafeReports(input: List<String>): Int {
    var nSafeReports = 0
    for (i in input.indices) {
        val isSafeReport = isSafeReport(input[i])
        nSafeReports += if (isSafeReport) 1 else 0
        println(input[i] + " is safe: $isSafeReport" )
    }
    return nSafeReports
}
