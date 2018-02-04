import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val k = scanner.nextInt()
    val queen = Point(scanner.nextInt(), scanner.nextInt())
    val obstacles = Array<Point>(k, { Point(0, 0) })
    for (i in 0 until k) {
        obstacles[i] = Point(scanner.nextInt(), scanner.nextInt())
    }

    val dirs = arrayOf(
            Point(0, 1),
            Point(1, 1),
            Point(1, 0),
            Point(1, -1),
            Point(0, -1),
            Point(-1, -1),
            Point(-1, 0),
            Point(-1, 1)
    )
    val distance = IntArray(8)

    distance[0] = n - queen.y // v
    distance[1] = if (queen.y > queen.x) n - queen.y else n - queen.x
    distance[2] = n - queen.x // h
    distance[3] = if (queen.y > n - queen.x) n - queen.x else queen.y - 1
    distance[4] = queen.y - 1// v
    distance[5] = if (queen.y > queen.x) queen.x - 1 else queen.y - 1
    distance[6] = queen.x - 1// h
    distance[7] = if (queen.y > n - queen.x) n - queen.y else queen.x - 1

    //distance = distance.map { if (it > 0) it - 1 else it }.toIntArray()

    for (o in obstacles) {
        if (isAttacking(queen, o)) {
            val attackDir = o.sub(queen).norm()
            val i = dirs.indexOf(attackDir)
            distance[i] = Math.min(distance[i], o.sub(queen).dirLength() - 1)
        }
    }
    val sum = distance.sum()

    println(sum)
}

fun isAttacking(queen: Point, obstacle: Point): Boolean {
    val sub = obstacle.sub(queen)
    return queen.x == obstacle.x
            || queen.y == obstacle.y
            || sub.x == sub.y
            || sub.x == -sub.y
}

data class Point(val x: Int, val y: Int) {

    fun sub(other: Point): Point {
        return Point(x - other.x, y - other.y)
    }

    fun dirLength(): Int {
        return if (Math.abs(x) > 0 && Math.abs(y) > 0) {
            Math.abs(x)
        } else {
            Math.abs(x) + Math.abs(y)
        }
    }

    fun norm(): Point {
        return Point(
                Math.signum(x.toDouble()).toInt(),
                Math.signum(y.toDouble()).toInt()
        )
    }
}
