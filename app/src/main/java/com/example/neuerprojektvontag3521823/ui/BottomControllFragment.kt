package com.example.neuerprojektvontag3521823.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import coil.load
import com.example.neuerprojektvontag3521823.databinding.FragmentBottomControllBinding
import java.util.concurrent.TimeUnit


class BottomControllFragment : Fragment() {
    private lateinit var binding: FragmentBottomControllBinding
    private val viewModel: MusicViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomControllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObserver()
    }

    fun addObserver() {
        viewModel.playerStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                MediaStatus.LOADING -> binding.iconPlay.visibility = View.GONE
                MediaStatus.READY -> binding.iconPlay.visibility = View.VISIBLE
                MediaStatus.PLAYING -> binding.iconPlay.visibility = View.VISIBLE
                MediaStatus.FINISHED -> binding.iconPlay.visibility = View.GONE
            }
        })
        viewModel.selctedMusic.observe(viewLifecycleOwner, Observer {
            binding.musicTitel.text = it.trackName
            binding.imgTollbar.load(it.artworkUrl100)
            binding.ProgressMusicTime.max = it.musicPreview
            binding.musicTimeStart.text = it.musicTimeStart.toString()
            binding.musicTimeEnd.text = it.musicTimeEnd.toString()
            binding.iconPlay.setOnClickListener {
                viewModel.playSong()
            }
        })

        viewModel.currentMusicTime.observe(viewLifecycleOwner, Observer {
            binding.ProgressMusicTime.progress = it / 1000
            binding.musicTimeStart.text = (transform(it.toLong()))
            binding.musicTimeEnd.text = (transform(it.toLong().downTo(0).last))
        })
    }

    private fun transform(milSek: Long): String {
        val min = TimeUnit.MILLISECONDS.toMinutes(milSek)
        val sec = TimeUnit.MILLISECONDS.toSeconds(milSek) % 60
        return String.format("%02d:%02d", min, sec)
    }
}