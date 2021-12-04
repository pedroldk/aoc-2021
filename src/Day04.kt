fun main() {

    fun checkIfBoardWon(board: MutableList<MutableList<Int>>): MutableList<MutableList<Int>>? {
        for (i in board.indices) {
            if (board.all { it[i] == -1 }) {
                return board
            }
        }

        for (row in board) {
            if (row.all { it == -1 }) {
                return board
            }
        }

        return null
    }

    fun checkIfAnyBoardWon(boards: MutableList<MutableList<MutableList<Int>>>): MutableList<MutableList<Int>>? {
        for (board in boards) {
            val boardThatWon = checkIfBoardWon(board)
            if (boardThatWon != null) {
                return boardThatWon
            }
        }
        return null
    }

    fun calculatePoints(i: Int, winningBoard: MutableList<MutableList<Int>>): Int {
        var sum = 0
        for (column in winningBoard) {
            for (rowValue in column) {
                if (rowValue != -1) {
                    sum += rowValue
                }
            }
        }
        return sum * i
    }

    fun part1(input: List<String>): Int {
        val pickedNumbers = input[0].split(",").map { it.toInt() }
        val boards = mutableListOf<MutableList<MutableList<Int>>>()
        var board = mutableListOf<MutableList<Int>>()
        for (i in 2 until input.size) {
            if (input[i] == "")
            {
                boards.add(board)
                board = mutableListOf()
            } else {
                board.add(input[i].trim().replace("  "," ").split(" ").map { it.toInt() }.toMutableList())
            }
        }
        boards.add(board)

        for (pickedNumber in pickedNumbers) {
            for (b in boards) {
                for (column in b) {
                    for (row in column.indices) {
                        if (column[row] == pickedNumber) {
                            column[row] = -1
                        }
                    }
                }
            }

            val winningBoard = checkIfAnyBoardWon(boards)

            if (winningBoard != null) {
                return calculatePoints(pickedNumber, winningBoard)
            }
        }

        return 0
    }

    fun removeFinishedBoards(boards: MutableList<MutableList<MutableList<Int>>>): MutableList<MutableList<MutableList<Int>>> {
        val playingBoards = mutableListOf<MutableList<MutableList<Int>>>()
        for (j in boards) {
            val winningBoard = checkIfBoardWon(j)
            if (winningBoard == null) {
                playingBoards.add(j)
            }
        }
        return playingBoards
    }

    fun part2(input: List<String>): Int {
        val pickedNumbers = input[0].split(",").map { it.toInt() }
        var boards = mutableListOf<MutableList<MutableList<Int>>>()
        var board = mutableListOf<MutableList<Int>>()
        for (i in 2 until input.size) {
            if (input[i] == "")
            {
                boards.add(board)
                board = mutableListOf()
            } else {
                board.add(input[i].trim().replace("  "," ").split(" ").map { it.toInt() }.toMutableList())
            }
        }
        boards.add(board)

        for (pickedNumber in pickedNumbers) {
            for (b in boards) {
                for (column in b) {
                    for (row in column.indices) {
                        if (column[row] == pickedNumber) {
                            column[row] = -1
                        }
                    }
                }
            }

            if (boards.count() > 1) {
                boards = removeFinishedBoards(boards)
            }

            if (boards.count() == 1 && checkIfBoardWon(boards.first()) != null) {
                return calculatePoints(pickedNumber, boards.first())
            }
        }

        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
