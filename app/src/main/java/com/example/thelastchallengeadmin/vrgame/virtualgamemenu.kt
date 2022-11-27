package com.example.thelastchallengeadmin.vrgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.addGame.physicalgamespage
import com.example.thelastchallengeadmin.vrgame.DrawingGame.drawingGame
import com.example.thelastchallengeadmin.vrgame.ForbiddenWords.forbiddenwordsmenu
import com.example.thelastchallengeadmin.vrgame.LaughWars.LaughWarsMenu
import com.example.thelastchallengeadmin.vrgame.Mimicry.MimicryMenu
import com.example.thelastchallengeadmin.vrgame.QuizGame.QuizgameMenu
import com.example.thelastchallengeadmin.vrgame.Songs.SongList.songlist
import com.example.thelastchallengeadmin.vrgame.Songs.SongsGameMenu
import com.example.thelastchallengeadmin.vrgame.StrungWords.StrungWordsMenu
import com.example.thelastchallengeadmin.vrgame.TheLetter.TheLetterDetailsEdit
import kotlinx.android.synthetic.main.activity_virtualgamemenu.*

class virtualgamemenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_virtualgamemenu)

        button.setOnClickListener {
            val intent = Intent(this, MimicryMenu::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, forbiddenwordsmenu::class.java)
            startActivity(intent)
        }

        quiz.setOnClickListener {
            val intent = Intent(this, QuizgameMenu::class.java)
            startActivity(intent)
        }

        draw.setOnClickListener {
            val intent = Intent(this, drawingGame::class.java)
            startActivity(intent)
        }

        Songs.setOnClickListener {
            val intent = Intent(this, SongsGameMenu::class.java)
            startActivity(intent)
        }

        theLetter.setOnClickListener {
            val intent = Intent(this, TheLetterDetailsEdit::class.java)
            startActivity(intent)
        }

        laughWars.setOnClickListener {
            val intent = Intent(this, LaughWarsMenu::class.java)
            startActivity(intent)
        }
        strungWords.setOnClickListener {
            val intent = Intent(this, StrungWordsMenu::class.java)
            startActivity(intent)
        }
    }

}