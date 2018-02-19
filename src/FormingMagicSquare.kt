import java.util.*
import kotlin.math.abs


typealias Matrix = Array<Int>

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    var matrix = Matrix(9, { 0 })

    for (i in 0..2) {
        for (j in 0..2) {
            matrix.cell(i, j, scanner.nextInt())
        }
    }
    val origin = matrix.clone()

    var cost = calcCostDiff(15, matrix)
    var i = 0

    val cells = arrayOf(
            Pair(1, 1),
            Pair(0, 0), Pair(0, 2), Pair(2, 0), Pair(2, 2),
            Pair(0, 1), Pair(1, 0), Pair(1, 2), Pair(2, 1))
    var pos = 0
    while (i < 100 && cost != 0) {

        val newMatrix = matrix.clone()
        val newMatrix2 = matrix.clone()

        val x = cells[pos % 9].first
        val y = cells[pos % 9].second
        val cell = newMatrix.cell(x, y)
        newMatrix.cell(x, y, cell + 1)
        var newCost = calcCostDiff(15, newMatrix)
        if (newCost < cost) {
            cost = newCost
            matrix = newMatrix

            i++
            pos = 0
            matrix.printMatrix()
            continue
        }

        newMatrix2.cell(x, y, cell - 1)
        newCost = calcCostDiff(15, newMatrix2)
        if (newCost < cost) {
            cost = newCost
            matrix = newMatrix2

            i++
            pos = 0
            matrix.printMatrix()
            continue
        }



        i++
        pos++
    }

}

private fun Matrix.printMatrix() {
    println()
    for (i in 0..2) {
        for (j in 0..2) {
            print("${cell(i, j)} ")
        }
        println()
    }
    println()
}

private fun calcCostDiff(expected: Int, matrix: Matrix): Int {
    var cost = 0

    var sumDiag = 0
    var sumDiagInv = 0

    for (i in 0..2) {
        var sumCol = 0
        var sumRow = 0
        for (j in 0..2) {
            sumCol += matrix.cell(i, j)
            sumRow += matrix.cell(j, i)
        }
        cost += abs(expected - sumCol)
        cost += abs(expected - sumRow)
        sumDiag += matrix.cell(i, i)
        sumDiagInv += matrix.cell(2 - i, i)
    }
    return cost
}

private fun Matrix.cell(x: Int, y: Int): Int {
    return get(x * 3 + y)
}

private fun Matrix.cell(x: Int, y: Int, value: Int) {
    return set(x * 3 + y, value)
}

