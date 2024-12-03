package main.Day03

import kotlin.streams.asStream
import main.readInput

fun main() {
    val input = readInput("src/main/kotlin/main/Day03/Day03_list")
    check(declutterAndSumMultiplications("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))") == 161)
    println("result = ${findAndSumAllMultiplications(input)}")
}

fun findAndSumAllMultiplications(input: List<String>): Int {
    return input.fold(0) { sum, line -> sum + declutterAndSumMultiplications(line) }
}

fun declutterAndSumMultiplications(s: String): Int {
    return Regex("mul\\((\\d*),(\\d*)\\)")
        .findAll(s)
        .asStream()
        .map {
            it.groupValues[1].toInt() * it.groupValues[2].toInt()
        }.reduce{ result, multiplied -> result + multiplied }.get()
}

//fun getResultAndPrintAllOperations(s: String): Int {
//    return Regex("mul\\((\\d*),(\\d*)\\)")
//        .findAll(s)
//        .asStream()
//        .map {
//            println("${it.groupValues[0]} | multiplying ${it.groupValues[1].toInt()} * ${it.groupValues[2].toInt()}: ${it.groupValues[1].toInt() * it.groupValues[2].toInt()}")
//            it.groupValues[1].toInt() * it.groupValues[2].toInt()
//        }.reduce{ result, multiplied -> result + multiplied }.get()
//}
