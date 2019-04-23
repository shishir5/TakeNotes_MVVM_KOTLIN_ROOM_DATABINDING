package com.shishir.takeNotesV2.notesHome

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.shishir.takeNotesV2.db.NotesDatabase
import com.shishir.takeNotesV2.pojo.NoteVO

class NotesViewModel(mApplication: Application): AndroidViewModel(mApplication) {
    private val mNotesDB:NotesDatabase

    init {
        mNotesDB = NotesDatabase.getInstance(mApplication)
    }

    fun addNoteToDb(id: Int, title: String, note: String) {
        if(id == 0) {
            val note = NoteVO(title, note)
            mNotesDB.getNotesDao().insert(note)
        } else {
            val note = NoteVO(title, note)
            note.id = id
            mNotesDB.getNotesDao().update(note)
        }
    }

    fun getAllNotes(): LiveData<List<NoteVO>> {
        return mNotesDB.getNotesDao().getAllNotes()
    }

    fun getNote(id: Int): NoteVO {
        return mNotesDB.getNotesDao().getNoteByNoteId(id)
    }
}