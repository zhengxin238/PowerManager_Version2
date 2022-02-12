package ma.um6p.powermanager.playground

fun main() {
    var months = mutableListOf("January", "February")
    months.add("Mars")
    months.removeAt(0)
    months.remove("February")
    println(months)


}

fun oldCode() {
    var canBeNull: String? = "Hello"
    canBeNull = null

    canBeNull?.let {
        println("canBeNull: ${canBeNull?.length}")
    }
    val name = canBeNull?: "Karim"
    println(name)

    when (0) {
        0 -> {
            print("No season")
            print(" Dead land ?")
        }
        1 -> println("Spring")
        2 -> println("Summer")
        3 -> println("Autumn")
        4 -> println("Winter")
        in 5..10 -> println("5th season.")
        else -> println("10th season doesn't exist.")
    }
    println()
    var x = 5
    while (x > 0) {
        print(" $x")
        x--
    }
    println()
    for (num in 0..10 step 2) { // until, downTo
        print(" $num")
    }

}