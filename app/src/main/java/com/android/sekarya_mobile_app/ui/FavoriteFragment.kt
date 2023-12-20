package com.android.sekarya_mobile_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.model.Favorite
import com.android.sekarya_mobile_app.model.Hire
import com.android.sekarya_mobile_app.ui.adapter.FavoriteAdapter
import com.android.sekarya_mobile_app.ui.adapter.HireAdapter

class FavoriteFragment: Fragment() {

    lateinit var imageFav: Array<Int>
    lateinit var imageUserFav: Array<Int>
    lateinit var tvArt: Array<String>

    private lateinit var recyclerView: RecyclerView
    private lateinit var favAdapter: FavoriteAdapter
    private lateinit var favList: ArrayList<Favorite>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataFav()
        setRvFav()
    }

    private fun setRvFav(){
        recyclerView = requireView().findViewById(R.id.rv_card_fav)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.setHasFixedSize(true)
        favAdapter = FavoriteAdapter(favList)
        recyclerView.adapter = favAdapter
    }

    private fun setDataFav(){
        favList = arrayListOf<Favorite>()

        imageFav = arrayOf(
            R.drawable.card_image,
            R.drawable.card_image_2,
            R.drawable.cid,
            R.drawable.wanderer,
            R.drawable.frieren,
            R.drawable.ryo,
            R.drawable.card_image,
            R.drawable.card_image_2,
            R.drawable.cid,
            R.drawable.wanderer,
        )

        imageUserFav = arrayOf(
            R.drawable.creator,
            R.drawable.creator_2,
            R.drawable.creator_3,
            R.drawable.creator,
            R.drawable.creator_2,
            R.drawable.creator_3,
            R.drawable.creator,
            R.drawable.creator_2,
            R.drawable.creator_3,
            R.drawable.creator
        )

        tvArt = arrayOf(
            getString(R.string.girlart),
            getString(R.string.panic),
            getString(R.string.john),
            getString(R.string.wanderer),
            getString(R.string.frieren),
            getString(R.string.ryo),
            getString(R.string.girlart),
            getString(R.string.panic),
            getString(R.string.john),
            getString(R.string.wanderer),
        )

        for (i in imageFav.indices){
            val fav = Favorite(imageFav[i], imageUserFav[i], tvArt[i])
            favList.add(fav)
        }
    }
}