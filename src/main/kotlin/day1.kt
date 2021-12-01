import java.io.File

val inputfile = "src/main/resources/inputdataday1.txt"

fun day1taskA() : Int {

    return File(inputfile).readLines()
        .map { it.toInt() }
        .windowed(2,1)
        .filter { it[1] > it[0] }
        .count()
}


fun day1taskB() : Int {
    return File(inputfile).readLines()
        .map { it.toInt() }
        .windowed(3,1)
        .map { it.sum() }
        .windowed(2,1)
        .filter { it[1] > it[0] }
        .count()
}