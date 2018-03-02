import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val p = scanner.nextInt()

    val info = Info()

    for (i in 0 until p) {
        val a = scanner.nextInt()
        val b = scanner.nextInt()

        info.add(a, b)
    }
    println(info.getPairsCount(n))
}

class Info {
    private val peoples = HashMap<Int, ArrayList<Int>>()
    private val countries = ArrayList<ArrayList<Int>>()
    //        1 2    3 4    5 6   2 3  1 6
    //        1 2 3 4    5 6
    fun add(a: Int, b: Int) {
        if (peoples.containsKey(a)) {
            if (peoples.containsKey(b)) {
                if (peoples[a] !== peoples[b]) {
                    peoples[a]!!.addAll(peoples[b]!!)

                    countries.remove(peoples[b]!!)
                    for (b1 in peoples[b]!!) {
                        peoples.set(b1, peoples[a]!!)
                    }
                    peoples.set(b, peoples[a]!!)
                }
            } else {
                addToCountry(peoples[a]!!, b)
            }
        } else {
            if (peoples.containsKey(b)) {
                addToCountry(peoples[b]!!, a)
            } else {
                val list = ArrayList<Int>()
                countries.add(list)
                addToCountry(list, a)
                addToCountry(list, b)
            }
        }
    }

    fun getPairsCount(n: Int): Long {
        var sum = 0L
        val m = n - peoples.keys.size.toLong()
        for (i in 0 until countries.size - 1) {
            for (j in i + 1 until countries.size) {
                sum += countries[i].size.toLong() * countries[j].size.toLong()
            }
            sum += m * countries[i].size.toLong()
        }
        sum += m * countries.last().size
        sum += m * (m - 1) / 2


        return sum
    }

    private fun addToCountry(country: ArrayList<Int>, a: Int) {
        peoples[a] = country
        country.add(a)
    }
}

