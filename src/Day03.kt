fun main() {
    fun part1(input: List<String>): Int {
        var score = 0
        for (i in input.indices)
        {
            val split = input[i].length / 2
            val first = input[i].subSequence(0, split).toString().asIterable()
            val second = input[i].subSequence(split, input[i].length).toString().asIterable()
            val dupe = first.intersect(second.toSet()).first()
            score += if (dupe > 'a') {
                dupe - 'a' + 1
            } else {
                dupe - 'A' + 27
            }
        }

        return score
    }

    fun part2(input: List<String>): Int {
        var index = 0
        var score = 0
        while (index < input.size) {
            val  first = input[index++].asIterable()
            val  second = input[index++].asIterable()
            val  third = input[index++].asIterable()
            val dupe = first.intersect(second).intersect(third).first()
            score += if (dupe > 'a') {
                dupe - 'a' + 1
            } else {
                dupe - 'A' + 27
            }
        }

        return score
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    println(part1(testInput))
    check(part1(testInput) == 157)
    println(part2(testInput))
    check(part2(testInput) == 70)

    val input = readInput("Day03")

    var split = input[0].length / 2

    println(part1(input))
    println(part2(input))
}
