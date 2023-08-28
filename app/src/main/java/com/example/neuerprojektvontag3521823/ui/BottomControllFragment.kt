package com.example.neuerprojektvontag3521823.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.neuerprojektvontag3521823.databinding.FragmentBottomControllBinding


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

        viewModel.currentMusic.observe(viewLifecycleOwner, Observer {
            binding.musicTitel.text = it.trackName

        })

        viewModel.currentMusicTime.observe(viewLifecycleOwner, Observer {
            binding.ProgressMusicTime.progress = it / 1000
        })


    }
}