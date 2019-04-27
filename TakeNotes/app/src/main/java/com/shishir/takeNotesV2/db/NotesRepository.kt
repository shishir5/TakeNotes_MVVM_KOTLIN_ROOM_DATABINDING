package com.shishir.takeNotesV2.db

import android.app.Application
import androidx.lifecycle.LiveData
import com.shishir.takeNotesV2.pojo.NoteVO

class NotesRepository(application: Application) {
    private val mNotesDao: NotesDao

    init {
        val notesDatabase: NotesDatabase = NotesDatabase.getInstance(application)
        mNotesDao = notesDatabase.getNotesDao()
    }

    fun getAllNotes(): LiveData<List<NoteVO>> {
        return mNotesDao.getAllNotes()
    }

    fun getNoteById(id: Int): NoteVO {
        return mNotesDao.getNoteByNoteId(id)
    }

    fun insertNote(note: NoteVO) {
        mNotesDao.insert(note)
    }

    fun updateNote(note: NoteVO){
        mNotesDao.update(note)
    }

}