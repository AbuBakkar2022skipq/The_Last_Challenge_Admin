package com.example.thelastchallengeadmin.vrgame.StrungWords

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.vrgame.StrungWords.StrungWordsEditGame.StrungWords
import com.example.thelastchallengeadmin.vrgame.StrungWords.StrungWordsWordsList.StrungWordsWordsList
import kotlinx.android.synthetic.main.activity_virtualgamemenu.*

class StrungWordsMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.songs_game_menu)
        textView4.text="Strung Words"
        button.setOnClickListener {
            val intent = Intent(this, StrungWords::class.java)
            startActivity(intent) }
        

        button2.setOnClickListener {
            val intent = Intent(this, StrungWordsWordsList::class.java)
            startActivity(intent)
        }
    }
}