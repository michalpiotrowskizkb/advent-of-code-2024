package main.Day01

import kotlin.math.abs
import main.readInput

fun main() {

    // Or read a large test input from the `src/Day02_shortList.txt` file:
    val testInput = readInput("src/main/kotlin/main/Day01/Day01_test")

    val (leftList, rightList) = testInput
        .map { it.split("   ") }
        .map { it[0].toInt() to it[1].toInt() }
        .unzip()

    val sortedLeftList = leftList.sorted()
    val sortedRightList = rightList.sorted()

    val totalDistance = sortedLeftList.zip(sortedRightList)
        .sumOf { (left, right) -> abs(left - right) }

    println("totalDistance = $totalDistance")
}
