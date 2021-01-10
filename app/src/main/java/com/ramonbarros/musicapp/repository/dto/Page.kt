package com.ramonbarros.musicapp.repository.dto

import com.ramonbarros.musicapp.domain.MusicData

data class Header(
    val status_code: Int,
    val execute_time: Double,
    val available: Int,
)

data class Page (
    val message: Message,
        )

data class Message(
    val header: Header,
    val body: Body
)

data class Body(
    val track_list: List<TrackDTO>
)

