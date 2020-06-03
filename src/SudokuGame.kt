import java.io.File
import java.util.*
class SudokuGame {


    val solution = createSolution()

    val visibleElements = initVisibleElements()

    val sudokuBoard = readSudokuBoard()

    private fun readSudokuBoard(): Array<IntArray> {
        val sudokuFile = File("./src/sudoku_1")

        val sudokuArray = Array(9) { IntArray(9) }
        sudokuFile.readLines().forEachIndexed { index, line ->
            sudokuArray[index] = line.split(" ").map { it.toInt() }.toIntArray()
        }

        return sudokuArray
    }

    private fun randomFileName(): String {
        val randomNumber = (1..10).random()
        return "res/sudoku_$randomNumber"
    }


    private fun createSolution(): Array<IntArray> {
        return arrayOf(
            intArrayOf(3, 5, 9, 6, 1, 8, 4, 2, 7),
            intArrayOf(7, 4, 2, 5, 3, 9, 8, 6, 1),
            intArrayOf(1, 6, 8, 4, 7, 2, 9, 5, 3),
            intArrayOf(4, 2, 3, 8, 9, 5, 7, 1, 6),
            intArrayOf(5, 8, 7, 1, 6, 4, 3, 9, 2),
            intArrayOf(6, 9, 1, 7, 2, 3, 5, 8, 4),
            intArrayOf(2, 7, 5, 9, 4, 6, 1, 3, 8),
            intArrayOf(8, 3, 4, 2, 5, 1, 6, 7, 9),
            intArrayOf(9, 1, 6, 3, 8, 7, 2, 4, 5))
    }


    private fun initVisibleElements(): Array<BooleanArray> {
        return arrayOf(
            booleanArrayOf(false, true, true, true, true, true, true, true, true),
            booleanArrayOf(true, true, true, true, false, true, true, true, false),
            booleanArrayOf(true, true, false, true, true, true, true, true, true),
            booleanArrayOf(false, true, true, true, false, true, true, true, true),
            booleanArrayOf(true, true, true, true, true, true, false, true, true),
            booleanArrayOf(true, false, true, false, true, true, true, true, true),
            booleanArrayOf(true, true, true, true, true, true, true, false, true),
            booleanArrayOf(true, true, true, true, true, true, true, false, true),
            booleanArrayOf(true, false, true, true, false, true, true, true, true))

    }



    fun isUserInputCorrect(userInput: Int, row: Int, col: Int): Boolean {

        if (userInput < 0 || userInput > 9) {
            return false
        }


        if (sudokuBoard[row].contains(userInput)) {
            return false
        }


        (0 until 9)
            .filter { sudokuBoard[it][col] == userInput }
            .forEach { return false }


        val startRow = row / 3 * 3
        val startColumn = col / 3 * 3
        (startRow until startRow + 3).forEach { i ->
            (startColumn until startColumn + 3)
                .filter { j -> sudokuBoard[i][j] == userInput }
                .forEach { return false }
        }

        return true
    }



    fun isSolved(): Boolean {
        visibleElements.forEach { array ->
            array.forEach { element ->
                if (!element) {
                    return false
                }
            }
        }
        return true
    }
}