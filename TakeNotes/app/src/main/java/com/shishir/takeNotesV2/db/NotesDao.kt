package com.shishir.takeNotesV2.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.shishir.takeNotesV2.pojo.NoteVO

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NoteVO)

    @Update
    fun update(note: NoteVO)

    @Query("Select * from NoteVO")
    fun getAllNotes(): LiveData<List<NoteVO>>

    @Query("Select * from NoteVO where NoteVO.id == :id")
    fun getNoteByNoteId(id: Int): NoteVO
}