package com.shishir.takeNotesV2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shishir.takeNotesV2.pojo.NoteVO

@Database(entities = [NoteVO::class], version = 1)
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