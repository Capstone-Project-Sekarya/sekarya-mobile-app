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
import com.android.sekarya_mobile_app.model.Hire
import com.android.sekarya_mobile_app.model.HomeArtist
import com.android.sekarya_mobile_app.ui.adapter.HireAdapter
import com.android.sekarya_mobile_app.ui.adapter.SliderAdapter

class HireFragment : Fragment() {

    lateinit var imageHire: Array<Int>
    lateinit var tvArt: Array<String>
    lateinit var tvPost: Array<String>
    lateinit var tvArtTitle: Array<String>
    lateinit var tvDeliver: Array<String>
    lateinit var tvPortofolio: Array<String>
    lateinit var tvPrice: Array<String>

    private lateinit var recyclerView: RecyclerView
    private lateinit var hireAdapter: HireAdapter
    private lateinit var hireList: ArrayList<Hire>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hire, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataHire()
        setRvHire()
    }

    private fun setRvHire(){
        recyclerView = requireView().findViewById(R.id.rv_hire)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        hireAdapter = HireAdapter(hireList)
        recyclerView.adapter = hireAdapter
    }

    private fun setDataHire(){
        hireList = arrayListOf<Hire>()

        imageHire = arrayOf(
            R.drawable.wanderer,
            R.drawable.ryo,
            R.drawable.frieren,
            R.drawable.cid,
            R.drawable.wanderer,
            R.drawable.ryo,
            R.drawable.frieren,
            R.drawable.cid,
            R.drawable.wanderer,
            R.drawable.ryo
        )

        tvArt = arrayOf(
            getString(R.string.captain_art),
            getString(R.string.captain_art),
            getString(R.string.captain_art),
            getString(R.string.captain_art),
            getString(R.string.captain_art),
            getString(R.string.captain_art),
            getString(R.string.captain_art),
            getString(R.string.captain_art),
            getString(R.string.captain_art),
            getString(R.string.captain_art)
        )

        tvPost = arrayOf(
            getString(R.string.posted_2hr_ago),
            getString(R.string.posted_3hr_ago),
            getString(R.string.posted_12hr_ago),
            getString(R.string.posted_10hr_ago),
            getString(R.string.posted_2hr_ago),
            getString(R.string.posted_3hr_ago),
            getString(R.string.posted_12hr_ago),
            getString(R.string.posted_10hr_ago),
            getString(R.string.posted_2hr_ago),
            getString(R.string.posted_3hr_ago)
        )

        tvArtTitle = arrayOf(
            getString(R.string.anime_fanart_ncostume_art),
            getString(R.string.anime_fanart_ncostume_art),
            getString(R.string.anime_fanart_ncostume_art),
            getString(R.string.anime_fanart_ncostume_art),
            getString(R.string.anime_fanart_ncostume_art),
            getString(R.string.anime_fanart_ncostume_art),
            getString(R.string.anime_fanart_ncostume_art),
            getString(R.string.anime_fanart_ncostume_art),
            getString(R.string.anime_fanart_ncostume_art),
            getString(R.string.anime_fanart_ncostume_art)
        )

        tvDeliver = arrayOf(
            getString(R.string.deliver_in_7_days),
            getString(R.string.deliver_in_2_days),
            getString(R.string.deliver_in_5_days),
            getString(R.string.deliver_in_8_days),
            getString(R.string.deliver_in_7_days),
            getString(R.string.deliver_in_2_days),
            getString(R.string.deliver_in_5_days),
            getString(R.string.deliver_in_8_days),
            getString(R.string.deliver_in_7_days),
            getString(R.string.deliver_in_2_days)
        )

        tvPortofolio = arrayOf(
            getString(R.string._100_portofolio),
            getString(R.string._50_portofolio),
            getString(R.string._200_portofolio),
            getString(R.string._150_portofolio),
            getString(R.string._100_portofolio),
            getString(R.string._50_portofolio),
            getString(R.string._200_portofolio),
            getString(R.string._150_portofolio),
            getString(R.string._100_portofolio),
            getString(R.string._50_portofolio),
        )

        tvPrice = arrayOf(
            getString(R.string.idr_100k_300k),
            getString(R.string.idr_100k_200k),
            getString(R.string.idr_200k_300k),
            getString(R.string.idr_100k_150k),
            getString(R.string.idr_100k_300k),
            getString(R.string.idr_100k_200k),
            getString(R.string.idr_200k_300k),
            getString(R.string.idr_100k_150k),
            getString(R.string.idr_100k_300k),
            getString(R.string.idr_100k_200k),
        )

        for (i in imageHire.indices){
            val hire = Hire(imageHire[i], tvArt[i], tvPost[i], tvArtTitle[i], tvDeliver[i], tvPortofolio[i], tvPrice[i])
            hireList.add(hire)
        }
    }
}