package com.example.thelastchallengeadmin.addGame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.thelastchallengeadmin.GameManagement.managegamepagepunishment
import com.example.thelastchallengeadmin.R

class physicalgamespage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.physicalgames)

        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener{
            val intent = Intent(this, addpunishment::class.java)
            startActivity(intent)
        }

        val btn2 = findViewById<Button>(R.id.button2)
        btn2.setOnClickListener{
            val intent = Intent(this, managegamepagepunishment::class.java)
            startActivity(intent)
        }

    }
}