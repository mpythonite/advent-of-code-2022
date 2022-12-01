fun main() {
    fun part1(input: List<String>): Int {
        var currentCalories = 0
        var maxCalories = 0

        for (i in input.indices)
        {
            if (input[i].isBlank())
            {
                if (currentCalories > maxCalories) {
                    maxCalories = currentCalories
                }
                currentCalories = 0
            }
            else currentCalories += input[i].toInt()
        }

        if (currentCalories > maxCalories) {
            maxCalories = currentCalories
        }

        return maxCalories
    }

    fun part2(input: List<String>): Int {
        var currentCalories = 0
        var calorieList: MutableList<Int> = mutableListOf()
        var totalCalories = 0

        for (i in input.indices) {
            if (input[i].isBlank()) {
                calorieList.add(currentCalories)
                currentCalories = 0
            } else currentCalories += input[i].toInt()
        }

        calorieList.add(currentCalories)

        calorieList.sortDescending()

        for (i in 0 until 3) {
            totalCalories += calorieList[i]
        }

        return totalCalories
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
