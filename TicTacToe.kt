val listString = mutableListOf<MutableList<Char>>()
fun printGrid() {
    println("---------")
    for (i in 0..2) {
        print("| ")
        for (j in 0..2) {
            print("${listString[i][j]} ")
        }
        println("|")
    }
    println("---------")
}

fun main() {
    var star = 0
    for (i in 0..2) {
        listString.add(mutableListOf())
        for (j in star..(star + 2)) {
            listString[i].add('_')
        }
        star += 3
    }
    printGrid()
    var current = 'X'
    var isExit = false
    while (!isExit) {
        print("Enter the coordinates: ")

        var valid = false
        while (!valid) {
            try {
                val (x, y) = readln().split(" ")

                val x2 = x.toInt()
                val y2 = y.toInt()
                if (x2 !in 1..3 || y2 !in 1..3) {
                    print("Coordinates should be from 1 to 3!\nEnter the coordinates:")
                } else {
                    if (listString[x2 - 1][y2 - 1] != '_') {
                        print("This cell is occupied! Choose another one!\nEnter the coordinates: ")
                    } else {
                        listString[x2 - 1][y2 - 1] = current
                        if (current == 'O') current = 'X' else current = 'O'
                        valid = true
                        printGrid()
                    }
                }


            } catch (e: NumberFormatException) {
                print("You should enter numbers!\nEnter the coordinates:")
            }
        }
        var input = ""
        listString.forEach { it -> it.forEach { it -> input += it } }
        if (checkState(input)) isExit = true
    }
}

fun checkState(input: String): Boolean {
    val x = ultimateCHecker(input, 'X')
    val o = ultimateCHecker(input, 'O')
    val spas = isContaintSpaces(input)
    val nx = input.count { it -> it == 'X' }
    val no = input.count { it -> it == 'O' }
    val impossible = (nx - no) >= 2 || (no - nx) >= 2
    if (x == 0 && o == 0 && !spas) {
        println("Draw")
        return true
    } else {
        if (x == 1 && !impossible && o != 1) {
            println("X wins")
            return true
        } else if (o == 1 && !impossible && x != 1) {
            println("O wins")
            return true
        }
    }
    return false
}

fun ultimateCHecker(input: String, char: Char): Int {
    var total = 0
    var tmp = 0
    var tmp2 = 0
    var start = 0
    var start2 = 0

    repeat(3) {
        tmp = 0
        tmp2 = 0

        for (i in start..(start + 2)) {
            if (input[i] == char) tmp++
        }

        for (i in start2..8 step +3) {
            if (input[i] == char) tmp2++
        }

        start += 3
        start2 += 1

        if (tmp == 3 || tmp2 == 3) total++
    }
    if (input[0] == char && input[4] == char && input[8] == char) total++
    if (input[2] == char && input[4] == char && input[6] == char) total++

    return total
}

fun isContaintSpaces(input: String): Boolean {
    return input.contains('_')
}
