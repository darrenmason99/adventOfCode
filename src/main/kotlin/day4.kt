import java.io.File

data class Square (
    val value : Int,
    var marked : Boolean
    )

data class Board (
    val squares : List<Square>
        )



val inputfile4 = "src/main/resources/inputdataday4.txt"

fun day4taskA() : Int {
    val file = File(inputfile4).readLines()
        .filter { it.isNotBlank() }
    val numberList = file.first().split(',')
    val boards = file.drop(1).chunked(5).map { boardrows ->
        Board(boardrows.map { it.split(' ').filter { it.isNotBlank() }.map { Square(it.toInt(), false) } }.flatten())
    }
    println(numberList)
    println(boards)
    numberList.forEach { currentNumber ->
        boards.forEach { board -> mark(currentNumber.toInt(), board) }
        val winning = boards.filter { wins(it) }
        if (winning.isNotEmpty()) {
            val winner = winning.first()
            val total = winner.squares.filter { !it.marked }.sumOf { it.value }
            println(total)
            println(currentNumber)
            println("Winner Winner Chicken Dinner ${winner}")
            return total * currentNumber.toInt()
        }
    }
    return 0
}

fun day4taskB() : Int {
    val file = File(inputfile4).readLines()
        .filter { it.isNotBlank() }
    val numberList = file.first().split(',')
    val boards = file.drop(1).chunked(5).map { boardrows ->
        Board(boardrows.map { it.split(' ').filter { it.isNotBlank() }.map { Square(it.toInt(), false) } }.flatten())
    }
    println(numberList)
    println(boards)
    var winningIndexes = mutableSetOf<Int>()
    numberList.forEach { currentNumber ->

        boards.forEach { board -> mark(currentNumber.toInt(), board) }
        val winning = boards.withIndex().filter { wins(it.value) }

        println("${winning.count()} winners")
        val winner = winning.filter {  !winningIndexes.contains(it.index) }
        println("winner count = ${winner.count()}")
        winningIndexes.addAll(winner.map { it.index })

       if (winning.count() == boards.count()) {
            val total = winner.last().value.squares.filter { !it.marked }.sumOf { it.value }

            println(total)
            println(currentNumber)
            println("Winner Winner Chicken Dinner ${winner.last().value}")
            return total * currentNumber.toInt()
        }
    }
    return 0
}


fun wins(board: Board) :Boolean {
    val columnWin = board.squares.chunked(5).any { row -> row.all { it.marked } }
    val rowWin = board.squares.withIndex().groupBy { it.index % 5}.values.any { column -> column.all { it.value.marked } }
    return columnWin || rowWin
}

fun mark(value: Int, board: Board) {
    board.squares.filter { it.value.equals(value) }.forEach { it.marked = true }
}