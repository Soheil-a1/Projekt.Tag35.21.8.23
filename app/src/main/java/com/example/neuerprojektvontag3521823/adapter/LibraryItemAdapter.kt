package com.example.neuerprojektvontag3521823.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.neuerprojektvontag3521823.R
import com.example.neuerprojektvontag3521823.data.model.Music
import com.example.neuerprojektvontag3521823.databinding.HomeItemListBinding
import com.example.neuerprojektvontag3521823.databinding.ListItemBinding
import com.example.neuerprojektvontag3521823.ui.LibraryFragmentDirections

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
        holder.binding.imgItemViewCard.setImageResource(itemes.musicImg)
        holder.binding.itemCvTrackName.text = itemes.trackName
        holder.binding.iCvArtistName.text = itemes.artistsName

        holder.itemView.setOnClickListener {
            val navController = holder.itemView.findNavController()
            navController.navigate(LibraryFragmentDirections.actionLibraryFragmentToMusicDetailsFragment())

            }
        }


    }

