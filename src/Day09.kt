import kotlin.math.abs

fun main() {
    data class Point(var x: Int = 0, var y: Int = 0)

    fun part1(input: List<String>): Int {
        var head = Point()
        var tail = Point()
        var trail = HashSet<Point>()
        var visited = 0

        input.forEach {
            val split = it.split(' ')
            val dir = split[0]
            val vel = split[1].toInt()

            for (i in 1..vel) {
                when (dir) {
                    "R" -> head.x++
                    "L" -> head.x--
                    "U" -> head.y++
                    "D" -> head.y--
                }

                if (tail.x == head.x) {
                    if (tail.y + 1 < head.y) tail.y++
                    if (tail.y - 1 > head.y) tail.y--
                }
                else if (tail.y == head.y) {
                    if (tail.x +1 < head.x) tail.x++
                    if (tail.x -1 > head.x) tail.x--
                }
                else {
                    if (abs(tail.x - head.x) + abs(tail.y - head.y) > 2) {
                        if (tail.x < head.x) tail.x++
                        else if (tail.x > head.x) tail.x--
                        if (tail.y < head.y) tail.y++
                        else if (tail.y > head.y) tail.y--
                    }
                }

                if(!trail.contains(tail)) {
                    visited++
                    trail.add(tail.copy())
                }
            }
        }

        return visited
    }

    fun part2(input: List<String>): Int {
        val rope = List(10) { Point() }
        var trail = HashSet<Point>()
        var visited = 0

        input.forEach {
            val split = it.split(' ')
            val dir = split[0]
            val vel = split[1].toInt()
            val head = rope[0]

            for (i in 1..vel) {
                when (dir) {
                    "R" -> head.x++
                    "L" -> head.x--
                    "U" -> head.y++
                    "D" -> head.y--
                }

                for (j in 1 until rope.size) {
                    if (rope[j].x == rope[j-1].x) {
                        if (rope[j].y + 1 < rope[j-1].y) rope[j].y++
                        if (rope[j].y - 1 > rope[j-1].y) rope[j].y--
                    } else if (rope[j].y == rope[j-1].y) {
                        if (rope[j].x + 1 < rope[j-1].x) rope[j].x++
                        if (rope[j].x - 1 > rope[j-1].x) rope[j].x--
                    } else {
                        if (abs(rope[j].x - rope[j-1].x) + abs(rope[j].y - rope[j-1].y) > 2) {
                            if (rope[j].x < rope[j-1].x) rope[j].x++
                            else if (rope[j].x > rope[j-1].x) rope[j].x--
                            if (rope[j].y < rope[j-1].y) rope[j].y++
                            else if (rope[j].y > rope[j-1].y) rope[j].y--
                        }
                    }
                }

                if(!trail.contains(rope[9])) {
                    visited++
                    trail.add(rope[9].copy())
                }
            }
        }

        return visited
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 1)

    val testInput2 = readInput("Day09_test2")
    check(part2(testInput2) == 36)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
