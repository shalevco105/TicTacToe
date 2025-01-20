package com.assignment1.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameMenuLogic extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);

        Button withAFriendBtn = findViewById(R.id.btn_start);
        Button startGameButton = findViewById(R.id.start_game_button);

        withAFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMenuLogic.this, GetPlayersNamesLogic.class);
                startActivity(intent);
            }
        });

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMenuLogic.this, GetPlayersNamesLogic.class);
                startActivity(intent);
            }
        });
    }
}
