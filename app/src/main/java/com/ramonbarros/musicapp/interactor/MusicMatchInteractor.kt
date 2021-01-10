package com.ramonbarros.musicapp.interactor

import com.ramonbarros.musicapp.domain.MusicData
import com.ramonbarros.musicapp.domain.SearchData
import com.ramonbarros.musicapp.repository.MusicMatchRepository

class MusicMatchInteractor {

    private val repo = MusicMatchRepository()

    suspend fun callAPI(data: SearchData): List<MusicData> {
        return repo.callAPI(data)

    }
}