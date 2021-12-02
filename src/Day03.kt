fun main() {

    fun getZerosAndOnesCount(
        input: List<String>,
        binaryCountZeros: IntArray,
        binaryCountOnes: IntArray
    ) {
        for (i in input) {
            val splitInput = i.chunked(1).map { it.toInt() }
            for (j in splitInput.indices) {
                if (splitInput[j] == 0) binaryCountZeros[j]++ else binaryCountOnes[j]++
            }
        }
    }

    fun part1(input: List<String>): Int {
        val binaryCountOnes = IntArray(input[0].length) { 0 }
        val binaryCountZeros = IntArray(input[0].length) { 0 }
        getZerosAndOnesCount(input, binaryCountZeros, binaryCountOnes)

        val gammaRateBinary = IntArray(input[0].length) { 0 }
        val epsilonRateBinary = IntArray(input[0].length) { 0 }

        for (i in binaryCountZeros.indices) {
            if (binaryCountZeros[i] > binaryCountOnes[i]) {
                gammaRateBinary[i] = 0
                epsilonRateBinary[i] = 1
            } else {
                gammaRateBinary[i] = 1
                epsilonRateBinary[i] = 0
            }

        }
        return gammaRateBinary.joinToString(separator="").toInt(2) * epsilonRateBinary.joinToString(separator="").toInt(2)
    }

    fun part2(input: List<String>): Int {
        var binaryOxygenCountOnes = IntArray(input[0].length) { 0 }
        var binaryOxygenCountZeros = IntArray(input[0].length) { 0 }
        getZerosAndOnesCount(input, binaryOxygenCountZeros, binaryOxygenCountOnes)

        val targetOxygenNumbers = mutableListOf<String>()
        val targetC02Numbers = mutableListOf<String>()

        if (binaryOxygenCountZeros[0] > binaryOxygenCountOnes[0]) {
            targetOxygenNumbers.addAll(input.filter {it[0] == '0' })
            targetC02Numbers.addAll(input.filter {it[0] == '1' })
        } else {
            targetOxygenNumbers.addAll(input.filter {it[0] == '1' })
            targetC02Numbers.addAll(input.filter {it[0] == '0' })
        }

        var binaryCO2CountOnes = binaryOxygenCountOnes.copyOf()
        var binaryCO2CountZeros = binaryOxygenCountZeros.copyOf()

        var finalTargetOxygenBinary = " "
        var finalTargetC02Binary = " "

        for (i in 1 until binaryOxygenCountZeros.size) {
            if (binaryOxygenCountZeros[i] > binaryOxygenCountOnes[i]) {
                targetOxygenNumbers.removeAll(targetOxygenNumbers.filter {it[i] == '1' })
            } else {
                targetOxygenNumbers.removeAll(targetOxygenNumbers.filter {it[i] == '0' })
            }

            if (targetOxygenNumbers.count() == 1) finalTargetOxygenBinary = targetOxygenNumbers.first()
            binaryOxygenCountOnes = IntArray(input[0].length) { 0 }
            binaryOxygenCountZeros = IntArray(input[0].length) { 0 }
            getZerosAndOnesCount(targetOxygenNumbers, binaryOxygenCountZeros, binaryOxygenCountOnes)
        }

        for (i in 1 until binaryCO2CountZeros.size) {
            if (binaryCO2CountZeros[i] > binaryCO2CountOnes[i]) {
                targetC02Numbers.removeAll(targetC02Numbers.filter {it[i] == '0' })
            } else {
                targetC02Numbers.removeAll(targetC02Numbers.filter {it[i] == '1' })
            }

            if (targetC02Numbers.count() == 1) finalTargetC02Binary = targetC02Numbers.first()
            binaryCO2CountOnes = IntArray(input[0].length) { 0 }
            binaryCO2CountZeros = IntArray(input[0].length) { 0 }
            getZerosAndOnesCount(targetC02Numbers, binaryCO2CountZeros, binaryCO2CountOnes)
        }

        return finalTargetOxygenBinary.toInt(2) * finalTargetC02Binary.toInt(2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
