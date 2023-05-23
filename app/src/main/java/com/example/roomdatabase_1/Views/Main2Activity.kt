package com.example.roomdatabase_1.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase_1.Adapters.NoteAdapter
import com.example.roomdatabase_1.Model.Note
import com.example.roomdatabase_1.NoteApplication
import com.example.roomdatabase_1.R
import com.example.roomdatabase_1.Repository.NoteRepository
import com.example.roomdatabase_1.ViewModel.NoteViewModel
import com.example.roomdatabase_1.ViewModel.NoteViewModelFactory

class Main2Activity : AppCompatActivity() {


    lateinit var noteViewModel : NoteViewModel
    lateinit var addActivityResultLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.NoteRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val noteadapter = NoteAdapter()

        registerActivityResultLauncher()

        recyclerView.adapter = noteadapter

        val noteViewModelFactory = NoteViewModelFactory((application as NoteApplication).respository)

        noteViewModel = ViewModelProvider(this,noteViewModelFactory).get(NoteViewModel::class.java)

        noteViewModel.allmyNotes.observe(this, Observer {
            noteadapter.setNote(it)
        })


    }

    fun registerActivityResultLauncher(){

        addActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()
            , ActivityResultCallback {
                val resultCode = it.resultCode
                val data = it.data
                if(resultCode == RESULT_OK && data!=null){

                    val noteTitle = data.getStringExtra("title").toString()
                    val noteDescription = data.getStringExtra("description").toString()

                    val note = Note(noteTitle,noteDescription)
                    noteViewModel.insert(note)

                }

            })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_addNote -> {
                val intent = Intent(this,NoteAddActivity::class.java)
                addActivityResultLauncher.launch(intent)
            }
            R.id.item_delete_note -> {
                Toast.makeText(applicationContext,"Delete Icon Clicked",Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }
}