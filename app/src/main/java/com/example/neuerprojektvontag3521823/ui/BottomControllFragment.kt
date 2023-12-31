package com.example.neuerprojektvontag3521823.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.neuerprojektvontag3521823.R
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
        //Hier wird der Observer aufgerufen.
        addObserver()

        // wenn iconPlay button geklickt ist, die playSong funktion wird von MusikViewModel aufgeruft.

      binding.iconPlay.setOnClickListener {
            //viewModel.paused()
          viewModel.playSong()
        }



        binding.clossIcon.setOnClickListener {
            binding.musicControllBar.visibility = View.GONE
        }
       binding.arrowUp.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_musicDetailsFragment)
       }

    }


    fun addObserver() {
        //Hier Observieren wir MediaStatus LiveData mit Name PlayerStatus.
        viewModel.playerStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                // Hier sagen wir, wenn Musik lädt, iconPlay soll versteckt sein.
                MediaStatus.LOADING -> binding.musicControllBar.visibility = View.GONE
                //wenn Musik bereit ist, soll iconPlay angezeigt werden.
                MediaStatus.READY -> binding.musicControllBar.visibility = View.VISIBLE
                //wenn Musik amlaufen ist, soll iconPlay angezeigt werden.
                MediaStatus.PLAYING -> binding.musicControllBar.visibility = View.VISIBLE
                //und am ende soll iconPlay soll nochmal versteckt werden.
                MediaStatus.FINISHED -> binding.musicControllBar.visibility = View.GONE
            }
        })


        //Bei diesem Codeabschnitt wird ausgewählte musik für player observt.
        viewModel.music.observe(viewLifecycleOwner, Observer {
            // hier werden musik daten von MusikViewModel klasse geholt und mit helfer von mutter klasse
            //sortiert und in fragment_bottom_controll.xml in richtige position angezeigt.
            binding.musicTitel.text = it.trackName
            binding.imgTollbar.load(it.artworkUrl100)
            binding.ProgressMusicTime.max = it.trackTimeSecond.toInt()
            binding.musicTimeStart.text = it.trackTimeSecond.toString()
            binding.musicTimeEnd.text = it.trackTimeSecond.toString()

        })
        //wir observieren eine Eigentschaft mit Name "currentMusikTime" aus MusikViewModel,damit
        //wir musik Zeit in echtzeit als LiveData mit <Int> als Parameter haben.
        viewModel.currentMusicTime.observe(viewLifecycleOwner, Observer {
            binding.ProgressMusicTime.progress = it / 1000
            binding.musicTimeStart.text = (viewModel.transform(it.toLong()))
            binding.musicTimeEnd.text = (viewModel.transform(it.toLong().downTo(0).last))
        })
    }


}