package com.example.neuerprojektvontag3521823.ui

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neuerprojektvontag3521823.data.Repository
import com.example.neuerprojektvontag3521823.data.model.Music
import com.example.neuerprojektvontag3521823.data.remote.MusicApiService
import kotlinx.coroutines.launch


class MusicViewModel : ViewModel() {

    private val repository = Repository()

    val musicList = repository.loadData()
    private val _music = MutableLiveData<Music>()
    val listOfMusic = mutableListOf<Music>()

    val music: LiveData<Music>
        get() = _music


    fun loadMusik() {
        viewModelScope.launch {
            musicList
        }
    }

    fun setCurrentMusic(music: Music){
        _music.value = music
    }

    fun shareMusic(context: Context) {
        val shareIntenet = Intent(Intent.ACTION_SEND)
        shareIntenet.type = "text/plain"
        shareIntenet.putExtra(Intent.EXTRA_TEXT, "Teilen Sie hier Ihre Musikinformationen")
        val chooserIntent = Intent.createChooser(shareIntenet, "Musik teilen")
        context.startActivity(chooserIntent)
    }

        fun onToggleMusicLike():Boolean{// muss erst erfahren, ob der Song bereit gelikt oder nicht, dann entweder als true oder false speichern, danach als Rückgabe
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

    fun setCurrenMusic(music: Music) {

        _music.value = music
    }

    fun removeMusic() {
        _music.value?.let {music ->
            if (listOfMusic.contains(music)){
                listOfMusic.remove(music)
            }
        }

    }

















}