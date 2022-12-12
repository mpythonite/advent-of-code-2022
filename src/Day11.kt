fun main() {
    class Monkey(var items: ArrayDeque<Long>, val operation: (Long) -> Long, val test: Long, val success: Int, val fail: Int) {
        var inspects: Long = 0
    }

    fun part1(monkeys: List<Monkey>): Long {
        for (i in 0 until 20) {
            for (currMonkey in monkeys) {
                while (currMonkey.items.any()) {
                    var item = currMonkey.items.removeFirst()
                    currMonkey.inspects++
                    item = currMonkey.operation(item)
                    item /= 3
                    if (item % currMonkey.test == 0.toLong()) monkeys[currMonkey.success].items.add(item)
                    else monkeys[currMonkey.fail].items.add(item)
                }
            }
        }

        var results = monkeys.map { m -> m.inspects }.sortedByDescending { i -> i }

        return results[0] * results[1]
    }

    fun part2(monkeys: List<Monkey>): Long {
        val meditation: Long = monkeys.map {it.test}.reduce(Long::times)
        for (i in 0 until 10000) {
            for (currMonkey in monkeys) {
                while (currMonkey.items.any()) {
                    var item = currMonkey.items.removeFirst()
                    currMonkey.inspects++
                    item = currMonkey.operation(item)
                    item %= meditation
                    if (item % currMonkey.test == 0.toLong()) monkeys[currMonkey.success].items.add(item)
                    else monkeys[currMonkey.fail].items.add(item)
                }
            }
        }

        var results = monkeys.map { m -> m.inspects }.sortedByDescending { i -> i }

        return results[0] * results[1]
    }

    val testMonkeys: List<Monkey> =
        listOf(
            Monkey(ArrayDeque(listOf(79, 98)), {i: Long -> i * 19}, 23, 2, 3),
            Monkey(ArrayDeque(listOf(54, 65, 75, 74)), {i: Long -> i + 6}, 19, 2, 0),
            Monkey(ArrayDeque(listOf(79, 60, 97)), {i: Long -> i * i}, 13, 1, 3),
            Monkey(ArrayDeque(listOf(74)), {i: Long -> i + 3}, 17, 0, 1),
        )

    // test if implementation meets criteria from the description, like:
    // check(part1(testMonkeys) == 10605.toLong())
    check(part2(testMonkeys) == 2713310158)

    val monkeys: List<Monkey> =
        listOf(
            
        )

    //println(part1(monkeys))
    println(part2(monkeys))
}
