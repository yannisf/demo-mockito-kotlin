package demo.mockito.kotlin

class Library(private val supporter: Supporter) {

    internal fun basicSolution(argX: String, argY: String): Int {
        repeat(80) { print("*") }
        supporter.support()
        //throw IllegalStateException() //Will uncomment later to make a point
        return 42
    }


    internal fun alternativeSolution(argX: String, argY: String): Int {
        repeat(80) { print("#") }
        return -1
    }

    fun calculate(decision: Int, argX: String, argY: String) =
            if (decision > 0)
                basicSolution(argX, argY)
            else
                alternativeSolution(argX, argY)

}
