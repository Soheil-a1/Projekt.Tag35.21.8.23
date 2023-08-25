package com.example.neuerprojektvontag3521823.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.neuerprojektvontag3521823.R
import com.example.neuerprojektvontag3521823.data.model.Music
import com.example.neuerprojektvontag3521823.data.model.SearchResult
import com.example.neuerprojektvontag3521823.data.remote.MusicApi
import com.example.neuerprojektvontag3521823.databinding.ListItemBinding
import com.example.neuerprojektvontag3521823.ui.MusicViewModel
import com.example.neuerprojektvontag3521823.ui.SearchFragmentDirections

class MusicSearchAdapter(private val iteme: List<Music>, val viewModel: MusicViewModel):RecyclerView.Adapter<MusicSearchAdapter.MyViewHolder>() {


    inner class MyViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return iteme.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataset = iteme[position]
        val imgUrl = dataset.artworkUrl100.toUri().buildUpon().scheme("https").build()
        val navController = holder.itemView.findNavController()
        holder.binding.iCvArtistName.text = dataset.artistsName
        holder.binding.itemCvTrackName.text = dataset.trackName

        viewModel.setCurrenMusic(dataset)

        holder.binding.itemCardView.setOnClickListener {
            navController.navigate(SearchFragmentDirections.actionSearchFragmentToMusicDetailsFragment())
        }
    }
}