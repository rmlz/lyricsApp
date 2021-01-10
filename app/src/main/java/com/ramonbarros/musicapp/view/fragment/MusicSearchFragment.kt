package com.ramonbarros.musicapp.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.ramonbarros.musicapp.databinding.FragmentMusicSearchBinding
import com.ramonbarros.musicapp.domain.MusicData
import com.ramonbarros.musicapp.domain.SearchData
import com.ramonbarros.musicapp.view.adapter.AdapterMusic

import com.ramonbarros.musicapp.viewmodel.MusicSearchViewModel



class MusicSearchFragment : Fragment() {

    lateinit var binding: FragmentMusicSearchBinding
    lateinit var  navController: NavController
    private val viewModel: MusicSearchViewModel by viewModels()
    var musicList: ArrayList<String> = ArrayList(3)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        binding = FragmentMusicSearchBinding.inflate(inflater, container, false)
        binding.musicSearchFragment = this
        binding.lifecycleOwner = this

        viewModel.screenResult.observe(viewLifecycleOwner) { list ->
            showApiResult(list)
        }
        binding.rvMusics.layoutManager = LinearLayoutManager(context)


        return binding.root
    }

    fun search(){
        var searchQuery = binding.etMusicSearch.text.toString()
        var data = SearchData(searchQuery)

        viewModel.search(data)
    }

    fun showApiResult(list: List<MusicData>){
        list.map { item ->
            Log.w("SAY WHAT", item.toString())
        }
        val adapter = AdapterMusic(list)
        binding.rvMusics.adapter = adapter
    }

}