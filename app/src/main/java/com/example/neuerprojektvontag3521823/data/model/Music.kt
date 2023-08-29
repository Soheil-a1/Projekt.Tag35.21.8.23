package com.example.neuerprojektvontag3521823.data.model

import com.squareup.moshi.Json

data class Music (
    @Json(name = "artistName") var artistsName: String,
    @Json(name = "trackName") var trackName: String = "",
    @Json(name = "previewUrl" ) var previewUrl: String,
    @Json(name = "artworkUrl100") var artworkUrl100: String,
    @Json(name = "trackTime") var trackTime: Int = 0,
    @Json(name = "kind") var kind: String,

){
    val trackTimeSecond : Double = (trackTime / 1000).toDouble()
    var liked: Boolean = false
    val forward10S: Int = 0
    val replay10S: Int= 0
    val musicPreview = 30
    val musicTimeStart: Int = 0

    val musicTimeEnd: Int = 0




}
