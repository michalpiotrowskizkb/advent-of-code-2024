package main.Day06

import main.readInput

var matrix2: Array<Array<Char>> = arrayOf()

fun main() {
    val input = readInput("src/main/kotlin/main/Day06/Day06_input")
    val shortInput = readInput("src/main/kotlin/main/Day06/Day06_shortInput")
    check(countVisitedPositions(shortInput) == 41)
    println("result = ${countVisitedPositions(input)}")
}

fun countVisitedPositions(input: List<String>): Int {
    // findStartingPosition: where is '^'?
    // its direction is up.
    // in a loop:
    //   add the position to the map of visited.
    //   look ahead along the direction.
    //      If an obstacle is in front, rotate along nextDirection
    //      If an out of bounds index is in front, we're done
    //   move it along the direction

    matrix2 = Array(input.size) { i -> input[i].toCharArray().toTypedArray() }
    var guard = Guard(0, 0, Direction.NORTH)
    val visitedPositions = mutableSetOf<Pair<Int, Int>>()
    for (y in matrix2.indices) {
        for (x in matrix2[y].indices) {
             if (matrix2[y][x] == '^') {
                 guard = Guard(x, y, Direction.NORTH)
             }
        }
    }

    while (true) {
        visitedPositions.add(guard.x to guard.y)
        if (lookAhead(guard, matrix2) == O.OBSTACLE) {
            guard.turn()
        } else if (lookAhead(guard, matrix2) == O.END_OF_MAP) {
            break
        }
        guard.move()
    }

    return visitedPositions.size
}

fun lookAhead(guard: Guard, matrix2: Array<Array<Char>>): O {
    try {
        return if (matrix2[guard.y + guard.direction.y][guard.x + guard.direction.x] == '#')
            O.OBSTACLE
        else
            O.PATH
    } catch (e: ArrayIndexOutOfBoundsException) {
        return O.END_OF_MAP
    }
}

enum class O {
    END_OF_MAP,
    OBSTACLE,
    PATH
}
data class Guard(var x: Int, var y: Int, var direction: Direction) {

    fun move()  {
        x += direction.x
        y += direction.y
    }

    fun turn() {
        this.direction = direction.nextDirection()
    }
}

enum class Direction(val y: Int, val x: Int) {
    NORTH(-1, 0),
    EAST(0, 1),
    SOUTH(1, 0),
    WEST(0, -1);

    fun nextDirection(): Direction {
        return when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
    }
}