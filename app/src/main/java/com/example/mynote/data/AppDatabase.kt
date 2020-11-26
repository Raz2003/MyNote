package com.example.mynote.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BasicNote::class,PrivateNote::class],version = 1)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun createNote() : NoteDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context : Context) : AppDatabase
        {
            if(INSTANCE!=null) return INSTANCE!!
            else
            {INSTANCE = Room.databaseBuilder(context,AppDatabase::class.java,"app_db").build()}
            return INSTANCE!!
        }
    }
}