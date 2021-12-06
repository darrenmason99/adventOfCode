import java.io.File

val inputfile3 = "src/main/resources/inputdataday3.txt"

fun day3taskA() : Int {
    val gStr = gamma(11)
    val iStr = invert(gStr)
    println(gStr + " : " + iStr)
    return Integer.parseInt(gStr, 2) * Integer.parseInt(iStr, 2)
}

fun day3taskB() : Int {
    //println(oxygen(4))
    //println(co2(4))
    return Integer.parseInt(oxygen(11), 2) * Integer.parseInt(co2(11), 2)
}

fun gamma(len : Int) : String {
    val yy =  File(inputfile3).readLines()
        .map { it.toCharArray() }
    return (0..len).toList().map { idx ->
        yy.groupBy { it[idx] }.map {
            it.key to it.value.count()
        }
    }.map {
        it.maxByOrNull { p -> p.second }?.first
    }.joinToString("")

}

fun oxygen(len : Int) : String {
    var yy =  File(inputfile3).readLines()
        .map { it.toCharArray() }
    var idx = 0
    while (yy.count() > 1) {
        val groupings = yy.groupBy { it[idx] }.map {
            it.key to it.value.count()
        }
        val counts = when {
            (groupings[0].second == groupings[1].second )  -> groupings.filter { it.first == '1' }.first()
            else -> groupings.maxByOrNull { it.second }
        }

        yy = yy.filter { it[idx] == counts?.first }
        idx++
    }

    return yy[0].joinToString("")
}

fun co2(len : Int) : String {
    var yy =  File(inputfile3).readLines()
        .map { it.toCharArray() }
    var idx = 0
    while (yy.count() > 1) {
        val groupings = yy.groupBy { it[idx] }.map {
            it.key to it.value.count()
        }
        val counts = when {
            (groupings[0].second == groupings[1].second )  -> groupings.filter { it.first == '0' }.first()
            else -> groupings.minByOrNull { it.second }
        }

        yy = yy.filter { it[idx] == counts?.first }
        idx++
    }

    return yy[0].joinToString("")
}


fun invert(value : String) : String {
    return value.map { c -> c.digitToInt().xor(1)}.joinToString("")
}