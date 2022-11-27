package com.example.thelastchallengeadmin.vrgame.DrawingGame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.vrgame.DrawingGame.AddWords.AddWords
import com.example.thelastchallengeadmin.vrgame.DrawingGame.DrawingGameWords.drawGameWordsList
import com.example.thelastchallengeadmin.vrgame.DrawingGame.EditGameDetails.EditDrawingGameDetails
import com.example.thelastchallengeadmin.vrgame.ForbiddenWords.forbiddenwordsmenu
import com.example.thelastchallengeadmin.vrgame.Mimicry.MimicryMenu
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.BottomSheetAddWords.bottomSheetAddWords
import com.example.thelastchallengeadmin.vrgame.QuizGame.QuizgameMenu
import kotlinx.android.synthetic.main.activity_virtualgamemenu.*

class drawingGame  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.draw_game_menu)

        button.setOnClickListener {
            val intent = Intent(this, EditDrawingGameDetails::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, drawGameWordsList::class.java)
            startActivity(intent)
        }

    }

}