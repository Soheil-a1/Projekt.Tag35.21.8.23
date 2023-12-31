package com.example.neuerprojektvontag3521823.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.neuerprojektvontag3521823.MainActivity
import com.example.neuerprojektvontag3521823.adapter.HomeItemAdapter
import com.example.neuerprojektvontag3521823.adapter.MusicSearchAdapter
import com.example.neuerprojektvontag3521823.data.Repository
import com.example.neuerprojektvontag3521823.databinding.FragmentHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MusicViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // die getGenre aus MusikViewModel und Observer funktion aufgererufen.
        viewModel.getGenre()
        addObserver()
    }


    private fun addObserver() {
        //Hier wird ein LiveData Methode aus MusikViewModel aufgerufen, um eine List von Musik zu bekommen,
        //und in rv anzeigen zu lassen.
        viewModel.getMusic.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.adapter = HomeItemAdapter(it, viewModel)
        })
    }





}

