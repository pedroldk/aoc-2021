fun main() {
    fun getAquariumSize(aquarium: MutableList<Int>, daysRemaining: Int): Long {
        val fishPositions = mutableListOf<Long>(0, 0, 0, 0, 0, 0, 0, 0, 0)

        for (i in aquarium) {
            fishPositions[i] += 1L
        }

        for (i in 0 until daysRemaining) {
            val valueIn0 = fishPositions[0]
            for (j in 1 until fishPositions.count()) {

                fishPositions[j - 1] = fishPositions[j]
            }

            if (valueIn0 > 0) {
                fishPositions[8] = valueIn0
                fishPositions[6] += valueIn0
            } else fishPositions[8] = 0

        }

        return fishPositions.sum()
    }

    fun part1(input: List<String>): Int {
        val aquarium = input[0].split(",").map { it.toInt() }.toMutableList()
        return getAquariumSize(aquarium, 80).toInt()
    }

    fun part2(input: List<String>): Long {
        val aquarium = input[0].split(",").map { it.toInt() }.toMutableList()

        return getAquariumSize(aquarium, 256)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
