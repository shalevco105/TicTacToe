package com.assignment1.tictactoe;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseSymbolLogic extends AppCompatActivity {

    private ImageView BackBtn, CrossImg, CrossRadioImg, CircleImg, CircleRadioImg;
    private Button ContinueBtn;

    private static final float SELECTED_ALPHA = 1.0f;
    private static final float UNSELECTED_ALPHA = 0.3f;

    // 0 = Cross, 1 = Circle
    private int selectedSymbol;
    private String playerOne;
    private String playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choose_symbol);

        playerOne = getIntent().getStringExtra("p1");
        playerTwo = getIntent().getStringExtra("p2");

        if (playerOne == null || playerTwo == null) {
            Toast.makeText(this, "Player names are missing!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        BackBtn = findViewById(R.id.pick_side_back_btn);
        CrossImg = findViewById(R.id.pick_side_cross_img);
        CircleImg = findViewById(R.id.pick_side_circle_img);
        CrossRadioImg = findViewById(R.id.pick_side_cross_radio);
        CircleRadioImg = findViewById(R.id.pick_side_circle_radio);
        ContinueBtn = findViewById(R.id.pick_side_continue_btn);

        CrossRadioImg.setOnClickListener(v -> updateSymbolSelection(false));
        CircleRadioImg.setOnClickListener(v -> updateSymbolSelection(true));

        BackBtn.setOnClickListener(v -> showBackConfirmationDialog());

        ContinueBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ChooseSymbolLogic.this, GameLogic.class);
            intent.putExtra("p1", playerOne);
            intent.putExtra("p2", playerTwo);
            intent.putExtra("ps", selectedSymbol);
            startActivity(intent);
        });
    }

    private void updateSymbolSelection(boolean isCircleSelected) {
        selectedSymbol = isCircleSelected ? 1 : 0;

        CrossRadioImg.setImageResource(isCircleSelected ? R.drawable.radio_button_unchecked : R.drawable.radio_button_checked);
        CircleRadioImg.setImageResource(isCircleSelected ? R.drawable.radio_button_checked : R.drawable.radio_button_unchecked);

        CrossImg.setAlpha(isCircleSelected ? UNSELECTED_ALPHA : SELECTED_ALPHA);
        CircleImg.setAlpha(isCircleSelected ? SELECTED_ALPHA : UNSELECTED_ALPHA);
    }

    private void showBackConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to go back?")
                .setPositiveButton("Yes", (dialog, which) -> super.onBackPressed())
                .setNegativeButton("No", null)
                .show();
    }
}
