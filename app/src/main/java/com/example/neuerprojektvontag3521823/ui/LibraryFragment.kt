package com.example.neuerprojektvontag3521823.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.neuerprojektvontag3521823.MainActivity
import com.example.neuerprojektvontag3521823.R
import com.example.neuerprojektvontag3521823.adapter.LibraryItemAdapter
import com.example.neuerprojektvontag3521823.data.model.Music
import com.example.neuerprojektvontag3521823.databinding.FragmentLibraryBinding


class LibraryFragment : Fragment() {
    private lateinit var binding: FragmentLibraryBinding
    private val viewModel: MusicViewModel by activityViewModels()
    //private lateinit var musiklikedLinsteaner: MusicViewModel.OnMusikLikedListenear


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recView = binding.recyclerView

        recView.adapter = LibraryItemAdapter(viewModel.listOfMusic)

        viewModel.music.value.let {
            recView.adapter



        }
    }


}