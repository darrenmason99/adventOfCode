import java.io.File
import java.lang.Integer.max
import java.lang.Integer.min

data class Line (
    val start : Pair<Int,Int>,
    val end : Pair<Int,Int>
        )

val inputfile5 = "src/main/resources/inputdataday5.txt"

fun day5taskA() : Int {
    val regex = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()
    val lines = File(inputfile5).readLines()
        .map {
            val (x1,y1,x2,y2) = regex.find(it)!!.destructured
            Line(Pair(x1.toInt(),y1.toInt()), Pair(x2.toInt(),y2.toInt()))
        }
    val maxX = lines.maxOf { max(it.start.first, it.end.first) } +1
    val maxY = lines.maxOf { max(it.start.second, it.end.second) } + 1
    val floor = Array(maxX*maxY) {0}
    println("$maxX , $maxY")
    //Apply the lines
    lines.forEach { line ->
        println(line)
        if (line.start.first == line.end.first) {
                println("vertical")
            (min(line.start.second,line.end.second) .. max(line.start.second,line.end.second)).forEach { currentY ->
                //println("currentY = $currentY")
                floor[currentY * maxX + line.start.first + 1]++
            }
        } else if (line.start.second == line.end.second) {
                println("horizontal")
            (min(line.start.first,line.end.first) .. max(line.start.first,line.end.first)).forEach { currentX ->
                //println("currentX = $currentX")
                floor[line.start.second * maxX + currentX + 1]++
            }
        } else {
            println("diagonal")
            var current = line.start
            floor[current.second * maxX + current.first + 1]++
            while (current.first != line.end.first) {
                var newx = 0
                if (current.first < line.end.first) {
                    newx = current.first + 1
                } else {
                    newx = current.first - 1
                }
                var newy = 0
                if (current.second < line.end.second) {
                    newy = current.second + 1
                } else {
                    newy = current.second - 1
                }
                current = Pair(newx,newy)
                println(current)
                floor[current.second * maxX + current.first + 1]++
            }
        }
    }
    val safe = floor.filter { it > 1 }.count()
    //println(safe)
    return safe
}