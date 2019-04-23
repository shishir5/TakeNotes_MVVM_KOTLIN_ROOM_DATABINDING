package com.shishir.takeNotesV2.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.shishir.takeNotesV2.pojo.NoteVO

@Database(entities = arrayOf(NoteVO::class), version = 1)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

    companion object {
        @Volatile
        private var mNotesDB: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase {
            val tempInstance = mNotesDB
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java,
                        "NotesDB").allowMainThreadQueries().build()
                mNotesDB = instance
                return instance
            }
        }
    }

}