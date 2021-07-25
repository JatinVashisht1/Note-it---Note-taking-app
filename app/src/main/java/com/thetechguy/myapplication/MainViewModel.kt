package com.thetechguy.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
var cate: String = "default"
class MainViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes : LiveData<List<Note>>
    val allNotesByTitle : LiveData<List<Note>>
    var allNotesByCategory: LiveData<List<Note>>
    val allNoteCategory : LiveData<List<String>>
    private val repository: NoteRepository

    init {
    val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
        allNotesByTitle = repository.allNotesByTitle
        allNotesByCategory = repository.allNotesByCategory
        allNoteCategory = repository.allNoteCategory
    }

    fun getAllNotesByCategories(categories: String){
        cate = categories
        allNotesByCategory  =  repository.getAllNotesByCategories(categories)
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

    fun onNoteSwiped(note: Note) = viewModelScope.launch (Dispatchers.IO) {
        repository.delete(note)
    }

}