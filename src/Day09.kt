fun main() {
    fun part1(input: List<String>): Int {
        val lowestPoints = mutableListOf<Int>()

        val heightMap = mutableListOf<List<Int>>()
        for (line in input) {
            val positions = line.toList().map { Character.getNumericValue(it) }
            heightMap.add(positions)
        }

        for (i in 0 until heightMap.size) {
            for (j in 0 until heightMap[i].size) {
                val pointBeingAnalyzed = heightMap[i][j]

                if (i == 0) {
                    val down = heightMap[i + 1][j]
                    if (j == 0) {
                        val right = heightMap[i][j + 1]
                        if (down > pointBeingAnalyzed && right > pointBeingAnalyzed) {
                            lowestPoints.add(pointBeingAnalyzed)
                        }
                    } else if (j == heightMap[i].size - 1) {
                        val left = heightMap[i][j - 1]
                        if (down > pointBeingAnalyzed && left > pointBeingAnalyzed) {
                            lowestPoints.add(pointBeingAnalyzed)
                        }
                    } else {
                        val left = heightMap[i][j - 1]
                        val right = heightMap[i][j + 1]
                        if (down > pointBeingAnalyzed && left > pointBeingAnalyzed && right > pointBeingAnalyzed) {
                            lowestPoints.add(pointBeingAnalyzed)
                        }
                    }
                } else if (i == heightMap.size - 1) {
                    val up = heightMap[i - 1][j]
                    if (j == 0) {
                        val right = heightMap[i][j + 1]
                        if (up > pointBeingAnalyzed && right > pointBeingAnalyzed) {
                            lowestPoints.add(pointBeingAnalyzed)
                        }
                    } else if (j == heightMap[i].size - 1) {
                        val left = heightMap[i][j - 1]
                        if (up > pointBeingAnalyzed && left > pointBeingAnalyzed) {
                            lowestPoints.add(pointBeingAnalyzed)
                        }
                    } else {
                        val left = heightMap[i][j - 1]
                        val right = heightMap[i][j + 1]
                        if (up > pointBeingAnalyzed && left > pointBeingAnalyzed && right > pointBeingAnalyzed) {
                            lowestPoints.add(pointBeingAnalyzed)
                        }
                    }
                } else {
                    val up = heightMap[i - 1][j]
                    val down = heightMap[i + 1][j]
                    if (j == 0) {
                        val right = heightMap[i][j + 1]
                        if (up > pointBeingAnalyzed && right > pointBeingAnalyzed && down > pointBeingAnalyzed) {
                            lowestPoints.add(pointBeingAnalyzed)
                        }
                    } else if (j == heightMap[i].size - 1) {
                        val left = heightMap[i][j - 1]
                        if (up > pointBeingAnalyzed && left > pointBeingAnalyzed && down > pointBeingAnalyzed) {
                            lowestPoints.add(pointBeingAnalyzed)
                        }
                    } else {
                        val left = heightMap[i][j - 1]
                        val right = heightMap[i][j + 1]
                        if (up > pointBeingAnalyzed && left > pointBeingAnalyzed && right > pointBeingAnalyzed && down > pointBeingAnalyzed) {
                            lowestPoints.add(pointBeingAnalyzed)
                        }
                    }
                }
            }
        }

        return lowestPoints.sumOf { it + 1 }
    }

    fun part2(input: List<String>): Int {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    //check(part2(testInput) == 1134)

    val input = readInput("Day09")
    println(part1(input))
    //println(part2(input))
}
