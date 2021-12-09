fun main() {
    var heightMap = mutableListOf<List<Int>>()

    fun getLowestPoints(): MutableList<Pair<Int, Int>> {
        val lowestPoints = mutableListOf<Pair<Int, Int>>()

        for (i in 0 until heightMap.size) {
            for (j in 0 until heightMap[i].size) {
                val pointBeingAnalyzed = heightMap[i][j]

                if (i == 0) {
                    val down = heightMap[i + 1][j]
                    if (j == 0) {
                        val right = heightMap[i][j + 1]
                        if (down > pointBeingAnalyzed && right > pointBeingAnalyzed) {
                            lowestPoints.add(Pair(i, j))
                        }
                    } else if (j == heightMap[i].size - 1) {
                        val left = heightMap[i][j - 1]
                        if (down > pointBeingAnalyzed && left > pointBeingAnalyzed) {
                            lowestPoints.add(Pair(i, j))
                        }
                    } else {
                        val left = heightMap[i][j - 1]
                        val right = heightMap[i][j + 1]
                        if (down > pointBeingAnalyzed && left > pointBeingAnalyzed && right > pointBeingAnalyzed) {
                            lowestPoints.add(Pair(i, j))
                        }
                    }
                } else if (i == heightMap.size - 1) {
                    val up = heightMap[i - 1][j]
                    if (j == 0) {
                        val right = heightMap[i][j + 1]
                        if (up > pointBeingAnalyzed && right > pointBeingAnalyzed) {
                            lowestPoints.add(Pair(i, j))
                        }
                    } else if (j == heightMap[i].size - 1) {
                        val left = heightMap[i][j - 1]
                        if (up > pointBeingAnalyzed && left > pointBeingAnalyzed) {
                            lowestPoints.add(Pair(i, j))
                        }
                    } else {
                        val left = heightMap[i][j - 1]
                        val right = heightMap[i][j + 1]
                        if (up > pointBeingAnalyzed && left > pointBeingAnalyzed && right > pointBeingAnalyzed) {
                            lowestPoints.add(Pair(i, j))
                        }
                    }
                } else {
                    val up = heightMap[i - 1][j]
                    val down = heightMap[i + 1][j]
                    if (j == 0) {
                        val right = heightMap[i][j + 1]
                        if (up > pointBeingAnalyzed && right > pointBeingAnalyzed && down > pointBeingAnalyzed) {
                            lowestPoints.add(Pair(i, j))
                        }
                    } else if (j == heightMap[i].size - 1) {
                        val left = heightMap[i][j - 1]
                        if (up > pointBeingAnalyzed && left > pointBeingAnalyzed && down > pointBeingAnalyzed) {
                            lowestPoints.add(Pair(i, j))
                        }
                    } else {
                        val left = heightMap[i][j - 1]
                        val right = heightMap[i][j + 1]
                        if (up > pointBeingAnalyzed && left > pointBeingAnalyzed && right > pointBeingAnalyzed && down > pointBeingAnalyzed) {
                            lowestPoints.add(Pair(i, j))
                        }
                    }
                }
            }
        }
        return lowestPoints
    }

    fun part1(input: List<String>): Int {
        heightMap = mutableListOf()
        for (line in input) {
            val positions = line.toList().map { Character.getNumericValue(it) }
            heightMap.add(positions)
        }

        val lowestPoints = getLowestPoints()
        var result = 0
        for ((i, j) in lowestPoints) {
            result += heightMap[i][j] + 1
        }

        return result
    }

    fun getNeighbors(row: Int, col: Int): List<Pair<Int, Int>> {
        return arrayOf((-1 to 0), (1 to 0), (0 to -1), (0 to 1))
            .map { (dx, dy) -> row + dx to col + dy }
            .filter { (x, y) -> x in heightMap.indices && y in heightMap.first().indices }
    }

    fun getBasinSize(row: Int, col: Int): List<Pair<Int, Int>> {
        return getNeighbors(row, col)
            .filterNot { (x, y) -> heightMap[x][y] == 9 }
            .fold(listOf((row to col))) { points, (x, y) ->
                points + if (heightMap[x][y] - heightMap[row][col] >= 1) getBasinSize(x, y) else emptyList()
            }
    }

    fun part2(input: List<String>): Int {
        heightMap = mutableListOf()

        for (line in input) {
            val positions = line.toList().map { Character.getNumericValue(it) }.toMutableList()
            heightMap.add(positions)
        }

        val lowestPoints = getLowestPoints()

        // Genius solution from https://github.com/ClouddJR
        return lowestPoints.map { (rowIdx, colIdx) -> getBasinSize(rowIdx, colIdx).toSet().size }.sortedDescending()
            .take(3).reduce { acc, i -> acc * i }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
