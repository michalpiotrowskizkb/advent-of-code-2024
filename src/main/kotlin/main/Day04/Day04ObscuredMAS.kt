package main.Day04

import main.readInput

var matrix2: Array<Array<Char>> = arrayOf()

fun main() {
    val input = readInput("src/main/kotlin/main/Day04/Day04_list")
    val shortInput = readInput("src/main/kotlin/main/Day04/Day04_shortList")
    check(countXmasOccurrences2(shortInput) == 9)
    println("result = ${countXmasOccurrences2(input)}")
}

//MAS Downward             MAS Sideways        SAM Downward       SAM Sideways
//     _  _  _ -> x+        _  _  _ -> x+       _  _  _ -> x+      _  _  _ -> x+
//    |M  .  M             |M  .  S            |S  .  S           |S  .  M
//    |.  A  .             |.  A  .            |.  A  .           |.  A  .
//    |S  .  S             |M  .  S            |M  .  M           |S  .  M
//y+ \/                 y+\/               y+ \/              y+ \/
fun countXmasOccurrences2(input: List<String>): Int {
    // fill a matrix2
    matrix2 = Array(input.size) { i -> input[i].toCharArray().toTypedArray() }
    var result = 0
    // iterate over all points and check two crossing MAS or SAM in all directions ➡⬅⬆⬇↗↘↙↖
    for (i in matrix2.indices) {
        for (j in matrix2[i].indices) {
            result += countSafe2(i, j, hasMASDownward)
            result += countSafe2(i, j, hasMASSideways)
            result += countSafe2(i, j, hasSAMDownward)
            result += countSafe2(i, j, hasSAMSideways)
        }
    }
    return result
}

fun countSafe2(x: Int, coordY: Int, hasXmasCheckImpl: (Int, Int) -> Boolean): Byte {
    try {
        return if (hasXmasCheckImpl(x, coordY))
            1
        else 0
    } catch (ArrayIndexOutOfBoundsException: ArrayIndexOutOfBoundsException) {
        return 0;
    }
}

val hasMASDownward = { x: Int, coordY: Int ->
    matrix2[coordY - 1][x - 1] == 'M'
        && matrix2[coordY][x] == 'A'
        && matrix2[coordY + 1][x + 1] == 'S'
        &&
        matrix2[coordY - 1][x + 1] == 'M'
        && matrix2[coordY][x] == 'A'
        && matrix2[coordY + 1][x - 1] == 'S'
}

val hasMASSideways = { x: Int, coordY: Int ->
    matrix2[coordY - 1][x - 1] == 'M'
        && matrix2[coordY][x] == 'A'
        && matrix2[coordY + 1][x + 1] == 'S'
        &&
        matrix2[coordY - 1][x + 1] == 'S'
        && matrix2[coordY][x] == 'A'
        && matrix2[coordY + 1][x - 1] == 'M'
}

val hasSAMDownward = { x: Int, coordY: Int ->
    matrix2[coordY - 1][x - 1] == 'S'
        && matrix2[coordY][x] == 'A'
        && matrix2[coordY + 1][x + 1] == 'M'
        &&
        matrix2[coordY - 1][x + 1] == 'S'
        && matrix2[coordY][x] == 'A'
        && matrix2[coordY + 1][x - 1] == 'M'
}

val hasSAMSideways = { x: Int, coordY: Int ->
    matrix2[coordY - 1][x - 1] == 'S'
        && matrix2[coordY][x] == 'A'
        && matrix2[coordY + 1][x + 1] == 'M'
        &&
        matrix2[coordY - 1][x + 1] == 'M'
        && matrix2[coordY][x] == 'A'
        && matrix2[coordY + 1][x - 1] == 'S'
}