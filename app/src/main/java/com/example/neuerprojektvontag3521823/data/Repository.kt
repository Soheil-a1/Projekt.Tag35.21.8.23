package com.example.neuerprojektvontag3521823.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.neuerprojektvontag3521823.R
import com.example.neuerprojektvontag3521823.data.model.Music

class Repository {

private val _results = MutableLiveData<Music>()

    val results: LiveData<Music>
        get() = _results



    fun loadData(): List<Music> {
        return listOf(
            Music("John Smith", "Into the Wild", "https://example.com/song1.mp3", "https://example.com/artwork1.jpg", 220, "Song", R.drawable.bild1),
            Music("Emily Johnson", "Eternal Sunshine", "https://example.com/song2.mp3", "https://example.com/artwork2.jpg", 190, "Song", R.drawable.bild2),
            Music("Michael Davis", "Midnight Serenade", "https://example.com/song3.mp3", "https://example.com/artwork3.jpg", 210, "Song", R.drawable.bild3),
            Music("Sophia Thompson", "Dreamscape", "https://example.com/song4.mp3", "https://example.com/artwork4.jpg", 230, "Song", R.drawable.bild4),
            Music("Daniel Wilson", "Rhythm of the Stars", "https://example.com/song5.mp3", "https://example.com/artwork5.jpg", 195, "Song", R.drawable.bild5),
            Music("Olivia Martinez", "Whispering Winds", "https://example.com/song6.mp3", "https://example.com/artwork6.jpg", 205, "Song", R.drawable.bild6),
            Music("Emma Taylor", "Serenity Falls", "https://example.com/song7.mp3", "https://example.com/artwork7.jpg", 250, "Song", R.drawable.bild7),
            Music("David Anderson", "Melodies of the Heart", "https://example.com/song8.mp3", "https://example.com/artwork8.jpg", 220, "Song", R.drawable.bild8),
            Music("Matthew Clark", "Enchanted Journey", "https://example.com/song9.mp3", "https://example.com/artwork9.jpg", 190, "Song", R.drawable.bild9),
            Music("Ava Rodriguez", "Harmony in Motion", "https://example.com/song10.mp3", "https://example.com/artwork10.jpg", 210, "Song", R.drawable.bild)
        )
    }

}