package main

fun main() {

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")

    val (leftList, rightList) = testInput
        .map { it.split("   ") }
        .map { it[0].toInt() to it[1].toInt() }
        .unzip()

    val alreadyCalculated = mutableMapOf<Int, Int>()
    var similarityScore = 0

    leftList.zip(rightList)
        .forEach {
            if (!alreadyCalculated.containsKey(it.first)) {
                val leftRight = it
                val nOccurrences = rightList.count { it == leftRight.first }
                alreadyCalculated.put(it.first, it.first * nOccurrences)
            }
            similarityScore += alreadyCalculated[it.first]!!
        }

    println("similarityScore = $similarityScore")
}
