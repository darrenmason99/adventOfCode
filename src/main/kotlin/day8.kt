import java.io.File

val inputfile8 = "src/main/resources/inputdataday8.txt"

fun day8taskA() : Int {
    val signals = File(inputfile8).readLines().map { it.split("|").get(1).split(" ").filter { it.isNotBlank()} }
    return signals.map { display ->
        val cnt = display.filter { it.length in listOf(2,3,4,7) }.count()
        //println(cnt)
            cnt
    }.sum()
}

fun day8taskB() : Int {
    val lines = File(inputfile8).readLines().map { it.split("|")  }
    val signals = lines.map { it.get(0).split(" ").filter { it.isNotBlank()} }
    val digits = lines.map { it.get(1).split(" ").filter { it.isNotBlank()} }

    val sum = lines.map { line ->
        val signals = line.get(0).split(" ").filter { it.isNotBlank() }
        val display = line.get(1).split(" ").filter { it.isNotBlank() }
        val digitmap = mutableMapOf<Int, String>()

        digitmap[1] = signals.filter { f -> f.length.equals(2) }.first()
        digitmap[8] = signals.filter { it.length == 7 }.first()
        digitmap[4] = signals.filter { it.length == 4 }.first()
        digitmap[7] = signals.filter { it.length == 3 }.first()
        signals.filter { it.length == 6 }.forEach { sixdigit ->
            // 9 contains all the characters of 4
            if (digitmap[4]!!.toCharArray().all { it in sixdigit }) {
                digitmap.put(9, sixdigit)
            } else if (digitmap[1]!!.toCharArray().all { it in sixdigit }) {
                digitmap.put(0, sixdigit)
            } else {
                digitmap.put(6, sixdigit)
            }
        }
        signals.filter { it.length == 5 }.forEach { fivedigit ->
            if (digitmap[1]!!.toCharArray().all { it in fivedigit }) {
                digitmap.put(3, fivedigit)
            } else if (fivedigit.toCharArray().all { it in digitmap[6]!! }) {
                digitmap.put(5, fivedigit)
            } else {
                digitmap.put(2, fivedigit)
            }
        }
        // Now work out the digits
        val cnt = display.map {
            val chars = it.toCharArray()
            when {
                (it.length == 7) -> 8
                (it.length == 3) -> 7
                (it.length == 4) -> 4
                (it.length == 2) -> 1
                chars.all { it in digitmap[5]!! } -> 5
                chars.all { it in digitmap[2]!! } -> 2
                chars.all { it in digitmap[3]!! } -> 3

                chars.all { it in digitmap[9]!! } -> 9
                chars.all { it in digitmap[6]!! } -> 6

                chars.all { it in digitmap[0]!! } -> 0

                else -> {
                    println("error")
                    -1
                }
            }
        }.joinToString("").toInt()
        cnt
    }.sum()

    return sum
}