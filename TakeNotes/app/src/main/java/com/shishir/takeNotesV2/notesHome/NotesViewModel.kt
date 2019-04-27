package com.shishir.takeNotesV2.notesHome

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.shishir.takeNotesV2.db.NotesRepository
import com.shishir.takeNotesV2.pojo.NoteVO

class NotesViewModel(mApplication: Application): AndroidViewModel(mApplication) {

    //Note Repository instance
    private val mNotesRepository:NotesRepository

    init {
        mNotesRepository = NotesRepository(mApplication)
    }

    //Method to add or Update a Note
    fun addNoteToDb(id: Int, title: String, note: String) {
        if(id == 0) {
            //If id == 0 , note is new
            val note = NoteVO(title, note)
            mNotesRepository.insertNote(note)
        } else {
            //If id != 0 , saved note is to be updated
            val note = NoteVO(title, note)
            note.id = id
            mNotesRepository.updateNote(note)
        }
    }

    fun getAllNotes(): LiveData<List<NoteVO>> {
        return mNotesRepository.getAllNotes()
    }

    //Method to fetch Note from DB by Note ID
    fun getNote(id: Int): NoteVO {
        return mNotesRepository.getNoteById(id)
    }
}