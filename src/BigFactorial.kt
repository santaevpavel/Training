import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()

    val f = factorial(n)
    println(f.toString())
}

fun factorial(n: Int): BigInteger {

    var result = BigInteger("1")

    if (n <= 1)
        return result

    for (i in 2..n) {
        val I = BigInteger(i.toString())
        result = result.mul(I)
        println(result)
    }

    return result
}

class BigInteger {

    private val value: IntArray

    constructor(strValue: String) {
        this.value = strValue.reversed().map { c -> c - '0' }.toIntArray()
    }

    private constructor(value: IntArray) {
        this.value = value
    }

    fun mul(other: BigInteger): BigInteger {
        var result = IntArray(other.value.size + value.size)
        val localResult = IntArray(other.value.size + value.size)
        println("size = ${value.size} ${other.value.size} ")
        alloc(other.value.size * value.size * 2, "mul")

        for (i in other.value.indices) {
            localResult.fill(0)

            var remainder = 0
            for (j in value.indices) {
                val mul = value[j] * other.value[i] + remainder
                localResult[i + j] = mul % 10
                remainder = mul / 10
            }
            localResult[i + value.size] = remainder

            val resultInt = BigInteger(result)
            val localInt = BigInteger(localResult)

            result = resultInt.sum(localInt).value
        }
        return BigInteger(result)
    }

    fun sum(other: BigInteger): BigInteger {
        val maxSize = Math.max(value.size, other.value.size)
        val result = IntArray(maxSize + 1)
        alloc(maxSize + 1, "sum")
        var carrier = 0
        for (i in 0..maxSize) {
            val sum = value.getOrElse(i, { 0 }) + other.value.getOrElse(i, { 0 }) + carrier
            result[i] = sum % 10
            carrier = sum / 10
        }

        return BigInteger(result)
    }

    override fun toString(): String {
        return value.reversed()
                .dropWhile { d -> d == 0 }
                .map { d -> d.toString() }
                .joinToString("")
                .let { if (it.isEmpty()) "0" else it }
    }

}

fun alloc(size: Int, sum: String) {
    //mem += size
    //println("mem ($sum) = ${size / 1024 * 4}  tot = ${mem / 1024 * 4}")
}

var mem = 0