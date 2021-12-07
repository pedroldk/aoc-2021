import java.lang.Math.toDegrees
import kotlin.math.abs
import kotlin.math.atan2

fun main() {
    fun calculateMultipleIntersections(board: MutableList<IntArray>): Int {
        var intersections = 0
        for (i in board) {
            for (j in i) {
                if (j > 1) {
                    intersections += 1
                }
            }
        }
        return intersections
    }

    fun invertCoordinatesIfBigger(firstCoordinate: Int, secondCoordinate: Int): Pair<Int, Int> {
        if (firstCoordinate > secondCoordinate) {
            return Pair(secondCoordinate, firstCoordinate)
        }
        return Pair(firstCoordinate, secondCoordinate)
    }

    fun is45Angle(x1: Int, x2: Int, y1: Int, y2: Int): Boolean {
        val deltaX = abs(x2 - x1)
        val deltaY = abs(y2 - y1)
        val thetaRadians = toDegrees(atan2(deltaY.toDouble(), deltaX.toDouble()))
        return abs(thetaRadians) == 45.0
    }

    fun addHorizontalAndVerticalLines(
        x1: Int,
        x2: Int,
        y1: Int,
        y2: Int,
        board: MutableList<IntArray>
    ): MutableList<IntArray> {
        val pairX = invertCoordinatesIfBigger(x1, x2)
        val pairY = invertCoordinatesIfBigger(y1, y2)

        for (firstPoint in pairX.first..pairX.second) {
            for (secondPoint in pairY.first..pairY.second) {
                board[firstPoint][secondPoint]++
            }
        }
        return board
    }

    fun part1(input: List<String>): Int {
        var board = mutableListOf<IntArray>()

        for (i in 0..1000) {
            val row = IntArray(1000) { 0 }
            board.add(row)
        }

        for (i in input) {
            val splitPoints = i.split(" -> ")
            val splitFirstCoordinates = splitPoints[0].split(",")
            val splitSecondCoordinates = splitPoints[1].split(",")

            val x1 = splitFirstCoordinates[0].toInt()
            val y1 = splitFirstCoordinates[1].toInt()
            val x2 = splitSecondCoordinates[0].toInt()
            val y2 = splitSecondCoordinates[1].toInt()

            if (x1 == x2 || y1 == y2) {
                board = addHorizontalAndVerticalLines(x1, x2, y1, y2, board)
            }
        }

        return calculateMultipleIntersections(board)
    }

    fun addDiagonalLines(
        x1: Int,
        x2: Int,
        y1: Int,
        y2: Int,
        board: MutableList<IntArray>
    ): MutableList<IntArray> {
        var firstPointX = x1
        var secondPointX = x2
        var firstPointY = y1
        var secondPointY = y2
        if (firstPointX > secondPointX && firstPointY > secondPointY) {
            for (i in secondPointX..firstPointX) {
                board[secondPointX][secondPointY]++
                secondPointX++
                secondPointY++
            }
        } else if (firstPointX > secondPointX && firstPointY < secondPointY) {
            for (i in secondPointX..firstPointX) {
                board[secondPointX][secondPointY]++
                secondPointX++
                secondPointY--
            }
        } else if (firstPointX < secondPointX && firstPointY > secondPointY) {
            for (i in firstPointX..secondPointX) {
                board[firstPointX][firstPointY]++
                firstPointX++
                firstPointY--
            }
        } else if (firstPointX < secondPointX && firstPointY < secondPointY) {
            for (i in firstPointX..secondPointX) {
                board[firstPointX][firstPointY]++
                firstPointX++
                firstPointY++
            }
        }
        return board
    }

    fun part2(input: List<String>): Int {
        var board = mutableListOf<IntArray>()

        for (i in 0..1000) {
            val row = IntArray(1000) { 0 }
            board.add(row)
        }

        for (i in input) {
            val splitPoints = i.split(" -> ")
            val splitFirstCoordinates = splitPoints[0].split(",")
            val splitSecondCoordinates = splitPoints[1].split(",")

            val x1 = splitFirstCoordinates[0].toInt()
            val y1 = splitFirstCoordinates[1].toInt()
            val x2 = splitSecondCoordinates[0].toInt()
            val y2 = splitSecondCoordinates[1].toInt()

            if (x1 == x2 || y1 == y2) {
                board = addHorizontalAndVerticalLines(x1, x2, y1, y2, board)
            } else if (is45Angle(x1, x2, y1, y2)) {
                board = addDiagonalLines(x1, x2, y1, y2, board)
            }
        }
        return calculateMultipleIntersections(board)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
