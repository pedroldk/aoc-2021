import kotlin.math.abs

fun main() {
    fun calculateCost(firstNumber: Int, secondNumber: Int, part: String): Int {
        val absDifference = abs(firstNumber - secondNumber)
        when (part) {
            "part1" -> return absDifference
            "part2" -> return (absDifference * (absDifference + 1)) / 2
        }
        return absDifference
    }

    fun calculateFuelCost(input: List<String>, part: String): Int {
        val positions = input[0].split(",").map { it.toInt() }.toMutableList()

        var lowestFuel = Int.MAX_VALUE

        val minValue = positions.minOf { it }
        val maxValue = positions.maxOf { it }

        for (i in minValue..maxValue) {
            var calculatedFuel = 0
            for (j in 0 until positions.count()) {
                if (positions[j] != i) {
                    calculatedFuel += calculateCost(positions[j], i, part)
                }
            }
            if (calculatedFuel < lowestFuel) {
                lowestFuel = calculatedFuel
            }
        }

        return lowestFuel
    }

    fun part1(input: List<String>): Int {
        return calculateFuelCost(input, "part1")
    }

    fun part2(input: List<String>): Int {
        return calculateFuelCost(input, "part2")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
