package main.Day04

import main.readInput

var matrix: Array<Array<Char>> = arrayOf()

fun main() {
    val input = readInput("src/main/kotlin/main/Day04/Day04_list")
    val shortInput = readInput("src/main/kotlin/main/Day04/Day04_shortList")
    check(countXmasOccurrences(shortInput) == 18)
    println("result = ${countXmasOccurrences(input)}")
}

fun countXmasOccurrences(input: List<String>): Int {
    // fill a matrix
    matrix = Array(input.size) { i -> input[i].toCharArray().toTypedArray() }
    var result = 0
    // iterate over all points and check XMAS in all directions ➡⬅⬆⬇↗↘↙↖
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            result += countSafe(i, j, hasXmasLeft) // ⬅ left
            result += countSafe(i, j, hasXmasRight) // ➡ right
            result += countSafe(i, j, hasXmasDown) // ↕ down
            result += countSafe(i, j, hasXmasUp) //  ↕ up
            result += countSafe(i, j, hasXmasDiagNE) // ↗ North East
            result += countSafe(i, j, hasXmasDiagSE) // ↘ South East
            result += countSafe(i, j, hasXmasDiagSW) // ↙ South West
            result += countSafe(i, j, hasXmasDiagNW) // ↖ North West
        }
    }
    return result
}

//↖

fun countSafe(x: Int, coordY: Int, hasXmasCheckImpl: (Int, Int) -> Boolean): Byte {
    try {
        return if (hasXmasCheckImpl(x, coordY))
            1
        else 0
    } catch (ArrayIndexOutOfBoundsException: ArrayIndexOutOfBoundsException) {
        return 0;
    }
}

//
val hasXmasDiagNW = {x: Int, coordY: Int ->
    matrix[coordY][x] == 'X'
    && matrix[coordY - 1][x - 1] == 'M'
    && matrix[coordY - 2][x - 2] == 'A'
    && matrix[coordY - 3][x - 3] == 'S'}

val hasXmasDiagSW = {x: Int, coordY: Int ->
    matrix[coordY][x] == 'X'
        && matrix[coordY + 1][x - 1] == 'M'
        && matrix[coordY + 2][x - 2] == 'A'
        && matrix[coordY + 3][x - 3] == 'S'
}

val hasXmasDiagSE = {x: Int, coordY: Int ->
    matrix[coordY][x] == 'X'
        && matrix[coordY + 1][x + 1] == 'M'
        && matrix[coordY + 2][x + 2] == 'A'
        && matrix[coordY + 3][x + 3] == 'S'
}

val hasXmasDiagNE = {x: Int, coordY: Int ->
    matrix[coordY][x] == 'X'
        && matrix[coordY - 1][x + 1] == 'M'
        && matrix[coordY - 2][x + 2] == 'A'
        && matrix[coordY - 3][x + 3] == 'S'
}

val hasXmasUp = {x: Int, coordY: Int ->
    matrix[coordY][x] == 'X'
        && matrix[coordY - 1][x] == 'M'
        && matrix[coordY - 2][x] == 'A'
        && matrix[coordY - 3][x] == 'S'
}

val hasXmasDown = {x: Int, coordY: Int ->
    matrix[coordY][x] == 'X'
        && matrix[coordY + 1][x] == 'M'
        && matrix[coordY + 2][x] == 'A'
        && matrix[coordY + 3][x] == 'S'
}

val hasXmasLeft = {x: Int, coordY: Int ->
    matrix[coordY][x] == 'X'
        && matrix[coordY][x - 1] == 'M'
        && matrix[coordY][x - 2] == 'A'
        && matrix[coordY][x - 3] == 'S'
}

val hasXmasRight = {x: Int, coordY: Int ->
    matrix[coordY][x] == 'X'
        && matrix[coordY][x + 1] == 'M'
        && matrix[coordY][x + 2] == 'A'
        && matrix[coordY][x + 3] == 'S'
}

private fun printMatrix(matrix: Array<Array<Char>>) {
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            print(matrix[i][j])
        }
        println()
    }
}