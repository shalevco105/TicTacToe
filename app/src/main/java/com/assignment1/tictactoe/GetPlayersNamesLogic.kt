package com.assignment1.tictactoe

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class GetPlayersNamesLogic : AppCompatActivity() {

    private lateinit var playerOneName: EditText
    private lateinit var playerTwoName: EditText
    private lateinit var playerOneButton: Button
    private lateinit var playerTwoButton: Button
    private lateinit var playerOneLayout: LinearLayout
    private lateinit var playerTwoLayout: LinearLayout
    private var playerOne: String = ""
    private var playerTwo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_players_names)

        playerOneName = findViewById(R.id.player_one_name_edttxt)
        playerTwoName = findViewById(R.id.player_two_name_edttxt)
        playerOneButton = findViewById(R.id.player_one_btn)
        playerTwoButton = findViewById(R.id.player_two_btn)
        playerOneLayout = findViewById(R.id.player_one_layout)
        playerTwoLayout = findViewById(R.id.player_two_layout)
        val backBtn: ImageView = findViewById(R.id.player_names_back_btn)

        playerOneButton.setOnClickListener {
            if (TextUtils.isEmpty(playerOneName.text.toString())) {
                Toast.makeText(this, "Please enter Player One's name", Toast.LENGTH_LONG).show()
            } else {
                playerOne = playerOneName.text.toString()
                playerOneLayout.visibility = View.GONE
                playerTwoLayout.visibility = View.VISIBLE
            }
        }

        playerTwoButton.setOnClickListener {
            if (TextUtils.isEmpty(playerTwoName.text.toString())) {
                Toast.makeText(this, "Please enter Player Two's name", Toast.LENGTH_LONG).show()
            } else {
                playerTwo = playerTwoName.text.toString()
                val intent = Intent(this@GetPlayersNamesLogic, ChooseSymbolLogic::class.java)
                intent.putExtra("p1", playerOne)
                intent.putExtra("p2", playerTwo)
                startActivity(intent)
            }
        }

        backBtn.setOnClickListener {
            onBackPressed()
        }
    }
}
