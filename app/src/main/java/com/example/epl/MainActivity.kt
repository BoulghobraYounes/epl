package com.example.epl


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(), SoccerTileInterface {


    lateinit var soccerTileList: ArrayList<SoccerTile>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soccerTileList = getList()

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragmentContainerView, ListFragment())
        }
    }


    override fun onLearnMoreButtonClicked(position: Int) {
        val soccerTile = soccerTileList[position]

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(null)
            val bundle = Bundle().apply {
                putString("soccerTileId", soccerTile.id)
            }

            setCustomAnimations(
                com.google.android.material.R.anim.m3_motion_fade_enter,
                com.google.android.material.R.anim.m3_motion_fade_exit,
                com.google.android.material.R.anim.m3_motion_fade_enter,
                com.google.android.material.R.anim.m3_motion_fade_exit
            )

            replace(R.id.fragmentContainerView, DetailFragment().apply {
                arguments = bundle
            })
        }
    }

    override fun onFavoriteClicked(position: Int) {
        val soccerTile = soccerTileList[position]
        soccerTile.isFavourite = !soccerTile.isFavourite

        (supportFragmentManager.fragments[0] as? ListFragment)?.onFavoriteClicked(position)
        SharedPrefUtil.setSoccerTileFavorite(soccerTile.id, soccerTile.isFavourite)
    }

    private fun getList(): ArrayList<SoccerTile> {
        return ArrayList<SoccerTile>().apply {
            add(
                SoccerTile(
                    id =  "manchester_united",
                    title = "Manchester United",
                    description = "Description of the club",
                    descriptionLong = "A long description of the club",
                    buttonText = "Learn more",
                    headerImageResId = R.drawable.manu_header,
                    headerImageUrl = "https://i.pinimg.com/originals/8f/85/15/8f85159ed8306846b050386384893c1e.jpg",
                    teamUrl = "https://www.manutd.com/",
                    isFavourite = SharedPrefUtil.getSoccerTileFavorite("manchester_united")
                ))
            add(
                SoccerTile(
                    id =  "manchester_city",
                    title = "Manchester City",
                    description = "Description of the club",
                    descriptionLong = "A long description of the club",
                    buttonText = "Learn more",
                    headerImageResId = R.drawable.mancity_header,
                    headerImageUrl = "https://64.media.tumblr.com/88b6c20d80badbc908dbc80eb22add4b/tumblr_od5f4vofqs1ude0uno1_500h.jpg",
                    teamUrl = "https://www.mancity.com/",
                    isFavourite = SharedPrefUtil.getSoccerTileFavorite("manchester_city")
                ))
            add(
                SoccerTile(
                    id =  "arsenal",
                    title = "Arsenal",
                    description = "Description of the club",
                    descriptionLong = "A long description of the club",
                    buttonText = "Learn more",
                    headerImageResId = R.drawable.arsenal_header,
                    headerImageUrl = "https://i.pinimg.com/originals/00/b9/57/00b957e908fd86d72b3e014892d4b895.jpg",
                    teamUrl = "https://www.arsenal.com/",
                    isFavourite = SharedPrefUtil.getSoccerTileFavorite("arsenal")
                ))
            add(
                SoccerTile(
                    id =  "tottenham",
                    title = "Manchester United",
                    description = "Description of the club",
                    descriptionLong = "A long description of the club",
                    buttonText = "Learn more",
                    headerImageResId = R.drawable.totenham_header,
                    headerImageUrl = "https://64.media.tumblr.com/7474355861d4269d4f27e91986895b8f/tumblr_odrogoBumv1ude0uno1_500h.jpg",
                    teamUrl = "https://www.tottenhamhotspur.com/",
                    isFavourite = SharedPrefUtil.getSoccerTileFavorite("tottenham")
                ))
            add(
                SoccerTile(
                    id =  "chelsea",
                    title = "Chelsea",
                    description = "Description of the club",
                    descriptionLong = "A long description of the club",
                    buttonText = "Learn more",
                    headerImageResId = R.drawable.chelsea_header,
                    headerImageUrl = "https://i.pinimg.com/736x/66/2b/ea/662beac3242f10215dc5e826919b3fb3.jpg",
                    teamUrl = "https://www.chelseafc.com/en",
                    isFavourite = SharedPrefUtil.getSoccerTileFavorite("chelsea")
                ))
            add(
                SoccerTile(
                    id =  "leicester",
                    title = "Leicester",
                    description = "Description of the club",
                    descriptionLong = "A long description of the club",
                    buttonText = "Learn more",
                    headerImageResId = R.drawable.leister_header,
                    headerImageUrl = "https://64.media.tumblr.com/87c9b804ffe8f4a0212be18368daf886/tumblr_od5g0cpoiE1ude0uno1_500h.jpg",
                    teamUrl = "https://www.lcfc.com/?lang=en",
                    isFavourite = SharedPrefUtil.getSoccerTileFavorite("leicester")
                ))
        }
    }
}
