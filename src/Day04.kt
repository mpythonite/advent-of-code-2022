import java.awt.PageAttributes.PrintQualityType

fun main() {
    fun part1(input: List<String>): Int {
        var redundancies = 0
        input.forEach{
            val splits = it.split(',')
            val first = splits.first().split('-')
            val second = splits.last().split('-')
            val low1 = first.first().toInt()
            val high1 = first.last().toInt()
            val low2 = second.first().toInt()
            val high2 = second.last().toInt()
            if (low1 <= low2 && high1 >= high2) redundancies++
            else if (low2 <= low1 && high2 >= high1) redundancies++
         }

        return redundancies
    }

    fun part2(input: List<String>): Int {
        var overlaps = 0
        input.forEach{
            val splits = it.split(',')
            val first = splits.first().split('-')
            val second = splits.last().split('-')
            val low1 = first.first().toInt()
            val high1 = first.last().toInt()
            val low2 = second.first().toInt()
            val high2 = second.last().toInt()
            if (low1 in low2..high2) overlaps++
            else if (low2 in low1..high1) overlaps++
        }

        return overlaps
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    println(part2(testInput))
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
