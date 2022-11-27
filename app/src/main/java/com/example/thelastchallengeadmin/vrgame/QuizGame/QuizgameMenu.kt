package com.example.thelastchallengeadmin.vrgame.QuizGame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.vrgame.QuizGame.EditDetails.QuizGameDetailsEdit
import com.example.thelastchallengeadmin.vrgame.QuizGame.EditQuestion.QuizgameQuestionsView
import kotlinx.android.synthetic.main.quiz_game_menu.*

class QuizgameMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_game_menu)

        button2.setOnClickListener {
            val intent = Intent(this, QuizgameQuestionsView::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val intent = Intent(this, QuizGameDetailsEdit::class.java)
            startActivity(intent)
        }
    }
}