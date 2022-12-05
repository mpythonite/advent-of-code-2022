fun main() {
    fun part1(input: List<String>): String {
        var stacks = mutableListOf<ArrayDeque<Char>>()
        val pattern = "^[^0-9]+([0-9]+)[^0-9]+([0-9]+)[^0-9]+([0-9]+)\$".toRegex()
        input.forEach{
            var index = 0
            if (it.isNotBlank() && it.indexOf('[') >= 0) {
                while(index * 4 < it.length) {
                    val current = it[index * 4 + 1]
                    if (current != ' ') {
                        while (stacks.size <= index) stacks.add(ArrayDeque())
                        stacks[index].addLast(current)
                    }
                    index++
                }
            }
            else if (it.startsWith("move")) {
                val match = pattern.find(it)!!
                val (amountStr, from, to) = match.destructured
                val amount = amountStr.toInt()-1
                for(i in 0..amount) {
                    stacks[to.toInt()-1].addFirst(stacks[from.toInt()-1].removeFirst())
                }
            }
        }

        return stacks.fold("") { result, element -> result + element.first()}
    }

    fun part2(input: List<String>): String {
        var stacks = mutableListOf<ArrayDeque<Char>>()
        val pattern = "^[^0-9]+([0-9]+)[^0-9]+([0-9]+)[^0-9]+([0-9]+)\$".toRegex()
        input.forEach{
            var index = 0
            if (it.isNotBlank() && it.indexOf('[') >= 0) {
                while(index * 4 < it.length) {
                    val current = it[index * 4 + 1]
                    if (current != ' ') {
                        while (stacks.size <= index) stacks.add(ArrayDeque())
                        stacks[index].addLast(current)
                    }
                    index++
                }
            }
            else if (it.startsWith("move")) {
                val match = pattern.find(it)!!
                val (amountStr, from, to) = match.destructured
                val amount = amountStr.toInt()-1
                val craneStack = ArrayDeque<Char>()
                for(i in 0..amount) {
                    craneStack.addFirst(stacks[from.toInt()-1].removeFirst())
                }
                for (i in 0..amount)
                stacks[to.toInt()-1].addFirst(craneStack.removeFirst())
            }
        }

        return stacks.fold("") { result, element -> result + element.first()}
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    // check(part2(testInput) == 1)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
