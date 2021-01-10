package com.ramonbarros.musicapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ramonbarros.musicapp.R
import com.ramonbarros.musicapp.databinding.LayoutListMusicBinding

import com.ramonbarros.musicapp.domain.MusicData

class AdapterMusic(private val list: List<MusicData>):
    RecyclerView.Adapter<AdapterMusic.MusicHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHolder {
        val viewXML = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_music, parent, false)
        val dataHolder = MusicHolder(viewXML)
        return dataHolder
    }

    override fun onBindViewHolder(holder: MusicHolder, position: Int) {
        val binding = holder.binding
        val music = list[position]
        binding.music = music
        binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return list.size
    }

        inner class MusicHolder(v: View): RecyclerView.ViewHolder(v) {
            val binding: LayoutListMusicBinding = LayoutListMusicBinding.bind(v)
        }
}