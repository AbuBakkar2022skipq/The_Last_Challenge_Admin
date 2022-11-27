package com.example.thelastchallengeadmin.vrgame.ForbiddenWords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.vrgame.ForbiddenWords.EditForbiddenWords.editForbiddenWords
import com.example.thelastchallengeadmin.vrgame.ForbiddenWords.ForbiddenWordsGameEdit.forbiddenWordsGameEdit
import kotlinx.android.synthetic.main.activity_forbiddenwords.*

class forbiddenwordsmenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forbiddenwords)

        button.setOnClickListener {
            val intent = Intent(this, forbiddenWordsGameEdit::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, editForbiddenWords::class.java)
            startActivity(intent)
        }
    }
}