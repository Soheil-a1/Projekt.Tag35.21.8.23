package com.example.neuerprojektvontag3521823.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.neuerprojektvontag3521823.data.model.Music
import com.example.neuerprojektvontag3521823.databinding.HomeItemListBinding
import com.example.neuerprojektvontag3521823.ui.HomeFragmentDirections
import com.example.neuerprojektvontag3521823.ui.MusicViewModel

class HomeItemAdapter(private val dataset: List<Music>, val viewModel: MusicViewModel) :
    RecyclerView.Adapter<HomeItemAdapter.MyViewHolder>() {


    inner class MyViewHolder(val binding: HomeItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            HomeItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemes = dataset[position]
        val imgUri = itemes.artworkUrl100.toUri().buildUpon().scheme("https").build()


        holder.binding.itemCardViewText.text = itemes.trackName
        holder.binding.imgViewCard.load(imgUri)


        holder.binding.itemCardView.setOnClickListener {
            viewModel.open(itemes)
            val navController = holder.itemView.findNavController()
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToMusicDetailsFragment())
        }


    }
}