fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for (i in input) {
            val separatedInput = i.split(" | ")
            val inputValues = separatedInput[0].split(" ")
            val outputValues = separatedInput[1].split(" ")

            val uniqueInput = inputValues.groupingBy { it.length }.eachCount().filter { it -> it.value == 1 }
            val uniqueNumbers = outputValues.filter { it -> it.length in uniqueInput.keys }
            result += uniqueNumbers.count()
        }

        return result
    }

    // Part 2 clever solution by James https://github.com/jkpr
    fun getPatternMap(patterns: List<Set<Char>>) = patterns.associateBy {
        it.size
    }.mapKeys {
        when (it.key) {
            2 -> 1
            3 -> 7
            4 -> 4
            7 -> 8
            else -> -1
        }
    }.filterKeys { it != -1 }.toMutableMap().also {
        val bd = it.getValue(4) - it.getValue(1)
        patterns.forEach { pattern ->
            when (pattern.size) {
                5 -> when {
                    pattern.intersect(it.getValue(1)).size == 2 -> it[3] = pattern
                    pattern.intersect(bd).size == 2 -> it[5] = pattern
                    else -> it[2] = pattern
                }
                6 -> when {
                    pattern.intersect(bd).size == 1 -> it[0] = pattern
                    pattern.intersect(it.getValue(1)).size == 1 -> it[6] = pattern
                    else -> it[9] = pattern
                }
            }
        }
    }

    fun part2(input: List<String>) = input.sumOf { line ->
        """\w+""".toRegex().findAll(line).map {
            it.value.toSet()
        }.toList().let { signals ->
            val patternToDigit = getPatternMap(signals.take(10)).entries.associate { (k, v) -> v to k }
            signals.takeLast(4).map {
                patternToDigit.getValue(it)
            }.joinToString("").toInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
