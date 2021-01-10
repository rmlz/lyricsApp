package com.ramonbarros.musicapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ramonbarros.musicapp.domain.MusicData
import com.ramonbarros.musicapp.domain.SearchData
import com.ramonbarros.musicapp.interactor.MusicMatchInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MusicSearchViewModel (app: Application): AndroidViewModel(app), CoroutineScope {

    override val coroutineContext = Dispatchers.Main
    val screenResult = MutableLiveData<List<MusicData>>()
    val interactor = MusicMatchInteractor()

    fun search(data: SearchData) {
        launch {
            val musicList = interactor.callAPI(data)
            screenResult.value = musicList
        }
    }
}