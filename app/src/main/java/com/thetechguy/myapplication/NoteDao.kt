package com.thetechguy.myapplication

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllNotes() : LiveData<List<Note>>

    @Query("SELECT * FROM note_table ORDER BY title ASC")
    fun getAllNotesByTitle() : LiveData<List<Note>>

    @Query("DELETE FROM note_table WHERE id>=0")
    suspend fun deleteAllNotes()

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteSelectedNotes(note: List<Note>)

    @Query("SELECT * FROM note_table WHERE title LIKE :searchQuery OR author LIKE :searchQuery")
    fun searchDatabase(searchQuery: String) : Flow<List<Note>>


}