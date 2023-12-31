package com.example.neuerprojektvontag3521823.data.remote


import com.example.neuerprojektvontag3521823.data.model.SearchResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://itunes.apple.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface MusicApiService {
    @GET("search")

    suspend fun getMusic(
        @Query("term") music: String,
        @Query("media") media: String = "music"
    ): SearchResult

    @GET("search")
    suspend fun getMusicHome(
        @Query("term") music: String,
        @Query("media") entity: String = "music",
        @Query("genreId") genreId: String
    ): SearchResult
}

object MusicApi {
    val retrofitService: MusicApiService by lazy { retrofit.create(MusicApiService::class.java) }
}




