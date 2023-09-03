package com.example.neuerprojektvontag3521823.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.neuerprojektvontag3521823.MainActivity
import com.example.neuerprojektvontag3521823.R
import com.example.neuerprojektvontag3521823.databinding.FragmentMusicDetailsBinding
import java.util.concurrent.TimeUnit


class MusicDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMusicDetailsBinding
    private val viewModel: MusicViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMusicDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObserver()
       if (viewModel.music.value?.liked == true) {
            binding.btnLike.setImageResource(R.drawable.icon_liked)
        } else {
            binding.btnLike.setImageResource(R.drawable.icon_like)
        }



        binding.iconPlayDetails.setOnClickListener {

                if (viewModel.mediaPlayer.isPlaying){
                    viewModel.pause()
                    binding.iconPlayDetails.setImageResource(R.drawable.icon_play)
                } else {
                    viewModel.playSong()
                    binding.iconPlayDetails.setImageResource(R.drawable.icon_pause)
                }
            }

        //Springt 10 Sekunden zurück
        binding.iconReplay10sDetails.setOnClickListener {
            viewModel.seekBackward()
        }
        //Springt 10 Sekunden vor
        binding.iconForward10sDetails.setOnClickListener {
            viewModel.seekForward()
        }
        //musik teilen
        binding.btnShare.setOnClickListener {
            viewModel.shareMusic(requireContext())
        }

        binding.btnLike.setOnClickListener {
            viewModel.onToggleMusicLike()
            Log.e("Disliked", "${viewModel.music.value?.liked}")
            if (viewModel.music.value?.liked == true) {
                binding.btnLike.setImageResource(R.drawable.icon_liked)
                viewModel.saveMusic()
            } else {
                binding.btnLike.setImageResource(R.drawable.icon_like)
                viewModel.removeMusic()
            }
        }

        //zurück zum Home Navigieren
        binding.imgButtonBack.setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }

        // Ton hohe und Nidrieg machen, leider es ist nur ui.
        binding.btnVolume.setOnClickListener {
            if (binding.ProgressVolume.visibility == View.GONE) {
                binding.ProgressVolume.visibility = View.VISIBLE
            } else {
                binding.ProgressVolume.visibility = View.GONE
            }
        }
    }

    fun addObserver() {
        viewModel.music.observe(viewLifecycleOwner, Observer {
            // val imgUri = it.artworkUrl100.toUri().buildUpon().scheme("https").build()

                binding.tvTrackNameDetails.text = it.trackName
                binding.tvArtistNameDetails.text = it.artistsName
                binding.musicTimeEnd.text = it.trackTime.toString()
                binding.musicTimeStart.text = it.trackTimeSecond.toString()
                binding.ProgressMusicTime.max = it.musicPreview
                binding.imgMusicDetails.load(it.artworkUrl100) {
                    error(R.drawable.broken_image)
                    transformations(
                        RoundedCornersTransformation(10f)
                    )
            }
        })

        viewModel.currentMusicTime.observe(viewLifecycleOwner, Observer {
            binding.ProgressMusicTime.progress = it/1000
            binding.musicTimeStart.text = (viewModel.transform(it.toLong()))
            binding.musicTimeEnd.text = (viewModel.transform(it.toLong().downTo(0).last))
        })

    }

}


