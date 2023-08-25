package com.example.neuerprojektvontag3521823.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.neuerprojektvontag3521823.R
import com.example.neuerprojektvontag3521823.data.model.Music
import com.example.neuerprojektvontag3521823.data.remote.MusicApi
import com.example.neuerprojektvontag3521823.data.remote.MusicApiService
import kotlinx.coroutines.delay
import java.lang.Exception

class Repository(val api: MusicApi) {

private val _results = MutableLiveData<List<Music>>()

    val results: LiveData<List<Music>>
        get() = _results


    suspend fun getMusic(term : String){
        delay(2000)
        try {
            _results.value = api.retrofitService.getMusic(term).results

        } catch (e: Exception){
            Log.e("Repository", "$e")
        }
    }


    fun loadData(): List<Music> {
        return listOf(
            Music("John Smith", "Into the Wild", "https://example.com/song1.mp3", "https://example.com/artwork1.jpg", 220, "Song",),
            Music("Emily Johnson", "Eternal Sunshine", "https://example.com/song2.mp3", "https://example.com/artwork2.jpg", 190, "Song",),
            Music("Michael Davis", "Midnight Serenade", "https://example.com/song3.mp3", "https://example.com/artwork3.jpg", 210, "Song",),
            Music("Sophia Thompson", "Dreamscape", "https://example.com/song4.mp3", "https://example.com/artwork4.jpg", 230, "Song", ),
            Music("Daniel Wilson", "Rhythm of the Stars", "https://example.com/song5.mp3", "https://example.com/artwork5.jpg", 195, "Song" ),
            Music("Olivia Martinez", "Whispering Winds", "https://example.com/song6.mp3", "https://example.com/artwork6.jpg", 205, "Song"),
            Music("Emma Taylor", "Serenity Falls", "https://example.com/song7.mp3", "https://example.com/artwork7.jpg", 250, "Song" ),
            Music("David Anderson", "Melodies of the Heart", "https://example.com/song8.mp3", "https://example.com/artwork8.jpg", 220, "Song"),
            Music("Matthew Clark", "Enchanted Journey", "https://example.com/song9.mp3", "https://example.com/artwork9.jpg", 190, "Song"),
            Music("Ava Rodriguez", "Harmony in Motion", "https://example.com/song10.mp3", "https://example.com/artwork10.jpg", 210, "Song")
        )
    }




}