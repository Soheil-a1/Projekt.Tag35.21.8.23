package com.example.neuerprojektvontag3521823.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.neuerprojektvontag3521823.R
import com.example.neuerprojektvontag3521823.data.model.Music
import com.example.neuerprojektvontag3521823.databinding.ListItemBinding
import com.example.neuerprojektvontag3521823.ui.LibraryFragmentDirections
import com.example.neuerprojektvontag3521823.ui.MusicViewModel


class LibraryItemAdapter(val dataset: List<Music>, val viewModel: MusicViewModel) :
    RecyclerView.Adapter<LibraryItemAdapter.LibraryViewHolder>() {


    inner class LibraryViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibraryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        val itemes = dataset[position]
        val binding = holder.binding
        val imgUri = itemes.artworkUrl100.toUri().buildUpon().scheme("https").build()

        binding.itemCvTrackName.text = itemes.trackName
        binding.iCvArtistName.text = itemes.artistsName
        binding.imgItemViewCard.load(imgUri)


        binding.itemCardView.setOnClickListener {
            viewModel.setCurrentMusic(itemes)
            val navController = holder.itemView.findNavController()
            navController.navigate(R.id.musicDetailsFragment)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}


/*
class LibraryItemAdapter (val dataset : List<Music>): RecyclerView.Adapter<LibraryItemAdapter.LibraryViewHolder>() {


    inner class LibraryViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibraryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        val itemes = dataset[position]
        val imgUri = itemes.artworkUrl100.toUri().buildUpon().scheme("https").build()

        holder.binding.itemCvTrackName.text = itemes.trackName
        holder.binding.iCvArtistName.text = itemes.artistsName
        holder.binding.imgItemViewCard.load(imgUri)


        holder.itemView.setOnClickListener {
            val navController = holder.itemView.findNavController()
            navController.navigate(LibraryFragmentDirections.actionLibraryFragmentToMusicDetailsFragment())
            }
        }
    }
*/