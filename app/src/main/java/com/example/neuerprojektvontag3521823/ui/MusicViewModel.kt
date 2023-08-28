package com.example.neuerprojektvontag3521823.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neuerprojektvontag3521823.data.Repository
import com.example.neuerprojektvontag3521823.data.model.Music
import com.example.neuerprojektvontag3521823.data.remote.MusicApi
import kotlinx.coroutines.launch
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import com.example.neuerprojektvontag3521823.data.model.SearchResult
import java.util.logging.Handler


enum class MediaStatus { LOADING, READY, PLAYING, FINISHED }

class MusicViewModel : ViewModel() {

    private val repository = Repository(MusicApi)
    val musicList = repository.results
    val listOfMusic = mutableListOf<Music>()
    var mediaPlayer: MediaPlayer = MediaPlayer()

    private val _currentMusic = MutableLiveData<Music>()
    val currentMusic: LiveData<Music>
        get() = _currentMusic


    private val _music = MutableLiveData<Music>()
    val music: LiveData<Music>
        get() = _music


    private val _selectedMusic = MutableLiveData<Music>()
    val selctedMusic: LiveData<Music>
        get() = _selectedMusic

    val searchResults = repository.results
    private val _genre = MutableLiveData("")
    val genre: LiveData<String> get() = _genre


    private val _playerStatus = MutableLiveData<MediaStatus>()
    val playerStatus: LiveData<MediaStatus>
        get() = _playerStatus


    private val _currentMusicTime = MutableLiveData<Int>()
    val currentMusicTime: LiveData<Int>
        get() = _currentMusicTime


    fun loadMusik(term: String) {
        viewModelScope.launch {
            repository.getMusic(term = term)
        }
    }

    fun setCurrentMusic(music: Music) {
        _music.value = music
    }

    fun shareMusic(context: Context) {
        val shareIntenet = Intent(Intent.ACTION_SEND)
        shareIntenet.type = "text/plain"
        shareIntenet.putExtra(Intent.EXTRA_TEXT, "Teilen Sie hier Ihre Musikinformationen")
        val chooserIntent = Intent.createChooser(shareIntenet, "Musik teilen")
        context.startActivity(chooserIntent)
    }

    fun onToggleMusicLike(): Boolean {// muss erst erfahren, ob der Song bereit gelikt oder nicht, dann entweder als true oder false speichern, danach als Rückgabe
        //Boolean schreiben,Boolean sagt uns, ob der Musik bereit gelike  ist oder nicht, und als return, dann man in DetailsFragment benutzen kann, um das Herz als Grün oder nicht grün markieren.

        _music.value?.let {
            it.liked = !it.liked
        }
        return _music.value!!.liked
    }


    fun saveMusic() {
        _music.value?.let { music ->
            if (!listOfMusic.contains(music)) {
                listOfMusic.add(music)
            }
        }
    }

    fun removeMusic() {
        _music.value?.let { music ->
            if (listOfMusic.contains(music)) {
                listOfMusic.remove(music)
            }
        }
    }

    fun getGenre() {
        val term: String = genreMap.keys.random()
        _genre.value = term
        val id: String = genreMap[term]!!
        viewModelScope.launch {
            repository.getSongGenre("term", id)
        }
    }

    fun open(artist: Music) {
        _music.value = artist
        Log.d("Open", "Current : ${_music.value}  $this")
    }


    private val genreMap = mapOf(
        "Country" to "6",
        "Pop" to "14",
        "Rock" to "21",
        "Hip-Hop/Rap" to "18",
        "R&B/Soul" to "15",
        "Metal" to "1153",
        "Blues" to "2",
        "Jazz" to "11",
        "Electronic" to "7",
        "Alternative" to "20"
    )

    var updateSongTime = object : Runnable {
        override fun run() {
            _currentMusicTime.value = mediaPlayer?.currentPosition
            android.os.Handler().postDelayed(this, 1000)
        }
    }

    fun setupMediaPlayer() {
        mediaPlayer.setOnPreparedListener(OnPreparedListener {
            Log.e(TAG, "MediaReady! ${mediaPlayer.duration}")
            _playerStatus.value = MediaStatus.READY
            mediaPlayer.start()
            _playerStatus.value = MediaStatus.PLAYING
            updateSongTime.run()
        })
        mediaPlayer.setOnCompletionListener {
            _playerStatus.value = MediaStatus.FINISHED
        }
    }


    fun playSong() {
        _currentMusic.value = _selectedMusic.value
        if (_playerStatus.value == MediaStatus.PLAYING) {
            mediaPlayer.reset()
            mediaPlayer = MediaPlayer()
        }
        setupMediaPlayer()
        _playerStatus.value = MediaStatus.LOADING
        mediaPlayer.setDataSource(selctedMusic.value?.previewUrl)
        mediaPlayer.prepareAsync()
    }
}















