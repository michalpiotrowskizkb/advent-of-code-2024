package main;

import kotlin.math.abs

fun main() {

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")

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
