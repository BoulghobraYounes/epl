package com.example.epl

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class ListFragment: Fragment(R.layout.layout_fragment_list) {

    private lateinit var soccerTileAdapter: SoccerTileAdapter
    private val soccerTileList: ArrayList<SoccerTile>
        get() = (activity as MainActivity).soccerTileList

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
        }


        soccerTileAdapter = SoccerTileAdapter(soccerTileList, activity as MainActivity)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = soccerTileAdapter
    }

    override fun onResume() {
        super.onResume()
        soccerTileAdapter.notifyDataSetChanged()
    }

    fun onFavoriteClicked(position: Int) {
        soccerTileAdapter.notifyItemChanged(position)
    }
}