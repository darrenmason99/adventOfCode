import java.io.File
import java.util.*

val inputfile10 = "src/main/resources/inputdataday10.txt"

fun day10taskA()  : Int {
    val commands = File(inputfile10).readLines().map { it.toCharArray().filter { c -> !c.isWhitespace() }}
    return commands.map { line ->
        println("line $line")
        val stack = ArrayDeque<Char>()
        val failures = line.map { c -> chunk(c, stack) }.filter { it != 'X' }
        if (failures.isNotEmpty())
            value(failures.first())
        else 0
    }.sum()
}
fun day10taskB()  : Long {
    val commands = File(inputfile10).readLines().map { it.toCharArray().filter { c -> !c.isWhitespace() }}
    val points =  commands.map { line ->
        //println("line $line")
        val stack = ArrayDeque<Char>()
        val failures = line.map { c -> chunk(c, stack) }.filter { it != 'X' }
        if (failures.isEmpty()) {
            println(stack)
            val p = points(stack.map { expect(it) })
            println(p)
            p
        } else 0L
    }.filterNot { it == 0L }.sorted()
    println(points)
    return points[((points.size - 1) /2)]
}

fun points(expected: List<Char>) : Long {
    return expected.fold(0) { acc, c ->
        (acc * 5) + when (c) {
            ')' -> 1
            ']' -> 2
            '}' -> 3
            '>' -> 4
            else -> 0
        }
    }
}

fun value(c: Char) : Int {
    return when (c) {
        ')' -> 3
        ']' -> 57
        '}' -> 1197
        '>' -> 25137
        else -> 0
    }
}
fun chunk(c : Char, stack : Deque<Char>) : Char {
    //println("Looking at $c with stack $stack")
    if (c in listOf('[','(','<','{'))  { stack.push(c) }
    else {
        if (stack.isEmpty()) return 'X'
        val p = expect(stack.pop())
        if (c != p) {
            println("Expected $p, but found $c instead.")
            return c
        }
    }
    return 'X'
}
fun expect(start: Char) : Char {
    if (start == '[') return ']'
    if (start == '<') return '>'
    if (start == '{') return '}'
    if (start == '(') return ')'
    return 'X'
}

fun match(a: Char, b: Char) : Boolean {
    if (a == ']') return (b == '[')
    if (a == '>') return (b == '<')
    if (a == '}') return (b == '{')
    if (a == ')') return (b == '(')
    return false
}