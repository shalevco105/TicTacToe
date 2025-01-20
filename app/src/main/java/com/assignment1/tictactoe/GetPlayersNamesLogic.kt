package com.assignment1.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GetPlayersNamesLogic extends AppCompatActivity {

    private EditText playerOneName, playerTwoName;
    private Button playerOneButton, playerTwoButton;
    private LinearLayout playerOneLayout, playerTwoLayout;
    private String playerOne, playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_players_names);

        playerOneName = findViewById(R.id.player_one_name_edttxt);
        playerTwoName = findViewById(R.id.player_two_name_edttxt);
        playerOneButton = findViewById(R.id.player_one_btn);
        playerTwoButton = findViewById(R.id.player_two_btn);
        playerOneLayout = findViewById(R.id.player_one_layout);
        playerTwoLayout = findViewById(R.id.player_two_layout);
        ImageView backBtn = findViewById(R.id.player_names_back_btn);

        playerOneButton.setOnClickListener(v -> {
            if (TextUtils.isEmpty(playerOneName.getText().toString())) {
                Toast.makeText(this, "Please enter Player One's name", Toast.LENGTH_LONG).show();
            } else {
                playerOne = playerOneName.getText().toString();
                playerOneLayout.setVisibility(View.GONE);
                playerTwoLayout.setVisibility(View.VISIBLE);
            }
        });

        playerTwoButton.setOnClickListener(v -> {
            if (TextUtils.isEmpty(playerTwoName.getText().toString())) {
                Toast.makeText(this, "Please enter Player Two's name", Toast.LENGTH_LONG).show();
            } else {
                playerTwo = playerTwoName.getText().toString();
                Intent intent = new Intent(GetPlayersNamesLogic.this, ChooseSymbolLogic.class);
                intent.putExtra("p1", playerOne);
                intent.putExtra("p2", playerTwo);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(v -> onBackPressed());
    }
}
