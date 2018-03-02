fun main(args: Array<String>) {
    val message = readLine()!!
    val l = message.length
    val sqrt = Math.sqrt(l.toDouble())
    val lFloor = Math.floor(sqrt).toInt()
    val lCeil = Math.ceil(sqrt).toInt()

    var rowMin = 0
    var colMin = 0
    for (row in lFloor..lCeil) {
        for (col in row..lCeil) {
            if (row * col >= l) {
                rowMin = row
                colMin = col
            }
        }
    }

    for (col in 0 until colMin) {
        for (row in 0 until rowMin) {
            val index = row * colMin + col
            if (index < l) {
                print(message.get(index))
            }
        }
        print(" ")
    }
}