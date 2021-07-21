package com.thetechguy.myapplication

import androidx.lifecycle.LiveData

class NoteRepository (private val noteDao: NoteDao) {


    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()// View Model is observing this LiveData which is providing all notes (Data)
    val allNotesByTitle: LiveData<List<Note>> = noteDao.getAllNotesByTitle()// View Model is observing this LiveData which is providing all notes (Data)

    suspend fun insert(note: Note)
    {
        noteDao.insertNote(note)
    }

    suspend fun delete(note: Note)
    {
        noteDao.deleteNote(note)
    }

    suspend fun deleteAll()
    {
        noteDao.deleteAllNotes()
    }

    suspend fun updateNote(note: Note)
    {
        noteDao.updateNote(note)
    }
}