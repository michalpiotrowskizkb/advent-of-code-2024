package main.Day07

import main.readInput

val OPERATORS = mutableListOf<MathOp>(Long::plus, Long::times) // +, *

fun main() {
    val input = readInput("src/main/kotlin/main/Day07/Day07_input")
    val shortInput = readInput("src/main/kotlin/main/Day07/Day07_shortInput")
    assert(generateOpCombinations(OPERATORS, 3).contains(mutableListOf(Long::plus, Long::times, Long::times)))
    check(sumValidEquations(shortInput) == 3749L)
    println("result = ${sumValidEquations(input)}")
}

typealias MathOp = (Long, Long) -> Long

fun sumValidEquations(input: List<String>): Long {
    var summed = 0L
    input.forEach equationLoop@{ equation ->
        // e.g. 210 to " 11 6 16 20"
        val (result, operandsLine) =
            equation.split(":").get(0).toLong() to
                equation.split(":").get(1)

        val operands = operandsLine.trimStart()
            .split(" ")
            .map { it.toLong() } // e.g. [11, 6, 16, 20]

        generateOpCombinations(OPERATORS, operands.size - 1)
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

// recursion example:
// +++; ++*; +**; *** ...
// [+, *], 3
//   [+, *], 2
//     [+, *], 1
//     return smallerCombnations = [[+], [*]]
//   return smallerCombnations = [[++], [+*], [*+], [**]]
//  ...
fun generateOpCombinations(operators: MutableList<MathOp>, length: Int): MutableList<MutableList<MathOp>> {
    if (length == 1) return operators.map { mutableListOf(it) }.toMutableList() // stop recursing, gives e.g. [mutableListOf('+'),mutableListOf('+')]

    val smallerCombinations = generateOpCombinations(operators, length - 1)
    val combinations = mutableListOf<MutableList<MathOp>>()

    for (smallerCombination in smallerCombinations) {
        for (operator in operators) {
            val copy = smallerCombination.toMutableList()
            copy.add(operator)
            combinations.add(copy)
        }
    }

    return combinations
}