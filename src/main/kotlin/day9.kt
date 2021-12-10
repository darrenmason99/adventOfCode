import java.io.File

val inputfile9 = "src/main/resources/inputdataday9.txt"

fun day9taskA()  {
    val depths = File(inputfile9).readLines().map { it.toCharArray().filter { c -> !c.isWhitespace() }.toCharArray() }.toTypedArray()
    val positions = depths.flatMapIndexed { idx, y ->  y.mapIndexed { xidx, x -> Pair(xidx,idx)} }
    //println(positions)
    val lowPoints = positions.filter { low ->
        adjacent(depths, low.first, low.second).all {  it > valueAt(depths, low.first, low.second) }
    }
    println(lowPoints)
    val risk = lowPoints.map { valueAt(depths, it.first, it.second) + 1 }.sum()
    println(risk)
}

fun day9taskB()  : Int {
    val depths = File(inputfile9).readLines().map { it.toCharArray().filter { c -> !c.isWhitespace() }.toCharArray() }.toTypedArray()
    val positions = depths.flatMapIndexed { idx, y ->  y.mapIndexed { xidx, x -> Pair(xidx,idx)} }
    //println(positions)
    val lowPoints = positions.filter { low ->
        adjacent(depths, low.first, low.second).all {  it > valueAt(depths, low.first, low.second) }
    }
    val lp = lowPoints.size
    var basins = mutableSetOf<Triple<Int,Int,Int>>()
    var newbasins = lowPoints.mapIndexed  { idx, lowpoint ->
        println("$idx of $lp ${lowpoint.first} ${lowpoint.second}")
        basin(depths, lowpoint.first, lowpoint.second, setOf())
    }.toSet()
    while (newbasins.isNotEmpty()) {
        newbasins = newbasins.flatMap { basin(depths, it.first, it.second, basins) }.toSet()
        basins.addAll(newbasins)
    }

    //println(basins)
    val top3 = basins.map { it.size }.sorted().takeLast(3)
    return (top3[0] * top3[1] * top3[2])
}

fun valueAt(depths : Array<CharArray>, x : Int, y : Int) : Int {
    if ((x < 0) or (x >= depths[0].size)) return -1
    if ((y < 0) or (y >= depths.size)) return -1
    return depths[y][x].digitToInt()
}


fun adjacent(depths : Array<CharArray>, x : Int, y : Int) : List<Int> {
    val a = listOf(valueAt(depths, x - 1, y), valueAt(depths, x + 1,y), valueAt(depths, x, y -1), valueAt(depths, x, y + 1))
    return a.filter { it != -1 }
}

fun adjacentxy(depths : Array<CharArray>, x : Int, y : Int) : List<Triple<Int,Int,Int>> {
    val a = listOf(Triple(x-1,y,valueAt(depths, x - 1, y)),
        Triple(x+1,y,valueAt(depths, x + 1,y)),
        Triple(x,y-1, valueAt(depths, x, y -1)),
        Triple(x, y+1, valueAt(depths, x, y + 1)))
    return a.filter { it.third != -1 }
}

fun basin(depths : Array<CharArray>, x : Int, y : Int, current: Set<Triple<Int,Int,Int>>) : Set<Triple<Int,Int,Int>> {
    println("basin with $x, $y and $current")
    val adj = adjacentxy(depths, x, y)
        .filter { (it.third != 9)  }
        

    return adj.plus(Triple(x,y,valueAt(depths,x,y))).filter {  it !in current }.toSet()
}