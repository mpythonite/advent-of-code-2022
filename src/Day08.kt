import java.beans.Visibility

fun main() {
    class Tree(val height: Int, var isVisible: Boolean = false, var scenicScore: Int = 1) {
        var up: Tree? = null
        var down: Tree? = null
        var left: Tree? = null
        var right: Tree? = null
    }

    fun buildForest(
        input: List<String>
    ): Array<Array<Tree>> {
        var forest = arrayOf<Array<Tree>>()
        for (i in input.indices) {
            var row = arrayOf<Tree>()
            for (j in input[i].indices) {
                val tree = Tree(height = input[i][j].digitToInt())
                if (i > 0) {
                    forest[i-1][j].down = tree
                    tree.up = forest[i-1][j]
                }
                if (j > 0) {
                    row[j-1].right = tree
                    tree.left = row[j-1]
                }
                row += tree
            }
            forest += row
        }
        return forest
    }

    fun part1(input: List<String>): Int {
        var forest = buildForest(input)

        for (trees in forest) {
            for (tree in trees) {
                var isVisible = true
                var next = tree.up
                while (next != null) {
                    if (next.height >= tree.height) {
                        isVisible = false
                        break
                    }
                    next = next.up
                }
                if (isVisible) tree.isVisible = true

                isVisible = true
                next = tree.down
                while (next != null) {
                    if (next.height >= tree.height) {
                        isVisible = false
                        break
                    }
                    next = next.down
                }
                if (isVisible) tree.isVisible = true

                isVisible = true
                next = tree.left
                while (next != null) {
                    if (next.height >= tree.height) {
                        isVisible = false
                        break
                    }
                    next = next.left
                }
                if (isVisible) tree.isVisible = true

                isVisible = true
                next = tree.right
                while (next != null) {
                    if (next.height >= tree.height) {
                        isVisible = false
                        break
                    }
                    next = next.right
                }
                if (isVisible) tree.isVisible = true
            }
        }

        var visible = 0
        for (trees in forest) {
            for (tree in trees) {
                if (tree.isVisible) visible++
            }
        }

        return visible
    }

    fun part2(input: List<String>): Int {
        var forest = buildForest(input)
        var maxScenicScore = 1

        for (trees in forest) {
            for (tree in trees) {
                var visibility = 0
                var next = tree.up
                while (true) {
                    if (next == null) {
                        tree.scenicScore *= visibility
                        break
                    }
                    if (next!!.height >= tree.height) {
                        tree.scenicScore *= ++visibility
                        break
                    }
                    visibility++
                    next = next!!.up
                }

                visibility = 0
                next = tree.down
                while (true) {
                    if (next == null) {
                        tree.scenicScore *= visibility
                        break
                    }
                    if (next!!.height >= tree.height) {
                        tree.scenicScore *= ++visibility
                        break
                    }
                    visibility++
                    next = next!!.down
                }

                visibility = 0
                next = tree.left
                while (true) {
                    if (next == null) {
                        tree.scenicScore *= visibility
                        break
                    }
                    if (next!!.height >= tree.height) {
                        tree.scenicScore *= ++visibility
                        break
                    }
                    visibility++
                    next = next!!.left
                }

                visibility = 0
                next = tree.right
                while (true) {
                    if (next == null) {
                        tree.scenicScore *= visibility
                        break
                    }
                    if (next!!.height >= tree.height) {
                        tree.scenicScore *= ++visibility
                        break
                    }
                    visibility++
                    next = next!!.right
                }

                if (tree.scenicScore > maxScenicScore) maxScenicScore = tree.scenicScore

            }
        }

        return maxScenicScore
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
