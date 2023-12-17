package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val buttons = Array(3) {
        arrayOfNulls<Button>(
            3
        )
    }
    private var textViewPlayer1: TextView? = null
    private var textViewPlayer2: TextView? = null

    private var currentPlayer = "X"
    private var moves = 0
    private var player1Moves = 0
    private var player2Moves = 0
    private var isWinnerAnnounced = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewPlayer1 = findViewById<TextView>(R.id.text_view_Player_1)
        textViewPlayer2 = findViewById<TextView>(R.id.text_view_Player_2)
        for (i in 0..2) {
            for (j in 0..2) {
                val buttonID = "button_$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById(resID)
                buttons[i][j]?.setOnClickListener(this)
            }
        }
        val buttonReset = findViewById<Button>(R.id.button_reset)
        buttonReset.setOnClickListener {
            buttons.forEach {
                it.forEach {
                    it?.text = ""
                }
            }
            moves = 0
            player1Moves = 0
            player2Moves = 0
            textViewPlayer1?.text = "Player 1: $player1Moves"
            textViewPlayer2?.text = "Player 2: $player2Moves"
            currentPlayer = "X"
            isWinnerAnnounced = false
        }
    }


    override fun onClick(view: View) {
        val button = view as Button

        if (button.text.toString() == "") {
            if (!isWinnerAnnounced) {
                moves++
                button.text = currentPlayer

                if (checkForWin()) {
                    isWinnerAnnounced = true
                    Toast.makeText(this, "Player $currentPlayer wins", Toast.LENGTH_SHORT).show()
                } else if (moves == 9) {
                    Toast.makeText(this, "Its a Draw", Toast.LENGTH_SHORT).show()
                } else {

                    if (
                        moves % 2 == 0
                    ) {
                        // player 2
                        button.text = "O"
                        player2Moves++
                        textViewPlayer2?.text = "Player 2: $player2Moves"
                    } else {
                        // player 1
                        button.text = "X"
                        player1Moves++
                        textViewPlayer1?.text = "Player 1: $player1Moves"
                    }

                    currentPlayer = if (currentPlayer == "X") "O" else "X"
                }
            }




        }

    }

    private fun checkForWin(): Boolean {

        if (buttons[0][0]?.text == currentPlayer
            && buttons[1][1]?.text == currentPlayer
            && buttons[2][2]?.text == currentPlayer
        ) {
            return true
        }

        for (i in 0 until 3) {
            if (buttons[i][0]?.text == currentPlayer
                && buttons[i][1]?.text == currentPlayer
                && buttons[i][2]?.text == currentPlayer
            ) {
                return true
            }

            if (buttons[0][i]?.text == currentPlayer
                && buttons[1][i]?.text == currentPlayer
                && buttons[2][i]?.text == currentPlayer
            ) {
                return true
            }
        }

        return false
    }

    private fun player1Wins() {}
    private fun player2Wins() {}
    private fun draw() {}
}