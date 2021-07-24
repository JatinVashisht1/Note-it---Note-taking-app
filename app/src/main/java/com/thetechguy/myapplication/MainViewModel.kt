package com.thetechguy.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes : LiveData<List<Note>>
    val allNotesByTitle : LiveData<List<Note>>
    private val repository: NoteRepository

    init {
    val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
        allNotesByTitle = repository.allNotesByTitle
    }

    //in below case the everything that will be added or written will work under scope of coroutine in background thread
    fun deleteNote (note: Note) = viewModelScope.launch(Dispatchers.IO ) {
        repository.delete(note)
    }

    // to add new note
    fun insertNote(note: Note) =  viewModelScope.launch (Dispatchers.IO) {
        repository.insert(note)
    }

    fun deleteAll() =  viewModelScope.launch (Dispatchers.IO)
    {
        repository.deleteAll()
    }

    fun updateNote(note: Note) =  viewModelScope.launch (Dispatchers.IO)
    {
        repository.updateNote(note)

    }

    fun deleteSelectedNotes(note: List<Note>) = viewModelScope.launch (Dispatchers.IO) {
        repository.deleteSelectedNotes(note)
    }

}