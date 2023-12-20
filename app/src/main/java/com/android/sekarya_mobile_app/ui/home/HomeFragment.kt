package com.android.sekarya_mobile_app.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.sekarya_mobile_app.BuildConfig
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.ui.adapter.HomeAdapter
import com.android.sekarya_mobile_app.ui.adapter.HomeArtistAdapter
import com.android.sekarya_mobile_app.ui.adapter.SliderAdapter
import com.android.sekarya_mobile_app.ui.adapter.TagAdapter
import com.android.sekarya_mobile_app.data.configuration.ApiConfig
import com.android.sekarya_mobile_app.databinding.FragmentHomeBinding
import com.android.sekarya_mobile_app.model.HomeArtist
import com.android.sekarya_mobile_app.model.HomeCard
import com.android.sekarya_mobile_app.model.Slider
import com.android.sekarya_mobile_app.model.TagList
import com.android.sekarya_mobile_app.model.response.AllArtResponse
import com.android.sekarya_mobile_app.model.response.AllArtistResponse
import com.android.sekarya_mobile_app.ui.ViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SliderAdapter
    private lateinit var artistAdapter: HomeArtistAdapter
    private lateinit var tagAdapter: TagAdapter
    private lateinit var homeAdapter: HomeAdapter

    private lateinit var recyclerView: RecyclerView

    private lateinit var sliderList: ArrayList<Slider>
    private lateinit var artistList: ArrayList<HomeArtist>
    private lateinit var tagList: ArrayList<TagList>
    private lateinit var cardList: ArrayList<AllArtResponse>

    lateinit var imageId: Array<Int>
    lateinit var imageBookmark: Array<Int>
    lateinit var imageUser: Array<Int>
    lateinit var tvname: Array<String>
    lateinit var tvuserName: Array<String>
    lateinit var tag: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDataSlider()
        setRvSlider()

        setupDataArtist()
        setRvArtist()

        setupDataTag()
        setupRvTag()

        getAllArt()
    }

    private fun getAllArt(){
        val apiService = ApiConfig.getApiService()
        val retroData = apiService.getAllArt(BuildConfig.API_KEY)

        retroData.enqueue(object : Callback<List<AllArtResponse>>{
            override fun onResponse(
                call: Call<List<AllArtResponse>>,
                response: Response<List<AllArtResponse>>
            ) {
                val data = response.body()!!
                homeAdapter = HomeAdapter(requireContext(), data)
                recyclerView.adapter = homeAdapter
                recyclerView.layoutManager = GridLayoutManager(context, 2)
                Log.d("Data",data.toString())
            }

            override fun onFailure(call: Call<List<AllArtResponse>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
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

    private fun setupRvTag(){
        recyclerView = requireView().findViewById(R.id.rv_tag)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        tagAdapter = TagAdapter(tagList)
        recyclerView.adapter = tagAdapter
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
            getString(R.string.dryy),
            getString(R.string.yayat),
            getString(R.string.jack),
            getString(R.string.dryy),
            getString(R.string.yayat),
            getString(R.string.jack),
            getString(R.string.dryy),
            getString(R.string.yayat),
            getString(R.string.jack)
        )

        tvuserName = arrayOf(
            getString(R.string.dreamart),
            getString(R.string.kirigayayat),
            getString(R.string.jack504),
            getString(R.string.dreamart),
            getString(R.string.kirigayayat),
            getString(R.string.jack504),
            getString(R.string.dreamart),
            getString(R.string.kirigayayat),
            getString(R.string.jack504)
        )

        for (i in imageId.indices){
            val artist = HomeArtist(imageId[i], tvname[i], tvuserName[i])
            artistList.add(artist)
        }
    }

    private fun setupDataTag(){
        tagList = arrayListOf<TagList>()

        tag = arrayOf(
            getString(R.string.all),
            getString(R.string.news),
            getString(R.string.mascot),
            getString(R.string.ui),
            getString(R.string.all),
            getString(R.string.news),
            getString(R.string.mascot),
            getString(R.string.ui),
            getString(R.string.all),
            getString(R.string.news)
        )

        for (i in tag.indices){
            val tag = TagList(tag[i])
            tagList.add(tag)
        }
    }
}