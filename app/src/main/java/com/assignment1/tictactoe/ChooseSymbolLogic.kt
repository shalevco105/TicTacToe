package com.assignment1.tictactoe

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChooseSymbolLogic : AppCompatActivity() {

    private lateinit var backBtn: ImageView
    private lateinit var crossImg: ImageView
    private lateinit var crossRadioImg: ImageView
    private lateinit var circleImg: ImageView
    private lateinit var circleRadioImg: ImageView
    private lateinit var continueBtn: Button

    private var selectedSymbol = 0 // 0 = Cross, 1 = Circle
    private var playerOne: String? = null
    private var playerTwo: String? = null

    companion object {
        private const val SELECTED_ALPHA = 1.0f
        private const val UNSELECTED_ALPHA = 0.3f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_choose_symbol)

        playerOne = intent.getStringExtra("p1")
        playerTwo = intent.getStringExtra("p2")

        if (playerOne.isNullOrEmpty() || playerTwo.isNullOrEmpty()) {
            Toast.makeText(this, "Player names are missing!", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        backBtn = findViewById(R.id.pick_side_back_btn)
        crossImg = findViewById(R.id.pick_side_cross_img)
        circleImg = findViewById(R.id.pick_side_circle_img)
        crossRadioImg = findViewById(R.id.pick_side_cross_radio)
        circleRadioImg = findViewById(R.id.pick_side_circle_radio)
        continueBtn = findViewById(R.id.pick_side_continue_btn)

        crossRadioImg.setOnClickListener { updateSymbolSelection(false) }
        circleRadioImg.setOnClickListener { updateSymbolSelection(true) }

        backBtn.setOnClickListener { showBackConfirmationDialog() }

        continueBtn.setOnClickListener {
            val intent = Intent(this@ChooseSymbolLogic, GameLogic::class.java).apply {
                putExtra("p1", playerOne)
                putExtra("p2", playerTwo)
                putExtra("ps", selectedSymbol)
            }
            startActivity(intent)
        }
    }

    private fun updateSymbolSelection(isCircleSelected: Boolean) {
        selectedSymbol = if (isCircleSelected) 1 else 0

        crossRadioImg.setImageResource(
        if (isCircleSelected) R.drawable.radio_button_unchecked else R.drawable.radio_button_checked
        )
        circleRadioImg.setImageResource(
        if (isCircleSelected) R.drawable.radio_button_checked else R.drawable.radio_button_unchecked
        )

        crossImg.alpha = if (isCircleSelected) UNSELECTED_ALPHA else SELECTED_ALPHA
        circleImg.alpha = if (isCircleSelected) SELECTED_ALPHA else UNSELECTED_ALPHA
    }

    private fun showBackConfirmationDialog() {
        AlertDialog.Builder(this)
                .setMessage("Are you sure you want to go back?")
                .setPositiveButton("Yes") { _, _ -> super.onBackPressed() }
            .setNegativeButton("No", null)
                .show()
    }
}
