package com.example.roomdatabase_1.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.roomdatabase_1.R

class NoteAddActivity : AppCompatActivity() {

    lateinit var  title : EditText
    lateinit var  descrption : EditText
    lateinit var Savebtn : Button
    lateinit var cancelbtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_add)
        supportActionBar?.title = "Add Note"

        title = findViewById(R.id.editTextTitle)
        descrption = findViewById(R.id.eidtTextDescription)
        Savebtn = findViewById(R.id.savebtn)
        cancelbtn = findViewById(R.id.Cancelbtn)

        cancelbtn.setOnClickListener {
            Toast.makeText(applicationContext,"Noting Saved",Toast.LENGTH_SHORT).show()
            finish()

        }
        Savebtn.setOnClickListener {
            saveNote()

        }
    }

    private fun saveNote() {
        val noteTitle : String = title.text.toString()
        val noteDescription : String = descrption.text.toString()

        val intent = Intent()
        intent.putExtra("title",noteTitle)
        intent.putExtra("description",noteDescription)
        setResult(RESULT_OK,intent)
        finish()
    }
}