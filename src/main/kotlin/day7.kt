import java.io.File
import java.lang.Math.abs

val inputfile7 = "src/main/resources/inputdataday7.txt"

fun day7taskA() : Int {
    val crabPos = File(inputfile7).readLines().first().split(",").map { it.toInt() }
    val minPos = crabPos.minOrNull()!!
    val maxPos = crabPos.maxOrNull()!!
    println("Checking $minPos to $maxPos")
    return (minPos .. maxPos).map { pos ->
        val cost =Pair(pos,crabPos.map { crab ->
            abs(crab-pos)
        }.sum())
        println(cost)
        cost
    }.minByOrNull { it.second }?.first ?: -1

}

fun day7taskB() : Pair<Int,Int> {
    val crabPos = File(inputfile7).readLines().first().split(",").map { it.toInt() }
    val minPos = crabPos.minOrNull()!!
    val maxPos = crabPos.maxOrNull()!!
    println("Checking $minPos to $maxPos")
    return (minPos .. maxPos).map { pos ->
        val cost =Pair(pos,crabPos.map { crab ->
            val steps = abs(crab-pos)
            (1..steps).sum()
        }.sum())
        println(cost)
        cost
    }.minByOrNull { it.second }!!

}

