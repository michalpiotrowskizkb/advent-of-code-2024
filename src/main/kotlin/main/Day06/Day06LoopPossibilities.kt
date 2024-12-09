package main.Day06

import main.readInput

var matrix: Array<Array<Char>> = arrayOf()

fun main() {
    val input = readInput("src/main/kotlin/main/Day06/Day06_input")
    val shortInput = readInput("src/main/kotlin/main/Day06/Day06_shortInput")
    check(countLoopPossibilities(shortInput) == 6)
    println("result = ${countLoopPossibilities(input)}")
}
    //   for each new position, check if the Guard can be made
    //   stuck in a loop by putting an obstacle in front of him.
fun countLoopPossibilities(input: List<String>): Int {
    matrix = Array(input.size) { i -> input[i].toCharArray().toTypedArray() }
    var guard = Guard(0, 0, Direction.NORTH)
    val visitedPositions = mutableSetOf<Pair<Int, Int>>()
    val possibleLoopLocations = mutableSetOf<Pair<Int, Int>>()
    for (y in matrix.indices) {
        for (x in matrix[y].indices) {
             if (matrix[y][x] == '^') {
                 guard = Guard(x, y, Direction.NORTH)
             }
        }
    }

    traversalHasLoop(visitedPositions, guard, possibleLoopLocations)
    return possibleLoopLocations.size
}

private fun traversalHasLoop(
    visitedPositions: MutableSet<Pair<Int, Int>>,
    guard: Guard,
    possibleLoopLocations: MutableSet<Pair<Int, Int>>,
) {
    while (true) {
        visitedPositions.add(guard.x to guard.y)
        if (canLoopGuard(guard)) {
            possibleLoopLocations.add((guard.y + guard.direction.y) to (guard.x + guard.direction.x))
        } else if (getObjectAhead(guard) == O.OBSTACLE) {
            guard.turn()
        } else if (getObjectAhead(guard) == O.END_OF_MAP) {
            break
        }
        guard.move()
    }
}

// * temporarily put an obstacle in front of the guard
// * turn the guard
// * continue traversal like in Part 1
// * if the object ahead is O, then the method managed to loop the guard.
// * Remove  The obstacle after the test
fun canLoopGuard(guard: Guard): Boolean {
    val guardCopy = guard.copy()
    if (getObjectAhead(guard) != O.PATH) {
        return false
    }
    matrix[guard.y + guard.direction.y][guard.x + guard.direction.x] = 'O'
    guardCopy.turn()
    val visited = mutableSetOf<Triple<Int, Int, Direction>>()
    while (true) {
        if (getObjectAhead(guardCopy) == O.OBSTACLE) {
            guardCopy.turn()
        } else if (getObjectAhead(guardCopy) == O.END_OF_MAP) {
            break
        } else if (getObjectAhead(guardCopy) == O.THEORETICAL_OBSTACLE || visited.contains(Triple(guardCopy.x, guardCopy.y, guardCopy.direction))) {
            matrix[guard.y + guard.direction.y][guard.x + guard.direction.x] = '.'
            return true
        }
        visited.add(Triple(guardCopy.x, guardCopy.y, guardCopy.direction))
        guardCopy.move()
    }
    matrix[guard.y + guard.direction.y][guard.x + guard.direction.x] = '.'
    return false
}


fun getObjectAhead(guard: Guard): O {
    try {
        return when (matrix[guard.y + guard.direction.y][guard.x + guard.direction.x]) {
            '#' -> O.OBSTACLE
            'O' -> O.THEORETICAL_OBSTACLE
            else -> O.PATH
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        return O.END_OF_MAP
    }
}
