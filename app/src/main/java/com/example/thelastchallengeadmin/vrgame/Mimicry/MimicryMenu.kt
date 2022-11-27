package com.example.thelastchallengeadmin.vrgame.Mimicry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.addGame.physicalgamespage
import com.example.thelastchallengeadmin.vrgame.Mimicry.EditGame.mimicryEditGame
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.words
import kotlinx.android.synthetic.main.activity_mimicry_menu.*

class MimicryMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mimicry_menu)
        button2.setOnClickListener {
            val intent = Intent(this, words::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val intent = Intent(this, mimicryEditGame::class.java)
            startActivity(intent)
        }
    }
}