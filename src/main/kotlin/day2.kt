import java.io.File

val inputfile2 = "src/main/resources/inputdataday2.txt"

fun day2taskA() : Pair<Int,Int> {

    return File(inputfile2).readLines()
        .map { it.split(' ') }
        .fold(Pair(0, 0)) { acc, (cmd,value) ->
            when (cmd) {
                "forward" -> Pair(acc.first + value.toInt(), acc.second)
                "down" -> Pair(acc.first, acc.second + value.toInt())
                "up" -> Pair(acc.first, acc.second - value.toInt())
                else -> Pair(0, 0)
            }
        }
}
    fun day2taskB() : Triple<Int,Int,Int> {

        return File(inputfile2).readLines()
            .map { it.split(' ') }
            .fold(Triple(0,0,0)) { acc , (cmd,value) ->
                when (cmd) {
                    "forward" -> Triple(acc.first + value.toInt(),acc.second + (acc.third * value.toInt()), acc.third)
                    "down" -> Triple(acc.first ,acc.second, acc.third  + value.toInt())
                    "up" -> Triple(acc.first,acc.second, acc.third  - value.toInt())
                    else -> Triple(0,0,0)
                }
            }

    }
