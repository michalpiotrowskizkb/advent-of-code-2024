package main.Day03

import kotlin.streams.asStream
import main.readInput

fun main() {
    val input = readInput("src/main/kotlin/main/Day03/Day03_list")
    check(
        toggledMultiplications2(
            listOf(
                "/~+?who()[do()mul(852,307)-[?;)how()don't()[[*->{!#^mul(678,864)",
                "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
            )
        ) == 852 * 307 + 8 * 5
    )
    println("\n-------------------------------")
    println("result = ${toggledMultiplications2(input)}")
}

fun toggledMultiplications2(input: List<String>): Int {
    var lastToggle = 1
    var result = 0
    input.forEach {
        Regex("mul\\((\\d*),(\\d*)\\)|(do\\(\\))|(don't\\(\\))")
            .findAll(it)
            .asStream()
            .forEach {
                print("\ngot a ${it.groupValues[0]} token. ")
                lastToggle =
                    if ("do()" == it.groupValues[0]) 1
                    else if ("don't()" == it.groupValues[0]) 0
                    else lastToggle
                if (lastToggle == 1 && it.groupValues[0].contains("mul")) {
                    result += it.groupValues[1].toInt() * it.groupValues[2].toInt()
                    print("toggle is on 🟢, new result: ${result}")
                }
            }
    }
    return result
}