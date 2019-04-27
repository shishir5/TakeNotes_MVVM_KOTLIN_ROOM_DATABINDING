package com.shishir.takeNotesV2.util

import androidx.recyclerview.widget.DiffUtil
import com.shishir.takeNotesV2.pojo.NoteVO

class NoteVODiffUtil(val oldNotesList: List<NoteVO>, val newNotesList: List<NoteVO>) : DiffUtil.Callback() {

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        if (newNotesList.get(p1).id == oldNotesList.get(p0).id)
            return true
        return false
    }

    override fun getOldListSize(): Int {
        return oldNotesList.size
    }

    override fun getNewListSize(): Int {
        return newNotesList.size
    }

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
        if (oldNotesList.get(p0).id == newNotesList.get(p1).id
                && oldNotesList.get(p0).title.equals(newNotesList.get(p1).title)
                && oldNotesList.get(p0).note.equals(newNotesList.get(p1).note))
            return true
        return false
    }

}