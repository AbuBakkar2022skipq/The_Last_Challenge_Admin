package com.example.thelastchallengeadmin.MainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.thelastchallengeadmin.Punishments.PunishmentsMenu
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.addGame.physicalgamespage
import com.example.thelastchallengeadmin.resourceAddition.add_resources
import com.example.thelastchallengeadmin.vrgame.virtualgamemenu
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener{
            val intent = Intent(this, physicalgamespage::class.java)
            startActivity(intent)
        }

        button7.setOnClickListener {
            val intent = Intent(this, add_resources::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, virtualgamemenu::class.java)
            startActivity(intent)
        }

        button77.setOnClickListener {
            val intent = Intent(this, PunishmentsMenu::class.java)
            startActivity(intent)
        }
    }
}