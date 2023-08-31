package com.example.neuerprojektvontag3521823.data.logal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.neuerprojektvontag3521823.data.model.Music

@Database(entities = [Music::class], version = 1)
abstract class MusicDataBase : RoomDatabase() {

    abstract val musicDataBaseDAO: MusicDataBaseDAO

    companion object {
        private lateinit var INSTANCE: MusicDataBase

        fun getDataBase(context: Context): MusicDataBase {
            synchronized(MusicDataBase::class.java) {
                if (!this::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MusicDataBase::class.java,
                        "music_database"
                    )
                        .build()
                }
            }
            return INSTANCE
        }
    }
}

