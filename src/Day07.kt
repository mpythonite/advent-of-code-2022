fun main() {
    data class File(val name: String, val size: Int)
    class Directory(val name: String, val dirs: MutableList<Directory> = mutableListOf(), val files: MutableList<File> = mutableListOf(), val parent: Directory? = null) {
        fun size(): Int {
            var size = this.files.fold(0){ sum, element -> sum + element.size}
            dirs.forEach{ size += it.size() }
            return size
        }
    }

    fun createFileSystem(input: List<String>): List<Directory> {
        val directories = mutableListOf(Directory(name = "/"))
        val fileSystem: MutableList<Directory> = mutableListOf(directories[0])
        var currentDirectory = Directory("")
        var iterator = input.listIterator()
        while (iterator.hasNext()) {
            var current = iterator.next()
            when (current.first()) {
                '$' -> {
                    when (current.substring(2, 4)) {
                        "cd" -> {
                            val arg = current.substring((5))
                            if (arg == "/") {
                                currentDirectory = fileSystem.first()
                            } else if (arg == "..") {
                                currentDirectory = currentDirectory.parent!!
                            } else {
                                currentDirectory = currentDirectory.dirs.find { it.name == arg }!!
                            }
                        }

                        "ls" -> {
                            while (iterator.hasNext()) {
                                current = iterator.next()
                                if (current.first() == '$') {
                                    current = iterator.previous()
                                    break
                                } else {
                                    if (current.substring(0, 3) == "dir") {
                                        val newDir = Directory(name = current.substring(4), parent = currentDirectory)
                                        currentDirectory.dirs.add(newDir)
                                        directories.add(newDir)
                                    } else {
                                        val fileArgs = current.split(' ')
                                        currentDirectory.files.add(File(size = fileArgs[0].toInt(), name = fileArgs[1]))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return directories
    }

    fun part1(input: List<String>): Int {
        val directories = createFileSystem(input)

        var totalSize = 0
        directories.forEach{ if (it.size() <= 100000) totalSize += it.size()}

        return totalSize
    }

    fun part2(input: List<String>): Int {
        val directories = createFileSystem(input)

        val freeSpace = 70000000 - directories.first().size()
        val neededSpace = 30000000
        var minSize = Int.MAX_VALUE
        directories.forEach{
            if (freeSpace + it.size() >= neededSpace && it.size() <= minSize) minSize = it.size()
        }

        return minSize
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
