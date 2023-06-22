package com.example.epl

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso

class SoccerTileAdapter(
    private var data: ArrayList<SoccerTile>,
    private var soccerTileInterface: SoccerTileInterface
) : RecyclerView.Adapter<SoccerTileAdapter.SoccerTileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoccerTileViewHolder {
        return SoccerTileViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SoccerTileViewHolder, position: Int) {
        holder.onBind(data[position], soccerTileInterface)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class SoccerTileViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_soccer_tile, parent, false)
        ){
            private val headerImageView : ImageView = itemView.findViewById(R.id.teamHeaderImageView)
            private val titleTextView : TextView = itemView.findViewById(R.id.titleTextView)
            private val descriptionTextView : TextView = itemView.findViewById(R.id.descriptionTextView)
            private val button : MaterialButton = itemView.findViewById(R.id.button)
            private val favouriteImageView: AppCompatImageView = itemView.findViewById(R.id.favouriteImageView)

            fun onBind(soccerTile: SoccerTile , soccerTileInterface: SoccerTileInterface) {
                Picasso.get().load(soccerTile.headerImageUrl).into(headerImageView);
                titleTextView.text = soccerTile.title
                descriptionTextView.text = soccerTile.description
                button.setOnClickListener{
                    soccerTileInterface.onLearnMoreButtonClicked(adapterPosition)
                }
                val icon = if(soccerTile.isFavourite) R.drawable.sharp_favorite_24 else R.drawable.round_favorite_border_24
                favouriteImageView.setImageResource(icon)
                favouriteImageView.setOnClickListener {
                    soccerTileInterface.onFavoriteClicked(adapterPosition)
                }
            }
        }

}