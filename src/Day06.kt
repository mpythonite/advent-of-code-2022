fun main() {
    fun part1(input: List<String>): Int {
        var myInput = input[0]
        for (i in 4..myInput.length) {
            val dupe = myInput.subSequence(i-4, i)
            var success = true
            for (j in dupe.indices) {
                for (k in j + 1 until dupe.length) {
                    if (dupe[j] == dupe[k]) {
                        success = false
                        break
                    }
                }
            }

            if (success) return i
        }

        return -1
    }

    fun part2(input: List<String>): Int {
        var myInput = input[0]
        for (i in 14..myInput.length) {
            val dupe = myInput.subSequence(i-14, i)
            var success = true
            for (j in dupe.indices) {
                for (k in j + 1 until dupe.length) {
                    if (dupe[j] == dupe[k]) {
                        success = false
                        break
                    }
                }
            }

            if (success) return i
        }

        return -1
    }

    // test if implementation meets criteria from the description, like:
    // val testInput = readInput("Day06_test")
    // check(part1(testInput) == 1)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
