package com.ramonbarros.musicapp.repository.dto

import java.math.BigInteger

data class MusicDTO (
    val track_id: BigInteger,
    val track_name: String,
    val track_rating: Int,
    val commontrack_id: Int,
    val has_lyrics: Int,
    val album_id: BigInteger,
    val album_name: String,
    val artist_id: BigInteger,
    val artist_name: String,
    val track_share_url: String,
        )

data class TrackDTO (
    val track: MusicDTO)
