fun main() {
    fun part1(input: List<String>): Int {
        var score = 0
        for (i in input.indices) {
            val them = input[i].first() - 'A' + 1
            val me = input[i].last() - 'X' + 1

            score += me
            if (me == them) score += 3
            if (me == them % 3 + 1) score += 6
        }

        return score
    }

    fun part2(input: List<String>): Int {
        var score = 0
        for (i in input.indices) {
            val them = input[i].first() - 'A' + 1

            var me = when(input[i].last()) {
                'X' -> (them + 1) % 3 + 1
                'Y' -> them
                'Z' -> them % 3 + 1
                else -> 0
            }

            score += me
            if (me == them) score += 3
            if (me == them % 3 + 1) score += 6
        }

        return score
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
