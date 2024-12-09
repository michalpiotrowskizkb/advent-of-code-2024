package main.Day07

import main.readInput

val OPERATORS_2 = mutableListOf<MathOp>(Long::plus, Long::times, Long::concat) // +, *, ||

fun Long.concat(other: Long): Long {
    return ("" + this + other).toLong()
}

fun main() {
    val input = readInput("src/main/kotlin/main/Day07/Day07_input")
    val shortInput = readInput("src/main/kotlin/main/Day07/Day07_shortInput")
    assert(generateOpCombinations(OPERATORS_2, 3).contains(mutableListOf(Long::plus, Long::times, Long::concat)))
    check(sumValidEquations2(shortInput) == 11387L)
    println("result = ${sumValidEquations2(input)}")
}

fun sumValidEquations2(input: List<String>): Long {
    var summed = 0L
    input.forEach equationLoop@{ equation ->
        // e.g. 210 to " 11 6 16 20"
        val (result, operandsLine) =
            equation.split(":").get(0).toLong() to
                equation.split(":").get(1)

        val operands = operandsLine.trimStart()
            .split(" ")
            .map { it.toLong() } // e.g. [11, 6, 16, 20]

        generateOpCombinations(OPERATORS_2, operands.size - 1)
            .forEach {
                val opCombination = it
                if (operands.foldIndexed(operands.get(0)) { index, acc, currentNumber ->
                        if (index == 0) {
                            acc
                        } else {
                            opCombination[index-1].invoke(acc, currentNumber)
                        }
                    } == result) {
                    summed += result
                    return@equationLoop
                }
            }
    }
    return summed
}