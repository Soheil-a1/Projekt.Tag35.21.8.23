package com.example.neuerprojektvontag3521823.ui

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.neuerprojektvontag3521823.MainActivity
import com.example.neuerprojektvontag3521823.data.model.Music
import com.example.neuerprojektvontag3521823.databinding.FragmentMusicDetailsBinding


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

        viewModel.music.observe(viewLifecycleOwner, Observer {
            binding.tvTrackNameDetails.text = it.trackName
            binding.tvArtistNameDetails.text = it.artistsName
            binding.imgMusicDetails.setImageResource(it.musicImg)
            binding.musicTimeEnd.setText(it.trackTime.toString())
            binding.musicTimeStart.setText(it.trackTimeSecond.toString())
            binding.ProgressMusicTime.setProgress(it.trackTimeSecond.toInt())
        })

        if (viewModel.music.value?.liked == true) {
            binding.btnLike.setImageResource(com.example.neuerprojektvontag3521823.R.drawable.icon_liked)
            viewModel.saveMusic()

        } else {
            binding.btnLike.setImageResource(com.example.neuerprojektvontag3521823.R.drawable.icon_like)
            viewModel.removeMusic()
        }


        binding.musicControllBarDeatils

        binding.iconReplay10sDetails.setOnClickListener {
            // musicInstance.trackTimeSecond.minus(10)
        }

        binding.iconForward10sDetails.setOnClickListener {
            //  musicInstance.trackTimeSecond.plus(10)
        }



        binding.btnShare.setOnClickListener {
            viewModel.shareMusic(requireContext())
        }


        binding.btnLike.setOnClickListener {
            viewModel.onToggleMusicLike()
            Log.e("Disliked",  "${viewModel.music.value?.liked}")
            if (viewModel.music.value?.liked == true) {
                binding.btnLike.setImageResource(com.example.neuerprojektvontag3521823.R.drawable.icon_liked)
                viewModel.saveMusic()

            } else {
                binding.btnLike.setImageResource(com.example.neuerprojektvontag3521823.R.drawable.icon_like)
                viewModel.removeMusic()
            }
        }

        binding.iconPlayDetails.setOnClickListener {
            musicPlay()
        }

        binding.imgButtonBack.setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }

        binding.btnVolume.setOnClickListener {
            if (binding.ProgressVolume.visibility == View.GONE) {
                binding.ProgressVolume.visibility = View.VISIBLE
            } else {
                binding.ProgressVolume.visibility = View.GONE
            }
        }
    }


    fun musicPlay() {
        binding.iconPlayDetails.setOnClickListener {
            binding.musicControllBarDeatils.visibility = View.VISIBLE
            binding.ProgressMusicTime.visibility = View.VISIBLE




            if (binding.iconPlayDetails.tag == "Pause") {
                binding.iconPlayDetails.setImageResource(com.example.neuerprojektvontag3521823.R.drawable.icon_play)

            } else {
                binding.iconPlayDetails.setImageResource(com.example.neuerprojektvontag3521823.R.drawable.icon_pause)
                binding.iconPlayDetails.tag = "Pause"
            }
        }
    }


}