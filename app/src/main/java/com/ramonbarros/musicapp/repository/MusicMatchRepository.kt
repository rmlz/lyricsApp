package com.ramonbarros.musicapp.repository

import android.util.Log
import com.google.gson.GsonBuilder
import com.ramonbarros.musicapp.domain.MusicData
import com.ramonbarros.musicapp.domain.SearchData
import com.ramonbarros.musicapp.repository.dto.Page
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.logging.Level


interface MusicMatch {

    @GET("track.search")
    @Headers("Content-Type: application/json")
    suspend fun getMusics(
        @Query("q_track") searchQuery: String,
        @Query("page_size") pageSize: String,
        @Query("page") page: String,
        @Query("s_track_rating") ascOrDesc: String,
        @Query("apikey") apiKey: String
    ): Page

}

class MusicMatchRepository {

    private val conector: Retrofit

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(logging) // <-- this is the important line!

        val gsonConfig = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
        conector = Retrofit.Builder()
            .baseUrl("http://api.musixmatch.com/ws/1.1/")
            .addConverterFactory(GsonConverterFactory.create(gsonConfig))
            .client(httpClient.build())
            .build()
    }

    suspend fun callAPI(data: SearchData): List<MusicData>{
        val service = conector.create(MusicMatch::class.java)
        val musicList = service.getMusics(
            data.searchQuery, "10", "1", "desc", "ea67de451b8be5eec63e499993207abf"
        ).message.body.track_list

        return musicList.map { dto ->
            MusicData(
                dto.track.track_name,
                dto.track.album_name,
                dto.track.artist_name,
                dto.track.track_share_url,
                dto.track.restricted,
                dto.track.has_lyrics,

            )
        }

    }
}