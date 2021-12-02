import java.io.File

val inputfile = "src/main/resources/inputdataday1.txt"

fun day1taskA() : Int {

    return File(inputfile).readLines()
        .map { it.toInt() }
        .windowed(2,1)
        .filter { (a,b) -> b > a }
        .count()
}

fun day1taskB() : Int {
    return File(inputfile).readLines()
        .map { it.toInt() }
        .windowed(4,1)
        .filter { (a,b,c,d) -> d > a }
        .count()
}

