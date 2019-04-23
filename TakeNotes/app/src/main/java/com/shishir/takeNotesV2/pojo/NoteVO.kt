package com.shishir.takeNotesV2.pojo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class NoteVO (var title: String, var note: String) {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}