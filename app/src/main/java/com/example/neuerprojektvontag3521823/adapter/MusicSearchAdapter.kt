package com.example.neuerprojektvontag3521823.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.neuerprojektvontag3521823.R
import com.example.neuerprojektvontag3521823.data.model.Music
import com.example.neuerprojektvontag3521823.databinding.ListItemBinding
import com.example.neuerprojektvontag3521823.ui.MusicViewModel
import com.example.neuerprojektvontag3521823.ui.SearchFragmentDirections

class MusicSearchAdapter(private val iteme: List<Music>, val viewModel: MusicViewModel) :
    RecyclerView.Adapter<MusicSearchAdapter.MyViewHolder>() {


    inner class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

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


        holder.binding.iCvArtistName.text = dataset.artistsName
        holder.binding.itemCvTrackName.text = dataset.trackName
        holder.binding.imgItemViewCard.load(imgUrl) {
            error(R.drawable.broken_image)
            transformations(RoundedCornersTransformation(8f))
            Log.d("img", "error: $imgUrl")
        }

        holder.binding.itemCardView.setOnClickListener {
            viewModel.open(dataset)
            val navController = holder.itemView.findNavController()
            navController.navigate(SearchFragmentDirections.actionSearchFragmentToMusicDetailsFragment())

        }
    }
}