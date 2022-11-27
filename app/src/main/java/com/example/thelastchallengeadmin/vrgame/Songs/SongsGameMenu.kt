package com.example.thelastchallengeadmin.vrgame.Songs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.vrgame.DrawingGame.DrawingGameWords.drawGameWordsList
import com.example.thelastchallengeadmin.vrgame.DrawingGame.EditGameDetails.EditDrawingGameDetails
import com.example.thelastchallengeadmin.vrgame.Songs.SongDetails.SongDetails
import com.example.thelastchallengeadmin.vrgame.Songs.SongList.songlist
import kotlinx.android.synthetic.main.activity_virtualgamemenu.*

class SongsGameMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.songs_game_menu)

        button.setOnClickListener {
            val intent = Intent(this, SongDetails::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, songlist::class.java)
            startActivity(intent)
        }

    }

}