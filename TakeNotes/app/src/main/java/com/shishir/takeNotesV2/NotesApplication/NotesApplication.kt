package com.shishir.takeNotesV2.NotesApplication

import android.app.Application
import com.shishir.takeNotesV2.db.NotesDatabase

class NotesApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        NotesDatabase.getInstance(this)
    }
}