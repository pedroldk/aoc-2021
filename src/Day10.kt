fun main() {

    fun part1(input: List<String>): Int {
        val invalidCharacters = mutableListOf<Char>()
        for (line in input) {
            val characters = line.toList()
            val stack = ArrayDeque<Char>()
            stack.add(characters[0])
            for (i in 1 until characters.size) {
                when (val currentChar = characters[i]) {
                    ')' -> if (stack.last() != '(') {
                        invalidCharacters.add(currentChar)
                        break
                    } else stack.removeLast()
                    ']' -> if (stack.last() != '[') {
                        invalidCharacters.add(currentChar)
                        break
                    } else stack.removeLast()
                    '}' -> if (stack.last() != '{') {
                        invalidCharacters.add(currentChar)
                        break
                    } else stack.removeLast()
                    '>' -> if (stack.last() != '<') {
                        invalidCharacters.add(currentChar)
                        break
                    } else stack.removeLast()
                    else -> stack.add(currentChar)
                }
            }
        }

        var result = 0
        for (i in invalidCharacters) {
            when (i) {
                ')' -> result += 3
                ']' -> result += 57
                '}' -> result += 1197
                '>' -> result += 25137
            }
        }
        return result
    }

    fun part2(input: List<String>): Long {
        val scores = mutableListOf<Long>()
        for (line in input) {
            val characters = line.toList()
            val stack = ArrayDeque<Char>()
            stack.add(characters[0])
            var invalidLine = false
            for (i in 1 until characters.size) {
                when (val currentChar = characters[i]) {
                    ')' -> if (stack.last() != '(') {
                        invalidLine = true
                        break
                    } else stack.removeLast()
                    ']' -> if (stack.last() != '[') {
                        invalidLine = true
                        break
                    } else stack.removeLast()
                    '}' -> if (stack.last() != '{') {
                        invalidLine = true
                        break
                    } else stack.removeLast()
                    '>' -> if (stack.last() != '<') {
                        invalidLine = true
                        break
                    } else stack.removeLast()
                    else -> stack.add(currentChar)
                }
            }
            if (!invalidLine) {
                var score = 0L
                while (stack.isNotEmpty()) {
                    when (stack.last()) {
                        '(' -> {
                            score = score * 5 + 1
                            stack.removeLast()
                        }
                        '[' -> {
                            score = score * 5 + 2
                            stack.removeLast()
                        }
                        '{' -> {
                            score = score * 5 + 3
                            stack.removeLast()
                        }
                        '<' -> {
                            score = score * 5 + 4
                            stack.removeLast()
                        }
                    }
                }
                scores.add(score)
            }
        }

        return scores.sorted()[scores.size / 2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
