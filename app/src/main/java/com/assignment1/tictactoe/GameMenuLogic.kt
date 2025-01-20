package com.assignment1.tictactoe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GameMenuLogic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_start)

        val withAFriendBtn: Button = findViewById(R.id.btn_start)
        val startGameButton: Button = findViewById(R.id.start_game_button)

        withAFriendBtn.setOnClickListener {
            val intent = Intent(this@GameMenuLogic, GetPlayersNamesLogic::class.java)
            startActivity(intent)
        }

        startGameButton.setOnClickListener {
            val intent = Intent(this@GameMenuLogic, GetPlayersNamesLogic::class.java)
            startActivity(intent)
        }
    }
}
