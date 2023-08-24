package com.example.neuerprojektvontag3521823.data.remote

import com.example.neuerprojektvontag3521823.data.model.MusicResponeData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "" //TODO zu ende machen

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit= Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface MusicApiService{
    @GET("music")   //TODO rechtige url besorgen!

    suspend fun getMusic(): MusicResponeData


    object MusicApi{
        val retrofitService: MusicApiService by lazy { retrofit.create(MusicApiService::class.java)}
    }
}