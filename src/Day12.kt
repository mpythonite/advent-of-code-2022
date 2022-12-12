import java.time.InstantSource

fun main() {
    class GridSpot(val height: Int, var distance: Int = Int.MAX_VALUE)
    class Move(val destX: Int, val destY: Int, val direction: String)
    class Grid() {
        var grid: Array<Array<GridSpot>> = arrayOf()
        var moves: ArrayDeque<Move> = ArrayDeque()

        fun addMoves(x: Int, y: Int, direction: String) {
            var source = grid[x][y]
            var dest: GridSpot
            if (x > 0 && direction != "right") {
                dest = grid[x-1][y]
                if (dest.height + 1 >= source.height && dest.distance > source.distance + 1) {
                    dest.distance = source.distance + 1
                    moves.addFirst(Move(x-1, y, "left"))
                }
            }
            if (x < grid.size - 1 && direction != "left") {
                dest = grid[x+1][y]
                if (dest.height + 1 >= source.height && dest.distance > source.distance + 1) {
                    dest.distance = source.distance + 1
                    moves.addFirst(Move(x+1, y, "right"))
                }
            }
            if (y > 0 && direction != "down") {
                dest = grid[x][y-1]
                if (dest.height + 1 >= source.height && dest.distance > source.distance + 1) {
                    dest.distance = source.distance + 1
                    moves.addFirst(Move(x, y-1, "up"))
                }
            }
            if (y < grid.first().size - 1 && direction != "up") {
                dest = grid[x][y+1]
                if (dest.height + 1 >= source.height && dest.distance > source.distance + 1) {
                    dest.distance = source.distance + 1
                    moves.addFirst(Move(x, y+1, "down"))
                }
            }
        }
    }

    fun CreateMap(
        input: List<String>,
        start: Pair<Int, Int>,
        end: Pair<Int, Int>,
        map: Grid
    ): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        var start1 = start
        var end1 = end
        for (i in input.indices) {
            var row = arrayOf<GridSpot>()
            for (j in input[i].indices) {
                val curr = input[i][j]
                when (curr) {
                    'S' -> {
                        start1 = Pair(i, j)
                        row += GridSpot(0)
                    }

                    'E' -> {
                        end1 = Pair(i, j)
                        row += GridSpot('z' - 'a', 0)
                    }

                    else -> {
                        row += GridSpot(curr - 'a')
                    }
                }
            }
            map.grid += row
        }
        return Pair(end1, start1)
    }

    fun part1(input: List<String>): Int {
        var map = Grid()
        var start: Pair<Int, Int> = Pair(-1, -1)
        var end: Pair<Int, Int> = Pair(-1, -1)
        val pair = CreateMap(input, start, end, map)
        end = pair.first
        start = pair.second

        map.addMoves(end.first, end.second, "none")
        while(map.moves.any()) {
            val move = map.moves.removeFirst()
            map.addMoves(move.destX, move.destY, move.direction)
        }

        return map.grid[start.first][start.second].distance
    }

    fun part2(input: List<String>): Int {
        var map = Grid()
        var start: Pair<Int, Int> = Pair(-1, -1)
        var end: Pair<Int, Int> = Pair(-1, -1)
        val pair = CreateMap(input, start, end, map)
        end = pair.first
        start = pair.second

        map.addMoves(end.first, end.second, "none")
        while(map.moves.any()) {
            val move = map.moves.removeFirst()
            map.addMoves(move.destX, move.destY, move.direction)
        }

        var shortest = Int.MAX_VALUE
        for (row in map.grid) {
            row.forEach {
                if (it.height == 0 && it.distance < shortest) shortest = it.distance
            }
        }

        return shortest
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")
    check(part1(testInput) == 31)
    check(part2(testInput) == 29)

    val input = readInput("Day12")
    println(part1(input))
    println(part2(input))
}
