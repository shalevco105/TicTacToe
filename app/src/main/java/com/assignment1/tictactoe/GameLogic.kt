package com.assignment1.tictactoe

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameLogic : AppCompatActivity(), View.OnClickListener {

    private lateinit var gameBoxes: Array<ImageView>

    private lateinit var playerOne: String
    private lateinit var playerTwo: String
    private var playerOneWinCount = 0
    private var playerTwoWinCount = 0
    private var currentPlayer = 0

    private var isGameActive = true
    private val filledPositions = IntArray(9) { -1 }
    private val WINNING_COMBINATIONS = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8), // Rows
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8), // Columns
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)  // Diagonals
    )

    private lateinit var playerOneWins: TextView
    private lateinit var playerTwoWins: TextView
    private lateinit var playerOneName: TextView
    private lateinit var playerTwoName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        playerOne = intent.getStringExtra("p1") ?: ""
        playerTwo = intent.getStringExtra("p2") ?: ""
        currentPlayer = intent.getIntExtra("ps", 0)

        playerOneName = findViewById(R.id.player_one_name_txt)
        playerTwoName = findViewById(R.id.player_two_name_txt)
        playerOneWins = findViewById(R.id.player_one_win_count_txt)
        playerTwoWins = findViewById(R.id.player_two_won_txt)

        playerOneName.text = playerOne
        playerTwoName.text = playerTwo

        gameBoxes = arrayOf(
            findViewById(R.id.img_1), findViewById(R.id.img_2), findViewById(R.id.img_3),
            findViewById(R.id.img_4), findViewById(R.id.img_5), findViewById(R.id.img_6),
            findViewById(R.id.img_7), findViewById(R.id.img_8), findViewById(R.id.img_9)
        )

        gameBoxes.forEach { box ->
            box.setOnClickListener(this)
        }

        findViewById<ImageView>(R.id.offline_game_back_btn).setOnClickListener {
            showQuitDialog()
        }

        updateScoreBoard()
    }

    override fun onClick(view: View) {
        if (!isGameActive) return

        val selectedBox = view as ImageView
        val boxIndex = (view.tag.toString().toInt()) - 1

        if (filledPositions[boxIndex] == -1) {
            filledPositions[boxIndex] = currentPlayer
            selectedBox.setImageResource(if (currentPlayer == 0) R.drawable.cross else R.drawable.circle)

            when {
                checkForWin() -> showWinDialog()
                checkForDraw() -> showDrawDialog()
                else -> switchPlayer()
            }
        }
    }

    private fun checkForWin(): Boolean {
        for (combo in WINNING_COMBINATIONS) {
            if (filledPositions[combo[0]] == currentPlayer &&
                filledPositions[combo[1]] == currentPlayer &&
                filledPositions[combo[2]] == currentPlayer
            ) {
                isGameActive = false
                return true
            }
        }
        return false
    }

    private fun checkForDraw(): Boolean {
        return if (filledPositions.all { it != -1 }) {
            isGameActive = false
            true
        } else {
            false
        }
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == 0) 1 else 0
    }

    private fun updateScoreBoard() {
        playerOneWins.text = playerOneWinCount.toString()
        playerTwoWins.text = playerTwoWinCount.toString()
    }

    private fun showWinDialog() {
        if (currentPlayer == 0) {
            playerOneWinCount++
        } else {
            playerTwoWinCount++
        }
        updateScoreBoard()

        AlertDialog.Builder(this)
            .setTitle("We Have a Winner!")
            .setMessage("${if (currentPlayer == 0) playerOne else playerTwo} Wins!")
            .setPositiveButton("Play Again") { _, _ -> resetGame() }
            .setNegativeButton("Quit") { _, _ -> finish() }
            .show()
    }

    private fun showDrawDialog() {
        AlertDialog.Builder(this)
            .setTitle("It's a Draw!")
            .setMessage("No one wins this round.")
            .setPositiveButton("Play Again") { _, _ -> resetGame() }
            .setNegativeButton("Quit") { _, _ -> finish() }
            .show()
    }

    private fun resetGame() {
        filledPositions.fill(-1)
        gameBoxes.forEach { box -> box.setImageResource(0) }
        isGameActive = true
        currentPlayer = intent.getIntExtra("ps", 0)
    }

    private fun showQuitDialog() {
        AlertDialog.Builder(this)
            .setTitle("Quit Game")
            .setMessage("Are you sure you want to quit?")
            .setPositiveButton("Yes") { _, _ -> finish() }
            .setNegativeButton("No", null)
            .show()
    }
}
