package com.example.neuerprojektvontag3521823.data.logal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.neuerprojektvontag3521823.data.model.Music
import retrofit2.http.DELETE

@Dao
interface MusicDataBaseDAO {

    @Insert //(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(music: Music): Long

    @Update
    suspend fun update(music: Music)

    @Query("SELECT * FROM Music")
    fun getAll(): LiveData<List<Music>>

    @Query("DELETE FROM Music WHERE id = :key")
    suspend fun deleteById(key: Long)


    /* @Query("DELETE FROM Music")
     suspend fun deleteAll()*/
}