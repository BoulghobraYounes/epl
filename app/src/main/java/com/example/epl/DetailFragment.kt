package com.example.epl

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class DetailFragment: Fragment(R.layout.layout_fragment_detail) {

    private val soccerTile: SoccerTile by lazy {
        (activity as MainActivity).soccerTileList.find {
            it.id == requireArguments().getString("soccerTileId")
        } ?: SoccerTile()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        val toolbar: androidx.appcompat.widget.Toolbar = view.findViewById(R.id.detailMenu)
        (activity as MainActivity).setSupportActionBar(toolbar)

        (activity as MainActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Club Overview"
        }

        val headerImageView: ImageView = view.findViewById(R.id.teamHeaderImageView)
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val longDescriptionTextView: TextView = view.findViewById(R.id.longDescriptionTextView)

        //headerImageView.setImageResource(soccerTile.headerImageResId)
        Picasso.get().load(soccerTile.headerImageUrl).into(headerImageView);
        titleTextView.text = soccerTile.title
        descriptionTextView.text = soccerTile.description
        longDescriptionTextView.text = soccerTile.descriptionLong
    }


    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_soccer_tile_detail, menu)
        if (soccerTile.isFavourite){
            menu.findItem(R.id.menuItemFavourite)?.setIcon(R.drawable.sharp_favorite_24)
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                (activity as MainActivity).supportFragmentManager.popBackStack()
                true
            }
            R.id.menuItemLink -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(soccerTile.teamUrl))
                startActivity(intent)
                true
            }
            R.id.menuItemFavourite ->{
                val isCurrentlyFavorited = soccerTile.isFavourite
                if (isCurrentlyFavorited){
                    item.setIcon(R.drawable.round_favorite_border_24)
                }else {
                    item.setIcon(R.drawable.sharp_favorite_24)
                }

                soccerTile.isFavourite = !isCurrentlyFavorited
                SharedPrefUtil.setSoccerTileFavorite(soccerTile.id, soccerTile.isFavourite)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}