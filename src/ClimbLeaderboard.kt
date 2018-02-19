import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val startScores = Array(n, { scanner.nextInt() })
    val m = scanner.nextInt()
    val aliceScores = Array(m, { scanner.nextInt() })

    val ranks = Array(n, { 0 })

    calcRanks(startScores, ranks)

    var underEdge = n - 1
    aliceScores.forEach { score ->
        var currentIdx = underEdge
        while (currentIdx >= 0 && score > startScores[currentIdx]) {
            currentIdx--
        }
        if (currentIdx < 0) {
            println("1")
        } else if (score == startScores[currentIdx]) {
            println("${ranks[currentIdx]}")
        } else {
            println("${ranks[currentIdx] + 1}")
        }
        underEdge = currentIdx
    }
}


fun calcRanks(scores: Array<Int>, outRanks: Array<Int>) {
    var currentRank = 1;
    var currentScore = scores.first()
    for (i in scores.indices) {
        val sc = scores[i]
        if (sc != currentScore) {
            currentRank++
            currentScore = sc
        }
        outRanks[i] = currentRank
    }
}