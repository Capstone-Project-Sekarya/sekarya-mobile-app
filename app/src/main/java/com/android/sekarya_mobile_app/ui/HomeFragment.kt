package com.android.sekarya_mobile_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.adapter.HomeArtistAdapter
import com.android.sekarya_mobile_app.adapter.SliderAdapter
import com.android.sekarya_mobile_app.model.HomeArtist
import com.android.sekarya_mobile_app.model.Slider



class HomeFragment: Fragment() {

    private lateinit var adapter: SliderAdapter
    private lateinit var artistAdapter: HomeArtistAdapter

    private lateinit var recyclerView: RecyclerView

    private lateinit var sliderList: ArrayList<Slider>
    private lateinit var artistList: ArrayList<HomeArtist>

    lateinit var imageId: Array<Int>
    lateinit var tvname: Array<String>
    lateinit var tvuserName: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDataSlider()
        setRvSlider()

        setupDataArtist()
        setRvArtist()
    }

    private fun setRvSlider(){
        recyclerView = requireView().findViewById(R.id.rv_banner)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        adapter = SliderAdapter(sliderList)
        recyclerView.adapter = adapter
    }

    private fun setRvArtist(){
        recyclerView = requireView().findViewById(R.id.rv_artist)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        artistAdapter = HomeArtistAdapter(artistList)
        recyclerView.adapter = artistAdapter
    }

    private fun setupDataSlider(){
        sliderList = arrayListOf<Slider>()

        imageId = arrayOf(
            R.drawable.dummy_banner_1,
            R.drawable.dummy_banner_2,
            R.drawable.dummy_banner_3,
            R.drawable.dummy_banner_1,
            R.drawable.dummy_banner_2
        )

        for (i in imageId.indices){
            val slider = Slider(imageId[i])
            sliderList.add(slider)
        }
    }

    private fun setupDataArtist(){
        artistList = arrayListOf<HomeArtist>()

        imageId = arrayOf(
            R.drawable.creator,
            R.drawable.creator_2,
            R.drawable.creator_3,
            R.drawable.creator,
            R.drawable.creator_2,
            R.drawable.creator_3,
            R.drawable.creator,
            R.drawable.creator_2,
            R.drawable.creator_3
        )

        tvname = arrayOf(
            getString(R.string.hamzah),
            getString(R.string.yayat),
            getString(R.string.rafli),
            getString(R.string.hamzah),
            getString(R.string.yayat),
            getString(R.string.rafli),
            getString(R.string.hamzah),
            getString(R.string.yayat),
            getString(R.string.rafli)
        )

        tvuserName = arrayOf(
            getString(R.string.nearl),
            getString(R.string.kirigayayat),
            getString(R.string.pio),
            getString(R.string.nearl),
            getString(R.string.kirigayayat),
            getString(R.string.pio),
            getString(R.string.nearl),
            getString(R.string.kirigayayat),
            getString(R.string.pio)
        )
    }
}