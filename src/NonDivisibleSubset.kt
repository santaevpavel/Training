import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val k = scanner.nextInt()

    val nums = IntArray(n, { 0 })
    for (i in 0 until n) {
        nums[i] = scanner.nextInt()
    }

    val counts = Array(k / 2 + 1, { i ->
        Column(
                i,
                nums.count { e -> e % k == i },
                nums.count { e -> e % k == k - i }
        )
    })

    var sum = 0
    for (c in counts) {
        //println(c)
        if (c.i == 0) {
            if (c.a1 > 0) {
                sum++
            }
        } else if (k % 2 == 0 && k / 2 == c.i && c.a1 > 0) {
            sum++
        } else {
            sum += Math.max(c.a1, c.a2)
        }
    }
    println(sum)

}

data class Column(val i: Int, val a1: Int, val a2: Int)
