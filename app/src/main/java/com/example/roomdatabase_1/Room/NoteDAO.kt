package com.example.roomdatabase_1.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomdatabase_1.Model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {

    @Insert
    suspend fun insert(note : Note)

    @Update
    suspend fun update(note :Note)

    @Delete
    suspend fun delete(note : Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM  note_table ORDER BY id ASC")
    fun getNotes() : Flow<List<Note>>
}