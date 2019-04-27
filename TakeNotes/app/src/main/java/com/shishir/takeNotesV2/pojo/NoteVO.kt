package com.shishir.takeNotesV2.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteVO (var title: String, var note: String) {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}