package com.example.roomdatabase_1

import android.app.Application
import com.example.roomdatabase_1.Repository.NoteRepository
import com.example.roomdatabase_1.Room.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy{ NoteDatabase.getdatabase(this,applicationScope) }
    val respository by lazy {NoteRepository(database.getNoteDao())}

}