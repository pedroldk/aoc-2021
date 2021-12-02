fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var position = 0
        for (i in input) {
            val splitInput = i.split(" ")
            val movement = splitInput[0]
            val movementValue = splitInput[1].toInt()
            when (movement) {
                "forward" -> position += movementValue
                "down" -> depth += movementValue
                "up" -> depth -= movementValue
            }
        }
        return depth * position
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var position = 0
        var aim = 0
        for (i in input) {
            val splitInput = i.split(" ")
            val movement = splitInput[0]
            val movementValue = splitInput[1].toInt()
            when (movement) {
                "forward" -> {
                    position += movementValue
                    depth += aim * movementValue
                }
                "down" -> aim += movementValue
                "up" -> aim -= movementValue
            }
        }
        return depth * position
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
