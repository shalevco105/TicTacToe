package com.assignment1.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameLogic extends AppCompatActivity implements View.OnClickListener {

    private ImageView[] gameBoxes;

    private String playerOne, playerTwo;
    private int playerOneWinCount = 0, playerTwoWinCount = 0;
    private int currentPlayer;

    private boolean isGameActive = true;
    private int[] filledPositions = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    private static final int[][] WINNING_COMBINATIONS = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6}             // Diagonals
    };

    private TextView playerOneWins, playerTwoWins, playerOneName, playerTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerOne = getIntent().getStringExtra("p1");
        playerTwo = getIntent().getStringExtra("p2");
        currentPlayer = getIntent().getIntExtra("ps", 0);

        playerOneName = findViewById(R.id.player_one_name_txt);
        playerTwoName = findViewById(R.id.player_two_name_txt);
        playerOneWins = findViewById(R.id.player_one_win_count_txt);
        playerTwoWins = findViewById(R.id.player_two_won_txt);

        playerOneName.setText(playerOne);
        playerTwoName.setText(playerTwo);

        gameBoxes = new ImageView[]{
                findViewById(R.id.img_1), findViewById(R.id.img_2), findViewById(R.id.img_3),
                findViewById(R.id.img_4), findViewById(R.id.img_5), findViewById(R.id.img_6),
                findViewById(R.id.img_7), findViewById(R.id.img_8), findViewById(R.id.img_9)
        };

        for (ImageView box : gameBoxes) {
            box.setOnClickListener(this);
        }

        findViewById(R.id.offline_game_back_btn).setOnClickListener(v -> showQuitDialog());

        updateScoreBoard();
    }

    @Override
    public void onClick(View view) {
        if (!isGameActive) return;

        ImageView selectedBox = (ImageView) view;
        int boxIndex = Integer.parseInt(view.getTag().toString()) - 1;

        if (filledPositions[boxIndex] == -1) {
            filledPositions[boxIndex] = currentPlayer;
            selectedBox.setImageResource(currentPlayer == 0 ? R.drawable.cross : R.drawable.circle);

            if (checkForWin()) {
                showWinDialog();
            } else if (checkForDraw()) {
                showDrawDialog();
            } else {
                switchPlayer();
            }
        }
    }

    private boolean checkForWin() {
        for (int[] combo : WINNING_COMBINATIONS) {
            if (filledPositions[combo[0]] == currentPlayer &&
                    filledPositions[combo[1]] == currentPlayer &&
                    filledPositions[combo[2]] == currentPlayer) {
                isGameActive = false;
                return true;
            }
        }
        return false;
    }

    private boolean checkForDraw() {
        for (int pos : filledPositions) {
            if (pos == -1) return false;
        }
        isGameActive = false;
        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 0) ? 1 : 0;
    }

    private void updateScoreBoard() {
        playerOneWins.setText(String.valueOf(playerOneWinCount));
        playerTwoWins.setText(String.valueOf(playerTwoWinCount));
    }

    private void showWinDialog() {
        if (currentPlayer == 0) {
            playerOneWinCount++;
        } else {
            playerTwoWinCount++;
        }
        updateScoreBoard();

        new AlertDialog.Builder(this)
                .setTitle("We Have a Winner!")
                .setMessage((currentPlayer == 0 ? playerOne : playerTwo) + " Wins!")
                .setPositiveButton("Play Again", (dialog, which) -> resetGame())
                .setNegativeButton("Quit", (dialog, which) -> finish())
                .show();
    }

    private void showDrawDialog() {
        new AlertDialog.Builder(this)
                .setTitle("It's a Draw!")
                .setMessage("No one wins this round.")
                .setPositiveButton("Play Again", (dialog, which) -> resetGame())
                .setNegativeButton("Quit", (dialog, which) -> finish())
                .show();
    }

    private void resetGame() {
        for (int i = 0; i < filledPositions.length; i++) {
            filledPositions[i] = -1;
        }
        for (ImageView box : gameBoxes) {
            box.setImageResource(0);
        }
        isGameActive = true;
        currentPlayer = getIntent().getIntExtra("ps", 0);
    }

    private void showQuitDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Quit Game")
                .setMessage("Are you sure you want to quit?")
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNegativeButton("No", null)
                .show();
    }
}
