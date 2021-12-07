import java.io.File
class counter (
    var value : Int
        ) {
    fun increment() {
        println("Incrementing")
        value++
    }
}
val inputfile6 = "src/main/resources/inputdataday6.txt"

fun day6taskA() : Long {
   var fish = File(inputfile6).readLines().first().split(",").map { it.toInt() }.asSequence()
    (0..255).forEach {
        //println("$it : ${fish.toList()}")
        println(it)
        fish = fish.flatMap { state ->
            when (state) {
                0 -> {
                    sequenceOf (6, 8)
                } else -> sequenceOf (state - 1)
            }
        }


    }
    return fish.fold(0L) { acc, _ -> acc + 1 }

}

fun day6taskB() : Long {
    val fishcount = mutableMapOf<Int,Long>()

    File(inputfile6).readLines().first().split(",").forEach {
        fishcount.put(it.toInt(),fishcount[it.toInt()]?.inc() ?: 1)
    }
    (0..255).forEach {
        println(fishcount)
        val zerocount = fishcount[0] ?: 0
        (0 .. 7).forEach{
            //println("setting ${it} to ${fishcount[it+1]} ")
            fishcount[it] = fishcount[(it+1)] ?: 0
        }
        // Now spawn
        fishcount[6] = (fishcount[6] ?: 0) + zerocount
        fishcount[8] = zerocount
    }
    return fishcount.values.sum()
}